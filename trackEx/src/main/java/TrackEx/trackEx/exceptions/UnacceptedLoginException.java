package TrackEx.trackEx.exceptions;

public class UnacceptedLoginException extends TrackExException{

    public UnacceptedLoginException() {
    }

    public UnacceptedLoginException(String message) {
        super(message);
    }
}
