package org.example.app.dao.user;

import org.example.app.dto.user.UserDtoRequest;
import org.example.app.entity.user.User;
import org.example.app.entity.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
// Class MapSqlParameterSource - реалізація SqlParameterSource,
// яка містить певний Map параметрів.
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/namedparam/MapSqlParameterSource.html
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
// Class NamedParameterJdbcTemplate - клас шаблону з базовим набором
// операцій JDBC, що дозволяє використовувати іменовані параметри
// замість традиційних '?' заповнювачів.
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/namedparam/NamedParameterJdbcTemplate.html
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
// Interface SqlParameterSource - інтерфейс, який визначає спільну
// функціональність для об’єктів, які можуть пропонувати значення
// параметрів для іменованих параметрів SQL, які служать аргументом
// для операцій NamedParameterJdbcTemplate.
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/namedparam/SqlParameterSource.html
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

// Named parameters are used in SQL-queries
@Repository ("UserDaoImpl")
public class UserDaoImpl implements UserDao {

    /*
        Spring NamedParameterJdbcTemplate — це клас шаблону
        з базовим набором операцій JDBC, що дозволяє використовувати
        іменовані параметри замість традиційного '?'
        (заповнювачі/placeholders).
        Після заміни іменованих параметрів на стиль JDBC,
        NamedParameterJdbcTemplate делегує обернутому JdbcTemplate
        свою роботу.
    */
    NamedParameterJdbcTemplate template;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public boolean create(UserDtoRequest request) {
        String sql = "INSERT INTO users (first_name, " +
                "last_name, email) " +
                "VALUES (:firstName, :lastName, :email)";
        SqlParameterSource paramSource =
                new MapSqlParameterSource()
                .addValue("firstName", request.firstName())
                .addValue("lastName", request.lastName())
                .addValue("email", request.email());
        return template.update(sql, paramSource) > 0;
    }

    @Override
    public Optional<List<User>> fetchAll() {
        String sql = "SELECT * FROM users";
        Optional<List<User>> optional;
        try {
            optional = Optional.of(template
                    .query(sql, new UserMapper()));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public Optional<User> fetchById(Long id) {
        String sql = "SELECT * FROM users " +
                "WHERE id = :id LIMIT 1";
        SqlParameterSource paramSource =
                new MapSqlParameterSource("id", id);
        Optional<User> optional;
        try {
            optional = Optional.ofNullable(template
                    .queryForObject(sql, paramSource, new UserMapper()));
        } catch (Exception e) {
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public boolean updateById(Long id, UserDtoRequest request) {
        String sql = "UPDATE users " +
                "SET first_name = :firstName, last_name = :lastName, " +
                "email = :email " +
                "WHERE id = :id";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("firstName", request.firstName())
                .addValue("lastName", request.lastName())
                .addValue("email", request.email())
                .addValue("id", id);
        return template.update(sql, paramSource) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = :id";
        SqlParameterSource paramSource =
                new MapSqlParameterSource("id", id);
        return template.update(sql, paramSource) > 0;
    }

    @Override
    public Optional<User> getLastEntity() {
        String sql = "SELECT * FROM users " +
                "ORDER BY id DESC LIMIT 1";
        SqlParameterSource paramSource =
                new MapSqlParameterSource();
        Optional<User> optional;
        try {
            optional = Optional.ofNullable(template
                    .queryForObject(sql, paramSource, new UserMapper()));
        } catch (Exception e) {
            optional = Optional.empty();
        }
        return optional;
    }

    // ---- Query Params ----------------------

    public Optional<List<User>> fetchByFirstName(String firstName) {
        String sql = "SELECT * FROM users WHERE first_name = :firstName";
        SqlParameterSource paramSource =
                new MapSqlParameterSource("firstName", firstName);
        Optional<List<User>> optional;
        try {
            optional = Optional.of(template
                    .query(sql,  paramSource, new UserMapper()));
        } catch (Exception e) {
            optional = Optional.empty();
        }
        return optional;
    }

    public Optional<List<User>> fetchByLastName(String lastName) {
        String sql = "SELECT * FROM users WHERE last_name = :lastName";
        SqlParameterSource paramSource =
                new MapSqlParameterSource("lastName", lastName);
        Optional<List<User>> optional;
        try {
            optional = Optional.of(template
                    .query(sql,  paramSource, new UserMapper()));
        } catch (Exception e) {
            optional = Optional.empty();
        }
        return optional;
    }

    public Optional<List<User>> fetchAllOrderBy(String orderBy) {
        String sql = "SELECT * FROM users ORDER BY " + orderBy;
        Optional<List<User>> optional;
        try {
            optional = Optional.of(template
                    .query(sql, new UserMapper()));
        } catch (Exception ex) {
            optional = Optional.empty();
        }
        return optional;
    }
}
