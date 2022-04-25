package thaitay.com.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import thaitay.com.fashion.entity.Product;
import thaitay.com.fashion.search.SearchProductByName;
import thaitay.com.fashion.service.ProductService;
import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<?> listProduct(){
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/admin/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @Transactional
    @PutMapping("/admin/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, @PathVariable Long id){
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product1.get().setName(product.getName());
        product1.get().setPrice(product.getPrice());
        product1.get().setDescription(product.getDescription());
        product1.get().setQuantity(product.getQuantity());
        product1.get().setCategory(product.getCategory());
        product1.get().setSupplier(product.getSupplier());
        product1.get().setPictures(product.getPictures());
        productService.save(product1.get());

        return new ResponseEntity<>(product1, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/admin/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable ("id") Long productId){
        Optional<Product> product = productService.findById(productId);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/product/search-product-by-name")
    public ResponseEntity<?> findProductByName(@RequestBody SearchProductByName searchProductByName) {
        if(searchProductByName.getNameProduct() == "" || searchProductByName.getNameProduct() == null) {
            List<Product> products = (List<Product>) productService.findAll();
            if(products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products,HttpStatus.OK);
        } else {
            List<Product> products = (List<Product>)
                    productService.findProductsByNameContaining(searchProductByName.getNameProduct());
            if(products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }
    @GetMapping("/product/list/category/{id}")
    public ResponseEntity<?> getProductByCategoryId(@PathVariable Long id){
        List<Product> product = productService.findAllByCategory_CategoryId(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/product/list/supplier/{id}")
    public ResponseEntity<?> getProductBySupplierId(@PathVariable Long id){
        List<Product> product = productService.findAllBySupplier_SupplierId(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product/page/{pageIndex}")
    public ResponseEntity<Map<String, Object>> getProductWithPaging(
            @PathVariable("pageIndex") Integer page/*,
             @RequestParam(name = "pageSize", defaultValue = "6") Integer size*/) {
        Map<String, Object> product = productService.getAllWithPagination(page, 6);

        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
//    @PostMapping("/test")
//    public List<Product> getProductWithPaging(
//            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
//            @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize) {
//        return productService.getAllWithPagination(pageNo, pageSize);
//    }

//    @PostMapping("/getAllWithPaging")
//    public List<Product> getProductWithPaging(@RequestParam(name = "name", defaultValue = "") String name,
//                                              @RequestParam(name = "pageIndex", defaultValue = "0") Integer pageIndex,
//                                              @RequestParam(name = "pageSize", defaultValue = "4") Integer pageSize) {
//        List<Product> list = new ArrayList<>();
//        try {
//            Pageable pageable = PageRequest.of(pageIndex, pageSize);
//            Page<Product> productPage;
//            if(name == null || name == ""){
//             productPage = productRepository.findAll(pageable);
//            }
//        else
//            productPage = productRepository.getPaging(name, pageable);
//            list.addAll(productPage.toList());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return list;
//    }

}
