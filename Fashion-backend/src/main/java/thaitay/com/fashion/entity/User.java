package thaitay.com.fashion.entity;

import com.fasterxml.jackson.annotation.*;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "USER_NAME"
        }),
        @UniqueConstraint(columnNames = {
                "EMAIL"
        })
})
@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_USERS")
    @SequenceGenerator(name = "S_USERS", sequenceName = "USERS_SEQ", allocationSize = 1)
    @Column(name = "USER_ID", updatable = false, insertable=false)
    @NaturalId(mutable=true)
    private Long userId;
    @Column(name = "NAME")
    private String name;
    @NotBlank
    @Column(name = "USER_NAME")
    private String username;
    @NotBlank
    @Email
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "AVATAR")
    private String avatar;
    @NotBlank
    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL},targetEntity = Commenter.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Commenter> commenters;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;


    @OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private ForgotPassword forgotPasswords;

    public User() {
    }

//    public User(@NotBlank String username, @NotBlank String password, @NotBlank @Email String email) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }

    public User(String name, @NotBlank String username, @NotBlank @Email String email, String phone, String address, @NotBlank String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Commenter> getCommenters() {
        return commenters;
    }

    public void setCommenters(List<Commenter> commenters) {
        this.commenters = commenters;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public ForgotPassword getForgotPasswords() {
        return forgotPasswords;
    }

    public void setForgotPasswords(ForgotPassword forgotPasswords) {
        this.forgotPasswords = forgotPasswords;
    }
}
