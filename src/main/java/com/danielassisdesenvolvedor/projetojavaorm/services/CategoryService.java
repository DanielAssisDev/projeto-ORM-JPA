package com.danielassisdesenvolvedor.projetojavaorm.services;

import com.danielassisdesenvolvedor.projetojavaorm.dto.CategoryDTO;
import com.danielassisdesenvolvedor.projetojavaorm.dto.ProductMinDTO;
import com.danielassisdesenvolvedor.projetojavaorm.entities.Category;
import com.danielassisdesenvolvedor.projetojavaorm.repositories.CategoryRepository;
import com.danielassisdesenvolvedor.projetojavaorm.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        try {
            return categoryRepository.findAll().stream().map(CategoryDTO::new).toList();
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException("Recursos não encontrados");
        }
    }
}
