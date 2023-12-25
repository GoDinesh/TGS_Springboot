package com.tgsbhadohi.TGS.service.Fees;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.fees.FeesDao;
import com.tgsbhadohi.TGS.entities.fees.Fees;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class FeesImpl implements FeesService {
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	private FeesDao feesDao;
	
	@Override
	public List<Fees> getAllFees() {
		return feesDao.findAll(Sort.by("id")); 
	}

	@Override
	public List<Fees> saveFees(Fees fees) {
		List<Fees> data = new ArrayList<Fees>();
		data.add(feesDao.save(fees));
		return data;
	}

	@Override
	public List<Fees> findByRegistrationNo(Fees fees) {
		return feesDao.findByRegistrationNo(fees.getRegistrationNo());
	}
	
	@Override
	public List<Fees> search(Fees fees) {
	try {
		String query = "SELECT * from fees where";
		if(fees.getAcademicYearCode().length()>0)
			query = query + " academic_year_code='"+fees.getAcademicYearCode()+"' and";
		if(fees.getClassCode().length()>0)
			query = query +" class_code='"+fees.getClassCode()+"' and";	
		if(fees.getRegistrationNo().length()>0)
			query = query +" registration_no='"+fees.getRegistrationNo()+"' and";
		if(fees.getPaymentMode().length()>0)
			query = query +" payment_mode='"+fees.getPaymentMode()+"' and";
				
		query= query+" 1";
		Query qry = entityManager.createNativeQuery(query,Fees.class);

		List<Fees> feesList = qry.getResultList();
        return feesList;
	}catch (Exception e) {}
		
	return null;
	}

}
