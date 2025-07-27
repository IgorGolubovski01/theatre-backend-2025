package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;
    @ManyToOne
    private Orders order;
    @ManyToOne
    private Projection projection;
    @ManyToOne
    private TicketStatus status;

}
