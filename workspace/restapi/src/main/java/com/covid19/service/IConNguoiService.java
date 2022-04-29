package com.covid19.service;

import java.util.List;

import com.covid19.Entity.ConNguoi;

public interface IConNguoiService {
	
	public boolean addConNguoi(ConNguoi connNguoi);
	
	public boolean updateConNguoi(String cmnd, ConNguoi conNguoi);
	
	public boolean deleteConNguoi(String cmnd);
	
	public List<ConNguoi> getAllConNguoi();
	
	public ConNguoi getOneConNguoi(String cmnd);
	
	public ConNguoi getOneConNguoiByUID(String uID);
	
	public ConNguoi getOneConNguoiByUID_NVYTe(String uID);

}
