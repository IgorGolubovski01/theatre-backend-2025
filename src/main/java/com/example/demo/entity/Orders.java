package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @ManyToOne
    private User user;
    private LocalDateTime orderDate;
    @ManyToOne
    private OrderStatus orderStatus;
    @OneToMany
    private List<Ticket> tickets = new ArrayList<>();
}
