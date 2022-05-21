package info.merorafael.pricecompare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class Product {
    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String ean;

    private AudiMetadata audit = new AudiMetadata();
}
