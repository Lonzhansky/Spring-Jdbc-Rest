package org.example.app.dto.user;

import org.example.app.entity.user.User;
import org.springframework.http.HttpStatus;

public record UserDtoGetByIdResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        User user) {

    public static final String SUCCESS_MESSAGE = "User with id %s has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "User with id %s has not been found!";

    public static UserDtoGetByIdResponse of(Long id, boolean isUserFound, User user) {
        if (isUserFound)
            return new UserDtoGetByIdResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), user);
        else
            return new UserDtoGetByIdResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}
