package info.merorafael.pricecompare.data.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_SYSADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
