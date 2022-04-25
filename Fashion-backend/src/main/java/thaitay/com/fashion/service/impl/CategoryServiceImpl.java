package thaitay.com.fashion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.Category;
import thaitay.com.fashion.repository.CategoryRepository;
import thaitay.com.fashion.service.CategoryService;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Iterable<Category> findAllCategoryName(String categoryName) {
        return categoryRepository.findCategoryByCategoryNameContaining(categoryName);
    }
}
