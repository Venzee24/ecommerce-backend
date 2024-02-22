package com.venzee.ecpj.ECPJ.dataTransferObject;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddToCartDto {
    private Integer id;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;
}
