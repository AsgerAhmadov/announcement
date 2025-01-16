package model.announcement.response;

import entity.enums.AnnouncementType;

public class AnnouncementReadResponse {
    private Long id;
    private String title;
    private String description;
    private AnnouncementType type;

    public AnnouncementReadResponse(){

    }

    public  AnnouncementReadResponse(Long id, String title, String description, AnnouncementType type){
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
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

    public AnnouncementType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
