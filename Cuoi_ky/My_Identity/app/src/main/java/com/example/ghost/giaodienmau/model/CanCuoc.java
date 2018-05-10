package com.example.ghost.giaodienmau.model;

/**
 * Created by namxathu on 17/04/2018.
 */

public class CanCuoc
{
    private String hansudung;
    private String nhandang;
    private String quequan;
    private String so;

    public CanCuoc() {
    }

    public CanCuoc(String hansudung, String nhandang, String quequan, String so) {
        this.hansudung = hansudung;
        this.nhandang = nhandang;
        this.quequan = quequan;
        this.so = so;
    }

    public String getHansudung() {
        return hansudung;
    }

    public void setHansudung(String hansudung) {
        this.hansudung = hansudung;
    }

    public String getNhandang() {
        return nhandang;
    }

    public void setNhandang(String nhandang) {
        this.nhandang = nhandang;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }
}
