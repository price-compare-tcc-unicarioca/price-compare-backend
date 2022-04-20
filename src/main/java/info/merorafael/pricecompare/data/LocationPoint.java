package info.merorafael.pricecompare.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationPoint {
    @NotNull
    protected Double longitude;

    @NotNull
    protected Double latitude;

    public GeoJsonPoint toGeoJsonPoint() {
        return new GeoJsonPoint(longitude, latitude);
    }
}
