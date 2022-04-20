package info.merorafael.pricecompare.repository;

import info.merorafael.pricecompare.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
}
