package com.example.covidapp.model.entity;

public class PhieuKhaiBaoYTe {

    private int maPhieuKhaiBao;

    private boolean cauHoi1;

    private boolean cauHoi2;

    private boolean cauHoi3_1;

    private boolean cauHoi3_2;

    private boolean cauHoi3_3;

    private String dateTime;

    private String cmnd_NguoiKhaiBao;

    private ConNguoi cmnd_ConNguoi;

    public PhieuKhaiBaoYTe(int maPhieuKhaiBao, boolean cauHoi1, boolean cauHoi2, boolean cauHoi3_1, boolean cauHoi3_2,
                           boolean cauHoi3_3, String dateTime, String cmnd_NguoiKhaiBao, ConNguoi cmnd_ConNguoi) {
        super();
        this.maPhieuKhaiBao = maPhieuKhaiBao;
        this.cauHoi1 = cauHoi1;
        this.cauHoi2 = cauHoi2;
        this.cauHoi3_1 = cauHoi3_1;
        this.cauHoi3_2 = cauHoi3_2;
        this.cauHoi3_3 = cauHoi3_3;
        this.dateTime = dateTime;
        this.cmnd_NguoiKhaiBao = cmnd_NguoiKhaiBao;
        this.cmnd_ConNguoi = cmnd_ConNguoi;
    }


    public PhieuKhaiBaoYTe() {
        // TODO Auto-generated constructor stub
    }


    public int getMaPhieuKhaiBao() {
        return maPhieuKhaiBao;
    }


    public void setMaPhieuKhaiBao(int maPhieuKhaiBao) {
        this.maPhieuKhaiBao = maPhieuKhaiBao;
    }


    public boolean isCauHoi1() {
        return cauHoi1;
    }


    public void setCauHoi1(boolean cauHoi1) {
        this.cauHoi1 = cauHoi1;
    }


    public boolean isCauHoi2() {
        return cauHoi2;
    }


    public void setCauHoi2(boolean cauHoi2) {
        this.cauHoi2 = cauHoi2;
    }


    public boolean isCauHoi3_1() {
        return cauHoi3_1;
    }


    public void setCauHoi3_1(boolean cauHoi3_1) {
        this.cauHoi3_1 = cauHoi3_1;
    }


    public boolean isCauHoi3_2() {
        return cauHoi3_2;
    }


    public void setCauHoi3_2(boolean cauHoi3_2) {
        this.cauHoi3_2 = cauHoi3_2;
    }


    public boolean isCauHoi3_3() {
        return cauHoi3_3;
    }


    public void setCauHoi3_3(boolean cauHoi3_3) {
        this.cauHoi3_3 = cauHoi3_3;
    }


    public String getDateTime() {
        return dateTime;
    }


    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    public String getCmnd_NguoiKhaiBao() {
        return cmnd_NguoiKhaiBao;
    }


    public void setCmnd_NguoiKhaiBao(String cmnd_NguoiKhaiBao) {
        this.cmnd_NguoiKhaiBao = cmnd_NguoiKhaiBao;
    }


    public ConNguoi getCmnd_ConNguoi() {
        return cmnd_ConNguoi;
    }


    public void setCmnd_ConNguoi(ConNguoi cmnd_ConNguoi) {
        this.cmnd_ConNguoi = cmnd_ConNguoi;
    }

}
