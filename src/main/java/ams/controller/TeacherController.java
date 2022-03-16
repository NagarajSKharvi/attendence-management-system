package ams.controller;

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

import ams.entity.Teacher;
import ams.repository.TeacherRepository;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	
	@PostMapping("")
	public Teacher create(@RequestBody Teacher teacher) {
		Teacher teacherInDb = teacherRepository.save(teacher);
		return teacherInDb;
	}
	
	@PutMapping("")
	public Teacher update(@RequestBody Teacher teacher) {
		Teacher teacherInDb = teacherRepository.save(teacher);
		return teacherInDb;
	}
	
	@GetMapping("")
	public List<Teacher> list() {
		List<Teacher> teachers = teacherRepository.findAll();
		return teachers;
	}
	
	@GetMapping("/{id}")
	public Teacher get(@PathVariable Long id) {
		Teacher teacher = teacherRepository.findById(id).get();
		return teacher;
	}
	
	@DeleteMapping("/{id}")
	public Boolean delete(@PathVariable Long id) {
		teacherRepository.deleteById(id);
		return true;
	}
	
}