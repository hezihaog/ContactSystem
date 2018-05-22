package exception;

/**
 * Package: exception
 * FileName: ContactExistException
 * Date: on 2018/5/22  下午4:44
 * Auther: Wally
 * Descirbe:联系人已经存在的异常
 */
public class ContactExistException extends Exception {
    public ContactExistException() {
    }

    public ContactExistException(String message) {
        super(message);
    }

    public ContactExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactExistException(Throwable cause) {
        super(cause);
    }

    public ContactExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}