package com.tgsbhadohi.TGS.dao.masters;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tgsbhadohi.TGS.entities.masters.UploadedProfileImage;
import com.tgsbhadohi.TGS.entities.student.Registration;

public interface UploadProfileImageDao extends JpaRepository<UploadedProfileImage, Long>{

	    UploadedProfileImage findByRegistrationId(Registration registration);
}
