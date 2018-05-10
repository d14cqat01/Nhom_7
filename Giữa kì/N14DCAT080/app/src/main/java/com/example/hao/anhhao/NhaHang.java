package com.example.hao.anhhao;

public class NhaHang {
    private String Hoten;
    private String Diachi;
    private String Phone;
    private String Quocgia;

    public NhaHang(String hoten, String diachi, String phone, String quocgia) {
        Hoten = hoten;
        Diachi = diachi;
        Phone = phone;
        Quocgia = quocgia;
    }

    public NhaHang() {
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getQuocgia() {
        return Quocgia;
    }

    public void setQuocgia(String quocgia) {
        Quocgia = quocgia;
    }
}