package com.danielassisdesenvolvedor.projetojavaorm.services;

import com.danielassisdesenvolvedor.projetojavaorm.dto.*;
import com.danielassisdesenvolvedor.projetojavaorm.entities.*;
import com.danielassisdesenvolvedor.projetojavaorm.repositories.OrderItemRepository;
import com.danielassisdesenvolvedor.projetojavaorm.repositories.OrderRepository;
import com.danielassisdesenvolvedor.projetojavaorm.repositories.ProductRepository;
import com.danielassisdesenvolvedor.projetojavaorm.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        return new OrderDTO(orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")));
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());
        for(OrderItemDTO itemDTO :  orderDTO.getItems()){
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem orderItem = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return new OrderDTO(order);
    }

}
