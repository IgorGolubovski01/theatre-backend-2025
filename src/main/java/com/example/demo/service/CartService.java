package com.example.demo.service;

import com.example.demo.dto.AddToCartRequest;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Projection;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.User;
import com.example.demo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final ProjectionRepository projectionRepository;
    private final OrdersRepository ordersRepository;
    private final TicketRepository ticketRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final TicketStatusRepository ticketStatusRepository;

    public ResponseEntity<String> addToCart(AddToCartRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID."));

        Projection projection = projectionRepository.findById(request.getProjectionId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid projection ID."));


        Orders order = new Orders();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(orderStatusRepository.getReferenceById(1));
        ordersRepository.save(order);

        for(int i = 0 ; i < request.getQuantity() ; i++) {
            Ticket ticket = new Ticket();
            ticket.setOrder(order);
            ticket.setProjection(projection);
            ticket.setTicketStatus(ticketStatusRepository.getReferenceById(1));
            ticketRepository.save(ticket);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
