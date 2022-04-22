package info.merorafael.pricecompare.data.request;

import info.merorafael.pricecompare.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.EAN;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProduct {
    @NotEmpty
    protected String name;

    @NotEmpty
    @EAN
    protected String ean;

    public Product toEntity() {
        return new Product()
                .setName(name)
                .setEan(ean);
    }
}
