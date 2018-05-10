package com.example.ghost.giaodienmau.model;

/**
 * Created by namxathu on 03/05/2018.
 */

public class History
{
    private String noidung;
    private String thoigian;

    public History() {
    }

    public History(String noidung, String thoigian) {
        this.noidung = noidung;
        this.thoigian = thoigian;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }
}
