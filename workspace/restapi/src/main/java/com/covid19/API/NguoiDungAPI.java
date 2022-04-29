package com.covid19.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.Entity.NguoiDung;
import com.covid19.service.INguoiDungService;

@RestController
public class NguoiDungAPI {

	@Autowired
	INguoiDungService iNguoiDungService;
	
	@GetMapping("/nguoidung/getone")
	public NguoiDung getOneNguoiDung(@RequestParam String uID)
	{
		return iNguoiDungService.getOneNguoiDung(uID);
	}
	
	@PostMapping("/nguoidung/add")
	public boolean addNguoiDung(@RequestBody NguoiDung nguoiDung)
	{
		return iNguoiDungService.addNguoiDung(nguoiDung);
	}
}
