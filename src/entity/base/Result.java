package entity.base;

/**
 * Package: entity
 * FileName: Result
 * Date: on 2018/5/21  下午9:57
 * Auther: Wally
 * Descirbe:
 */
public class Result extends Base {
    public static final String SUCCESS = "0";
    public static final String ERROR = "1";

    public IContent content;
    public String status = "";
    public String msg = "";

    public Result() {
    }

    public IContent getContent() {
        return content;
    }

    public void setSuccess() {
        this.status = SUCCESS;
    }

    public void setError() {
        this.status = ERROR;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setContent(IContent content) {
        this.content = content;
    }
}