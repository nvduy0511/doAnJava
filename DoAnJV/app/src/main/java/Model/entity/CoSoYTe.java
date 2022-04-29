package Model.entity;

import java.io.Serializable;

public class CoSoYTe implements Serializable {

    private int maCSYT;
    private String tenCSYT;
    private String diaChi;
    private String sdt;

    public CoSoYTe(int maCSYT, String tenCSYT, String diaChi, String sdt) {
        super();
        this.maCSYT = maCSYT;
        this.tenCSYT = tenCSYT;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public CoSoYTe() {
        // TODO Auto-generated constructor stub
    }

    public int getMaCSYT() {
        return maCSYT;
    }

    public void setMaCSYT(int maCSYT) {
        this.maCSYT = maCSYT;
    }

    public String getTenCSYT() {
        return tenCSYT;
    }

    public void setTenCSYT(String tenCSYT) {
        this.tenCSYT = tenCSYT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
