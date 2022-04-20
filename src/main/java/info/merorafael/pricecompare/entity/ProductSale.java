package info.merorafael.pricecompare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ProductSale {
    @DBRef
    protected Company company;

    @DBRef
    protected Product product;

    protected BigDecimal price;

    protected AudiMetadata audit;
}
