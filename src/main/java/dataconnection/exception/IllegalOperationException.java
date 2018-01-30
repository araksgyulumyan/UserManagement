package dataconnection.exception;

/**
 * Created by araksgyulumyan
 * Date - 1/30/18
 * Time - 7:31 PM
 */
public class IllegalOperationException extends RuntimeException {

    public IllegalOperationException() {
        super();
    }

    public IllegalOperationException(String message) {
        super(message);
    }
}
