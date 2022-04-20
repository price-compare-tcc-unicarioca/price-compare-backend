package info.merorafael.pricecompare.data.request;

import info.merorafael.pricecompare.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignup {
    @NotNull
    @NotEmpty
    protected String firstName;

    @NotNull
    @NotEmpty
    protected String lastName;

    @NotNull
    @NotEmpty
    @Email
    protected String email;

    @NotNull
    @NotEmpty
    @Length(min = 6)
    protected String password;

    public User toUser() {
        return new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(new BCryptPasswordEncoder().encode(password));
    }
}
