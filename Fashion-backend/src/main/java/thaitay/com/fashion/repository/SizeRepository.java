package thaitay.com.fashion.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import thaitay.com.fashion.entity.Sizes;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Sizes, Long> {
    Optional<Sizes> findById(Long id);
}
