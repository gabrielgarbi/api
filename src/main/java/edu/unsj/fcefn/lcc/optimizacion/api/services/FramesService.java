package edu.unsj.fcefn.lcc.optimizacion.api.services;


import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FramesDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.FrameEntity;
import edu.unsj.fcefn.lcc.optimizacion.api.model.mappers.FramesMapper;
import edu.unsj.fcefn.lcc.optimizacion.api.model.repositories.FramesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FramesService {
    @Autowired
    FramesRepository framesRepository;

    @Autowired
    FramesMapper framesMapper;


    List<FramesDTO> frames;

    @PostConstruct
    private void init(){
        this.frames=this.findAll();
    }

    public List<FramesDTO> getFrames() {
        return this.frames;
    }

    public List<FramesDTO> findByIdDepartureStopAndIdArrivalStop(Integer idDepartureStop, Integer idArrivalStop){
        return this.frames
                .stream()
                .filter(framesDTO -> framesDTO.getId_stop_departure().equals(idDepartureStop))
                .filter(framesDTO -> framesDTO.getId_stop_arrival().equals(idArrivalStop))
                .collect(Collectors.toList());
    }

    public List<FramesDTO> findAll(){
        return framesRepository
                .findAll()
                .stream()
                .map(frameEntity -> framesMapper.entityToDTO(frameEntity))
                .collect(Collectors.toList());
    }
    
  

    public FramesDTO find(Integer id){
        return framesRepository
                .findById(id)
                .map(frameEntity -> framesMapper.entityToDTO(frameEntity))
                .orElse(null);
    }

    public FramesDTO add(FramesDTO framesDTO){
        FrameEntity frameEntity = framesMapper.dtoToEntity(framesDTO);
        frameEntity = framesRepository.save(frameEntity);
        return framesMapper.entityToDTO(frameEntity);
    }

    public FramesDTO edit(FramesDTO framesDTO){
        FrameEntity frameEntity = framesMapper.dtoToEntity(framesDTO);
        frameEntity = framesRepository.save(frameEntity);
        return framesMapper.entityToDTO(frameEntity);
    }

    public FramesDTO delete(Integer id) throws Exception {
        Optional<FrameEntity>  frameEntityOptional = framesRepository.findById(id);
        if(frameEntityOptional.isPresent()){
            framesRepository.deleteById(id);
            return framesMapper.entityToDTO(frameEntityOptional.get());
        } else {
            throw new Exception("Frame not found");
        }
    }
}
