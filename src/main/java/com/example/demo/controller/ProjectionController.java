package com.example.demo.controller;

import com.example.demo.entity.Projection;
import com.example.demo.service.ProjectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("projection")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectionController {

    private final ProjectionService projectionService;

    @GetMapping("getProjections")
    public ResponseEntity<List<Projection>> getProjections(){
        return projectionService.getAllProjections();
    }

    @GetMapping("getProjectionById/{projectionId}")
    public ResponseEntity<Projection> getProjectionById(@PathVariable int projectionId){
        return projectionService.getProjectionById(projectionId);
    }
}
