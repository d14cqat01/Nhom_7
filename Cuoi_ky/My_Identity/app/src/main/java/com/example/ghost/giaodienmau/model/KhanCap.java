package com.example.ghost.giaodienmau.model;

/**
 * Created by namxathu on 28/04/2018.
 */

public class KhanCap
{
    private String noidung;
    private Integer image;
    private String number;



    public KhanCap() {
    }

    public KhanCap(String noidung, Integer image,String number) {
        this.noidung = noidung;
        this.image = image;
        this.number =number;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
