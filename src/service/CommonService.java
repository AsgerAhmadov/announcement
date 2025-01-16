package service;

import constraint.AnnouncementConstraint;
import entity.Announcement;
import entity.User;
import entity.enums.AnnouncementType;
import exception.AnnouncementNotFoundException;
import exception.UserNotFoundException;
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
        User user = new User.Builder()
                .setId(createRequest.getId())
                .setAge(createRequest.getAge())
                .setEmail(createRequest.getEmail())
                .setUsername(createRequest.getUsername())
                .setPassword(createRequest.getPassword())
                .build();


        users.add(user);
        UserCreateResponse createResponse = new UserCreateResponse.Builder()
                .setAge(user.getAge())
                .setEmail(user.getEmail())
                .setId(user.getId())
                .setUsername(user.getUsername())
                .build();

        return createResponse;
    }

    public UserReadResponse readUser(Long id) {
        for (User user : users) {
            if (user.getId() != id) {
                throw new UserNotFoundException(AnnouncementConstraint.user_not_found);
            }
            UserReadResponse userReadResponse = new UserReadResponse.Builder()
                    .setId(user.getId())
                    .setUsername(user.getUsername())
                    .setEmail(user.getEmail())
                    .build();

            return userReadResponse;
        }
        return null;
    }
//stream read metodu ucun yarat.
    //getall metodu yarat(stream tetbiq et).


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
            Announcement announcement = new Announcement.Builder()
                    .setId(createRequest.getId())
                    .setDescription(createRequest.getDescription())
                    .setType(AnnouncementType.SPORT)
                    .setTitle(createRequest.getTitle())
                    .setCreatedAt(LocalDateTime.now())
                    .build();
            announcementList.add(announcement);

            AnnouncementCreateResponse announcementCreateResponse = new AnnouncementCreateResponse.Builder()
                    .setDescription(announcement.getDescription())
                    .setTitle(announcement.getTitle())
                    .setType(announcement.getType())
                    .setCreatedAt(announcement.getCreatedAt())
                    .build();
            return announcementCreateResponse;
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
    //announcement ucun update ve delete metodu ,find all metodu yaz.

    public AnnouncementReadResponse readAnnouncement(Long id) {
        for (Announcement announcement : announcementList) {
            if (announcement.getId() != id) {
                throw new AnnouncementNotFoundException(AnnouncementConstraint.announcement_not_found);
            }
            AnnouncementReadResponse readResponse = new AnnouncementReadResponse();
            readResponse.setId(announcement.getId());
            readResponse.setDescription(announcement.getDescription());
            readResponse.setTitle(announcement.getTitle());
            readResponse.setType(announcement.getType());

            return readResponse;
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
            if (announcement.getId() == readResponse.getId()) {
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


}
