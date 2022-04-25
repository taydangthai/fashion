package thaitay.com.fashion.service;

import thaitay.com.fashion.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAllCategory();
    Optional<Category> findByCategoryId(Long categoryId);
    Category saveCategory(Category category);
    void removeCategory(Long categoryId);
    Iterable<Category> findAllCategoryName(String categoryName);
}
