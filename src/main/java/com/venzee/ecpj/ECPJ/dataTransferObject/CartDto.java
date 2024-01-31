package com.venzee.ecpj.ECPJ.dataTransferObject;

import lombok.Data;

import java.util.List;
@Data
public class CartDto {
    private List<CartItemDto> cartItemDtos;
    private double totalCost;
}
