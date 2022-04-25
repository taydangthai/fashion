package thaitay.com.fashion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PRODUCT")
    @SequenceGenerator(name = "S_PRODUCT", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @Column(name = "PRODUCT_ID", updatable = false, insertable=false)
    private Long productId;
    @Column(name = "PRODUCT_NAME")
    private String name;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "QUANTITY")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "SUPPLIER_ID")
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Commenter> commenters;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
    @JsonManagedReference
    private List<Picture> pictures;

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Commenter> getCommenters() {
        return commenters;
    }

    public void setCommenters(List<Commenter> commenters) {
        this.commenters = commenters;
    }
}
