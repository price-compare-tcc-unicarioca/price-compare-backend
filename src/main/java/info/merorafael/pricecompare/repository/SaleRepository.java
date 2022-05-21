package info.merorafael.pricecompare.repository;

import info.merorafael.pricecompare.entity.Company;
import info.merorafael.pricecompare.entity.Sale;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SaleRepository extends PagingAndSortingRepository<Sale, String> {
    List<Sale> findByProductIdAndCompanyInOrderByPrice(
            String productId,
            List<Company> companies
    );

    void deleteAllByCompanyId(String companyId);
}
