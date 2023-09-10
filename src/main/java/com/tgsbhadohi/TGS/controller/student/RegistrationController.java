package com.tgsbhadohi.TGS.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.FileUploadHelper;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.student.Registration;
import com.tgsbhadohi.TGS.service.student.RegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/studentList")
	private ResponseEntity<ResponseModel> getAllRegistration(@RequestBody Registration registration){
		System.out.println(registration);
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,registrationService.getAllRegistration(registration));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<ResponseModel> getRegistrationById(@PathVariable String id){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,registrationService.getRegistrationById(Long.parseLong(id)));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/registration")
	private ResponseEntity<ResponseModel> saveRegistration(@Valid @RequestBody Registration registration) {
		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true ,registrationService.saveRegistration(registration));
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PostMapping("/upload-image")
	private ResponseEntity<ResponseModel> uploadImage(@RequestParam("profileImage") MultipartFile file) {
		try {
			boolean flag = fileUploadHelper.uploadfile(file);
			if(flag) {
				String urlString = fileUploadHelper.generatelinkForImage(file);
				ResponseModel res = new ResponseModel(urlString,Constants.SUCCESS, true ,null);
				return new ResponseEntity<>(res, HttpStatus.CREATED);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		
		ResponseModel res = new ResponseModel(Constants.ERROR,Constants.ERROR, true ,null);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/filter")
	private ResponseEntity<ResponseModel> filterRegistration(@RequestBody Registration registration){
		ResponseModel res = new ResponseModel(Constants.GET_RECORD,Constants.SUCCESS, false ,registrationService.search(registration));
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	

}
