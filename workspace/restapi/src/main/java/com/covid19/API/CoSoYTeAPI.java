package com.covid19.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covid19.Entity.CoSoYTe;
import com.covid19.service.ICoSoYTeService;

@RestController
public class CoSoYTeAPI {
	
	@Autowired
	ICoSoYTeService iCoSoYTeService;
	
	@PostMapping("/cosoyte/add")
	public CoSoYTe addCoSoYTe(@RequestBody CoSoYTe cosoyte)
	{
		return iCoSoYTeService.addCoSoYTe(cosoyte);
	}
	
	@GetMapping("/cosoyte/getall")
	public List<CoSoYTe> getAllCoSoYTe()
	{
		return iCoSoYTeService.getAllCoSoYTe();
	}
	
	@GetMapping("/cosoyte/getone")
	public CoSoYTe getOneCoSoYTe(@RequestParam int macsyt)
	{
		return iCoSoYTeService.getOneCoSoYTe(macsyt);
	}
	
	@PutMapping("/cosoyte/updatecsyt")
	public boolean updateCoSoYTe(@RequestParam int macsyt, @RequestBody CoSoYTe coSoYTe)
	{
		return iCoSoYTeService.updateCoSoYTe(macsyt, coSoYTe);
	}
	
	@DeleteMapping("/cosoyte/deletecsyt")
	public boolean deleteCoSoYTe(@ RequestParam int macsyt)
	{
		return iCoSoYTeService.deleteCoSoYTe(macsyt);
	}
	
	@PostMapping("/cosoyte/undocsyt")
	public boolean UndoCoSoYTe(@RequestBody CoSoYTe coSoYTe)
	{
		return iCoSoYTeService.UndoCoSoYTe(coSoYTe);
	}
}
