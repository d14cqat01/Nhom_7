package com.example.ghost.giaodienmau.model;

/**
 * Created by namxathu on 02/05/2018.
 */

public class Global {
    public static String id,username,password;

    public Global(String id,String username,String password)
    {
        this.id=id;
        this.username =username;
        this.password = password;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Global.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Global.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Global.password = password;
    }
}
