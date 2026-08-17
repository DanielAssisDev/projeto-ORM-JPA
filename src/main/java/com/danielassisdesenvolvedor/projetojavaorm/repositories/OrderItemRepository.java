package com.danielassisdesenvolvedor.projetojavaorm.repositories;

import com.danielassisdesenvolvedor.projetojavaorm.entities.Order;
import com.danielassisdesenvolvedor.projetojavaorm.entities.OrderItem;
import com.danielassisdesenvolvedor.projetojavaorm.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
