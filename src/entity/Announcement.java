package entity;

import entity.enums.AnnouncementType;

import java.time.LocalDateTime;

public class Announcement {
    private Long id ;
    private String title;
    private String description;
    private AnnouncementType type;
    private LocalDateTime createdAt;

    public Announcement(LocalDateTime createdAt, String description, Long id, String title, AnnouncementType type) {
        this.createdAt = createdAt;
        this.description = description;
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(AnnouncementType type) {
        this.type = type;
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

    @Override
    public String toString(){
        return "Announcement : " + id + " " + title + " " + description + " " + type + " " + createdAt;
    }
}
