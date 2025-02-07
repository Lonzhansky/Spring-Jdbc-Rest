package org.example.app.service.user;

import org.example.app.dto.user.UserDtoRequest;
import org.example.app.entity.user.User;
import org.example.app.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User create(UserDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        userDao.create(request);
        return userDao.getLastEntity()
                .orElse(null);
    }

    @Override
    public List<User> fetchAll() {
        return userDao.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    public User fetchById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return userDao.fetchById(id)
                .orElse(null);
    }

    @Override
    public User updateById(Long id, UserDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        if (userDao.fetchById(id).isPresent()) {
            userDao.updateById(id, request);
        }
        return userDao.fetchById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (userDao.fetchById(id).isPresent()) {
            userDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User getLastEntity() {
        return userDao.getLastEntity()
                .orElse(null);
    }

    // ---- Query Params ----------------------

    public List<User> fetchByFirstName(String firstName) {
        return userDao.fetchByFirstName(firstName)
                .orElse(Collections.emptyList());
    }

    public List<User> fetchByLastName(String lastName) {
        return userDao.fetchByLastName(lastName)
                .orElse(Collections.emptyList());
    }

    public List<User> fetchAllOrderBy(String orderBy) {
        return userDao.fetchAllOrderBy(orderBy)
                .orElse(Collections.emptyList());
    }

}
