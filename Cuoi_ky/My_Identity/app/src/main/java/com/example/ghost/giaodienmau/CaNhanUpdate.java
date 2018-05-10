package com.example.ghost.giaodienmau;

public class CaNhanUpdate {
    public String iduser   ;
    public String vitri;
    public String loaiGiayTo;
    public String muc;
    public String noidung;
    public String urlImage;
    public CaNhanUpdate(){

    }
    public CaNhanUpdate(String iduser, String vitri, String loaiGiayTo, String muc, String noidung, String urlImage) {
        this.iduser = iduser;
        this.vitri = vitri;
        this.loaiGiayTo = loaiGiayTo;
        this.muc = muc;
        this.noidung = noidung;
        this.urlImage = urlImage;
    }

    public String getLoaiGiayTo() {
        return loaiGiayTo;
    }
}
