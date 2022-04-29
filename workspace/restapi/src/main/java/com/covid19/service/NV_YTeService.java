package com.covid19.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19.Entity.NV_Yte;
import com.covid19.Repository.NV_YteRepository;

@Service
public class NV_YTeService implements INV_YTeService {

	@Autowired
	NV_YteRepository nv_YteRepository;
	
	@Autowired
    EntityManager entityManager;
	
	@Override
	public NV_Yte addNV_Yte(NV_Yte nv_yte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NV_Yte updateNV_Yte(String uID, NV_Yte nv_yte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteNV_Yte(String uID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<NV_Yte> getAllNV_Yte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NV_Yte getOneNV_Yte(String uID) {
		
		return nv_YteRepository.findOne(uID);
	}

}
