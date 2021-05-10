package edu.unsj.fcefn.lcc.optimizacion.api.model.mappers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopsDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.entities.StopEntity;
import org.springframework.stereotype.Component;

@Component
public class StopsMapper {
    public StopsDTO entityToDTO(StopEntity stopEntity){
        StopsDTO stopsDTO = new StopsDTO();

        stopsDTO.setId(stopEntity.getId());
        stopsDTO.setName(stopEntity.getName());
        stopsDTO.setCity(stopEntity.getCity());
        stopsDTO.setProvince(stopEntity.getProvince());
        stopsDTO.setCountry(stopEntity.getCountry());
        stopsDTO.setLatitud(stopEntity.getLatitud());
        stopsDTO.setLongitud(stopEntity.getLongitud());
        stopsDTO.setRanking(stopEntity.getRanking());

        return stopsDTO;
    }

    public StopEntity dtoToEntity(StopsDTO stopsDTO){
        StopEntity stopEntity = new StopEntity();

        stopEntity.setId(stopsDTO.getId());
        stopEntity.setName(stopsDTO.getName());
        stopEntity.setCity(stopsDTO.getCity());
        stopEntity.setProvince(stopsDTO.getProvince());
        stopEntity.setCountry(stopsDTO.getCountry());
        stopEntity.setLatitud(stopsDTO.getLatitud());
        stopEntity.setLongitud(stopsDTO.getLongitud());
        stopEntity.setRanking(stopsDTO.getRanking());


        return stopEntity;
    }
}
