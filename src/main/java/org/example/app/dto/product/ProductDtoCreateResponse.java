package org.example.app.dto.product;

import org.example.app.entity.product.Product;
import org.springframework.http.HttpStatus;

public record ProductDtoCreateResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Product product) {

    public static final String SUCCESS_MESSAGE = "Product has been created successfully.";
    public static final String FAILURE_MESSAGE = "Product has not been created!";

    public static ProductDtoCreateResponse of(boolean isProductCreated, Product product) {
        if (isProductCreated)
            return new ProductDtoCreateResponse(
                    HttpStatus.OK.value(),
                    HttpStatus.OK.getReasonPhrase(),
                    true, SUCCESS_MESSAGE, product);
        else
            return new ProductDtoCreateResponse(
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    false, FAILURE_MESSAGE, null);
    }
}
