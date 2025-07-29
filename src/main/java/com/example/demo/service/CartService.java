package com.example.demo.service;

import com.example.demo.dto.AddToCartRequest;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.TicketDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        int ticketQuantity = request.getQuantity();
        int availableTickets = projection.getProjectionCapacity()-projection.getSoldTickets();

        if(ticketQuantity > availableTickets) {
            return new ResponseEntity<>("Not enough available tickets.", HttpStatus.BAD_REQUEST);
        }


        List<Ticket> tickets = new ArrayList<>();
        for(int i = 0 ; i <= ticketQuantity ; i++) {
            Ticket ticket = new Ticket();
            ticket.setOrder(order);
            ticket.setProjection(projection);
            ticket.setTicketStatus(ticketStatusRepository.getReferenceById(1));
            tickets.add(ticket);
            ticketRepository.save(ticket);
        }
        order.setTickets(tickets);
        ordersRepository.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    public List<OrderDto> getCartTickets(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        List<Orders> orders = ordersRepository.findByUser(user);

        List<OrderDto> orderDtos = orders.stream().map(order -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(order.getOrderId());
            orderDto.setOrderDate(order.getOrderDate());

            List<TicketDto> ticketDtos = order.getTickets().stream().map(ticket -> {
                TicketDto ticketDto = new TicketDto();
                ticketDto.setTicketId(ticket.getTicketId());
                ticketDto.setProjectionId(ticket.getProjection().getProjectionId());
                return ticketDto;
            }).collect(Collectors.toList());
            orderDto.setTickets(ticketDtos);
            return orderDto;

        }).collect(Collectors.toList());


        return orderDtos;
    }

}
