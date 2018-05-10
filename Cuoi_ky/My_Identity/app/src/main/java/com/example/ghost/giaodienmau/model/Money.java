package com.example.ghost.giaodienmau.model;

import java.io.Serializable;

/**
 * Created by IT on 1/5/2018.
 */

public class Money implements Serializable{
    private String lydo,sotien;
    private boolean chosen = false;
    private int mucdich = 1;
    private int id;

    public Money() {
    }

    public Money(int id, int mucdich, String sotien, String lydo) {
        this.sotien = sotien;
        this.lydo = lydo;
        this.mucdich = mucdich;
        this.chosen = false;
        this.id = id;
    }

    public Money(int mucdich, String sotien, String lydo) {
        this.sotien = sotien;
        this.lydo = lydo;
        this.mucdich = mucdich;
    }

    public Money(int mucdich, String sotien, String lydo, boolean chosen) {
        this.sotien = sotien;
        this.lydo = lydo;
        this.mucdich = mucdich;
        this.chosen = false;
    }

    @Override
    public String toString() {
        return "Money{" +
                "lydo='" + lydo + '\'' +
                ", sotien='" + sotien + '\'' +
                ", mucdich='" + mucdich + '\'' +
                ", chosen=" + chosen +
                ", id=" + id +
                '}';
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public int getMucdich() {
        return mucdich;
    }

    public void setMucdich(int mucdich) {
        this.mucdich = mucdich;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
