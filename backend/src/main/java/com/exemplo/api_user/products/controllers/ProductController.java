package com.exemplo.api_user.products.controllers;

import com.exemplo.api_user.products.dtos.ProductDTO;
import com.exemplo.api_user.products.exceptions.ProductAlreadyExistsException;
import com.exemplo.api_user.products.exceptions.ProductDoesNotExistsException;
import com.exemplo.api_user.products.exceptions.SkuAlreadyExistsException;
import com.exemplo.api_user.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOList = productService.getAllProducts();

        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable UUID id) throws ProductDoesNotExistsException {
        ProductDTO productDTO = productService.getProductById(id);

        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) throws ProductAlreadyExistsException {
        ProductDTO createdProduct = productService.createProduct(productDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @RequestBody ProductDTO productDTO) throws ProductDoesNotExistsException, SkuAlreadyExistsException {
        ProductDTO updateProduct = productService.updateProduct(id, productDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
