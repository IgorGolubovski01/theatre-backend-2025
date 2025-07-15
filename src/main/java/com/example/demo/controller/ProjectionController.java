package com.example.demo.controller;

import com.example.demo.entity.Projection;
import com.example.demo.service.ProjectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}
