package com.covid19.service;

import java.util.List;

import com.covid19.Entity.CoSoYTe;

public interface ICoSoYTeService {

	public CoSoYTe addCoSoYTe(CoSoYTe coSoYTe);
	
	public boolean updateCoSoYTe(int macsyt, CoSoYTe coSoYTe);
	
	public boolean deleteCoSoYTe(int macsyt);
	
	public List<CoSoYTe> getAllCoSoYTe();
	
	public CoSoYTe getOneCoSoYTe(int macsyt);
	
	public boolean UndoCoSoYTe(CoSoYTe coSoYTe);
}
