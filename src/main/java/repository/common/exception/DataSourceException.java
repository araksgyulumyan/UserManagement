package repository.common.exception;

/**
 * Created by araksgyulumyan
 * Date - 1/29/18
 * Time - 10:11 PM
 */
public class DataSourceException extends RuntimeException {

    public DataSourceException() {
        super();
    }

    public DataSourceException(String message) {
        super(message);
    }

    public DataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceException(Throwable cause) {
        super(cause);
    }
}
