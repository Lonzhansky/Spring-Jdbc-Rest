package org.example.app.controller;

import org.example.app.dto.user.*;
import org.example.app.entity.user.User;
import org.example.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

// Вхідна точка (REST-контроллер)
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

//    @PostMapping
//    public ResponseEntity<UserDtoCreateResponse> createUser(
//            @RequestBody UserDtoRequest request) {
//        User user = userService.create(request);
//        // NO ternary operator
//        if (user != null)
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(UserDtoCreateResponse.of(true,
//                            user));
//        else
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(UserDtoCreateResponse.of(false,
//                            null));
//    }

    @PostMapping
    public ResponseEntity<UserDtoCreateResponse> createUser(
            @RequestBody UserDtoRequest request) {
        User user = userService.create(request);
        // ternary operator usage
        return (user != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoCreateResponse.of(true,
                                user)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoCreateResponse.of(false,
                                null));
    }

    @GetMapping
    public ResponseEntity<UserDtoListResponse> fetchAllUsers() {
        List<User> list = userService.fetchAll();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoListResponse.of(true,
                            Collections.emptyList()));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoListResponse.of(false,
                            list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoGetByIdResponse> fetchUserById(
            @PathVariable("id") Long id) {
        User user = userService.fetchById(id);
        if (user != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoGetByIdResponse.of(id, true,
                            user));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoGetByIdResponse.of(id, false,
                            null));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<UserDtoUpdateResponse> updateUserById(
//            @PathVariable("id") Long id,
//            @RequestBody UserDtoRequest request) {
//        User userToUpdate = userService.fetchById(id);
//        if (userToUpdate != null) {
//            User userUpdated = userService.updateById(id, request);
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(UserDtoUpdateResponse.of(id, true,
//                    userUpdated));
//        } else {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(UserDtoUpdateResponse.of(id, false,
//                    null));
//        }
//    }

    // Refactored method updateUserById()
    @PutMapping("/{id}")
    public ResponseEntity<UserDtoUpdateResponse> updateUserById(
            @PathVariable("id") Long id,
            @RequestBody UserDtoRequest request) {
        if (userService.fetchById(id) != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoUpdateResponse.of(id, true,
                            userService.updateById(id, request)));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoUpdateResponse.of(id, false,
                            null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDtoDeleteResponse> deleteUserById(
            @PathVariable("id") Long id) {
        if (userService.deleteById(id))
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoDeleteResponse.of(id, true));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoDeleteResponse.of(id, false));
    }

    @GetMapping("/last-entity")
    public ResponseEntity<UserDtoGetLastEntityResponse> getLastEntity() {
        User user = userService.getLastEntity();
        if (user != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoGetLastEntityResponse.of(true,
                            user));
        else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(UserDtoGetLastEntityResponse.of(false,
                            null));
    }

    // ---- Query Params ----------------------

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-firstname?firstName=Tom
        If firstName does not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-firstname?firstName=Tomas
    */
    @GetMapping("/query-by-firstname")
    public ResponseEntity<UserDtoListResponse> fetchByFirstName(@RequestParam("firstName") final String firstName) {
        List<User> list = userService.fetchByFirstName(firstName);
        // ternary operator usage
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(false,
                                list));
    }

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname?lastName=Bright
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname?lastName=Terra
        If lastName does not exist
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-by-lastname?lastName=Mars
    */
    @GetMapping("/query-by-lastname")
    public ResponseEntity<UserDtoListResponse> fetchByLastName(@RequestParam("lastName") final String lastName) {
        List<User> list = userService.fetchByLastName(lastName);
        // ternary operator usage
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(false,
                                list));
    }

    /*
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-order-by?orderBy=first_name
        http://localhost:8080/Your-Project-Name-1.0-SNAPSHOT/api/v1/users/query-order-by?orderBy=last_name
    */
    @GetMapping("/query-order-by")
    public ResponseEntity<UserDtoListResponse> fetchAllOrderBy(@RequestParam("orderBy") final String orderBy) {
        List<User> list = userService.fetchAllOrderBy(orderBy);
        // ternary operator usage
        return (list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(true,
                                Collections.emptyList())) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserDtoListResponse.of(false,
                                list));
    }

}
