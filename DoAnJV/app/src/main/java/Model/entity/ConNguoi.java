package Model.entity;

public class ConNguoi {

    private String cmnd;
    private String hoTen;
    private String diaChi;
    private String ngaySinh;
    private String sdt;
    private String gioiTinh;



    public ConNguoi(String cmnd, String hoTen, String diaChi, String ngaySinh, String sdt, String gioiTinh) {
        super();
        this.cmnd = cmnd;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
    }
    public ConNguoi() {
        // TODO Auto-generated constructor stub
    }
    public ConNguoi(String CMND){
        this.cmnd  = CMND;
    }

    public String getCmnd() { return cmnd; }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() { return diaChi; }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }


}