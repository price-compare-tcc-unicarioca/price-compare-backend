package info.merorafael.pricecompare.data.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;

@EqualsAndHashCode
public class ResponseError {
    protected @Getter Instant timestamp;
    protected @Getter String message;

    public ResponseError(String message) {
        this.timestamp = Instant.now();
        this.message = message;
    }
}
