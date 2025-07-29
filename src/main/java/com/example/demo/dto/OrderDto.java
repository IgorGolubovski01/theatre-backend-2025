package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Integer orderId;
    private LocalDateTime orderDate;
    private List<TicketDto> tickets;

}
