package info.merorafael.pricecompare.data.request;

import info.merorafael.pricecompare.data.LocationPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RequestSaleNear extends LocationPoint {
    protected String ean;
}
