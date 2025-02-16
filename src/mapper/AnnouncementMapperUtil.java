package mapper;

import entity.Announcement;
import entity.enums.AnnouncementType;
import model.announcement.request.AnnouncementCreateRequest;
import model.announcement.request.AnnouncementUpdateRequest;
import model.announcement.response.AnnouncementCreateResponse;
import model.announcement.response.AnnouncementReadResponse;
import model.announcement.response.AnnouncementUpdateResponse;
import model.user.response.UserUpdateResponse;

import java.time.LocalDateTime;

public class AnnouncementMapperUtil {

    private  AnnouncementMapperUtil(){

    }

    public static Announcement announcementCreateRequestToEntity(AnnouncementCreateRequest createRequest){
        return new Announcement.Builder()
                .setId(createRequest.getId())
                .setDescription(createRequest.getDescription())
                .setType(AnnouncementType.SPORT)
                .setTitle(createRequest.getTitle())
                .setCreatedAt(LocalDateTime.now())
                .build();
    }

    public static AnnouncementCreateResponse entityToAnnouncementCreateResponse(Announcement announcement) {
        return new AnnouncementCreateResponse.Builder()
                .setDescription(announcement.getDescription())
                .setTitle(announcement.getTitle())
                .setType(announcement.getType())
                .setCreatedAt(announcement.getCreatedAt())
                .build();
    }

    public static AnnouncementReadResponse entityToAnnouncementReadResponse(Announcement announcement){
        AnnouncementReadResponse readResponse = new AnnouncementReadResponse();
        readResponse.setId(announcement.getId());
        readResponse.setDescription(announcement.getDescription());
        readResponse.setTitle(announcement.getTitle());
        readResponse.setType(announcement.getType());

        return readResponse;
    }

    public static Announcement readResponseToEntity(AnnouncementReadResponse announcementReadResponse){
        return new Announcement.Builder()
                        .setId(announcementReadResponse.getId())
                        .setType(announcementReadResponse.getType())
                        .setTitle(announcementReadResponse.getTitle())
                        .setDescription(announcementReadResponse.getDescription())
                        .build();
    }

    public static  Announcement updateRequestToEntity(AnnouncementUpdateRequest request){
        return new Announcement.Builder()
                .setId(request.getId())
                .setTitle(request.getTitle())
                .setDescription(request.getDescription())
                .build();
    }

    public static AnnouncementUpdateResponse entityToUpdateResponse(Announcement announcement){

        AnnouncementUpdateResponse updateResponse = new AnnouncementUpdateResponse();
        updateResponse.setId(announcement.getId());
        updateResponse.setType(announcement.getType());
        updateResponse.setTitle(announcement.getTitle());
        updateResponse.setDescription(announcement.getDescription());
        return updateResponse;
    }
}
