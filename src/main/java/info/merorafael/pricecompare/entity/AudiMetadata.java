package info.merorafael.pricecompare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudiMetadata implements Serializable {
    @Serial
    private static final long serialVersionUID = 3980836643237880733L;

    @CreatedBy
    @JsonIgnore
    protected String createdBy;

    @CreatedDate
    protected Instant createdDate;

    @LastModifiedBy
    @JsonIgnore
    protected String lastModifiedBy;

    @LastModifiedDate
    protected Instant lastModifiedDate;
}
