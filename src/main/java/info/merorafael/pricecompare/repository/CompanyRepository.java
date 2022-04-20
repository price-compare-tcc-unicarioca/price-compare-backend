package info.merorafael.pricecompare.repository;

import info.merorafael.pricecompare.entity.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CompanyRepository extends PagingAndSortingRepository<Company, String> {
    Optional<Company> findByDocument(String document);
}
