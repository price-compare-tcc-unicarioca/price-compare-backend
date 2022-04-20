package info.merorafael.pricecompare.data.response;

import lombok.*;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class ValidationResponseError extends ResponseError {
    protected @Getter
    List<InvalidFieldError> errors;

    public ValidationResponseError(String message, List<FieldError> errors) {
        super(message);
        this.errors = new ArrayList<>();
        for (FieldError error : errors) {
            this.errors.add(new InvalidFieldError(error.getField(), error.getDefaultMessage()));
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    protected static class InvalidFieldError {
        protected String field;
        protected String error;
    }
}
