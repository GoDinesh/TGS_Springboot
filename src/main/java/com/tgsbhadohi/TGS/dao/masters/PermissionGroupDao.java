package com.tgsbhadohi.TGS.dao.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.masters.PermissionGroup;
@Repository
public interface PermissionGroupDao extends JpaRepository<PermissionGroup, Long> {

}
