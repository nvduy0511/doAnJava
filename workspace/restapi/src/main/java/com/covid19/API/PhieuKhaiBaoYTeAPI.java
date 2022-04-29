package com.covid19.API;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.Entity.PhieuKhaiBaoYTe;
import com.covid19.service.IPhieuKhaiBaoYTeService;

@RestController
public class PhieuKhaiBaoYTeAPI {


	@Autowired
	IPhieuKhaiBaoYTeService iPhieuKhaiBaoYTeService;
	
	@PostMapping("/phieukhaibaoyte/add")
	public boolean addConNguoi(@RequestBody PhieuKhaiBaoYTe  phieuKhaiBaoYTe)
	{
		return iPhieuKhaiBaoYTeService.addPhieuKhaiBaoYTe(phieuKhaiBaoYTe);
	}
	
	@GetMapping("/phieukhaibaoyte/getlistphieukhaibao")
	public List<PhieuKhaiBaoYTe> getListPhieuKhaiBaoYTe(@RequestParam String cmnd)
	{
		return iPhieuKhaiBaoYTeService.getListPhieuKhaiBaoByCmndNguoiKhaiBao(cmnd);
	}
	
	

}
