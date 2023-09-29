package com.tgsbhadohi.TGS.dao.authorization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.authorization.PermissionGroup;
@Repository
public interface PermissionGroupDao extends JpaRepository<PermissionGroup, Long> {

}
