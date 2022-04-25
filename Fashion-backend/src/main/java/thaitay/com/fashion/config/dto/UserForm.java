package thaitay.com.fashion.config.dto;

public class UserForm {
    private Long userId;
    private String name;
    private String avatar;

    public UserForm() {
    }

    public UserForm(Long userId, String name, String avatar) {
        this.userId = userId;
        this.name = name;
        this.avatar = avatar;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
