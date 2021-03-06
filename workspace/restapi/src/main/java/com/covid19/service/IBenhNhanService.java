package com.covid19.service;

import java.util.List;

import com.covid19.Entity.BenhNhan;
import com.covid19.Model.BenhNhanCustom;

public interface IBenhNhanService {

	public BenhNhan addBenhNhan(BenhNhan benhNhan, int maCoYTe);
	
	public boolean updateBenhNhan(int maBN, BenhNhan benhNhan);
	
	public boolean deleteBenhNhan(int maBN);
	
	public List<BenhNhan> getAllBenhNhan();
	
	public BenhNhan getOneBenhNhan(int maBN);
	
	public List<BenhNhanCustom> getAllBenhNhanCustom();
	
	public List<BenhNhanCustom> getDiaChiBenhNhanTheoNgay(String date);
	
	public boolean UndoBenhNhan(BenhNhan benhNhan);
}
