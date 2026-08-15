package com.danielassisdesenvolvedor.projetojavaorm.repositories;

import com.danielassisdesenvolvedor.projetojavaorm.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
