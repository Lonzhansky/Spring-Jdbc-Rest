package org.example.app.dao.user;

import org.example.app.dto.user.UserDtoRequest;
import org.example.app.entity.user.User;
import org.example.app.dao.BaseDao;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User, UserDtoRequest> {
    boolean create(UserDtoRequest request);
    Optional<List<User>> fetchAll();
    Optional<User> fetchById(Long id);
    boolean updateById(Long id, UserDtoRequest request);
    boolean deleteById(Long id);
    Optional<User> getLastEntity();

    // ---- Query Params ----------------------
    Optional<List<User>> fetchByFirstName(String firstName);
    Optional<List<User>> fetchByLastName(String lastName);
    Optional<List<User>> fetchAllOrderBy(String orderBy);
}
