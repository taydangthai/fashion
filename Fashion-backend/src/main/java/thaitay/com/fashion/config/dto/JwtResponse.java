package thaitay.com.fashion.config.dto;


import java.util.List;

public class JwtResponse {

    private Long userId;
    private String token;
    private String type = "Bearer";
    private List<String> roles;
    private String username;


    public JwtResponse(String token, List<String> roles, Long userId,  String username) {

        this.token = token;
        this.roles = roles;
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
