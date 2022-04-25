package thaitay.com.fashion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "PAYMENT")
@Proxy(lazy = false)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PAYMENT")
    @SequenceGenerator(name = "S_PAYMENT", sequenceName = "PAYMENT_SEQ", allocationSize = 1)
    @Column(name = "PAYMENT_ID")
    private Long paymentId;

    @Column(name = "PAYMENT_NAME")
    private String paymentName;

    @OneToMany(mappedBy = "payment", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Order> orders;

    public Payment() {

    }

    public Payment(Long paymentId, String paymentName) {
        this.paymentId = paymentId;
        this.paymentName = paymentName;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }
}
