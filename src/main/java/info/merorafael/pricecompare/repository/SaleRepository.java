package info.merorafael.pricecompare.repository;

import info.merorafael.pricecompare.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SaleRepository extends PagingAndSortingRepository<Sale, String> {
    Page<Sale> findByProductEanAndCompanyPointNear(
            String productEan,
            GeoJsonPoint point,
            Pageable pageable
    );

    void deleteAllByCompanyId(String companyId);

    Optional<Sale> findByCompanyIdAndProductEan(String companyId, String productEan);
}
