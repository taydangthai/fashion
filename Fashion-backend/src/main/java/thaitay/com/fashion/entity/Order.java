package thaitay.com.fashion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ORDERS")
    @SequenceGenerator(name = "S_ORDERS", sequenceName = "ORDERS_SEQ", allocationSize = 1)
    @Column(name = "ORDER_ID", updatable = false, insertable=false)
    private Long orderId;
    @Column(name = "DATE_TIME")
    private Date dateTime;
    @Column(name = "TOTAL")
    private Long total;
    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;
    @Column(name = "PHONE_ORDER")
    private String phoneOrder;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.MERGE})
    @JoinColumn(name = "USER_ID")
    private User user;

    @JoinColumn(name = "PAYMENT_ID")
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JsonIgnore
    private Payment payment;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL},targetEntity = ProductDetail.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<ProductDetail> productDetails;

    public Order() {
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPhoneOrder() {
        return phoneOrder;
    }

    public void setPhoneOrder(String phoneOrder) {
        this.phoneOrder = phoneOrder;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }
}
