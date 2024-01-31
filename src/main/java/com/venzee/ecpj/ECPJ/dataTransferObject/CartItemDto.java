package com.venzee.ecpj.ECPJ.dataTransferObject;

import com.venzee.ecpj.ECPJ.model.Product;
import lombok.Data;

@Data
public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;

}
