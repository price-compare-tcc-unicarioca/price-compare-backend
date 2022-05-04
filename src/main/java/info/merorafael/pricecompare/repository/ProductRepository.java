package info.merorafael.pricecompare.repository;

import info.merorafael.pricecompare.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {
    Optional<Product> findByEan(String ean);
}
