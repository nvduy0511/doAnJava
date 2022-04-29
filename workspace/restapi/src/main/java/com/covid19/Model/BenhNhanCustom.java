package com.covid19.Model;

import com.covid19.Entity.BenhNhan;
import com.covid19.Entity.ConNguoi;

public class BenhNhanCustom {
	
    private int maBN;
	
    private String ngayPhatHien;
	
    private int soMuiVacin;
	
    private int soLanMac;
	
    private ConNguoi cmnd_BenhNhan;
    
    public BenhNhanCustom(BenhNhan bn)
    {
    	super();
		this.maBN = bn.getMaBN();
		this.ngayPhatHien = bn.getNgayPhatHien();
		this.soMuiVacin = bn.getSoMuiVacin();
		this.soLanMac = bn.getSoLanMac();
		this.cmnd_BenhNhan = bn.getCmnd_BenhNhan();
    }

	public BenhNhanCustom(int maBN, String ngayPhatHien, int soMuiVacin, int soLanMac, ConNguoi cmnd_BenhNhan) {
		super();
		this.maBN = maBN;
		this.ngayPhatHien = ngayPhatHien;
		this.soMuiVacin = soMuiVacin;
		this.soLanMac = soLanMac;
		this.cmnd_BenhNhan = cmnd_BenhNhan;
	}

	public BenhNhanCustom() {
		// TODO Auto-generated constructor stub
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
	
}
