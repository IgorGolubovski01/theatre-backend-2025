package com.example.demo.service;

import com.example.demo.entity.Projection;
import com.example.demo.repository.ProjectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectionService {

    private final ProjectionRepository projectionRepository;

    public ResponseEntity<List<Projection>> getAllProjections() {
        return new ResponseEntity<>(projectionRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Projection> getProjectionById(int projectionId) {
        Optional<Projection> optionalProjection = projectionRepository.findById(projectionId);
        if(optionalProjection.isPresent()) {
            return new ResponseEntity<>(optionalProjection.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
