package thaitay.com.fashion.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
public class Commenter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_COMMENTER")
    @SequenceGenerator(name = "S_COMMENTER", sequenceName = "COMMENTER_SEQ", allocationSize = 1)
    @Column(name = "COMMENTER_ID", updatable = false, insertable=false)
    private Long commenterId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "DATE_TIME")
    private String dateTime;

    @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.MERGE}, targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE}, targetEntity = User.class)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Commenter() {
    }

    public Long getCommenterId() {
        return commenterId;
    }

    public void setCommenterId(Long commenterId) {
        this.commenterId = commenterId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
