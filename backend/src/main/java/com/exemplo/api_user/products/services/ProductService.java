package com.exemplo.api_user.products.services;

import com.exemplo.api_user.products.dtos.ProductDTO;
import com.exemplo.api_user.products.dtos.ProductDTOMapper;
import com.exemplo.api_user.products.exceptions.ProductAlreadyExistsException;
import com.exemplo.api_user.products.exceptions.ProductDoesNotExistsException;
import com.exemplo.api_user.products.exceptions.SkuAlreadyExistsException;
import com.exemplo.api_user.products.models.Product;
import com.exemplo.api_user.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDTOMapper productDTOMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(UUID id) throws ProductDoesNotExistsException {

        return productRepository.findById(id)
                .map(productDTOMapper)
                .orElseThrow(() -> new ProductDoesNotExistsException(
                        "Product with id [%s] does not exist.".formatted(id)
                ));
    }

    public ProductDTO createProduct(ProductDTO productDTO) throws ProductAlreadyExistsException {

        Product newProduct = new Product();
        newProduct.setName(productDTO.name());
        newProduct.setDescription(productDTO.description());
        newProduct.setPrice(productDTO.price());
        productRepository.save(newProduct);

        return productDTOMapper.apply(newProduct);
    }

    public ProductDTO updateProduct(UUID id, ProductDTO productDTO) throws SkuAlreadyExistsException, ProductDoesNotExistsException {

        Product updateProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductDoesNotExistsException(
                        "Product with id [%s] does not exists.".formatted(id)
                ));

        updateProduct.setName(productDTO.name());
        updateProduct.setDescription(productDTO.description());
        updateProduct.setPrice(productDTO.price());
        productRepository.save(updateProduct);

        return productDTOMapper.apply(updateProduct);
    }

    public void deleteProduct(@PathVariable UUID id) {
        productRepository.deleteById(id);
    }
}
