package org.example.app.dto.product;

import org.example.app.entity.product.Product;
import org.springframework.http.HttpStatus;

public record ProductDtoGetLastEntityResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Product product) {

    public static final String SUCCESS_MESSAGE = "Product has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Product has not been found!";

    public static ProductDtoGetLastEntityResponse of(boolean isProductFound, Product product) {
        if (isProductFound)
            return new ProductDtoGetLastEntityResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE, product);
        else
            return new ProductDtoGetLastEntityResponse(
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    false, FAILURE_MESSAGE, null);
    }
}
