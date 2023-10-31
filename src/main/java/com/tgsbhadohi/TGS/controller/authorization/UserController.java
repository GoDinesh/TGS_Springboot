package com.tgsbhadohi.TGS.controller.authorization;

import com.tgsbhadohi.TGS.classes.Constants;
import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.authorization.User;
import com.tgsbhadohi.TGS.service.authorization.UserService;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/auth/user")
public class UserController {

  @Autowired
  private UserService userService;

  //	@Autowired
  //	private CustomUserDetailsService customUserdetailService;

  @PostMapping("/insert")
  public ResponseEntity<ResponseModel> CreateUser(
    @Valid @RequestBody User user
  ) {
    userService.createUser(user);
    ResponseModel res = new ResponseModel(
      Constants.CREATE_RECORD,
      Constants.SUCCESS,
      true,
      null
    );
    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }

  @GetMapping("/findall")
  public ResponseEntity<ResponseModel> findAll() {
    ResponseModel res = new ResponseModel(
      Constants.GET_RECORD,
      Constants.SUCCESS,
      false,
      userService.getAllUser()
    );
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @GetMapping("/current-user")
  public String getLoggedInUser(Principal principal) {
    return principal.getName();
  }

  @PostMapping("/findbyid")
  public ResponseEntity<ResponseModel> getUserById(
    @Valid @RequestBody String userName
  ) {
    ResponseModel res = new ResponseModel(
      Constants.CREATE_RECORD,
      Constants.SUCCESS,
      false,
      userService.getUserByName(userName)
    );
    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }
}
