package com.tgsbhadohi.TGS.controller.masters;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.User;
import com.tgsbhadohi.TGS.service.masters.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userService;
//	@Autowired
//	private CustomUserDetailsService customUserdetailService;
	
	@PostMapping("/create-user")
	public ResponseEntity<ResponseModel> CreateUser(@Valid @RequestBody User user ) {
		userService.createUser(user);
		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true , null );
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}
//	public ResponseEntity<ResponseModel> getUserById(@Valid @RequestBody String userName ) {
//		User user = customUserdetailService.loadUserByUsername(userName);
//		ResponseModel res = new ResponseModel(Constants.CREATE_RECORD,Constants.SUCCESS, true , null );
//		return new ResponseEntity<>(res, HttpStatus.CREATED);
//	}
	
}
