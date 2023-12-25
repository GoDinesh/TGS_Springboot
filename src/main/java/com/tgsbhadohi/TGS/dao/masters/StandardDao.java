package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgsbhadohi.TGS.entities.masters.Standard;

public interface StandardDao extends JpaRepository<Standard, Long>{
	public List<Standard> findByActive(boolean status);
}
