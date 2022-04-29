package com.covid19.API;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.Entity.ConNguoi;
import com.covid19.service.IConNguoiService;


@RestController
public class NewAPI {

	@Autowired
	IConNguoiService iConNguoiService;
	
	@GetMapping("/test")
	public String testAPI() {
		return "success";
	}
	
	@PostMapping("/connguoi/add")
	public boolean addConNguoi(@RequestBody ConNguoi  conNguoi)
	{
		return iConNguoiService.addConNguoi(conNguoi);
	}
	@GetMapping("/connguoi/getconnguoibyuid")
	public ConNguoi getConNguoiByUID(@RequestParam String uID)
	{
		return iConNguoiService.getOneConNguoiByUID(uID);
	}
//	
	@PutMapping("/connguoi/update")
	public boolean updateSanPham(@RequestParam String cmnd, @RequestBody ConNguoi conNguoi)
	{
		return iConNguoiService.updateConNguoi(cmnd, conNguoi);
	}
//	
//	@DeleteMapping("/delete")
//	public boolean deleteSanPham(@RequestParam long id)
//	{
//		return iSanPhamService.deleteSanPham(id);
//	}
//	
	@GetMapping("/connguoi/getall")
	public List<ConNguoi> getAll()
	{
		return iConNguoiService.getAllConNguoi();
	}
	
	@GetMapping("/connguoi/getone")
	public ConNguoi getOne(@RequestParam String cmnd)
	{
		return iConNguoiService.getOneConNguoi(cmnd);
	}
	
	@GetMapping("/connguoi/getonebyuid")
	public ConNguoi getOneByuID(@RequestParam String uID)
	{
		return iConNguoiService.getOneConNguoiByUID(uID);
	}
	
	@GetMapping("/connguoi/getonebyuidnvyte")
	public ConNguoi getOneByuID_NVYTe(@RequestParam String uID)
	{
		return iConNguoiService.getOneConNguoiByUID_NVYTe(uID);
	}
	
	
}
