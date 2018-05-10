package com.example.ghost.giaodienmau;

import java.sql.Date;
import java.sql.Time;

public class HistoryUpdate {
    String key;

    String noidung;
    String time;
    String id;
    public HistoryUpdate(){

    }

    public HistoryUpdate(String id, String noidung, String time) {
        this.id = id;
        this.noidung = noidung;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getNoidung() {
        return noidung;
    }

    public String getTime() {
        return time;
    }

    public String getKeys() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
