package com.covid19.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19.Entity.NguoiDung;
import com.covid19.Repository.NguoiDungRepository;

@Service
public class NguoiDungService implements INguoiDungService {

	@Autowired
	NguoiDungRepository nguoiDungRepository;
	
	@Autowired
    EntityManager entityManager;
	
	@Override
	public boolean addNguoiDung(NguoiDung nguoiDung) {
		nguoiDungRepository.save(nguoiDung);
		return true;
	}

	@Override
	public boolean updateNguoiDung(String uID, NguoiDung nguoiDung) {
		if(nguoiDung != null)
		{
			NguoiDung nd = nguoiDungRepository.findOne(uID);
			if(nd != null)
			{
				nd.setCmnd_ConNguoi(nguoiDung.getCmnd_ConNguoi());
				nguoiDungRepository.save(nd);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteNguoiDung(String uID) {
		NguoiDung nd = nguoiDungRepository.findOne(uID);
		if(nd != null)
		{
			nguoiDungRepository.delete(nd);
			return true;
		}
		return false;
	}

	@Override
	public List<NguoiDung> getAllNguoiDung() {
		return nguoiDungRepository.findAll();
	}

	@Override
	public NguoiDung getOneNguoiDung(String uID) {
		return nguoiDungRepository.findOne(uID);
	}

}
