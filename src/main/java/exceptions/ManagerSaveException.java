package exceptions;

public class ManagerSaveException extends RuntimeException {

    public ManagerSaveException() {
        super();
    }

    public ManagerSaveException(String message) {
        super(message);
    }

    public ManagerSaveException(String s, IllegalArgumentException e) {
    }
}
