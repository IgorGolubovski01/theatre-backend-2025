package com.example.demo.dto;

import lombok.Data;

@Data
public class AddToCartRequest {
    private Integer userId;
    private Integer projectionId;
    private int quantity;
}
