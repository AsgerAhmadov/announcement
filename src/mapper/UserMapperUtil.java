package mapper;

import entity.Announcement;
import entity.User;
import entity.enums.AnnouncementType;
import model.user.request.UserCreateRequest;
import model.user.response.UserCreateResponse;
import model.user.response.UserReadResponse;

import java.time.LocalDateTime;

public class UserMapperUtil {

    private UserMapperUtil() {

    }


    public static User userCreateRequestToEntity(UserCreateRequest createRequest) {

        return new User.Builder()
                .setId(createRequest.getId())
                .setAge(createRequest.getAge())
                .setEmail(createRequest.getEmail())
                .setUsername(createRequest.getUsername())
                .setPassword(createRequest.getPassword())
                .build();

    }
    public  static UserCreateResponse entityToCreateResponse(User user){
        return new UserCreateResponse.Builder()
                .setAge(user.getAge())
                .setEmail(user.getEmail())
                .setId(user.getId())
                .setUsername(user.getUsername())
                .build();
    }
}
