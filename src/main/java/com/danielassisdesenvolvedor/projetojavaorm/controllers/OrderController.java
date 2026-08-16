package com.danielassisdesenvolvedor.projetojavaorm.controllers;

import com.danielassisdesenvolvedor.projetojavaorm.dto.OrderDTO;
import com.danielassisdesenvolvedor.projetojavaorm.dto.OrderItemMinDTO;
import com.danielassisdesenvolvedor.projetojavaorm.dto.ProductDTO;
import com.danielassisdesenvolvedor.projetojavaorm.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO order) {
        return ResponseEntity.ok(orderService.insert(order));
    }
}
