import entity.Announcement;
import entity.User;
import model.request.UserCreateRequest;
import model.response.UserCreateResponse;

import java.util.ArrayList;
import java.util.List;

public class CommonService {
    private final List<User> users = new ArrayList<>();
    private final List<Announcement> announcementList = new ArrayList<>();


    public UserCreateResponse registerUser (UserCreateRequest createRequest) {
        User user = new User();
        user.setId(createRequest.getId());
        user.setAge(createRequest.getAge());
        user.setEmail(createRequest.getEmail());
        user.setUsername(createRequest.getUsername());
        
        users.add(user);
        UserCreateResponse createResponse = new UserCreateResponse();
        createResponse.setAge(user.getAge());
        createResponse.setEmail(user.getEmail());
        createResponse.setId(user.getId());
        createResponse.setUsername(user.getUsername());

        return createResponse;
    }
}
