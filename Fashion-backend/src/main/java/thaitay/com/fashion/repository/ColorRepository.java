package thaitay.com.fashion.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import thaitay.com.fashion.entity.Color;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findById(Long colorId);
}
