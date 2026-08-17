package com.danielassisdesenvolvedor.projetojavaorm.dto;

import com.danielassisdesenvolvedor.projetojavaorm.entities.Order;
import com.danielassisdesenvolvedor.projetojavaorm.entities.OrderItem;
import com.danielassisdesenvolvedor.projetojavaorm.entities.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        client = new ClientDTO(order.getClient());
        payment = (order.getPayment() == null) ? null : new PaymentDTO(order.getPayment());
        status = order.getStatus();
        for(OrderItem orderItem : order.getItems()){
            items.add(new OrderItemDTO(orderItem));
        }
    }

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public Double getTotal(){
        Double total = 0.0;
        for(OrderItemDTO orderItemDTO : items){
           total += orderItemDTO.getSubtotal();
        }
        return total;
    }
}
