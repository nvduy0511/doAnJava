package com.covid19.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ConNguoi" )
public class ConNguoi {
	
	@Id
    @Column(name = "cmnd",length = 15, nullable = false)
    private String cmnd;
	
	@Column(name = "hoTen", length = 50, nullable = false)
    private String hoTen;
	
	@Column(name = "diaChi", length = 128, nullable = false)
    private String diaChi;
	
	@Column(name = "ngaySinh", length = 15, nullable = false)
    private String ngaySinh;
	
	@Column(name = "sdt", length = 11, nullable = false)
    private String sdt;
	
	@Column(name = "gioiTinh", length = 5, nullable = false)
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

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

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
