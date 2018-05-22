package exception;

/**
 * Package: exception
 * FileName: ContactIsExistException
 * Date: on 2018/5/22  下午4:44
 * Auther: Wally
 * Descirbe:联系人已经存在的异常
 */
public class ContactIsExistException extends Exception {
    public ContactIsExistException() {
    }

    public ContactIsExistException(String message) {
        super(message);
    }

    public ContactIsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactIsExistException(Throwable cause) {
        super(cause);
    }

    public ContactIsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}