package info.merorafael.pricecompare.repository;

import info.merorafael.pricecompare.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
    Optional<User> findByEmail(String email);
}
