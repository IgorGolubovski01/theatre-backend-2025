package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Projection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectionId;
    private String name;
    private String description;
    @ManyToOne
    private Genre genre;
    private String duration;
    private String director;
    @OneToMany
    private List<Actor> actors;
    private Date releaseDate;
    private Date projectionDate;
    private BigDecimal price;
    private Float rating;
    private Integer projectionCapacity;
    private Integer soldTickets;

}
