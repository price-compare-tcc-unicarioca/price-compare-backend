package info.merorafael.pricecompare.data.request;

import info.merorafael.pricecompare.data.LocationPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCompany {
    @NotNull
    @Valid
    protected LocationPoint point;
}
