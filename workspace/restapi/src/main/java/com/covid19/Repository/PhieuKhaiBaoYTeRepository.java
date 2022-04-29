package com.covid19.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.covid19.Entity.PhieuKhaiBaoYTe;

@Repository
public interface PhieuKhaiBaoYTeRepository extends JpaRepository<PhieuKhaiBaoYTe, Integer> {

	@Query("select u from PhieuKhaiBaoYTe u where u.cmnd_NguoiKhaiBao = ?1")
	List<PhieuKhaiBaoYTe> findByCmnd_NguoiKhaiBao(String cmnd);
}
