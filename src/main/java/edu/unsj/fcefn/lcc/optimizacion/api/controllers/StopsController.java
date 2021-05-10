package edu.unsj.fcefn.lcc.optimizacion.api.controllers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopsDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.StopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stops")
public class StopsController {
    @Autowired
    StopsService stopsService;

    @GetMapping(value = "")
    public List<StopsDTO> findAll(){
        return stopsService.findAll();
    }

    @GetMapping(value = "{id}")
    public StopsDTO find(@PathVariable("id") Integer id){
        return stopsService.find(id);
    }

    @PostMapping(value = "")
    public StopsDTO add(@RequestBody StopsDTO stopsDTO){
        return stopsService.add(stopsDTO);
    }

    @PutMapping(value = "")
    public StopsDTO edit(@RequestBody StopsDTO stopsDTO){
        return stopsService.edit(stopsDTO);
    }

    @DeleteMapping(value = "{id}")
    public StopsDTO delete(@PathVariable("id") Integer id) throws Exception {
        return stopsService.delete(id);
    }
}
