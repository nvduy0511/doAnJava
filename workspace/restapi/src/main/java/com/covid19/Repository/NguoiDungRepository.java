package com.covid19.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid19.Entity.NguoiDung;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {

}
