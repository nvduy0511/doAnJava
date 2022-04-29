package Model.entity;

public class NV_Yte {
    private String uID;

    private String chucVu;

    private ConNguoi cmnd_ConNguoi;

    private CoSoYTe ma_CSYT;

    public NV_Yte(String uID, String chucVu, ConNguoi cmnd_ConNguoi, CoSoYTe ma_CSYT) {
        super();
        this.uID = uID;
        this.chucVu = chucVu;
        this.cmnd_ConNguoi = cmnd_ConNguoi;
        this.ma_CSYT = ma_CSYT;
    }

    public NV_Yte() {
        // TODO Auto-generated constructor stub
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public ConNguoi getCmnd_ConNguoi() {
        return cmnd_ConNguoi;
    }

    public void setCmnd_ConNguoi(ConNguoi cmnd_ConNguoi) {
        this.cmnd_ConNguoi = cmnd_ConNguoi;
    }

    public CoSoYTe getMa_CSYT() {
        return ma_CSYT;
    }

    public void setMa_CSYT(CoSoYTe ma_CSYT) {
        this.ma_CSYT = ma_CSYT;
    }
}
