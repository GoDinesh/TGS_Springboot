package com.tgsbhadohi.TGS.dao.student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.student.Registration;

@Repository
public interface RegistrationDao extends JpaRepository<Registration, Long>{
}
