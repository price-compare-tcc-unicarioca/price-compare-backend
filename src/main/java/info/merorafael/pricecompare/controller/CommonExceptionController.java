package info.merorafael.pricecompare.controller;

import info.merorafael.pricecompare.data.response.ResponseError;
import info.merorafael.pricecompare.data.response.ValidationResponseError;
import info.merorafael.pricecompare.exception.CompanyNotFoundException;
import info.merorafael.pricecompare.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ResponseError> handleCommonException(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationResponseError("Invalid data received", ex.getFieldErrors()));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    ResponseEntity<ResponseError> handleCommonException(CompanyNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError("Company not found"));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<ResponseError> handleCommonException(ProductNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError("Product not found"));
    }
}
