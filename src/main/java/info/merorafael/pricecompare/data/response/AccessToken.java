package info.merorafael.pricecompare.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken {
    protected String email;

    protected String token;

    protected Date expirationDate;
}
