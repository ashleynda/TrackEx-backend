package TrackEx.trackEx.exceptions;

public class IncorrectPasswordException extends TrackExException{
    public IncorrectPasswordException() {
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }
}

