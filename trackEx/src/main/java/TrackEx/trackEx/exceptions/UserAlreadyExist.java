package TrackEx.trackEx.exceptions;

public class UserAlreadyExist extends TrackExException{
    public UserAlreadyExist() {
    }

    public UserAlreadyExist(String message) {
        super(message);
    }
}
