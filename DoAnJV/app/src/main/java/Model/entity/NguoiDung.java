package Model.entity;

public class NguoiDung {

    private String uID;
    private ConNguoi cmnd_ConNguoi;

    public NguoiDung(String uID, ConNguoi cmnd_ConNguoi) {
        super();
        this.uID = uID;
        this.cmnd_ConNguoi = cmnd_ConNguoi;
    }

    public NguoiDung() {
        // TODO Auto-generated constructor stub
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public ConNguoi getCmnd_ConNguoi() {
        return cmnd_ConNguoi;
    }

    public void setCmnd_ConNguoi(ConNguoi cmnd_ConNguoi) {
        this.cmnd_ConNguoi = cmnd_ConNguoi;
    }


}
