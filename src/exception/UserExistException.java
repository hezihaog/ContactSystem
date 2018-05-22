package exception;

/**
 * Package: exception
 * FileName: UserExistException
 * Date: on 2018/5/22  下午5:30
 * Auther: Wally
 * Descirbe:用户名已经存在的异常
 */
public class UserExistException extends Exception {
    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }

    public UserExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
