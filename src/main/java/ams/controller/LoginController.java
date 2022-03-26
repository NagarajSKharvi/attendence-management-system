package ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.entity.Student;
import ams.entity.Teacher;
import ams.entity.Users;
import ams.repository.StudentRepository;
import ams.repository.TeacherRepository;
import ams.repository.UserRepository;
import ams.request.AccountRequest;
import ams.request.LoginRequest;
import ams.request.LoginResponse;

@RestController
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getUsername());
		System.out.println(loginRequest.getPassword());
		Users user = userRepository.findByUsernameIgnoreCase(loginRequest.getUsername());
		if (ObjectUtils.isEmpty(user)) {
			return LoginResponse.builder().response("User not found").build();
		} else if (!loginRequest.getPassword().equals(user.getPassword())) {
			return LoginResponse.builder().response("Invalid Credentials").build();
		}
		return LoginResponse.builder().response("Success").id(user.getId()).userName(user.getUsername())
			.userType(user.getAccountType()).build(); 
	}
	
	@PostMapping("/create")
	public Users create(@RequestBody AccountRequest accountRequest) throws Exception {
		String userType = accountRequest.isStudent() ? "student" : "teacher";
		Long userId = null;
		if (accountRequest.isStudent()) {
			Student student = studentRepository.findByRollNumber(accountRequest.getNumber());
			if (ObjectUtils.isEmpty(student)) {
				throwError("Student with Rollnumber not found");
			}
			userId = student.getId();
		} else {
			Teacher teacher = teacherRepository.findByTeacherNumber(accountRequest.getNumber());
			if (ObjectUtils.isEmpty(teacher)) {
				throwError("Teacher with number not found");
			}
			userId = teacher.getId();
		}
		Users user = userRepository.findByIdAndAccountType(userId, userType);
		if (!ObjectUtils.isEmpty(user)) {
			throwError("Account exists");
		}
		
		user = userRepository.findByUsernameIgnoreCase(accountRequest.getUsername());
		if (!ObjectUtils.isEmpty(user)) {
			throwError("Username already taken !!!");
		}
		
		user = Users.builder().id(userId).accountType(userType).username(accountRequest.getUsername())
				.password(accountRequest.getPassword()).build();
		return userRepository.save(user);
	}
	
	private void throwError(String errorMessage) throws Exception {
		throw new Exception(errorMessage);
	}
}
