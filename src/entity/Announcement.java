package entity;

import entity.enums.AnnouncementType;

import java.time.LocalDateTime;

public class Announcement {
    private Long id;
    private String title;
    private String description;
    private AnnouncementType type;
    private LocalDateTime createdAt;
    private Long userId;

    private Announcement(Builder builder) {
        this.createdAt = builder.createdAt;
        this.description = builder.description;
        this.id = builder.id;
        this.title = builder.title;
        this.type = builder.type;
        this.userId = builder.userId;
    }

    @Override
    public String toString(){
        return "Announcement : " + id + " " + title + " " + description + " " + type + " " + createdAt + " " + userId;
    }

    public Long getUserId(){
        return userId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AnnouncementType getType() {
        return type;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String description;
        private AnnouncementType type;
        private LocalDateTime createdAt;
        private Long userId;

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;

        }

        public Builder setId(Long id) {
            this.id = id;
            return this;

        }

        public Builder setUserId(Long userId){
            this.userId = userId;
            return  this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;

        }

        public Builder setType(AnnouncementType type) {
            this.type = type;
            return this;
        }

        public Announcement build() {
            return new Announcement(this);
        }
    }
}


