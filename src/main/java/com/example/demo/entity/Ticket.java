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
    @JoinColumn(name = "order_order_id", nullable = true)
    private Orders order;
    @ManyToOne
    private Projection projection;
    @ManyToOne
    private TicketStatus ticketStatus;



}
