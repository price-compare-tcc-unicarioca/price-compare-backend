package info.merorafael.pricecompare.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = -5828844360720186167L;

    @NotNull
    protected String streetAddress;

    protected String complement;

    @NotNull
    protected String postalCode;

    @NotNull
    protected String city;

    @NotNull
    protected String state;
}
