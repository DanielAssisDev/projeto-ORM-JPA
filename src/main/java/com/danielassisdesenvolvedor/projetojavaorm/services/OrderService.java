package com.danielassisdesenvolvedor.projetojavaorm.services;

import com.danielassisdesenvolvedor.projetojavaorm.dto.*;
import com.danielassisdesenvolvedor.projetojavaorm.entities.*;
import com.danielassisdesenvolvedor.projetojavaorm.repositories.OrderRepository;
import com.danielassisdesenvolvedor.projetojavaorm.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        return new OrderDTO(orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")));
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Order order = new Order();
        copyDTOToEntity(orderDTO, order);
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }

    public void copyDTOToEntity (OrderDTO orderDTO, Order order){
        order.setMoment(orderDTO.getMoment());
        User user = new User();
        user.setId(orderDTO.getClient().getId());
        order.setClient(user);
        order.setStatus(orderDTO.getStatus());
        Payment payment = new Payment();
        payment.setId(orderDTO.getPayment().getId());
        order.setPayment((orderDTO.getPayment() == null) ? null : order.getPayment());
        for(OrderItemDTO orderItemDTO : orderDTO.getItems()){
            OrderItem orderItem = new OrderItem();
            Product product = new Product();
            product.setId(orderItemDTO.getProductId());
            orderItem.setProduct(product);
            orderItem.setOrder(order);
        }
    }
}
