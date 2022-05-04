package info.merorafael.pricecompare.data.request;

import info.merorafael.pricecompare.data.LocationPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SearchSaleNear extends LocationPoint {
    @NotNull
    protected String ean;
}
