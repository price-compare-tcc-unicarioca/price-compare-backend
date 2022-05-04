package info.merorafael.pricecompare.repository;

import info.merorafael.pricecompare.entity.Company;
import info.merorafael.pricecompare.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SaleRepository extends PagingAndSortingRepository<Sale, String> {
    Page<Sale> findByProductIdAndCompanyInOrderByPrice(
            String productId,
            List<Company> companies,
            Pageable pageable
    );

    void deleteAllByCompanyId(String companyId);
}
