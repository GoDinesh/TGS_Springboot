package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgsbhadohi.TGS.entities.masters.Subject;

public interface SubjectDao extends JpaRepository<Subject, Long>{
	public List<Subject> findByActive(boolean status);
	public Integer countBySubject(String subject);
}