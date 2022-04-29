package Model;

public class TraCuuBenhNhan {
    private String name;
    private String date;
    private String cmnd;
    private String sdt;
    private String diachi;

    public TraCuuBenhNhan(String name, String date, String cmnd, String sdt, String diachi) {
        this.name = name;
        this.date = date;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
