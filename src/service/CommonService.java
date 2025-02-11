package service;

import constraint.AnnouncementConstraint;
import entity.Announcement;
import entity.User;
import entity.enums.AnnouncementType;
import exception.AnnouncementNotFoundException;
import exception.UserNotFoundException;
import mapper.AnnouncementMapperUtil;
import mapper.UserMapperUtil;
import model.announcement.request.AnnouncementCreateRequest;
import model.announcement.response.AnnouncementCreateResponse;
import model.announcement.request.AnnouncementUpdateRequest;
import model.announcement.response.AnnouncementReadResponse;
import model.user.request.UserCreateRequest;
import model.user.request.UserUpdateRequest;
import model.announcement.response.AnnouncementUpdateResponse;
import model.user.response.UserCreateResponse;
import model.user.response.UserReadResponse;
import model.user.response.UserUpdateResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommonService {
    private static final List<User> users = new ArrayList<>();
    private static final List<Announcement> announcementList = new ArrayList<>();


    public UserCreateResponse registerUser(UserCreateRequest createRequest) {
        User user = UserMapperUtil.userCreateRequestToEntity(createRequest);

        users.add(user);


        return UserMapperUtil.entityToCreateResponse(user);
    }

    public UserReadResponse readUser(Long id) {
        for (User user : users) {
            if (!user.getId().equals(id)) {
                throw new UserNotFoundException(AnnouncementConstraint.user_not_found);
            }

            return UserMapperUtil.entityToReadResponse(user);
        }
        return null;
    }


    public static List<UserReadResponse> findAll() {
        if (users.isEmpty()) {
            throw new UserNotFoundException(AnnouncementConstraint.user_not_found);
        } else {
            return users.stream()
                    .map(user -> new UserReadResponse.Builder()
                            .setId(user.getId())
                            .setAge(user.getAge())
                            .setUsername(user.getUsername())
                            .setEmail(user.getEmail())
                            .build())
                    .collect(Collectors.toList());

        }
    }

    public AnnouncementCreateResponse createAnnouncement(Long userId, AnnouncementCreateRequest createRequest) {

        UserReadResponse user = readUser(userId);
        if (user != null) {
            Announcement announcement = AnnouncementMapperUtil.announcementCreateRequestToEntity(createRequest);

            announcementList.add(announcement);

            return AnnouncementMapperUtil.entityToAnnouncementCreateResponse(announcement);

        }
        throw new UserNotFoundException(AnnouncementConstraint.user_not_found);
    }

    public UserUpdateResponse updateUser(UserUpdateRequest updateRequest, Long id) {
        UserReadResponse foundUser = readUser(id);
        UserReadResponse updatedUser = new UserReadResponse.Builder()
                .setId(foundUser.getId())
                .setAge(updateRequest.getAge())
                .setEmail(updateRequest.getEmail())
                .setUsername(updateRequest.getUsername())
                .build();

        UserUpdateResponse response = new UserUpdateResponse();
        response.setAge(updatedUser.getAge());
        response.setEmail(updatedUser.getEmail());
        response.setId(updatedUser.getId());
        response.setUsername(updatedUser.getUsername());

        return response;
    }

    public void deleteUser(Long id) {
        UserReadResponse foundUser = readUser(id);

        for (User user : users) {
            if (user.getId() == foundUser.getId()) {
                users.remove(user);
            }
        }
    }

    public AnnouncementReadResponse readAnnouncement(Long id) {
        for (Announcement announcement : announcementList) {
            if (!announcement.getId().equals(id)) {
                throw new AnnouncementNotFoundException(AnnouncementConstraint.announcement_not_found);
            }

        return AnnouncementMapperUtil.entityToAnnouncementReadResponse(announcement);

        }
        return null;
    }

    public AnnouncementUpdateResponse updateAnnouncement(AnnouncementUpdateRequest request, Long id) {
        AnnouncementReadResponse readResponse = readAnnouncement(id);
        AnnouncementReadResponse updateAnnouncement = new AnnouncementReadResponse();
        updateAnnouncement.setId(readResponse.getId());
        updateAnnouncement.setType(request.getType());
        updateAnnouncement.setTitle(request.getTitle());
        updateAnnouncement.setDescription(request.getDescription());

        AnnouncementUpdateResponse response = new AnnouncementUpdateResponse();
        response.setId(updateAnnouncement.getId());
        response.setDescription(updateAnnouncement.getDescription());
        response.setTitle(updateAnnouncement.getTitle());
        response.setType(updateAnnouncement.getType());

        return response;
    }

    public void deleteAnnouncement(Long id) {
        AnnouncementReadResponse readResponse = readAnnouncement(id);

        for (Announcement announcement : announcementList) {
            if (announcement.getId().equals(readResponse.getId())) {
                announcementList.remove(announcement);
            }
        }
    }

    public static List<AnnouncementReadResponse> findAllAnnouncement(Announcement announcement) {

        List<AnnouncementReadResponse> responseList = new ArrayList<>();

        if (announcementList.isEmpty()) {
            throw new AnnouncementNotFoundException(AnnouncementConstraint.announcement_not_found);
        } else {
            AnnouncementReadResponse readResponse = new AnnouncementReadResponse();
            readResponse.setId(announcement.getId());
            readResponse.setDescription(announcement.getDescription());
            readResponse.setTitle(announcement.getTitle());
            readResponse.setTitle(announcement.getTitle());

            responseList.add(readResponse);

            return responseList;
        }
    }


    public void userDeleteAnnouncement(Long userId, Long announcementId) {

        List<AnnouncementReadResponse> announcementReadResponses = readAnnouncementByUserId(userId);
        for (AnnouncementReadResponse announcementReadResponse : announcementReadResponses) {
            if (announcementReadResponse.getId().equals(announcementId)) {

                Announcement announcement = new Announcement.Builder()
                        .setId(announcementReadResponse.getId())
                        .setType(announcementReadResponse.getType())
                        .setTitle(announcementReadResponse.getTitle())
                        .setDescription(announcementReadResponse.getDescription())
                        .build();
                announcementList.remove(announcement);
            }
        }


    }

    public List<AnnouncementReadResponse> readAnnouncementByUserId(Long userId) {
        for (User user : users) {
            if (!user.getId().equals(userId)) {
                throw new UserNotFoundException(AnnouncementConstraint.user_not_found);
            }

            List<AnnouncementReadResponse> userAnnouncement = new ArrayList<>();
            for (Announcement announcement : announcementList) {
                if (announcement.getUserId().equals(userId)) {
                    AnnouncementReadResponse announcementReadResponse = new AnnouncementReadResponse();
                    announcementReadResponse.setId(announcement.getId());
                    announcementReadResponse.setType(announcement.getType());
                    announcementReadResponse.setTitle(announcement.getTitle());
                    announcementReadResponse.setDescription(announcement.getDescription());
                    userAnnouncement.add(announcementReadResponse);
                }
            }
            return userAnnouncement;
        }
        return null;
    }

    public List<AnnouncementReadResponse> readAnnouncementByType(String type) {
        List<AnnouncementReadResponse> filterAnnouncement = new ArrayList<>();

        AnnouncementReadResponse readResponse = new AnnouncementReadResponse();
        if (readResponse.getType().name().equals(type)) {
            for (Announcement announcement : announcementList) {
                readResponse.setId(announcement.getId());
                readResponse.setType(announcement.getType());
                readResponse.setTitle(announcement.getTitle());
                readResponse.setDescription(announcement.getDescription());
                filterAnnouncement.add(readResponse);
            }
        }
        return filterAnnouncement;
    }

}
