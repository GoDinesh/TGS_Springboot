package com.tgsbhadohi.TGS.dao.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tgsbhadohi.TGS.entities.masters.Installment;

public interface InstallmentDao extends JpaRepository<Installment, Long> {
}
