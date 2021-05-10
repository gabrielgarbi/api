package edu.unsj.fcefn.lcc.optimizacion.api.Algorithm;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FramesDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopsDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.FramesService;
import edu.unsj.fcefn.lcc.optimizacion.api.services.StopsService;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.Permutation;
import org.moeaframework.problem.AbstractProblem;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class RoutingProblem extends AbstractProblem {

    @Autowired
    StopsService stopsService;

    @Autowired
    FramesService framesService;

    List<StopsDTO> stops;

    public RoutingProblem() {
        super(1 , 2);//1 variable, 2 objetivos
        stops = stopsService
                .findAll()
                .stream()
                .sorted(Comparator.comparing(StopsDTO::getRanking).reversed())//Ordena lista por ranking de menor a mayor, le aplicamos.reversed(Mayor a menor)
                .collect(Collectors.toList())   //Lo pasa a lista
                .subList(0,20);
    }

    @Override
    public void evaluate(Solution solution) {
        solution.setObjective(0,totalPrice((Permutation) solution.getVariable(0)));//cálculo del precio
        solution.setObjective(1,totalTime((Permutation) solution.getVariable(1)));//cálculo del tiempo

    }

    private double totalPrice(Permutation permutation){

        double totalPrice=0;
        for(int i=0;i<permutation.size()-1;i++){
            StopsDTO departureStop = stops.get(permutation.get(i));
            StopsDTO arrivalStop = stops.get(permutation.get(i + 1));

            List<FramesDTO> frames = framesService
                    .findByIdDepartureStopAndIdArrivalStop(departureStop.getId(),arrivalStop.getId());

            if(frames.size() == 0){
                return Double.MAX_VALUE;
            }

            FramesDTO bestPriceFrame =frames
                    .stream()
                    .min(Comparator.comparing(FramesDTO::getPrice))
                    .orElse(null);

            if (Objects.isNull(bestPriceFrame)){
                return Double.MAX_VALUE;
            }

            totalPrice+= bestPriceFrame.getPrice();
        }

        return totalPrice;
    }

    private double totalTime(Permutation permutation){//Tiempo de viaje, calcular tiempo de espera

        double totalTime=0;

        for(int i=0;i<permutation.size()-1;i++){
            StopsDTO departureStop = stops.get(permutation.get(i));
            StopsDTO arrivalStop = stops.get(permutation.get(i + 1));//Tiempo de tramo-de viaje
            //StopsDTO arrivalStop = stops.get(permutation.get(i + 2));//Tiempo de espera entre un viaje y otro

            List<FramesDTO> frames = framesService
                    .findByIdDepartureStopAndIdArrivalStop(departureStop.getId(),arrivalStop.getId());


            Map<Integer, Long> mapTime = getTimeMaps(frames);

            Map.Entry<Integer,Long> frameIdTimeToArrival = mapTime//Tiempo de viaje
                    .entrySet()
                    .stream()
                    .min(Map.Entry.comparingByValue())
                    .orElse(null);

            if (Objects.isNull(frameIdTimeToArrival)){
                return Double.MAX_VALUE;
            }

            FramesDTO framesDTO = frames
                    .stream()
                    .filter(frame -> frame.getId().equals(frameIdTimeToArrival.getKey()))
                    .findFirst()
                    .orElse(null);


            if (Objects.isNull(framesDTO)){
                return Double.MAX_VALUE;
            }

            totalTime+= frameIdTimeToArrival.getValue();
        }
        return totalTime;
    }

    private Map<Integer,Long> getTimeMaps(List<FramesDTO> frames){

        Map<Integer, Long> mapTime = new HashMap<>();

        for(FramesDTO frame: frames){
            if(frame.getDeparture_datetime().isBefore(frame.getArrival_datetime())){
                Long timeToArrival= Duration.between(frame.getDeparture_datetime(),frame.getArrival_datetime()).getSeconds();
                mapTime.put(frame.getId(), timeToArrival);
            }
            else {
                Long timeToRange1= Duration.between(frame.getDeparture_datetime(), LocalTime.MIDNIGHT).getSeconds();
                Long timeToRange2= Duration.between(LocalTime.MIDNIGHT,frame.getArrival_datetime()).getSeconds();
                Long timeToArrival= timeToRange1 + timeToRange2;
                mapTime.put(frame.getId(), timeToArrival);
            }
        }
        return mapTime;
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(1,2);
        solution.setVariable(0, EncodingUtils.newPermutation(20));//índice 0 por 1 variable;
        return solution;
    }
}
