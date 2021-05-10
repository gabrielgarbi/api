package edu.unsj.fcefn.lcc.optimizacion.api.controllers;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FramesDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.FramesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frames")
public class FramesController {

    @Autowired
    FramesService framesService;

    @GetMapping(value = "")
    public List<FramesDTO> findAll(){   return framesService.findAll();
    }

    @GetMapping(value = "{id}")
    public FramesDTO find(@PathVariable("id") Integer id){
        return framesService.find(id);
    }

    @PostMapping(value = "")
    public FramesDTO add(@RequestBody FramesDTO framesDTO){
        return framesService.add(framesDTO);
    }

    @PutMapping(value = "")
    public FramesDTO edit(@RequestBody FramesDTO framesDTO){
        return framesService.edit(framesDTO);
    }

    @DeleteMapping(value = "{id}")
    public FramesDTO delete(@PathVariable("id") Integer id) throws Exception {
        return framesService.delete(id);
    }
}
