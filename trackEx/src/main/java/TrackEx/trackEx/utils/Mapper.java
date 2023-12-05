package TrackEx.trackEx.utils;

import TrackEx.trackEx.data.model.User;
import TrackEx.trackEx.dtos.request.RegisterUserRequest;
import TrackEx.trackEx.dtos.response.LoginUserResponse;
import TrackEx.trackEx.dtos.response.RegisterUserResponse;

import java.math.BigDecimal;

public class Mapper {

    public static User map(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(registerUserRequest.getPassword());
        user.setLoggedIn(false);
        user.setIncome(BigDecimal.ZERO);
        user.setSavingGoal(BigDecimal.ZERO);
        user.setBalance(BigDecimal.ZERO);
        return user;
    }

    public static RegisterUserResponse map(User registeredUser) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId(registeredUser.getId());
        registerUserResponse.setEmail(registeredUser.getEmail());
        return registerUserResponse;
    }

    public static LoginUserResponse map(String id, User user) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        loginUserResponse.setId(user.getId());
        loginUserResponse.setEmail(user.getEmail());
        loginUserResponse.setLoggedIn(true);
        return loginUserResponse;
    }

}
