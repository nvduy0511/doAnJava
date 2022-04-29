package com.covid19.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19.Entity.PhieuKhaiBaoYTe;
import com.covid19.Repository.PhieuKhaiBaoYTeRepository;


@Service
public class PhieuKhaiBaoYTeService implements IPhieuKhaiBaoYTeService {

	@Autowired
	PhieuKhaiBaoYTeRepository phieuKhaiBaoYTeRepository;
	
	
	@Autowired
    EntityManager entityManager;
	
	@Override
	public boolean addPhieuKhaiBaoYTe(PhieuKhaiBaoYTe phieuKhaiBaoYTe) {
		if(phieuKhaiBaoYTe == null)
			return false;
		phieuKhaiBaoYTeRepository.save(phieuKhaiBaoYTe);
		return true;
	}
	@Override
	public boolean updatePhieuKhaiBaoYTe(int maPhieuKhaiBao, PhieuKhaiBaoYTe phieuKhaiBaoYTe) {

		return false;
	}

	@Override
	public boolean deletePhieuKhaiBaoYTe(int maPhieuKhaiBao) {
		PhieuKhaiBaoYTe pkbyt = phieuKhaiBaoYTeRepository.findOne(maPhieuKhaiBao);
		if(pkbyt != null)
		{
			phieuKhaiBaoYTeRepository.delete(pkbyt);
			return true;
		}
		return false;
	}

	@Override
	public List<PhieuKhaiBaoYTe> getAllPhieuKhaiBaoYTe() {
		return phieuKhaiBaoYTeRepository.findAll();
	}

	@Override
	public PhieuKhaiBaoYTe getOnePhieuKhaiBaoYTe(int maPhieuKhaiBao) {
		return phieuKhaiBaoYTeRepository.findOne(maPhieuKhaiBao);
	}
	
	@Override
	public List<PhieuKhaiBaoYTe> getListPhieuKhaiBaoByCmndNguoiKhaiBao(String cmnd) {
		return phieuKhaiBaoYTeRepository.findByCmnd_NguoiKhaiBao(cmnd);
	}

	
}
