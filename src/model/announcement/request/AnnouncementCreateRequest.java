package model.announcement.request;

import entity.Announcement;
import entity.enums.AnnouncementType;

import java.time.LocalDateTime;

public class AnnouncementCreateRequest {
    private Long id;
    private String title;
    private String description;
    private AnnouncementType type;
    private LocalDateTime createdAt;

    private AnnouncementCreateRequest(AnnouncementCreateRequest.Builder builder) {
        this.createdAt = builder.createdAt;
        this.description = builder.description;
        this.id = builder.id;
        this.title = builder.title;
        this.type = builder.type;
    }

    @Override
    public String toString(){
        return "Announcement : " + id + " " + title + " " + description + " " + type + " " + createdAt;
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

        public AnnouncementCreateRequest.Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AnnouncementCreateRequest.Builder setDescription(String description) {
            this.description = description;
            return this;

        }

        public AnnouncementCreateRequest.Builder setId(Long id) {
            this.id = id;
            return this;

        }

        public AnnouncementCreateRequest.Builder setTitle(String title) {
            this.title = title;
            return this;

        }

        public AnnouncementCreateRequest.Builder setType(AnnouncementType type) {
            this.type = type;
            return this;
        }

        public AnnouncementCreateRequest build() {
            return new AnnouncementCreateRequest(this);
        }
    }
}
