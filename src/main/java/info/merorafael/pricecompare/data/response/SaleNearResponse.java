package info.merorafael.pricecompare.data.response;

import info.merorafael.pricecompare.entity.Product;
import info.merorafael.pricecompare.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleNearResponse {
    protected Product product;

    protected List<Sale> sales;
}
