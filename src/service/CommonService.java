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
                    .map(UserMapperUtil::entityToReadResponse)
                    .toList();


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
        User user = UserMapperUtil.updateRequestToEntity(updateRequest);
        user.setId(foundUser.getId());

        return  UserMapperUtil.entityToUpdateResponse(user);
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
        Announcement announcement = AnnouncementMapperUtil.updateRequestToEntity(request);
        announcement.setId(readResponse.getId());

        return  AnnouncementMapperUtil.entityToUpdateResponse(announcement);
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

            return announcementList.stream()
                    .map(AnnouncementMapperUtil::entityToAnnouncementReadResponse)
                    .toList();

        }
    }


    public void userDeleteAnnouncement(Long userId, Long announcementId) {

        List<AnnouncementReadResponse> announcementReadResponses = readAnnouncementByUserId(userId);
        for (AnnouncementReadResponse announcementReadResponse : announcementReadResponses) {
            if (announcementReadResponse.getId().equals(announcementId)) {

                Announcement announcement =
                        AnnouncementMapperUtil.readResponseToEntity(announcementReadResponse);

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
                    AnnouncementReadResponse announcementReadResponse =
                            AnnouncementMapperUtil.entityToAnnouncementReadResponse(announcement);

                    userAnnouncement.add(announcementReadResponse);
                }
            }
            return userAnnouncement;
        }
        throw new AnnouncementNotFoundException(AnnouncementConstraint.announcement_not_found);
    }

    public List<AnnouncementReadResponse> readAnnouncementByType(String type) {
        List<AnnouncementReadResponse> filterAnnouncement = new ArrayList<>();

        for (Announcement announcement : announcementList) {
            if (announcement.getType().name().equals(type)) {
                AnnouncementReadResponse announcementReadResponse =
                        AnnouncementMapperUtil.entityToAnnouncementReadResponse(announcement);
                filterAnnouncement.add(announcementReadResponse);
            }
        }

        return filterAnnouncement;
    }

}
