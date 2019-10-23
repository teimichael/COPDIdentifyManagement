package stu.napls.copdmanage.core.exception;

/**
 * @author Yepeng Ding
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
