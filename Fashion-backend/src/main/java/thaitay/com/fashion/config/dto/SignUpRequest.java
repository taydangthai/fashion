package thaitay.com.fashion.config.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class SignUpRequest {
    @NotBlank
    @Size(max = 50)
    @JsonProperty("name")
    private String name;
    @NotBlank
    @Size(max = 50)
    @JsonProperty("username")
    private String username;
    @NotBlank
    @Size(max = 60)
    @Email
    @JsonProperty("email")
    private String email;

    private Set<String> role = new HashSet<>();

    @NotBlank
    @Size( max = 40)
    private String password;
    @NotBlank
    @Size(max = 40)
    private String phone;
    @NotBlank
    @Size(min = 3)
    private String address;

    public SignUpRequest() {
    }

    public SignUpRequest(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String username, @NotBlank @Size(max = 60) @Email String email, @NotBlank @Size(max = 40) String password, @NotBlank @Size(max = 20) String phone, @NotBlank @Size(min = 3) String address) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
