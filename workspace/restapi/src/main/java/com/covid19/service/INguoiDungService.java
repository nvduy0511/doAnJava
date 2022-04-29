package com.covid19.service;

import java.util.List;

import com.covid19.Entity.NguoiDung;

public interface INguoiDungService {

	public boolean addNguoiDung(NguoiDung nguoiDung);
	
	public boolean updateNguoiDung(String uID, NguoiDung nguoiDung);
	
	public boolean deleteNguoiDung(String uID);
	
	public List<NguoiDung> getAllNguoiDung();
	
	public NguoiDung getOneNguoiDung(String uID);
}
