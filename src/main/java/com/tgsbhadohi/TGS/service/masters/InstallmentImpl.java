package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.entities.masters.Installment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class InstallmentImpl implements InstallmentService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void deleteByFeeStructureId(long feeStructureId) {
		try {
			String query = "Delete from installment where fee_structure_id="+feeStructureId+"";
			Query qry = entityManager.createNativeQuery(query,Installment.class);
			int result = qry.executeUpdate();	        
		}catch (Exception e) {}
	}

}
