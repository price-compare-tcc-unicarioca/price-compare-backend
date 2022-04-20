package info.merorafael.pricecompare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {
    @Id
    protected String id;

    protected String name;

    protected String ean;

    protected User reporter;

    protected AudiMetadata audit;
}
