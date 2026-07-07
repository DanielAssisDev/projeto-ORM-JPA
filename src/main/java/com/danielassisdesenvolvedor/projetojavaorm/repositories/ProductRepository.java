package com.danielassisdesenvolvedor.projetojavaorm.repositories;

import com.danielassisdesenvolvedor.projetojavaorm.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
