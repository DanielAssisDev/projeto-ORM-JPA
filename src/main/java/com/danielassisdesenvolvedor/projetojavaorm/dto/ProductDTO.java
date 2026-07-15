package com.danielassisdesenvolvedor.projetojavaorm.dto;

import com.danielassisdesenvolvedor.projetojavaorm.entities.Product;
import jakarta.persistence.Column;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class ProductDTO {

    private Long id;
    @Size(message = "O nome deve ter de 3 a 80 caracteres", min = 3, max = 80)
    @NotBlank(message = "O nome não pode estar vazio")
    private String name;
    @Column(columnDefinition = "TEXT")
    @Size(min = 10, message = "A descrição precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "A descrição não pode estar vazia")
    private String description;
    @Positive(message = "O preço deve ter valor positivo")
    private Double price;
    private String imgUrl;
    
    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        id = product.getId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
