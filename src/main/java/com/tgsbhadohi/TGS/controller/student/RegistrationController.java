package com.tgsbhadohi.TGS.controller.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgsbhadohi.TGS.classes.Constants;
//import com.tgsbhadohi.TGS.classes.FileUploadHelper;
import com.tgsbhadohi.TGS.classes.FileUploadHelper;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesInstallment;
import com.tgsbhadohi.TGS.entities.fees.StudentFeesStructure;
import com.tgsbhadohi.TGS.entities.masters.BookDressFees;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;
import com.tgsbhadohi.TGS.entities.masters.Installment;
import com.tgsbhadohi.TGS.dao.student.UploadedDocumentsDao;
import com.tgsbhadohi.TGS.entities.masters.UploadedDocuments;
import com.tgsbhadohi.TGS.entities.masters.UploadedProfileImage;
import com.tgsbhadohi.TGS.entities.student.Registration;
import com.tgsbhadohi.TGS.service.masters.BookDressFeesService;
import com.tgsbhadohi.TGS.service.masters.FeesStructureService;
import com.tgsbhadohi.TGS.service.student.RegistrationService;

import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private UploadedDocumentsDao uploadedDocumentsDao;

	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@Autowired
	private FeesStructureService feesStructureService;
	
	@Autowired
	private BookDressFeesService bookDressFeesService; 
	
	@PostMapping("/studentList")
	private ResponseEntity<ResponseModel> getAllRegistration(@RequestBody Registration registration){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,registrationService.getAllRegistration(registration));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	private ResponseEntity<ResponseModel> getRegistrationById(@PathVariable String id) {
		ResponseModel res = new ResponseModel(Constants.GET_RECORD, Constants.SUCCESS, false,
				registrationService.getRegistrationById(Long.parseLong(id)));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

//	@PostMapping("/registration")
//	private ResponseEntity<ResponseModel> saveRegistration(@Valid @RequestBody Registration registration) {
//		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true ,registrationService.saveRegistration(registration));
//		return new ResponseEntity<>(res, HttpStatus.CREATED);
//	}

	@PostMapping("/upload-image")
	private ResponseEntity<ResponseModel> uploadImage(@RequestParam(name="profileImage", required=false) MultipartFile file,
			@RequestParam("requestData") String reqData,
			@RequestParam(name="documentUpload[]", required=false) MultipartFile[] documentUpload) {

		final Registration tempRegistration;
		Integer installmentDiscount = 0;
		Registration registration = new Registration();
		try {

			//change string to Registration object
			ObjectMapper mapper = new ObjectMapper(); 
			registration = mapper.readValue(reqData, Registration.class);
			tempRegistration = registration;
			
			if(registration.getRegistrationId()==0){
						FeesStructure feesStructure = new FeesStructure();
						feesStructure.setAcademicYearCode(registration.getAcademicYearCode());
						feesStructure.setClassCode(registration.getStandard());
						feesStructure.setEnrollmentType(registration.getEnrollmentType());
						
						List<FeesStructure> feeStructureList = new ArrayList<>();
						feeStructureList = feesStructureService.getFeeStructureById(feesStructure);
						if(!feeStructureList.isEmpty()) {
			
								feesStructure = feeStructureList.get(0);
								
								StudentFeesStructure studentFeesStructure =new StudentFeesStructure();
								studentFeesStructure.setStudentFeeStructureId(0);
								studentFeesStructure.setRegistrationId(registration);
								studentFeesStructure.setClassCode(feesStructure.getClassCode());
								studentFeesStructure.setEnrollmentType(feesStructure.getEnrollmentType());
								studentFeesStructure.setAcademicYearCode(feesStructure.getAcademicYearCode());
								studentFeesStructure.setPaymentType("INSTALLMENT");	
								studentFeesStructure.setTotalFees(feesStructure.getTotalFees());
								studentFeesStructure.setDiscountReasonCode(feesStructure.getDiscountReasonCode());
								studentFeesStructure.setDiscountAmount(feesStructure.getDiscountAmount());
								studentFeesStructure.setNetAmountAfterDiscount(feesStructure.getNetAmountAfterDiscount());
		//						studentFeesStructure.setRegistrationFees(feesStructure.getRegistrationFees());
		//						studentFeesStructure.setAnnualFees(feesStructure.getAnnualFees());
		//						studentFeesStructure.setAnnualFeesDate(feesStructure.getAnnualFeesDate());
								//studentFeesStructure.setRegFeesDiscount(feesStructure.getRegFeesDiscount());
								//studentFeesStructure.setRegFeesDiscountReason(feesStructure.getRegFeesDiscountReason());
								studentFeesStructure.setActive(feesStructure.isActive());
								
								
								List<StudentFeesInstallment> studentFeesInstallmentsList = new ArrayList<>();
								for (Installment installment : feesStructure.getInstallment()) {
									try {
										if(installment.getInstallmentDiscount().toString().length()>0) {
											if(installment.getInstallmentDiscount()>0) {
												installmentDiscount=installmentDiscount+ installment.getInstallmentDiscount();
											}
										}
									}catch(Exception ex) {
										System.out.println("ex-"+ex);
									}
									
									StudentFeesInstallment studentFeesInstallment = new StudentFeesInstallment();
									studentFeesInstallment.setId(0);
									studentFeesInstallment.setStudentFeeStructureId(studentFeesStructure);
									studentFeesInstallment.setClassCode(installment.getClassCode());
									studentFeesInstallment.setAcademicYearCode(installment.getAcademicYearCode());
									studentFeesInstallment.setInstallmentNumber(installment.getInstallmentNumber());
									studentFeesInstallment.setInstallmentDate(installment.getInstallmentDate());
									studentFeesInstallment.setInstallmentAmount(installment.getInstallmentAmount());
									studentFeesInstallment.setInstallmentType(installment.getInstallmentType());
									studentFeesInstallment.setInstallmentDiscount(installment.getInstallmentDiscount());
									studentFeesInstallment.setInstallmentAmountAfterDiscount(installment.getInstallmentAmountAfterDiscount());
									studentFeesInstallment.setDiscountReason("");
									studentFeesInstallment.setDiscountAmount(0);
									
									studentFeesInstallmentsList.add(studentFeesInstallment);
								}
								
								studentFeesStructure.setStudentFeesInstallment(studentFeesInstallmentsList);
								
								List<StudentFeesStructure> studentFeesStructureList =new ArrayList<>();
								studentFeesStructureList.add(studentFeesStructure);
								
								
								
								
								registration.setStudentFeesStructure(studentFeesStructureList);
								//add fees related details
								registration.setTotalFees(feesStructure.getTotalFees());
								//registration.setPendingFees(feesStructure.getTotalFees());
								registration.setPendingFees(feesStructure.getTotalFees()-installmentDiscount);
								registration.setDiscountAmount(installmentDiscount);
					}
			}

			// delete document
			try {
				if(documentUpload!=null) {
				// Fetch existing documents for this registration from the database
				List<UploadedDocuments> existingDocuments = uploadedDocumentsDao
						.findByRegistrationId(tempRegistration);

				// Convert uploaded documents to a set for easy comparison
				Set<String> uploadedDocsSet = Arrays.stream(documentUpload).map(MultipartFile::getOriginalFilename)
						.collect(Collectors.toSet());

				// Identify documents to be deleted
				List<UploadedDocuments> documentsToDelete = existingDocuments.stream()
						.filter(doc -> !uploadedDocsSet.contains(doc.getFileName())).collect(Collectors.toList());

				// Delete documents from directory and database
				documentsToDelete.forEach(doc -> {
					// Delete from directory
					boolean isDeleted = fileUploadHelper.deleteFile(doc, tempRegistration);
					System.out.println("deleted from directory");
					
					// If deletion from directory is successful, delete from database
					if (isDeleted) {
						System.out.println("not deleted from database");
						uploadedDocumentsDao.delete(doc);
					}
				});
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception caught during database query");
			}

			if(documentUpload!=null) {
			// Upload document;
			List<UploadedDocuments> documentList = new ArrayList<UploadedDocuments>();
			Arrays.asList(documentUpload).stream().forEach(doc -> {
				UploadedDocuments document = new UploadedDocuments();
				try {
					// Check if the document already exists in the database
					Optional<UploadedDocuments> existingDoc = uploadedDocumentsDao
							.findByFileNameAndRegistrationId(doc.getOriginalFilename(), tempRegistration);

//					if (existingDoc.isEmpty()) {
//						boolean flag = fileUploadHelper.uploadfile(doc, tempRegistration, false);
//						if (flag) {
//							document.setLink(fileUploadHelper.generatelinkForImage(doc, tempRegistration, false));
//							document.setFileName(doc.getOriginalFilename());
//							document.setRegistrationId(tempRegistration);
//							documentList.add(document);
//						}
//					} else {
//						// Document already exists in the database; no need to upload and save details.
//						System.out.println("already exists");
//					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Exception caught during database query");
				}
			});
		

			// Only update the documents list in the Registration object if new documents
			// were uploaded.
			if (!documentList.isEmpty()) {
				registration.setDocuments(documentList);
			}
			}
			
			
			// Upload profile image
			if(file!=null) {
			UploadedProfileImage uploadedProfileImage = new UploadedProfileImage();
			boolean flag = fileUploadHelper.uploadfile(file, tempRegistration, true);
			if (flag) {
				String urlString = fileUploadHelper.generatelinkForImage(file, tempRegistration, true);
				uploadedProfileImage.setLink(urlString);
				uploadedProfileImage.setFileName(file.getOriginalFilename());
				uploadedProfileImage.setRegistrationId(registration);

				registration.setProfileImage(uploadedProfileImage);
			}
			}

			//registrationService.saveRegistration(registration);
			 return new ResponseEntity<>(registrationService.saveRegistration(registration), HttpStatus.CREATED);

		}catch(Exception ex) {
			System.out.println(ex);
//			ex.printStackTrace();
		}
		
		ResponseModel res = new ResponseModel(Constants.FAILURE,Constants.ERROR, true ,null);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PostMapping("/filter")
	private ResponseEntity<ResponseModel> filterRegistration(@RequestBody Registration registration) {
		ResponseModel res = new ResponseModel(Constants.GET_RECORD, Constants.SUCCESS, false,
				registrationService.search(registration));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/dropout-list")
	private ResponseEntity<ResponseModel> dropoutList(@RequestBody Registration registration) {
		ResponseModel res = new ResponseModel(Constants.GET_RECORD, Constants.SUCCESS, false,
				registrationService.dropoutList(registration));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/get-rollnumber")
	private ResponseEntity<ResponseModel> getRollNumber(@RequestBody Registration registration) {
		int rollnumber = registrationService.getRollNumber(registration);
		List<Registration> regList = new ArrayList<Registration>();
		Registration reg = new Registration();
		
		reg.setRollNumber(rollnumber);
		regList.add(reg);

		ResponseModel res = new ResponseModel(Constants.GET_RECORD, Constants.SUCCESS, false, regList);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/get-max-registration-number")
	private ResponseEntity<ResponseModel> getMaxRegistrationNumber() {
		int rollnumber = registrationService.getRegistrationNumber();
		List<Registration> regList = new ArrayList<Registration>();
		Registration reg = new Registration();
		
		reg.setRollNumber(rollnumber);
		regList.add(reg);

		ResponseModel res = new ResponseModel(Constants.GET_RECORD, Constants.SUCCESS, false, regList);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/filter-by-keyword")
	private ResponseEntity<ResponseModel> filterListByKeyword(@RequestBody String inputString) {
		ResponseModel res = new ResponseModel(Constants.GET_RECORD, Constants.SUCCESS, false,
				registrationService.filterListByKeyword(inputString));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/promote-student")
	private ResponseEntity<ResponseModel> promoteStudent(@RequestBody Registration promotedStudent[]) {
		for (Registration registration : promotedStudent) {
			try {
				//if(registration.getRegistrationId()!=0){
				Integer installmentDiscount = 0;
				
						FeesStructure feesStructure = new FeesStructure();
						feesStructure.setAcademicYearCode(registration.getAcademicYearCode());
						feesStructure.setClassCode(registration.getStandard());
						feesStructure.setEnrollmentType("Old Student");
						
						BookDressFees bookDressFees = new BookDressFees();
						bookDressFees.setAcademicYearCode(registration.getAcademicYearCode());
						bookDressFees.setStandard(registration.getStandard());
						
						List<FeesStructure> feeStructureList = new ArrayList<>();
						feeStructureList = feesStructureService.getFeeStructureById(feesStructure);
						
						List<BookDressFees> bookDressFeesList = new ArrayList<>();
						bookDressFeesList = bookDressFeesService.findByAcademicYearCodeAndStandard(bookDressFees);
						
						if(!feeStructureList.isEmpty() && !bookDressFeesList.isEmpty()) {
										feesStructure = feeStructureList.get(0);
										
										StudentFeesStructure studentFeesStructure =new StudentFeesStructure();
										studentFeesStructure.setStudentFeeStructureId(0);
										studentFeesStructure.setRegistrationId(registration);
										studentFeesStructure.setClassCode(feesStructure.getClassCode());
										studentFeesStructure.setEnrollmentType(feesStructure.getEnrollmentType());
										studentFeesStructure.setAcademicYearCode(feesStructure.getAcademicYearCode());
										studentFeesStructure.setPaymentType("INSTALLMENT");	
										studentFeesStructure.setTotalFees(feesStructure.getTotalFees());
										studentFeesStructure.setDiscountReasonCode(feesStructure.getDiscountReasonCode());
										studentFeesStructure.setDiscountAmount(feesStructure.getDiscountAmount());
										studentFeesStructure.setNetAmountAfterDiscount(feesStructure.getNetAmountAfterDiscount());
										//studentFeesStructure.setRegistrationFees(feesStructure.getRegistrationFees());
										//studentFeesStructure.setRegistrationFees(0);
										//studentFeesStructure.setAnnualFees(feesStructure.getAnnualFees());
										//studentFeesStructure.setAnnualFeesDate(feesStructure.getAnnualFeesDate());
										//studentFeesStructure.setRegFeesDiscount(feesStructure.getRegFeesDiscount());
										//studentFeesStructure.setRegFeesDiscountReason(feesStructure.getRegFeesDiscountReason());
										studentFeesStructure.setActive(feesStructure.isActive());
										
										
										List<StudentFeesInstallment> studentFeesInstallmentsList = new ArrayList<>();
										for (Installment installment : feesStructure.getInstallment()) {
											try {
												if(installment.getInstallmentDiscount()!=null) {
													if(installment.getInstallmentDiscount().toString().length()>0) {
														if(installment.getInstallmentDiscount()!=null) {
														if(installment.getInstallmentDiscount()>0) {
														installmentDiscount = installmentDiscount+installment.getInstallmentDiscount();
														}
														}
													}
												}
											}catch(Exception ex) {
												System.out.println(ex);
											}
											
											StudentFeesInstallment studentFeesInstallment = new StudentFeesInstallment();
											studentFeesInstallment.setId(0);
											studentFeesInstallment.setStudentFeeStructureId(studentFeesStructure);
											studentFeesInstallment.setClassCode(installment.getClassCode());
											studentFeesInstallment.setAcademicYearCode(installment.getAcademicYearCode());
											studentFeesInstallment.setInstallmentNumber(installment.getInstallmentNumber());
											studentFeesInstallment.setInstallmentDate(installment.getInstallmentDate());
											studentFeesInstallment.setInstallmentAmount(installment.getInstallmentAmount());
											studentFeesInstallment.setInstallmentType(installment.getInstallmentType());
											studentFeesInstallment.setInstallmentDiscount(installment.getInstallmentDiscount());
											studentFeesInstallment.setInstallmentAmountAfterDiscount(installment.getInstallmentAmountAfterDiscount());
											studentFeesInstallment.setDiscountReason("");
											studentFeesInstallment.setDiscountAmount(0);
											
											studentFeesInstallmentsList.add(studentFeesInstallment);
										}
										
										studentFeesStructure.setStudentFeesInstallment(studentFeesInstallmentsList);
										
										List<StudentFeesStructure> studentFeesStructureList =new ArrayList<>();
										studentFeesStructureList.add(studentFeesStructure);
										
										registration.setStudentFeesStructure(studentFeesStructureList);
										registration.setIsChecked(false);
										registration.setPaidFees(0);
										registration.setIsTotalFeesPaid(false);
										registration.setEnrollmentType("Old Student");
										
										//add fees related details
										double totalfees = feesStructure.getTotalFees();
										registration.setTotalFees(totalfees);
										registration.setPendingFees(totalfees - installmentDiscount);
										registration.setDiscountAmount(installmentDiscount);
										
										//add ssm related details
										BookDressFees bookdressFees = new BookDressFees();
										bookdressFees = bookDressFeesList.get(0);
										registration.setBookFees(bookdressFees.getBookFees());
										registration.setPaidBookFees(0);
										registration.setPendingBookFees(bookdressFees.getBookFees());
										registration.setIsTotalBookFeesPaid(false);
										
										//School Related data
										registration.setSchoolName("Time Global School Bhadohi");
										registration.setSchoolAddress("INDRA GANDHI MARG, CHAKSAIF BHADOHI");
										
										
										
										//generate new roll number
										int rollnumber = registrationService.getRollNumber(registration);
										registration.setRollNumber(rollnumber);
										
								//}
										//System.out.println(registration);
										registrationService.savePromotedRegistration(registration);
						}else {
							ResponseModel res = new ResponseModel(Constants.FEES_STRUCTURE_OR_SSM_FEES_NOT_AVAILABLE,Constants.ERROR, true ,null);
							return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
						}
			}catch(Exception ex) {
				System.out.println(ex);
				ResponseModel res = new ResponseModel(Constants.ERROR,Constants.ERROR, true ,null);
				return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
			}
	}
	ResponseModel res = new ResponseModel(Constants.STUDENT_PROMOTED,Constants.SUCCESS, true ,null);
	return new ResponseEntity<>(res, HttpStatus.CREATED);	
}
	
	
	@PostMapping("/update-status-as-inactive")
	private ResponseEntity<ResponseModel> updateStatusAsInactive(@RequestBody Registration promotedStudent[]) {
		boolean flag = registrationService.updateStatus(promotedStudent);
		if(flag) {
			ResponseModel res = new ResponseModel(Constants.UPDATE_STATUS, Constants.SUCCESS, false,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}else {
			ResponseModel res = new ResponseModel(Constants.ERROR, Constants.ERROR, false,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/update-fees-details")
	private ResponseEntity<ResponseModel> updateFeesDetails(@RequestBody Registration registration) {
		boolean flag = registrationService.updateFeesDetails(registration);
		if(flag) {
			ResponseModel res = new ResponseModel(Constants.SUCCESS, Constants.SUCCESS, false,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}else {
			ResponseModel res = new ResponseModel(Constants.ERROR, Constants.ERROR, false,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	
	@PostMapping("/update-book-fees-details")
	private ResponseEntity<ResponseModel> updateBookFeesDetails(@RequestBody Registration registration) {
		boolean flag = registrationService.updateBookFeesDetails(registration);
		if(flag) {
			ResponseModel res = new ResponseModel(Constants.SUCCESS, Constants.SUCCESS, false,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}else {
			ResponseModel res = new ResponseModel(Constants.ERROR, Constants.ERROR, false,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	
	
	
	@PostMapping("/update-student-details")
	private ResponseEntity<ResponseModel> updateStudentDetails(@RequestBody Registration registration) {
		boolean flag = registrationService.updateStudentDetails(registration);
		if(flag) {
			ResponseModel res = new ResponseModel(Constants.UPDATE_RECORD, Constants.SUCCESS, true,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}else {
			ResponseModel res = new ResponseModel(Constants.ERROR, Constants.ERROR, true,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	
	@PostMapping("/dropout_the_student")
	private ResponseEntity<ResponseModel> dropoutStudent(@RequestBody Registration registration) {
		boolean flag = registrationService.dropout(registration);
		if(flag) {
			ResponseModel res = new ResponseModel(Constants.DROPOUT_SUCCESSFULLY, Constants.SUCCESS, true,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}else {
			ResponseModel res = new ResponseModel(Constants.ERROR, Constants.ERROR, true,null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	
	
	

}
