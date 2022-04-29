package com.covid19.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CoSoYTe")
public class CoSoYTe {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "maCSYT", nullable = false)
    private int maCSYT;
	
	@Column(name = "tenCSYT", length = 70, nullable = false)
    private String tenCSYT;
	
	@Column(name = "diaChi", length = 70, nullable = false)
    private String diaChi;
	
	@Column(name = "sdt", length = 11, nullable = false)
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
