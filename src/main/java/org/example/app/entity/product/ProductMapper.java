package org.example.app.entity.product;

// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/RowMapper.html
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// Mapper властивостей Java-об'єкта (entity)
// із стовчиками таблиці у БД
public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("measure"),
                rs.getDouble("quota"),
                rs.getDouble("price")
        );
    }
}
