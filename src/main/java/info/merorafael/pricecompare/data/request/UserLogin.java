package info.merorafael.pricecompare.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @NotEmpty
    @NotNull
    protected String email;

    @NotEmpty
    @NotNull
    @Length(min = 6)
    protected String password;
}
