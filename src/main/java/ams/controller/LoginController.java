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
		User user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
		if (ObjectUtils.isEmpty(user)) {
			return LoginResponse.builder().errorMessage("Not found").build();
		}
		return LoginResponse.builder().id(user.getId()).userName(user.getUsername())
			.userType(user.getAccountType()).build(); 
	}
}
