package com.example.namxathu.myapplication.model;

public class Student
{
    private String hoten;
    private String mssv;
    private String lop;
    private String gioitinh;

    public String getGioitinh() {
        return gioitinh;
    }

    public Student()
    {

    }

    public Student(String hoten, String mssv, String lop, String gioitinh) {
        this.hoten = hoten;
        this.mssv = mssv;
        this.lop = lop;
        this.gioitinh = gioitinh;
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

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}

