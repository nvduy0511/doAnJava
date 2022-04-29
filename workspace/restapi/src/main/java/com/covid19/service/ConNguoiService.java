package com.covid19.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covid19.Entity.ConNguoi;
import com.covid19.Entity.NV_Yte;
import com.covid19.Entity.NguoiDung;
import com.covid19.Repository.ConNguoiRepository;
import com.covid19.Repository.NV_YteRepository;
import com.covid19.Repository.NguoiDungRepository;

@Service
public class ConNguoiService implements IConNguoiService {
	
	@Autowired
	ConNguoiRepository conNguoiRepository;
	
	@Autowired
	NguoiDungRepository nguoiDungRepository;
	
	@Autowired
	NV_YteRepository nv_YteRepository;
	
	@Autowired
    EntityManager entityManager;

	@Override
	public boolean addConNguoi(ConNguoi connNguoi) {
		ConNguoi cn = conNguoiRepository.findOne(connNguoi.getCmnd());
		if(cn == null)
		{
			conNguoiRepository.save(connNguoi);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateConNguoi(String cmnd, ConNguoi conNguoi) {
		if(conNguoi != null)
		{
			ConNguoi cn = new ConNguoi();
			cn = conNguoiRepository.findOne(cmnd);
			if(cn == null) 
				return false;
			cn.setDiaChi(conNguoi.getDiaChi());
			cn.setGioiTinh(conNguoi.getGioiTinh());
			cn.setHoTen(conNguoi.getHoTen());
			cn.setNgaySinh(conNguoi.getNgaySinh());
			cn.setSdt(conNguoi.getSdt());
			conNguoiRepository.save(cn);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteConNguoi(String cmnd) {
		ConNguoi cn = conNguoiRepository.findOne(cmnd);
		if(cn != null)
		{
			conNguoiRepository.delete(cn);
			return true;
		}
		return false;
	}

	@Override
	public List<ConNguoi> getAllConNguoi() {
		
		return conNguoiRepository.findAll();
	}

	@Override
	public ConNguoi getOneConNguoi(String cmnd) {
		
		return conNguoiRepository.findOne(cmnd);
	}

	@Override
	public ConNguoi getOneConNguoiByUID(String uID) {
		NguoiDung nguoiDung = nguoiDungRepository.findOne(uID);
		if(nguoiDung != null)
		{
			return nguoiDung.getCmnd_ConNguoi();
		}
		return null;
	}
	
	@Override
	public ConNguoi getOneConNguoiByUID_NVYTe(String uID)
	{
		NV_Yte nv_Yte = nv_YteRepository.findOne(uID);
		if(nv_Yte != null)
		{
			return nv_Yte.getCmnd_ConNguoi();
		}
		return null;
	}
	
	
}
