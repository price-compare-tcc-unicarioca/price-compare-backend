package info.merorafael.pricecompare.entity;

import info.merorafael.pricecompare.data.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class Company {
    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String document;

    private Address address;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint point;

    private List<CompanyUser> users = Collections.emptyList();

    private AudiMetadata audit = new AudiMetadata();

    public boolean hasPermissionToChange(User user) {
        return users.stream()
                .anyMatch(companyUser -> companyUser.getUser().getId().equals(user.getId()));
    }
}
