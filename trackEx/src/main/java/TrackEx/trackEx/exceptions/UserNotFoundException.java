package TrackEx.trackEx.exceptions;

public class UserNotFoundException extends TrackExException{

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
