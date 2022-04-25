package thaitay.com.fashion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "PICTURE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PICTURE")
    @SequenceGenerator(name = "S_PICTURE", sequenceName = "PICTURE_SEQ", allocationSize = 1)
    @Column(name = "PICTURE_ID")
    private Long pictureId;

    @Column(name = "PICTURE_NAME")
    private String pictureName;

    @NotBlank
    @Column(name = "SRC")
    private String src;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID")
    @JsonBackReference
    private Product product;

    public Picture() {
    }

    public Picture(String pictureName, @NotBlank String src) {
        this.pictureName = pictureName;
        this.src = src;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
