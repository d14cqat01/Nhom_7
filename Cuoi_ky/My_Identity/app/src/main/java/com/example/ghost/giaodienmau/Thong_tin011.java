package com.example.ghost.giaodienmau;

import java.io.Serializable;

/**
 * Created by king_ on 14-Apr-18.
 */

public class Thong_tin011 implements Serializable{
    private String name;
    private String CMND;
    private String ngay;
    private String queQuan;
    private String so_BH;
    private String email;
    private String so_dt;
    private String imageID;

    public Thong_tin011(String name, String CMND, String ngay, String queQuan, String so_BH, String email, String so_dt) {
        this.name = name;
        this.CMND = CMND;
        this.ngay = ngay;
        this.queQuan = queQuan;
        this.so_BH = so_BH;
        this.email = email;
        this.so_dt = so_dt;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }


    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getSo_BH() {
        return so_BH;
    }

    public void setSo_BH(String so_BH) {
        this.so_BH = so_BH;
    }

    public Thong_tin011() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSo_dt() {
        return so_dt;
    }

    public void setSo_dt(String so_dt) {
        this.so_dt = so_dt;
    }
}
