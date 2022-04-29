package com.covid19.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
	
	@Id
	@Column(name = "uID",length = 128, nullable = false)
    private String uID;
	
	@OneToOne
    @JoinColumn(name = "cmnd_ConNguoi", referencedColumnName = "cmnd")
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
