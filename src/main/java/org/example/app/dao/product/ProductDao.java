package org.example.app.dao.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.product.Product;
import org.example.app.dao.BaseDao;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends BaseDao<Product, ProductDtoRequest> {
    boolean create(ProductDtoRequest request);
    Optional<List<Product>> fetchAll();
    Optional<Product> fetchById(Long id);
    boolean updateById(Long id, ProductDtoRequest request);
    boolean deleteById(Long id);
    Optional<Product> getLastEntity();
}
