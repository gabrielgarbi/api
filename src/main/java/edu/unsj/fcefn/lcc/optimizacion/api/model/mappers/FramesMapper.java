package edu.unsj.fcefn.lcc.optimizacion.api.model.mappers;



import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FramesDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.FrameEntity;

import org.springframework.stereotype.Component;

@Component
public class FramesMapper {
    public FramesDTO entityToDTO(FrameEntity frameEntity){
        FramesDTO framesDTO = new FramesDTO();

        framesDTO.setId(frameEntity.getId());
        framesDTO.setId_transport_company(frameEntity.getId_transport_company());
        framesDTO.setId_stop_departure(frameEntity.getId_stop_departure());
        framesDTO.setId_stop_arrival(frameEntity.getId_stop_arrival());
        framesDTO.setPrice(frameEntity.getPrice());
        framesDTO.setCategory(frameEntity.getCategory());
        framesDTO.setDeparture_datetime(frameEntity.getDeparture_datetime());
        framesDTO.setArrival_datetime(frameEntity.getArrival_datetime());
        return framesDTO;
    }

    public FrameEntity dtoToEntity(FramesDTO framesDTO){
        FrameEntity frameEntity = new FrameEntity();

        frameEntity.setId(framesDTO.getId());
        frameEntity.setId_transport_company(framesDTO.getId_transport_company());
        frameEntity.setId_stop_departure(framesDTO.getId_stop_departure());
        frameEntity.setId_stop_arrival(framesDTO.getId_stop_arrival());
        frameEntity.setPrice(framesDTO.getPrice());
        frameEntity.setCategory(framesDTO.getCategory());
        frameEntity.setDeparture_datetime(framesDTO.getDeparture_datetime());
        frameEntity.setArrival_datetime(framesDTO.getArrival_datetime());

        return frameEntity;
    }

}
