package model.response;

public class UserCreateResponse {

    private Long id;
    private String email;
    private String username;
    private Long age;

    public UserCreateResponse() {

    }

    public UserCreateResponse(Long id, String email, String username, Long age) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.age = age;
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

    @Override
    public String toString() {
        return "User : " + id + " " + email + " " + username + " " + age;

    }
}
