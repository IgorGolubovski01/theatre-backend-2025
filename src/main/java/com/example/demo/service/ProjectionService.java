package com.example.demo.service;

import com.example.demo.entity.Projection;
import com.example.demo.repository.ProjectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectionService {

    private final ProjectionRepository projectionRepository;

    public ResponseEntity<List<Projection>> getAllProjections() {
        return new ResponseEntity<>(projectionRepository.findAll(), HttpStatus.OK);
    }
}
