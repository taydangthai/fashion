package thaitay.com.fashion.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thaitay.com.fashion.entity.ProductDetail;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    ProductDetail findByProductProductIdAndOrderOrderId(Long productId, Long orderId);

    List<ProductDetail> findByOrderOrderId(Long orderId);
    ProductDetail findByProductAndOrder(Long productId, Long orderId);
    List<ProductDetail> findByOrder(Long orderId);
    Optional<ProductDetail> findByProductProductId(Long productId);
}
