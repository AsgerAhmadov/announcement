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
        User user = new User();
        user.setId(createRequest.getId());
        user.setAge(createRequest.getAge());
        user.setEmail(createRequest.getEmail());
        user.setUsername(createRequest.getUsername());
        user.setPassword(createRequest.getPassword());

        users.add(user);
        UserCreateResponse createResponse = new UserCreateResponse();
        createResponse.setAge(user.getAge());
        createResponse.setEmail(user.getEmail());
        createResponse.setId(user.getId());
        createResponse.setUsername(user.getUsername());

        return createResponse;
    }

    public UserReadResponse read(Long id) {
        for (User user : users) {
            if (user.getId() != id) {
                throw new UserNotFoundException(AnnouncementConstraint.user_not_found);
            }
            UserReadResponse userReadResponse = new UserReadResponse();
            userReadResponse.setId(user.getId());
            userReadResponse.setUsername(user.getUsername());
            userReadResponse.setEmail(user.getEmail());
            return userReadResponse;
        }
        return null;
    }

}
