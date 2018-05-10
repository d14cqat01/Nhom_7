package com.example.ghost.giaodienmau.model;

/**
 * Created by namxathu on 28/04/2018.
 */
public class KhieuNai
{
    private String ten;
    private String thanhpho;
    private String quan;
    private String diachi;
    private String noidung;
    private String id;

    public KhieuNai() {
    }

    public KhieuNai(String ten, String thanhpho, String quan, String diachi, String noidung,String id) {
        this.ten = ten;
        this.thanhpho = thanhpho;
        this.quan = quan;
        this.diachi = diachi;
        this.noidung = noidung;
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getThanhpho() {
        return thanhpho;
    }

    public void setThanhpho(String thanhpho) {
        this.thanhpho = thanhpho;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}