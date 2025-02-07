package org.example.app.service.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.product.Product;
import org.example.app.service.BaseService;

import java.util.List;

public interface ProductService extends BaseService<Product, ProductDtoRequest> {
    Product create(ProductDtoRequest request);
    List<Product> fetchAll();
    Product fetchById(Long id);
    Product updateById(Long id, ProductDtoRequest request);
    boolean deleteById(Long id);
    Product getLastEntity();
}
