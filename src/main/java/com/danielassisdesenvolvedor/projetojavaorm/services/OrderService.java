package com.danielassisdesenvolvedor.projetojavaorm.services;

import com.danielassisdesenvolvedor.projetojavaorm.dto.OrderDTO;
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

}
