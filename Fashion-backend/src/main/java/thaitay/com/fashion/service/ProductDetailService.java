package thaitay.com.fashion.service;



import thaitay.com.fashion.entity.ProductDetail;

import java.util.List;
import java.util.Optional;

public interface ProductDetailService {
    List<ProductDetail> findAllProductDetail();
    Optional<ProductDetail> findDetailById(Long id);
    ProductDetail saveProductDetail(ProductDetail productDetail);
    void removeProductDetail(Long id);
    ProductDetail findByProductIdAndOrderId(Long productId, Long orderId);
    List<ProductDetail> findByOrderId(Long orderId);
    void deleteProductDetail(ProductDetail productDetail);

}
