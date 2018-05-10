package com.example.ghost.giaodienmau.model;

/**
 * Created by namxathu on 17/04/2018.
 */

public class CoBan {
    private String email;
    private String gioitinh;
    private String hoten;
    private String ngaysinh;
    private String noithuongtru;
    private String quoctich;
    private String sdt;

    public CoBan() {
    }

    public CoBan(String email, String gioitinh, String hoten, String ngaysinh, String noithuongtru, String quoctich, String sdt) {
        this.email = email;
        this.gioitinh = gioitinh;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.noithuongtru = noithuongtru;
        this.quoctich = quoctich;
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getNoithuongtru() {
        return noithuongtru;
    }

    public void setNoithuongtru(String noithuongtru) {
        this.noithuongtru = noithuongtru;
    }

    public String getQuoctich() {
        return quoctich;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }


}
