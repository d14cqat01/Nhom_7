package com.example.ghost.giaodienmau.model;

/**
 * Created by Ghost on 1/1/2018.
 */
public class UserAccount {
    public String id,pwd;
    public UserAccount(){}
    public UserAccount (String id, String pwd){
        this.id = id;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
