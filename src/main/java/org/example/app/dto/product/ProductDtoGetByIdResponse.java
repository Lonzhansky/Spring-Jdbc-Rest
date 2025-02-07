package org.example.app.dto.product;

import org.example.app.entity.product.Product;
import org.springframework.http.HttpStatus;

public record ProductDtoGetByIdResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Product product) {

    public static final String SUCCESS_MESSAGE = "Product with id %s has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Product with id %s has not been found!";

    public static ProductDtoGetByIdResponse of(Long id, boolean isProductFound, Product product) {
        if (isProductFound)
            return new ProductDtoGetByIdResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE.formatted(id), product);
        else
            return new ProductDtoGetByIdResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE.formatted(id), null);
    }
}
