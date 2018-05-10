package com.example.ghost.giaodienmau.model;

/**
 * Created by namxathu on 08/05/2018.
 */

public class DanhGia
{
    private String sao;
    private String noidung;
    private String username;

    public DanhGia(String sao, String noidung,String username) {
        this.sao = sao;
        this.noidung = noidung;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DanhGia() {
    }

    public String getSao() {
        return sao;
    }

    public void setSao(String sao) {
        this.sao = sao;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
