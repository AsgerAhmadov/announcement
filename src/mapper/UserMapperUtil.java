package mapper;
import entity.User;
import model.user.request.UserCreateRequest;
import model.user.request.UserUpdateRequest;
import model.user.response.UserCreateResponse;
import model.user.response.UserReadResponse;
import model.user.response.UserUpdateResponse;


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

    public static UserReadResponse entityToReadResponse(User user) {
        return new UserReadResponse.Builder()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .build();
    }
    public static User userUpdateRequest(UserUpdateRequest updateRequest){
        return  null;
    }

    public static UserUpdateResponse userUpdateResponse(User updatedUser){
        UserUpdateResponse response = new UserUpdateResponse();
        response.setAge(updatedUser.getAge());
        response.setEmail(updatedUser.getEmail());
        response.setId(updatedUser.getId());
        response.setUsername(updatedUser.getUsername());

        return  response;
    }
}
