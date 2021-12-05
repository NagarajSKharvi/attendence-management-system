package ams.attendence.management.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.attendence.management.system.entity.Student;
import ams.attendence.management.system.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@PostMapping("")
	public Student create(@RequestBody Student student) {
		Student studentInDb = studentRepository.save(student);
		return studentInDb;
	}
	
	@PutMapping("")
	public Student update(@RequestBody Student student) {
		Student studentInDb = studentRepository.save(student);
		return studentInDb;
	}
	
	@GetMapping("")
	public List<Student> list() {
		List<Student> students = studentRepository.findAll();
		return students;
	}
	
	@GetMapping("/{id}")
	public Student get(@PathVariable Long id) {
		Student student = studentRepository.findById(id).get();
		return student;
	}
	
	@DeleteMapping("/{id}")
	public Boolean delete(@PathVariable Long id) {
		studentRepository.deleteById(id);
		return true;
	}
	
}