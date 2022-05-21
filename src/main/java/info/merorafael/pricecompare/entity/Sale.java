package info.merorafael.pricecompare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class Sale {
    @DBRef
    private Company company;

    @DBRef
    private Product product;

    private BigDecimal price;

    private AudiMetadata audit = new AudiMetadata();
}
