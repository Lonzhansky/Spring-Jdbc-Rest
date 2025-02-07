package org.example.app.dto.user;

import org.example.app.entity.user.User;
import org.springframework.http.HttpStatus;

public record UserDtoGetLastEntityResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        User user) {

    public static final String SUCCESS_MESSAGE = "User has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "User has not been found!";

    public static UserDtoGetLastEntityResponse of(boolean isUserFound, User user) {
        if (isUserFound)
            return new UserDtoGetLastEntityResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE, user);
        else
            return new UserDtoGetLastEntityResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE, null);
    }
}
