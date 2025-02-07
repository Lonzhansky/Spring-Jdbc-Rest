package org.example.app.dto.user;

import org.example.app.entity.user.User;
import org.springframework.http.HttpStatus;

public record UserDtoUpdateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        User user) {

    public static final String SUCCESS_MESSAGE = "User with id %s has been updated successfully.";
    public static final String FAILURE_MESSAGE = "User with id %s has not been found!";

    public static UserDtoUpdateResponse of(Long id, boolean isUserFound, User userUpdated) {
        if (isUserFound)
            return new UserDtoUpdateResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), userUpdated);
        else
            return new UserDtoUpdateResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}
