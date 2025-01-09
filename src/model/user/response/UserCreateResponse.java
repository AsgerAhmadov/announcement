package model.user.response;

public class UserCreateResponse {

    private Long id;
    private String email;
    private String username;
    private Long age;


    private UserCreateResponse(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.username = builder.username;
        this.age = builder.age;
    }

    @Override
    public String toString() {
        return "User : " + id + " " + email + " " + username + " " + age;

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

    public static class Builder {
        private Long id;
        private String email;
        private String username;
        private Long age;

        public Builder setAge(Long age) {
            this.age = age;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;

        }

        public Builder setId(Long id) {
            this.id = id;
            return this;

        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserCreateResponse build() {
            return new UserCreateResponse(this);
        }
    }
}
