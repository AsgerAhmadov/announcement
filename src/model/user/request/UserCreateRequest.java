package model.user.request;

public class UserCreateRequest {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Long age;

    public UserCreateRequest(Long id, String email, String username, Long age, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.age = age;
        this.password = password;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public Long getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User : " + id + " " + email + " " + username + " " + age + " " + password;

    }
}
