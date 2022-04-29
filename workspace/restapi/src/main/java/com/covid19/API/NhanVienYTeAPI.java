package com.covid19.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.Entity.NV_Yte;
import com.covid19.service.INV_YTeService;

@RestController
public class NhanVienYTeAPI {
	
	@Autowired
	INV_YTeService inv_YTeService;
	
	@GetMapping("/nvyte/getonebyuID")
	public NV_Yte getOneNVYTeByUID(@RequestParam String uID)
	{
		return inv_YTeService.getOneNV_Yte(uID);
	}
}
