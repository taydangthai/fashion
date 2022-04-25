package thaitay.com.fashion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.Product;
import thaitay.com.fashion.repository.ProductRepository;
import thaitay.com.fashion.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "productId", key = "#productId")
    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    @Cacheable(value = "allProductCache")
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Caching( evict = { @CacheEvict( value = "allProductCache" , allEntries = true ) ,
            @CacheEvict ( value = "productCache" , key = "#product.productId" )
    })
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Caching ( evict = { @CacheEvict ( value = "allProductCache" , allEntries = true ) ,
            @CacheEvict ( value = "productCache" , key = "#productId" )
    })
    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Iterable<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findAllByCategory_CategoryId(Long categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }

    @Override
    public List<Product> findAllBySupplier_SupplierId(Long supplierId) {
        return productRepository.findAllBySupplier_SupplierId(supplierId);
    }

    @Override
    public Iterable<Product> findProductsByNameContaining(String name) {
        return productRepository.findProductsByNameContaining(name);
    }

    @Override
    public Map<String, Object> getAllWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Product> product = productRepository.findAll(pageable).getContent();
        int totalpage = productRepository.findAll(pageable).getTotalPages();
        Map<String, Object> response = new HashMap<>();
        response.put("product",product);
        response.put("totalPage",totalpage);
        return response;
    }


}
