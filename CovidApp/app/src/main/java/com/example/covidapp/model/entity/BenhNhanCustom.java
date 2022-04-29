package com.example.covidapp.model.entity;

public class BenhNhanCustom {
    private int maBN;

    private String ngayPhatHien;

    private int soMuiVacin;

    private int soLanMac;

    private ConNguoi cmnd_BenhNhan;

    private String trangThai;

    public BenhNhanCustom(int maBN, String ngayPhatHien, int soMuiVacin, int soLanMac, ConNguoi cmnd_BenhNhan) {
        super();
        this.maBN = maBN;
        this.ngayPhatHien = ngayPhatHien;
        this.soMuiVacin = soMuiVacin;
        this.soLanMac = soLanMac;
        this.cmnd_BenhNhan = cmnd_BenhNhan;
    }

    public BenhNhanCustom() {
    }

    public int getMaBN() {
        return maBN;
    }

    public void setMaBN(int maBN) {
        this.maBN = maBN;
    }

    public String getNgayPhatHien() {
        return ngayPhatHien;
    }

    public void setNgayPhatHien(String ngayPhatHien) {
        this.ngayPhatHien = ngayPhatHien;
    }

    public int getSoMuiVacin() {
        return soMuiVacin;
    }

    public void setSoMuiVacin(int soMuiVacin) {
        this.soMuiVacin = soMuiVacin;
    }

    public int getSoLanMac() {
        return soLanMac;
    }

    public void setSoLanMac(int soLanMac) {
        this.soLanMac = soLanMac;
    }

    public ConNguoi getCmnd_BenhNhan() {
        return cmnd_BenhNhan;
    }

    public void setCmnd_BenhNhan(ConNguoi cmnd_BenhNhan) {
        this.cmnd_BenhNhan = cmnd_BenhNhan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
