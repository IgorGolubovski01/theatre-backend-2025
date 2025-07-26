package com.example.demo.controller;

import com.example.demo.entity.Projection;
import com.example.demo.service.ProjectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("projection")
@AllArgsConstructor
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
