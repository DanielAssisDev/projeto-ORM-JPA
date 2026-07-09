package com.danielassisdesenvolvedor.projetojavaorm.services;

import com.danielassisdesenvolvedor.projetojavaorm.dto.ProductDTO;
import com.danielassisdesenvolvedor.projetojavaorm.entities.Product;
import com.danielassisdesenvolvedor.projetojavaorm.repositories.ProductRepository;
import com.danielassisdesenvolvedor.projetojavaorm.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        try {
            return productRepository.findAll(pageable).map(ProductDTO::new);
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException("Recursos não encontrados");
        }
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return new ProductDTO(productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")));
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();
        copyDTOToEntity(productDTO, product);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productRepository.getReferenceById(id);
        copyDTOToEntity(productDTO, product);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private void copyDTOToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
    }
}
