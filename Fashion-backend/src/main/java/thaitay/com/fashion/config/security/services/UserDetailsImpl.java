package thaitay.com.fashion.config.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import thaitay.com.fashion.entity.User;
import java.util.*;
import java.util.stream.Collectors;

//1
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String address;
//    private String avatar;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public UserDetailsImpl(Long userId,String name, String username, String email, String phone, String address, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.authorities = authorities;
    }

//    public static UserDetailsImpl build(User user){
//        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
//                new SimpleGrantedAuthority(role.getRoleName().name())
//        ).collect(Collectors.toList());
//
//        return new UserDetailsImpl(user.getUserId(), user.getUsername(), user.getPassword(), authorities);
//    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName().name())
        ).collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getUserId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getPassword(),
                authorities);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
