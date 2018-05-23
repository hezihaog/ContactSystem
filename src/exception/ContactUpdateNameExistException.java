package exception;

/**
 * Package: exception
 * FileName: ContactUpdateNameExistException
 * Date: on 2018/5/23  下午9:36
 * Auther: Wally
 * Descirbe:要更新的联系人姓名已存在
 */
public class ContactUpdateNameExistException extends Exception {
    public ContactUpdateNameExistException() {
    }

    public ContactUpdateNameExistException(String message) {
        super(message);
    }

    public ContactUpdateNameExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactUpdateNameExistException(Throwable cause) {
        super(cause);
    }

    public ContactUpdateNameExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}