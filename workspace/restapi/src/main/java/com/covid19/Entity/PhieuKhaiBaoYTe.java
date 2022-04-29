package com.covid19.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PhieuKhaiBaoYTe" )
public class PhieuKhaiBaoYTe {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "maPhieuKhaiBao", nullable = false)
    private int maPhieuKhaiBao;
	
	@Column(name = "cauHoi1", nullable = false)
	private boolean cauHoi1;
	
	@Column(name = "cauHoi2", nullable = false)
	private boolean cauHoi2;
	
	
	@Column(name = "cauHoi3_1", nullable = false)
	private boolean cauHoi3_1;
	
	@Column(name = "cauHoi3_2", nullable = false)
	private boolean cauHoi3_2;
	
	@Column(name = "cauHoi3_3", nullable = false)
	private boolean cauHoi3_3;
	
	@Column(name = "dateTime", nullable = true)
	private String dateTime;
	
	@Column(name = "cmnd_NguoiKhaiBao", nullable = true)
    private String cmnd_NguoiKhaiBao;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cmnd_ConNguoi")
    private ConNguoi cmnd_ConNguoi;

	public PhieuKhaiBaoYTe(int maPhieuKhaiBao, boolean cauHoi1, boolean cauHoi2, boolean cauHoi3_1, boolean cauHoi3_2,
			boolean cauHoi3_3, String dateTime, String cmnd_NguoiKhaiBao, ConNguoi cmnd_ConNguoi) {
		super();
		this.maPhieuKhaiBao = maPhieuKhaiBao;
		this.cauHoi1 = cauHoi1;
		this.cauHoi2 = cauHoi2;
		this.cauHoi3_1 = cauHoi3_1;
		this.cauHoi3_2 = cauHoi3_2;
		this.cauHoi3_3 = cauHoi3_3;
		this.dateTime = dateTime;
		this.cmnd_NguoiKhaiBao = cmnd_NguoiKhaiBao;
		this.cmnd_ConNguoi = cmnd_ConNguoi;
	}
	
	
	public PhieuKhaiBaoYTe() {
		// TODO Auto-generated constructor stub
	}


	public int getMaPhieuKhaiBao() {
		return maPhieuKhaiBao;
	}


	public void setMaPhieuKhaiBao(int maPhieuKhaiBao) {
		this.maPhieuKhaiBao = maPhieuKhaiBao;
	}


	public boolean isCauHoi1() {
		return cauHoi1;
	}


	public void setCauHoi1(boolean cauHoi1) {
		this.cauHoi1 = cauHoi1;
	}


	public boolean isCauHoi2() {
		return cauHoi2;
	}


	public void setCauHoi2(boolean cauHoi2) {
		this.cauHoi2 = cauHoi2;
	}


	public boolean isCauHoi3_1() {
		return cauHoi3_1;
	}


	public void setCauHoi3_1(boolean cauHoi3_1) {
		this.cauHoi3_1 = cauHoi3_1;
	}


	public boolean isCauHoi3_2() {
		return cauHoi3_2;
	}


	public void setCauHoi3_2(boolean cauHoi3_2) {
		this.cauHoi3_2 = cauHoi3_2;
	}


	public boolean isCauHoi3_3() {
		return cauHoi3_3;
	}


	public void setCauHoi3_3(boolean cauHoi3_3) {
		this.cauHoi3_3 = cauHoi3_3;
	}


	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public String getCmnd_NguoiKhaiBao() {
		return cmnd_NguoiKhaiBao;
	}


	public void setCmnd_NguoiKhaiBao(String cmnd_NguoiKhaiBao) {
		this.cmnd_NguoiKhaiBao = cmnd_NguoiKhaiBao;
	}


	public ConNguoi getCmnd_ConNguoi() {
		return cmnd_ConNguoi;
	}


	public void setCmnd_ConNguoi(ConNguoi cmnd_ConNguoi) {
		this.cmnd_ConNguoi = cmnd_ConNguoi;
	}
	
}
