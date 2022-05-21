package info.merorafael.pricecompare.entity;

import info.merorafael.pricecompare.data.enums.CompanyRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUser {
    @DBRef
    private User user;

    private List<CompanyRole> roles;
}
