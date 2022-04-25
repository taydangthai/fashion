package thaitay.com.fashion.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FORGOT_PASSWORD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_FORGOT_PASSWORD")
    @SequenceGenerator(name = "S_FORGOT_PASSWORD", sequenceName = "FORGOT_PASSWORD_SEQ", allocationSize = 1)
    @Column(name = "FORGOT_PASSWORD_ID")
    private Long id;

    @Column(name = "RESET_PASSWORD_TOKEN")
    private String token;

    @Column(name = "TOKEN_CREATION_DATE")
    private LocalDateTime tokenCreationDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTokenCreationDate() {
        return tokenCreationDate;
    }

    public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
        this.tokenCreationDate = tokenCreationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
