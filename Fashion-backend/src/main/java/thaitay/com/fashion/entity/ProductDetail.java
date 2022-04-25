package thaitay.com.fashion.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_DETAIL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PRODUCT_DETAIL")
    @SequenceGenerator(name = "S_PRODUCT_DETAIL", sequenceName = "PRODUCT_DETAIL_SEQ", allocationSize = 1)
    @Column(name = "DETAIL_ID", updatable = false, insertable=false)
    private Long detailId;
    @Column(name = "QUANTITY")
    private long quantity;
    //    private Long salePrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, targetEntity = Sizes.class)
    @JoinColumn(name = "SIZE_ID")
    private Sizes size;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, targetEntity = Color.class)
    @JoinColumn(name = "COLOR_ID")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, targetEntity = Order.class)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public ProductDetail() {
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Sizes getSize() {
        return size;
    }

    public void setSize(Sizes size) {
        this.size = size;
    }
}
