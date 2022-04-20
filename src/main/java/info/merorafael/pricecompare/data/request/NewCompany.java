package info.merorafael.pricecompare.data.request;

import info.merorafael.pricecompare.data.Address;
import info.merorafael.pricecompare.data.LocationPoint;
import info.merorafael.pricecompare.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCompany {
    @NotEmpty
    protected String name;

    @NotEmpty
    @CNPJ
    protected String document;

    @NotNull
    @Valid
    protected Address address;

    @NotNull
    @Valid
    protected LocationPoint point;

    public Company toCompany() {
        return new Company()
                .setName(name)
                .setDocument(document)
                .setAddress(address)
                .setPoint(point.toGeoJsonPoint());
    }
}
