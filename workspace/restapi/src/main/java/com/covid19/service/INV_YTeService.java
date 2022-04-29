package com.covid19.service;

import java.util.List;

import com.covid19.Entity.NV_Yte;

public interface INV_YTeService {
	
	public NV_Yte addNV_Yte(NV_Yte nv_yte);
	
	public NV_Yte updateNV_Yte(String uID, NV_Yte nv_yte);
	
	public boolean deleteNV_Yte(String uID);
	
	public List<NV_Yte> getAllNV_Yte();
	
	public NV_Yte getOneNV_Yte(String uID);

}
