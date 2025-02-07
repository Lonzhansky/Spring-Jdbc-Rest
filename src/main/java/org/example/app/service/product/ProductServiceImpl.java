package org.example.app.service.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.product.Product;
import org.example.app.dao.product.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Product create(ProductDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        productDao.create(request);
        return productDao.getLastEntity()
                .orElse(null);
    }

    @Override
    public List<Product> fetchAll() {
        return productDao.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    public Product fetchById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return productDao.fetchById(id)
                .orElse(null);
    }

    @Override
    public Product updateById(Long id, ProductDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        if (productDao.fetchById(id).isPresent()) {
            productDao.updateById(id, request);
        }
        return productDao.fetchById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (productDao.fetchById(id).isPresent()) {
            productDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Product getLastEntity() {
        return productDao.getLastEntity()
                .orElse(null);
    }
}
