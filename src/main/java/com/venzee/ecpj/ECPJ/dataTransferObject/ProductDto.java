package com.venzee.ecpj.ECPJ.dataTransferObject;

import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    private Long id;
    private @NotNull String name;
    private @NotNull String imageUrl;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;
}
