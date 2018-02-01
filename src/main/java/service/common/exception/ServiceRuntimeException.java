package service.common.exception;

/**
 * Created by araksgyulumyan
 * Date - 2/1/18
 * Time - 10:15 PM
 */
public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException() {
        super();
    }

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
    }

    protected ServiceRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
