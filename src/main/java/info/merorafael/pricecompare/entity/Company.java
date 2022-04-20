package info.merorafael.pricecompare.entity;

import info.merorafael.pricecompare.data.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = -4075803080902016893L;

    @Id
    protected String id;

    protected String name;

    @Indexed(unique = true)
    protected String document;

    protected Address address;

    protected GeoJsonPoint point;

    protected AudiMetadata audit = new AudiMetadata();
}
