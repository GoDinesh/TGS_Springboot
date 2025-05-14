package com.tgsbhadohi.TGS.service.student;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.dao.student.RegistrationDao;
import com.tgsbhadohi.TGS.entities.student.Registration;
import com.tgsbhadohi.TGS.service.masters.BookDressFeesService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationImpl implements RegistrationService {

  @PersistenceContext
  EntityManager entityManager;

  @Autowired
  private RegistrationDao registrationDao;

  @Override
  public List<Registration> getAllRegistration(Registration registration) {
    return registrationDao.findAll(Sort.by("studentName"));
  }

  @Override
  public List<Registration> getRegistrationById(Long id) {
    List<Registration> data = new ArrayList<Registration>();
    data.add(registrationDao.findById(id).get());
    return data;
  }

//  @Transactional
  @Override
  public ResponseModel saveRegistration(Registration registration) {
    List<Registration> data = new ArrayList<Registration>();
    ResponseModel res = new ResponseModel();
    if(registration.getRegistrationId()>0) {
    	data.add(registrationDao.save(registration));
		res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
    }else {
		if(registrationDao.countByAadhaarNumber(registration.getAadhaarNumber())>0 && (!registration.getAadhaarNumber().isEmpty())) {
			res = new ResponseModel(Constants.AADHAR_NUMBER_ALREADY_AVAILABLE, Constants.ERROR, true, null); 
		}
//		else if(registrationDao.countByFatherContactNoOrMotherContactNumberOrEmergencyNumber(
//				registration.getFatherContactNo(), registration.getMotherAadharNumber(), registration.getEmergencyNumber())>0) {
//			res = new ResponseModel(Constants.MOBILE_NUMBER_ALREADY_AVAILABLE, Constants.ERROR, true, null); 
//		}
		else {
			data.add(registrationDao.save(registration));
			res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
		}
    }
	return res;
  }
  
  
  @Override
  public ResponseModel savePromotedRegistration(Registration registration) {
    List<Registration> data = new ArrayList<Registration>();
    ResponseModel res = new ResponseModel();
    if(registration.getRegistrationId()>0) {
    	data.add(registrationDao.save(registration));
		res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true, data);
    }else {
			data.add(registrationDao.save(registration));
			res = new ResponseModel(Constants.CREATE_RECORD, Constants.SUCCESS, true, data);
    }
	return res;
  }
  
  

  @Override
  public List<Registration> search(Registration registration) {
    try {
      String query = "SELECT * from registration where";
      if (registration.getStandard().length() > 0) query =
        query + " standard='" + registration.getStandard() + "' and";
      if (registration.getAcademicYearCode().length() > 0) query =
        query +
        " academic_year_code='" +
        registration.getAcademicYearCode() +
        "' and";
      if (registration.getRegistrationNo().length() > 0) query =
        query +
        " registration_no='" +
        registration.getRegistrationNo() +
        "' and";
      if (registration.getFatherContactNo().length() > 0) query =
        query +
        " ( father_contact_no='" +
        registration.getFatherContactNo() +
        "' or mother_contact_number='" +
        registration.getFatherContactNo() +
        "' or emergency_number='" +
        registration.getFatherContactNo() +
        "' ) and";
      if (registration.getStudentName().length() > 0) query =
        query +
        " student_name like '%" +
        registration.getStudentName() +
        "%' and";

      query = query + " 1 order by standard,student_name";
      Query qry = entityManager.createNativeQuery(query, Registration.class);

      List<Registration> studentList = qry.getResultList();
      return studentList;
    } catch (Exception e) {}

    return null;
  }

  @Override
  public Integer getRollNumber(Registration registration) {
    try {
      int rollnumber = registrationDao.getRollNumber(
        registration.getAcademicYearCode(),
        registration.getStandard()
      );
      return rollnumber;
    } catch (Exception e) {}
    return null;
  }
  
  @Override
  public Integer getRegistrationNumber() {
    try {
      int registrationNumber = registrationDao.getRegistrationNumber();
      return registrationNumber;
    } catch (Exception e) {}
    return null;
  }

  @Override
  public List<Registration> filterListByKeyword(String keyword) {
    return registrationDao.filterListByKeyword(keyword);
  }

@Override
public boolean updateStatus(Registration[] studentList) {
	try {
		for (Registration registration : studentList) {
			registrationDao.updateIsActiveByRegistartionId(registration.getRegistrationId());
		}
	} catch (Exception e) {
		return false;
	}
	return true;
	
}

@Override
public boolean updateFeesDetails(Registration registration) {
	try {
			registrationDao.updateFeesDetailsByRegistrationId(registration.getRegistrationId(),
					registration.getPaidFees(),
					registration.getPendingFees(),	
					//registration.getTotalFees(),					
					registration.getIsTotalFeesPaid());
	} catch (Exception e) {
		return false;
	}
	return true;
}

@Override
public boolean updateBookFeesDetails(Registration registration) {
	try {
			registrationDao.updateBookFeesDetailsByRegistrationId(registration.getRegistrationId(),
					registration.getPaidBookFees(),
					registration.getPendingBookFees(),	
					//registration.getTotalFees(),					
					registration.getIsTotalBookFeesPaid());
	} catch (Exception e) {
		return false;
	}
	return true;
}




@Override
@Transactional
public boolean updateStudentDetails(Registration reg) {
	 try {
	      String query = "update registration as r, (select registration_id from registration where registration_no='"+reg.getRegistrationNo()+"') as p set";
	      if (reg.getBloodGroup().length() > 0)
	    	  query +=" r.blood_group ='"+reg.getBloodGroup()+"',";
	      if (reg.getStudentName().length()>0)
	    	  query +=" r.student_name = '"+reg.getStudentName()+"',";	      
	      if (reg.getAadhaarNumber().length() > 0)
	    	  query +=" r.aadhaar_number ='"+reg.getAadhaarNumber()+"',";
	      if (reg.getArea().length() > 0)
	    	  query +=" r.area ='"+reg.getArea()+"',";
	      if (reg.getCategory().length() > 0)
	    	  query +=" r.category='"+reg.getCategory()+"',";
	      if (reg.getCity().length() > 0)
	    	  query +=" r.city='"+reg.getCity()+"',";
	      if (reg.getCountry().length() > 0)
	    	  query +=" r.country='"+reg.getCountry()+"',";
	      if (reg.getDateOfBirth().length() > 0)
	    	  query +=" r.date_of_birth='"+reg.getDateOfBirth()+"',";
	      if (reg.getDateOfAdmission().length() > 0)
	    	  query +=" r.date_of_admission='"+reg.getDateOfAdmission()+"',";
	      if (reg.getEmergencyContactPerson().length() > 0)
	    	  query +=" r.emergency_contact_person='"+reg.getEmergencyContactPerson()+"',";
	      if (reg.getEmergencyNumber().length() > 0)
	    	  query +=" r.emergency_number='"+reg.getEmergencyNumber()+"',";
	      if (reg.getFatherAadharNo().length() > 0)
	    	  query +=" r.father_aadhar_no='"+reg.getFatherAadharNo()+"',";
	      if (reg.getFatherContactNo().length() > 0)
	    	  query +=" r.father_contact_no='"+reg.getFatherContactNo()+"',";
	      if (reg.getFatherEmailId().length() > 0)
	    	  query +=" r.father_email_id='"+reg.getFatherEmailId()+"',";
	      if (reg.getFatherName().length() > 0)
	    	  query +=" r.father_name='"+reg.getFatherName()+"'," ;
	      if (reg.getFatherProfession().length() > 0)
	    	  query +=" r.father_profession='"+reg.getFatherProfession()+"',";
	      if (reg.getFatherQualification().length() > 0)
	    	  query +=" r.father_qualification='"+reg.getFatherQualification()+"',";
	      if (reg.getGender().length() > 0)
	    	  query +=" r.gender='"+reg.getGender()+"',";
	      if (reg.getGuardianName().length() > 0)
	    	  query +=" r.guardian_name='"+reg.getGuardianName()+"',";
	      if (reg.getMotherAadharNumber().length() > 0)
	    	  query +=" r.mother_aadhar_number='"+reg.getMotherAadharNumber()+"',";
	      if (reg.getMotherContactNumber().length() > 0)
	    	  query +=" r.mother_contact_number='"+reg.getMotherContactNumber()+"',";
	      if (reg.getMotherName().length() > 0)
	    	  query +=" r.mother_name='"+reg.getMotherName()+"',";
	      if (reg.getMotherProfession().length() > 0)
	    	  query +=" r.mother_profession='"+reg.getMotherProfession()+"',";
	      if (reg.getPassedClass().length() > 0)
	    	  query +=" r.passed_class='"+reg.getPassedClass()+"',";
	      if (reg.getPassedClassMarks().length() > 0)
	    	  query +=" r.passed_class_marks='"+reg.getPassedClassMarks()+"',";
	      if (reg.getPincode().length() > 0)
	    	  query +=" r.pincode='"+reg.getPincode()+"',";
	      if (reg.getReligion().length() > 0)
	    	  query +=" r.religion='"+reg.getReligion()+"',";
	      if (reg.getSchoolAddress().length() > 0)
	    	  query +=" r.school_address='"+reg.getSchoolAddress()+"',";
	      if (reg.getSchoolAddress().length() > 0)
	    	  query +=" r.school_name='"+reg.getSchoolAddress()+"',";
	      if (reg.getSection().length() > 0)
	    	  query +=" r.section='"+reg.getSection()+"',";
	      if (reg.getState().length() > 0)
	    	  query +=" r.state='"+reg.getState()+"',";
	      if (reg.getStudentName().length() > 0)
	    	  query +=" r.student_name='"+reg.getStudentName()+"',";
	      if (reg.getTcNumber().length() > 0)
	    	  query +=" r.tc_number='"+reg.getTcNumber()+"'," ;
	      if (reg.getBirthCirtificateSubmitted()!=null) {
	    	  if(reg.getBirthCirtificateSubmitted().length() > 0)
	    	  query +=" r.birth_cirtificate_submitted='"+reg.getBirthCirtificateSubmitted()+"'," ;
	      }
	      
	      query +=" r.temp='' where r.registration_id=p.registration_id";
	      
	      Query qry = entityManager.createNativeQuery(query, Registration.class);
	      int status = qry.executeUpdate();
	      System.out.println(status);
	      if(status==0)
	    	  return false;
	      else
	    	  return true;
	      
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

	    return false;
}
}
