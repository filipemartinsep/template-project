package com.exemplo.api_user.products.dtos;

import com.exemplo.api_user.products.models.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getIdProduct(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
