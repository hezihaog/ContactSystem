package entity;

import entity.base.ObjectContent;

/**
 * Package: entity
 * FileName: Contact
 * Date: on 2018/5/21  下午2:44
 * Auther: Wally
 * Descirbe:联系人实体
 */
public class Contact extends ObjectContent {
    //联系人Id
    private String id;
    //用户Id
    private String userId;
    //联系人姓名
    private String name;
    //联系人性别
    private String gender;
    //联系人年龄
    private int age = -1;
    //联系人手机号
    private String phone;
    //联系人电子邮箱
    private String email;
    //联系人QQ
    private String qq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}