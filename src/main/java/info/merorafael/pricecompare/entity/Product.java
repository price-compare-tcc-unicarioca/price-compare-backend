package info.merorafael.pricecompare.entity;

import info.merorafael.pricecompare.data.enums.ProductState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class Product {
    @Id
    protected String id;

    protected String name;

    protected String ean;

    protected User reporter;

    protected ProductState state = ProductState.IN_ANALYSIS;

    protected AudiMetadata audit = new AudiMetadata();
}
