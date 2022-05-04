package info.merorafael.pricecompare.repository;

import info.merorafael.pricecompare.entity.Company;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends PagingAndSortingRepository<Company, String> {
    Optional<Company> findByDocument(String document);
    List<Company> findByPointNear(GeoJsonPoint point, Distance distance);
}
