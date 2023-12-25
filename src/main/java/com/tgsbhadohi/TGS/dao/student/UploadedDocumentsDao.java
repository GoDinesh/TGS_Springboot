package com.tgsbhadohi.TGS.dao.student;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgsbhadohi.TGS.entities.masters.UploadedDocuments;
import com.tgsbhadohi.TGS.entities.student.Registration;

@Repository
public interface UploadedDocumentsDao extends JpaRepository<UploadedDocuments, Long> {
    Optional<UploadedDocuments> findByFileNameAndRegistrationId(String fileName, Registration registration);
    
    List<UploadedDocuments> findByRegistrationId(Registration registration);
}
