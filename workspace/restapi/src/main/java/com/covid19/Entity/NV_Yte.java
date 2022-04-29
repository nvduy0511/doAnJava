package com.covid19.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "NV_Yte")
public class NV_Yte {
	
	@Id
    @Column(name = "uID", length = 128,  nullable = false)
    private String uID;
	
	@Column(name = "chucVu", length = 20, nullable = false)
    private String chucVu;
	
	@OneToOne
    @JoinColumn(name = "cmnd_ConNguoi", referencedColumnName = "cmnd")
	private ConNguoi cmnd_ConNguoi;
	
	@ManyToOne
    @JoinColumn(name = "ma_CSYT")
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
