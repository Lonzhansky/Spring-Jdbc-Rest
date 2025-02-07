package org.example.app.controller;

import org.example.app.dto.product.*;
import org.example.app.entity.product.Product;
import org.example.app.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

// Вхідна точка (REST-контроллер)
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDtoCreateResponse> createProduct(
            @RequestBody ProductDtoRequest request) {
        Product product = productService.create(request);
        // ternary operator usage
        return (product != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoCreateResponse.of(true,
                                product)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoCreateResponse.of(false,
                                null));
    }

    @GetMapping
    public ResponseEntity<ProductDtoListResponse> fetchAllProducts() {
        List<Product> list = productService.fetchAll();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoListResponse.of(true,
                            Collections.emptyList()));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoListResponse.of(false,
                            list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDtoGetByIdResponse> fetchProductById(
            @PathVariable("id") Long id) {
        Product product = productService.fetchById(id);
        if (product != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoGetByIdResponse.of(id, true,
                            product));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoGetByIdResponse.of(id, false,
                            null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoUpdateResponse> updateProductById(
            @PathVariable("id") Long id,
            @RequestBody ProductDtoRequest request) {
        if (productService.fetchById(id) != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoUpdateResponse.of(id, true,
                            productService.updateById(id, request)));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoUpdateResponse.of(id, false,
                            null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDtoDeleteResponse> deleteProductById(
            @PathVariable("id") Long id) {
        return (productService.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoDeleteResponse.of(id, true)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(ProductDtoDeleteResponse.of(id, false));
    }

    @GetMapping("/last-entity")
    public ResponseEntity<ProductDtoGetLastEntityResponse> getLastEntity() {
        Product product = productService.getLastEntity();
        if (product != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoGetLastEntityResponse.of(true,
                            product));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ProductDtoGetLastEntityResponse.of(false,
                            null));
    }
}
