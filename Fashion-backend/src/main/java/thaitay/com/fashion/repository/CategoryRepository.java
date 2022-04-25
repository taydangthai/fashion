package thaitay.com.fashion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thaitay.com.fashion.entity.Category;


import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Iterable<Category> findAllByCategoryName(String categoryName);
    List<Category> findCategoryByCategoryNameContaining(String categoryName);
}
