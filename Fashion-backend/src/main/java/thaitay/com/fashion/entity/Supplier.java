package thaitay.com.fashion.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SUPPLIER")
@Proxy(lazy = false)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_SUPPLIER")
    @SequenceGenerator(name = "S_SUPPLIER", sequenceName = "SUPPLIER_SEQ", allocationSize = 1)
    @Column(name = "SUPPLIER_ID")
    private Long supplierId;

    @Column(name = "SUPPLIER_NAME")
    private String supplierName;

    @OneToMany(mappedBy = "supplier", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, targetEntity = Product.class)
    @JsonIgnore
    private List<Product> productList;

    public Supplier() {
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
