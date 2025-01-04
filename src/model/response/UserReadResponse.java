package model.response;

import entity.Announcement;

public class UserReadResponse {
    private Long id;
    private String email;
    private String username;
    private Long age;

    private UserReadResponse(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.username = builder.username;
    }

    @Override
    public String toString() {
        return "User" + id + " " + email + " " + " " + username;
    }

    public Long getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
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

        public UserReadResponse build() {
            return new UserReadResponse(this);
        }
    }

}
