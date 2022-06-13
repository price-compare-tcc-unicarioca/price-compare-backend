package info.merorafael.pricecompare.data.response;

import info.merorafael.pricecompare.entity.Company;
import info.merorafael.pricecompare.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportedSheet {
    protected Company company;

    protected List<Sale> sales = Collections.emptyList();
}
