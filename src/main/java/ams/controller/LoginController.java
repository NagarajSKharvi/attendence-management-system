package ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.entity.User;
import ams.repository.UserRepository;
import ams.request.LoginRequest;
import ams.request.LoginResponse;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:19006")
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getUsername());
		System.out.println(loginRequest.getPassword());
		User user = userRepository.findByUsernameIgnoreCase(loginRequest.getUsername());
		if (ObjectUtils.isEmpty(user)) {
			return LoginResponse.builder().response("User not found").build();
		} else if (!loginRequest.getPassword().equals(user.getPassword())) {
			return LoginResponse.builder().response("Invalid Credentials").build();
		}
		return LoginResponse.builder().response("Success").id(user.getId()).userName(user.getUsername())
			.userType(user.getAccountType()).build(); 
	}
}
