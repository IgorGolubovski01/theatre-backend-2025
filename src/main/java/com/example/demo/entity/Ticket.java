package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;
    @ManyToOne
    private Projection projection;
    @ManyToOne
    private User user;
    private Date bookingDate;
    @OneToOne
    private Status status;
    @ManyToOne
    private Cart cart;

}
