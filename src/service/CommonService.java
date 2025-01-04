package service;

import constraint.AnnouncementConstraint;
import entity.Announcement;
import entity.User;
import exception.UserNotFoundException;
import model.request.UserCreateRequest;
import model.response.UserCreateResponse;
import model.response.UserReadResponse;

import java.util.ArrayList;
import java.util.List;

public class CommonService {
    private final List<User> users = new ArrayList<>();
    private final List<Announcement> announcementList = new ArrayList<>();


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

    public UserReadResponse read(Long id) {
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

}
