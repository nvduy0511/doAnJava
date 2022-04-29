package com.covid19.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid19.Entity.NV_Yte;

@Repository
public interface NV_YteRepository extends JpaRepository<NV_Yte, String> {

}
