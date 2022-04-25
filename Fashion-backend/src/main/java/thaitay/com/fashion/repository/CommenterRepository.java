package thaitay.com.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thaitay.com.fashion.entity.Commenter;

@Repository
public interface CommenterRepository extends JpaRepository<Commenter, Long> {

    Iterable<Commenter> findCommentersByProductProductId(Long productId);
}
