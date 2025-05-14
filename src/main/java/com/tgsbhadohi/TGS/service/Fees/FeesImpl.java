package com.tgsbhadohi.TGS.service.Fees;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tgsbhadohi.TGS.dao.fees.FeesDao;
import com.tgsbhadohi.TGS.dao.masters.StandardDao;
import com.tgsbhadohi.TGS.entities.fees.Fees;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesInstallment;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesStructure;
import com.tgsbhadohi.TGS.entities.masters.Standard;
import com.tgsbhadohi.TGS.entities.student.Registration;
import com.tgsbhadohi.TGS.service.student.RegistrationService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class FeesImpl implements FeesService {
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	private FeesDao feesDao;
	
	@Autowired
	private StandardDao standardDao;
	
	@Autowired
	private RegistrationService registrationService;
	
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
		if(fees.getPaymenttype().length()>0)
			query = query +" paymenttype='"+fees.getPaymenttype()+"' and";
		if(fees.getStartDate().length()>0 && !fees.getStartDate().equalsIgnoreCase("Invalid date"))
			query = query +" payment_date>='"+fees.getStartDate()+"' and";
		if(fees.getEndDate().length()>0 && !fees.getEndDate().equalsIgnoreCase("Invalid date"))
			query = query +" payment_date<='"+fees.getEndDate()+"' and";
		
				
		query= query+" 1";
		Query qry = entityManager.createNativeQuery(query,Fees.class);

		List<Fees> feesList = qry.getResultList();
        return feesList;
	}catch (Exception e) {}
		
	return null;
	}
	
	
	@Override
	public List<Fees> todayFeesCollection(Fees fees) {
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
		if(fees.getPaymenttype().length()>0)
			query = query +" paymenttype='"+fees.getPaymenttype()+"' and";
		if(fees.getStartDate().length()>0 && !fees.getStartDate().equalsIgnoreCase("Invalid date"))
			query = query +" CAST(created_on as DATE)>='"+fees.getStartDate()+"' and";
		if(fees.getEndDate().length()>0 && !fees.getEndDate().equalsIgnoreCase("Invalid date"))
			query = query +" CAST(created_on as DATE)<='"+fees.getEndDate()+"' and";
		
				
		query= query+" 1";
		System.out.println(query);
		Query qry = entityManager.createNativeQuery(query,Fees.class);

		List<Fees> feesList = qry.getResultList();
        return feesList;
	}catch (Exception e) {}
		
	return null;
	}


	@Override
	public String getReceiptNo() {
		List<Fees> list = new ArrayList<Fees>();
		Fees fees = new Fees();
		list = feesDao.findTopByOrderByIdDesc();
		if(list.isEmpty())
			return "1";
		else {
			fees = list.get(0);
			Long id = fees.getId();
			return ""+(id+1);
		}
	}

	@Override
	public List<Fees> filterFeesByReceiptNumber(String receiptNo) {
		return feesDao.findByReceiptNo(receiptNo);	
		
	}

	@Override
	public List<Registration> getPendingFees(Registration registration){
		try {
		List<Registration> regList = new ArrayList<Registration>();
		List<Registration> pendingFeesList = new ArrayList<Registration>();
		int year = 0; 
		String month = registration.getTemp();
		if(Integer.parseInt(month)>3) {
			year = Integer.parseInt(registration.getAcademicYearCode().substring(0,4));
		}else {
			year = Integer.parseInt(registration.getAcademicYearCode().substring(4,8));
		}
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, Integer.parseInt(month));
			int numDays = calendar.getActualMaximum(Calendar.DATE);
			
		Date date1= simpleDateFormat.parse(year+"-"+month+"-"+numDays);
		 
		double totalInstallment = 0;
		int totalDiscount = 0;
		double totalPaidFees = 0;
		double pendingFees = 0;
		regList = registrationService.search(registration);
		for (Registration reg : regList) {
			List<StudentFeesInstallment> installmentList =  reg.getStudentFeesStructure().get(0).getStudentFeesInstallment();
			totalInstallment = 0;
			totalDiscount = 0;
			totalPaidFees = 0;
			pendingFees =0;
			for (StudentFeesInstallment installment : installmentList) {
				
				Date date2=simpleDateFormat.parse(installment.getInstallmentDate());
				if(date1.after(date2) || date1.equals(date2)) {
					totalInstallment = totalInstallment + installment.getInstallmentAmount();
					if(installment.getInstallmentDiscount()!=null)
						totalDiscount = totalDiscount + installment.getInstallmentDiscount();
					}
					if(installment.getDiscountAmount()!=null) {
						totalDiscount = totalDiscount + installment.getDiscountAmount();
					}
			}
			//reg.setTotalFees(totalInstallment);
			reg.setTemp(""+(int) Math.round(totalInstallment));
			reg.setDiscountAmount(totalDiscount);
			
//			Get Paid Amount on selected month
			Fees fees =new Fees();
			fees.setAcademicYearCode(reg.getAcademicYearCode());
			fees.setClassCode(reg.getStandard());
			fees.setRegistrationNo(reg.getRegistrationNo());
			if(month.equals("03")) {
				fees.setEndDate(year+"-"+12+"-"+31);
			}else {
				fees.setEndDate(year+"-"+month+"-"+numDays);
			}
			fees.setPaymentMode("");
			fees.setStartDate("");
			fees.setPaymenttype("Fees");
			List<Fees> paidFees = search(fees);
			if(paidFees!=null) {
					for (Fees feesPaid : paidFees) {
						totalPaidFees = totalPaidFees + feesPaid.getAmount();
					}
			}
			reg.setPaidFees(totalPaidFees);
			pendingFees = ((totalInstallment-totalDiscount)-totalPaidFees);
			reg.setPendingFees(pendingFees);
			if(pendingFees>0) {
				pendingFeesList.add(reg);
			}
			
		}
		return pendingFeesList;
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;	
	}
	
	
	@Override
	public List<String> getTotalPendingFeesClassWise(Registration registration){
		try {
		List<Registration> regList = new ArrayList<Registration>();
		List<String> pendingFeesList = new ArrayList<String>();
		int year = 0; 
		String month = registration.getTemp();
		if(Integer.parseInt(month)>3) {
			year = Integer.parseInt(registration.getAcademicYearCode().substring(0,4));
		}else {
			year = Integer.parseInt(registration.getAcademicYearCode().substring(4,8));
		}
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, Integer.parseInt(month));
			int numDays = calendar.getActualMaximum(Calendar.DATE);
			
		Date date1= simpleDateFormat.parse(year+"-"+month+"-"+numDays);
		
		List<Standard> classlist = standardDao.findByActive(true);
		for (Standard standard : classlist) {
			registration.setStandard(standard.getClassCode());
		
					double totalInstallment = 0;
					int totalDiscount = 0;
					double totalPaidFees = 0;
					double pendingFees = 0;
					
					regList = registrationService.search(registration);
					for (Registration reg : regList) {
						List<StudentFeesInstallment> installmentList =  reg.getStudentFeesStructure().get(0).getStudentFeesInstallment();
						for (StudentFeesInstallment installment : installmentList) {
							
							Date date2=simpleDateFormat.parse(installment.getInstallmentDate());
							if(date1.after(date2) || date1.equals(date2)) {
								totalInstallment = totalInstallment + installment.getInstallmentAmount();
								if(installment.getInstallmentDiscount()!=null)
									totalDiscount = totalDiscount + installment.getInstallmentDiscount();
								}
								if(installment.getDiscountAmount()>0) {
									totalDiscount = totalDiscount + installment.getDiscountAmount();
								}
						}
						Fees fees =new Fees();
						fees.setAcademicYearCode(reg.getAcademicYearCode());
						fees.setClassCode(reg.getStandard());
						fees.setRegistrationNo(reg.getRegistrationNo());
						fees.setEndDate(year+"-"+month+"-"+numDays);
						fees.setPaymentMode("");
						fees.setStartDate("");
						fees.setPaymenttype("Fees");
						List<Fees> paidFees = search(fees);
						if(paidFees!=null) {
								for (Fees feesPaid : paidFees) {
									totalPaidFees = totalPaidFees + feesPaid.getAmount();
								}
						}
						
					}
					pendingFees = ((totalInstallment-totalDiscount)-totalPaidFees);
					pendingFeesList.add(standard.getClassName()+"#"+pendingFees);
		}
		if(!pendingFeesList.isEmpty())
			return pendingFeesList;
		
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;	
	}
	
}
