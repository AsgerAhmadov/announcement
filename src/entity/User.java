package entity;

import java.util.List;

public class User {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Long age;
    private List<Announcement> announcementList;

    private User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.username = builder.username;
        this.password = builder.password;
        this.age = builder.age;
        this.announcementList = builder.announcementList;
    }

    @Override
    public String toString() {
        return "User : " + id + " " + email + " " + username + " " + age + " " + password;

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

    public static class Builder {
        private Long id;
        private String email;
        private String username;
        private String password;
        private Long age;
        private List<Announcement> announcementList;

        public Builder setAge(Long age) {
            this.age = age;
            return this;
        }

        public Builder setAnnouncementList(List<Announcement> announcementList) {
            this.announcementList = announcementList;
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

        public Builder setPassword(String password) {
            this.password = password;
            return this;

        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;

        }

        public User build() {
            return new User(this);
        }
    }
}

