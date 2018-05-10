package com.example.ghost.giaodienmau;

import java.io.Serializable;

public class Username implements Serializable {
    public   String id;
    private  String password;

    public Username(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Username() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
