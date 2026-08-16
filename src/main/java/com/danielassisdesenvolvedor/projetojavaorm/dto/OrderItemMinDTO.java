package com.danielassisdesenvolvedor.projetojavaorm.dto;

import com.danielassisdesenvolvedor.projetojavaorm.entities.OrderItem;

public class OrderItemMinDTO {
    private Long productId;
    private Integer quantity;

    public OrderItemMinDTO(OrderItem orderItem) {
        productId = orderItem.getProduct().getId();
        quantity = orderItem.getQuantity();
    }

    public OrderItemMinDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
