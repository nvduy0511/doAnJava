package com.covid19.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BenhNhan")
public class BenhNhan {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "maBN", nullable = false)
    private int maBN;
	
	@Column(name = "ngayPhatHien", length = 20, nullable = true)
    private String ngayPhatHien;
	
	@Column(name = "soMuiVacin",nullable = false)
    private int soMuiVacin;
	
	@Column(name = "soLanMac",nullable = false)
    private int soLanMac;
	
	@ManyToOne
    @JoinColumn(name = "cmnd_ConNguoi")
    private ConNguoi cmnd_BenhNhan;
	
	@ManyToOne
    @JoinColumn(name = "maCSYT")
    private CoSoYTe maCSYT_BenhNhan;
	
	@Column(name = "TT",  nullable = true)	
    private String trangThai;

	public BenhNhan(int maBN, String ngayPhatHien, int soMuiVacin, int soLanMac, ConNguoi cmnd_BenhNhan,
			CoSoYTe maCSYT_BenhNhan, String trangThai) {
		super();
		this.maBN = maBN;
		this.ngayPhatHien = ngayPhatHien;
		this.soMuiVacin = soMuiVacin;
		this.soLanMac = soLanMac;
		this.cmnd_BenhNhan = cmnd_BenhNhan;
		this.maCSYT_BenhNhan = maCSYT_BenhNhan;
		this.trangThai = trangThai;
	}

	public BenhNhan() {
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

	public CoSoYTe getMaCSYT_BenhNhan() {
		return maCSYT_BenhNhan;
	}

	public void setMaCSYT_BenhNhan(CoSoYTe maCSYT_BenhNhan) {
		this.maCSYT_BenhNhan = maCSYT_BenhNhan;
	}
	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
