package org.example.app.dto.user;

import org.example.app.entity.user.User;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public record UserDtoListResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        List<User> userList) {

    public static final String SUCCESS_MESSAGE = "User list has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "User list has not been found!";

    public static UserDtoListResponse of(boolean isUserListEmpty, List<User> userList) {
        if (isUserListEmpty)
            return new UserDtoListResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE, Collections.emptyList());
        else
            return new UserDtoListResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE, userList);
    }
}
