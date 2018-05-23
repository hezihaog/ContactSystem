package exception;

/**
 * Package: exception
 * FileName: ContactNoExistException
 * Date: on 2018/5/23  下午9:23
 * Auther: Wally
 * Descirbe:联系人不存在的异常
 */
public class ContactNoExistException extends Exception {
    public ContactNoExistException() {
    }

    public ContactNoExistException(String message) {
        super(message);
    }

    public ContactNoExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactNoExistException(Throwable cause) {
        super(cause);
    }

    public ContactNoExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}