package com.covid19.service;

import java.util.List;

import com.covid19.Entity.PhieuKhaiBaoYTe;
public interface IPhieuKhaiBaoYTeService {
	
	public boolean addPhieuKhaiBaoYTe(PhieuKhaiBaoYTe phieuKhaiBaoYTe);
	
	public boolean updatePhieuKhaiBaoYTe(int maPhieuKhaiBao, PhieuKhaiBaoYTe phieuKhaiBaoYTe);
	
	public boolean deletePhieuKhaiBaoYTe(int maPhieuKhaiBao);
	
	public List<PhieuKhaiBaoYTe> getAllPhieuKhaiBaoYTe();
	
	public PhieuKhaiBaoYTe getOnePhieuKhaiBaoYTe(int maPhieuKhaiBao);
	
	public List<PhieuKhaiBaoYTe> getListPhieuKhaiBaoByCmndNguoiKhaiBao(String cmnd);

}
