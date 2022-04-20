package info.merorafael.pricecompare.repository;

import info.merorafael.pricecompare.entity.ProductSale;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductSaleRepository extends PagingAndSortingRepository<ProductSale, String> {
}
