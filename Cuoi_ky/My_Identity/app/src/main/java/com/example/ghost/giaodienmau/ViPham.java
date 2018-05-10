package com.example.ghost.giaodienmau;

public class ViPham {
    String key;
    String chitiet;
    String sotienphat;
    String thugiu;
    String vipham;
    String time;
    public ViPham(){

    }
    public ViPham(String chitiet, String sotienphat, String thugiu, String vipham, String time) {
        this.chitiet = chitiet;
        this.sotienphat = sotienphat;
        this.thugiu = thugiu;
        this.vipham = vipham;
        this.time = time;
    }

    public String getChitiet() {
        return chitiet;
    }

    public String getSotienphat() {
        return sotienphat;
    }

    public String getThugiu() {
        return thugiu;
    }

    public String getVipham() {
        return vipham;
    }
    public String getTime() {
        return time;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
