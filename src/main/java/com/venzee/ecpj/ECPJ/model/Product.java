package com.venzee.ecpj.ECPJ.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotNull String name;
    @Column(name = "image_url")
    private @NotNull String imageUrl;
    private @NotNull double price;
    private @NotNull String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
