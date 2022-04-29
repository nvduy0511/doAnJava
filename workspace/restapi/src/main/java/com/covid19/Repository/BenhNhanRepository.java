package com.covid19.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.covid19.Entity.BenhNhan;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, Integer> {

	@Query("select u from BenhNhan u where u.ngayPhatHien = ?1")
	List<BenhNhan> getAllBenhNhanByNgay(String date);
	
	@Query("select u from BenhNhan u where u.maCSYT_BenhNhan.maCSYT = ?1")
	BenhNhan findByCSYT_BenhNhan(int macsyt);
}
