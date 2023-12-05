package TrackEx.trackEx.dtos.request;


import lombok.Data;

@Data
public class RegisterUserRequest {

    private String email;
    private String password;
}
