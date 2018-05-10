package com.example.ghost.giaodienmau.model;

/**
 * Created by namxathu on 08/05/2018.
 */

public class LaySo
{
    private int so;
    private String hoten;
    private String bhyt;
    private String cmnd;
    private String dichvu;
    private String benhvien;
    private String id;

    public LaySo() {
    }

    public LaySo(int so,String hoten, String bhyt, String cmnd, String dichvu, String benhvien, String id) {
        this.so =so ;
        this.hoten = hoten;
        this.bhyt = bhyt;
        this.cmnd = cmnd;
        this.dichvu = dichvu;
        this.benhvien = benhvien;
        this.id = id;
    }

    public int getSo() {
        return so;
    }

    public void setSo(int so) {
        this.so = so;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getBhyt() {
        return bhyt;
    }

    public void setBhyt(String bhyt) {
        this.bhyt = bhyt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDichvu() {
        return dichvu;
    }

    public void setDichvu(String dichvu) {
        this.dichvu = dichvu;
    }

    public String getBenhvien() {
        return benhvien;
    }

    public void setBenhvien(String benhvien) {
        this.benhvien = benhvien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
