package exception;

import entity.User;

public class UserNotFoundException extends  RuntimeException {

    public UserNotFoundException(String message){
        super(message);
    }
}
