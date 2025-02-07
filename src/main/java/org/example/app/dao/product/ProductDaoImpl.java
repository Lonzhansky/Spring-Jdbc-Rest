package org.example.app.dao.product;

import org.example.app.dto.product.ProductDtoRequest;
import org.example.app.entity.product.Product;
import org.example.app.entity.product.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
// Class JdbcTemplate спрощує використання JDBC і допомагає уникнути типових помилок.
// Він виконує основний робочий процес JDBC, залишаючи код програми для забезпечення SQL
// та отримання результатів.
// Цей клас виконує SQL-запити або оновлення, ініціюючи ітерацію над ResultSets
// та перехоплюючи винятки JDBC і переводячи їх до загальної ієрархії
// винятків org.springframework.dao.
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

// Маніпуляція з даними за допомогою Class JdbcTemplate.
// Positional parameters are used in SQL-queries
@Repository("ProductDaoImpl")
public class ProductDaoImpl implements ProductDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean create(ProductDtoRequest request) {
        String sql = "INSERT INTO products (name, measure, quota, price) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, request.productName(),
                request.measure(), request.quota(),
                request.price()) > 0;
    }

    @Override
    public Optional<List<Product>> fetchAll() {
        String sql = "SELECT * FROM products";
        Optional<List<Product>> optional;
        try {
            optional = Optional.of(jdbcTemplate
                    .query(sql, new ProductMapper()));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public Optional<Product> fetchById(Long id) {
        String sql = "SELECT * FROM products " +
                "WHERE id = ? LIMIT 1";
        Optional<Product> optional;
        try {
            optional = Optional.ofNullable(jdbcTemplate
                    .queryForObject(sql, new ProductMapper(), id));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public boolean updateById(Long id, ProductDtoRequest request) {
        String sql = "UPDATE products " +
                "SET name = ?, measure = ?, quota = ?, price = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, request.productName(),
                request.measure(), request.quota(), request.price(),
                id) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public Optional<Product> getLastEntity() {
        String sql = "SELECT * FROM products " +
                "ORDER BY id DESC LIMIT 1";
        Optional<Product> optional;
        try {
            optional = Optional.ofNullable(jdbcTemplate
                    .queryForObject(sql, new ProductMapper()));
        } catch (Exception e) {
            optional = Optional.empty();
        }
        return optional;
    }
}
