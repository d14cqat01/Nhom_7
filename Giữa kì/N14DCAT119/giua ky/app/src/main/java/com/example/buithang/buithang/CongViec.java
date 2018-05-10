package com.example.buithang.buithang;

/**
 * Created by lephi on 09/04/2018.
 */

public class CongViec {
    private String TenCongViec;
    private String NoiDung;
    private String ThoiGianThucHien;
    private String ThoiGianHoanThanh;
    private String GhiChu;
    private int _id;

    public CongViec(String tenCongViec, String noiDung, String thoiGianThucHien, String thoiGianHoanThanh, String ghiChu, int _id) {
        TenCongViec = tenCongViec;
        NoiDung = noiDung;
        ThoiGianThucHien = thoiGianThucHien;
        ThoiGianHoanThanh = thoiGianHoanThanh;
        GhiChu = ghiChu;
        this._id = _id;
    }
    public CongViec(String tenCongViec, String noiDung, String thoiGianThucHien, String thoiGianHoanThanh, String ghiChu) {
        TenCongViec = tenCongViec;
        NoiDung = noiDung;
        ThoiGianThucHien = thoiGianThucHien;
        ThoiGianHoanThanh = thoiGianHoanThanh;
        GhiChu = ghiChu;
    }

    public String getTenCongViec() {
        return TenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        TenCongViec = tenCongViec;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getThoiGianThucHien() {
        return ThoiGianThucHien;
    }

    public void setThoiGianThucHien(String thoiGianThucHien) {
        ThoiGianThucHien = thoiGianThucHien;
    }

    public String getThoiGianHoanThanh() {
        return ThoiGianHoanThanh;
    }

    public void setThoiGianHoanThanh(String thoiGianHoanThanh) {
        ThoiGianHoanThanh = thoiGianHoanThanh;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
