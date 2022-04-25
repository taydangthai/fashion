package thaitay.com.fashion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import thaitay.com.fashion.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Iterable<Product> findByName(String name);
    List<Product> findByCategory_CategoryId(Long categoryId);
    List<Product> findAllBySupplier_SupplierId(Long supplierId);
    Iterable<Product> findProductsByNameContaining(String name);
    Page<Product> findAll(Pageable pageable);

    @Query(value = "select * from PRODUCT a " +
            "left join CATEGORY c on c.CATEGORY_ID = a.CATEGORY_ID " +
            "left join SUPPLIER s on s.SUPPLIER_ID = a.SUPPLIER_ID where a.PRODUCT_NAME = :name"
            ,countQuery = "select count(1) from PRODUCT a " +
            "left join CATEGORY c on c.CATEGORY_ID = a.CATEGORY_ID " +
            "left join SUPPLIER s on s.SUPPLIER_ID = a.SUPPLIER_ID where a.PRODUCT_NAME = :name"
            ,nativeQuery = true)
    Page<Product> getPaging(@Param("name") String name, Pageable pageable);
}
