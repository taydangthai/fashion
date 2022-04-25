package thaitay.com.fashion.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.ProductDetail;
import thaitay.com.fashion.repository.ProductDetailRepository;
import thaitay.com.fashion.service.ProductDetailService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public List<ProductDetail> findAllProductDetail() {
        return productDetailRepository.findAll();
    }

    @Override
    public Optional<ProductDetail> findDetailById(Long id) {
        return productDetailRepository.findById(id);
    }

    @Override
    public ProductDetail saveProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public void removeProductDetail(Long id) {
        productDetailRepository.deleteById(id);
    }

    @Override
    public ProductDetail findByProductIdAndOrderId(Long productId, Long orderId) {
        return productDetailRepository.findByProductProductIdAndOrderOrderId(productId, orderId);
    }

    @Override
    public List<ProductDetail> findByOrderId(Long Order_Id) {
        return productDetailRepository.findByOrderOrderId(Order_Id);
    }

    @Override
    public void deleteProductDetail(ProductDetail productDetail) {
        productDetailRepository.delete(productDetail);
    }
}
