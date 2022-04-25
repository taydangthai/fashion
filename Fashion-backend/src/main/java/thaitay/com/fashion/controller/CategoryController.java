package thaitay.com.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import thaitay.com.fashion.entity.Category;
import thaitay.com.fashion.service.CategoryService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/category")
//    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> getCategory(@PathVariable("categoryId") Long categoryId) {
        Optional<Category> category = categoryService.findByCategoryId(categoryId);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @Transactional
    @PostMapping(path = "/category")
//    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Category> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        categoryService.saveCategory(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/category/{categoryId}").buildAndExpand(category.getCategoryId()).toUri());
        return new ResponseEntity<Category>(headers, HttpStatus.CREATED);
    }
    @Transactional
    @PutMapping("/category/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> editCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category category) {
        Optional<Category> currentCategory = categoryService.findByCategoryId(categoryId);
        if (!currentCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentCategory.get().setCategoryName(category.getCategoryName());
        categoryService.saveCategory(currentCategory.get());
        return new ResponseEntity<>(currentCategory, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/category/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        Optional<Category> thisCategory = categoryService.findByCategoryId(categoryId);
        if (!thisCategory.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.removeCategory(categoryId);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/category")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> findCategory(@RequestParam("name") Optional<String> categoryName) {
        Iterable<Category> categories;
        if (categoryName.isPresent()) {
            categories = categoryService.findAllCategoryName(categoryName.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
