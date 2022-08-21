package avia.user.exception;

public class UnsupportedRequestException extends UnsupportedOperationException {

    public UnsupportedRequestException(String message) {
        super("Invalid request data: " + message);
    }
}
