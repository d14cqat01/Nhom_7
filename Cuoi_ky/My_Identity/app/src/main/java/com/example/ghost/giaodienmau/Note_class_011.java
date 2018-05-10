package com.example.ghost.giaodienmau;

import java.io.Serializable;

/**
 * Created by king_ on 24-Apr-18.
 */

public class Note_class_011 implements Serializable {
    private int maNote;
    private String tieude;
    private String Noidung;

    public int getMaNote() {
        return maNote;
    }

    public Note_class_011(int maNote, String tieude, String noidung, String date) {
        this.maNote = maNote;
        this.tieude = tieude;
        Noidung = noidung;
        this.date = date;
    }

    public Note_class_011() {
    }

    public void setMaNote(int maNote) {
        this.maNote = maNote;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return Noidung;
    }

    public void setNoidung(String noidung) {
        Noidung = noidung;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;
}

