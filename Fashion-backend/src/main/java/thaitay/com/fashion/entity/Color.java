package thaitay.com.fashion.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "COLOR")
@Proxy(lazy = false)
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_COLOR")
    @SequenceGenerator(name = "S_COLOR", sequenceName = "COLOR_SEQ", allocationSize = 1)
    @Column(name = "COLOR_ID")
    private Long colorId;

    @Column(name = "COLOR_NAME")
    private String colorName;

    @OneToMany(mappedBy = "color", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonIgnore
    List<ProductDetail> productDetails;

    public Color() {
    }

    public Color(Long colorId, String colorName) {
        this.colorId = colorId;
        this.colorName = colorName;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }
}
