package model.announcement.response;

import entity.enums.AnnouncementType;
import model.announcement.request.AnnouncementCreateRequest;

import java.time.LocalDateTime;

public class AnnouncementCreateResponse {

    private String title;
    private String description;
    private AnnouncementType type;
    private LocalDateTime createdAt;

    private AnnouncementCreateResponse(AnnouncementCreateResponse.Builder builder) {
        this.createdAt = builder.createdAt;
        this.description = builder.description;
        this.title = builder.title;
        this.type = builder.type;
    }

    @Override
    public String toString(){
        return "Announcement : "  + " " + title + " " + description + " " + type + " " + createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }


    public String getTitle() {
        return title;
    }

    public AnnouncementType getType() {
        return type;
    }

    public static class Builder {
        private String title;
        private String description;
        private AnnouncementType type;
        private LocalDateTime createdAt;

        public AnnouncementCreateResponse.Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AnnouncementCreateResponse.Builder setDescription(String description) {
            this.description = description;
            return this;

        }

        public AnnouncementCreateResponse.Builder setTitle(String title) {
            this.title = title;
            return this;

        }

        public AnnouncementCreateResponse.Builder setType(AnnouncementType type) {
            this.type = type;
            return this;
        }

        public AnnouncementCreateResponse build() {
            return new AnnouncementCreateResponse(this);
        }
    }
}
