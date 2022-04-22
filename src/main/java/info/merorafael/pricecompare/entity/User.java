package info.merorafael.pricecompare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import info.merorafael.pricecompare.data.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document
public class User implements UserDetails {
    @Serial
    private static final long serialVersionUID = -1553103627435916489L;

    @Id
    protected String id;

    protected String firstName;

    protected String lastName;

    @Indexed(unique = true)
    protected String email;

    @JsonIgnore
    protected String password;

    protected List<Role> roles = Collections.emptyList();

    @DBRef
    protected List<Company> companies = Collections.emptyList();

    protected AudiMetadata audit = new AudiMetadata();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
