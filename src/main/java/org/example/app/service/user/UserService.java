package org.example.app.service.user;

import org.example.app.dto.user.UserDtoRequest;
import org.example.app.entity.user.User;
import org.example.app.service.BaseService;

import java.util.List;

public interface UserService extends BaseService<User, UserDtoRequest> {
    User create(UserDtoRequest request);
    List<User> fetchAll();
    User fetchById(Long id);
    User updateById(Long id, UserDtoRequest request);
    boolean deleteById(Long id);
    User getLastEntity();

    // ---- Query Params ----------------------
    List<User> fetchByFirstName(String firstName);
    List<User> fetchByLastName(String lastName);
    List<User> fetchAllOrderBy(String orderBy);
}
