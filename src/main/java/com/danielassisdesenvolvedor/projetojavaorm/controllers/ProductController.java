package com.danielassisdesenvolvedor.projetojavaorm.controllers;

import com.danielassisdesenvolvedor.projetojavaorm.dto.ProductDTO;
import com.danielassisdesenvolvedor.projetojavaorm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id){return productService.findById(id);
    }

    @GetMapping("/")
    public List<ProductDTO> findAll(Reader reader) {
        return productService.findAll();
    }
}
