package entity;

import entity.base.ObjectContent;

/**
 * Package: entity
 * FileName: User
 * Date: on 2018/5/22  下午5:04
 * Auther: Wally
 * Descirbe:
 */
public class User extends ObjectContent {
    private String id;
    private String userName;
    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
