package ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.entity.SectionStudent;
import ams.entity.SectionStudentId;
import ams.entity.TeacherSubject;
import ams.repository.SectionStudentRepository;
import ams.repository.TeacherSubjectRepository;

@RestController
@RequestMapping("/link")
public class LinkController {
	
	@Autowired
	private SectionStudentRepository sectionStudentRepository;
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepository;
	
	
	@PostMapping("/student/{sectionId}/{studentId}")
	public boolean linkSection(@PathVariable Long sectionId, @PathVariable Long studentId) throws Exception {
		SectionStudent sectionStudent = sectionStudentRepository.findAllBySectionStudentIdStudIdAndSectionStudentIdSectionId(studentId, sectionId);
		if (ObjectUtils.isEmpty(sectionStudent)) {
			sectionStudent = new SectionStudent(new SectionStudentId(sectionId, studentId));
			return !ObjectUtils.isEmpty(sectionStudentRepository.save(sectionStudent));
		}
		throw new Exception("Student is already linked with Section");
	}
	
	@DeleteMapping("/student/{sectionId}/{studentId}")
	public boolean delinkSection(@PathVariable Long sectionId, @PathVariable Long studentId) throws Exception {
		SectionStudent sectionStudent = sectionStudentRepository.findAllBySectionStudentIdStudIdAndSectionStudentIdSectionId(studentId, sectionId);
		if (ObjectUtils.isEmpty(sectionStudent)) {
			throw new Exception("No linking present");
		}
		sectionStudentRepository.delete(sectionStudent);
		return true;
	}
	
	@PostMapping("/teacher/{subjectId}/{teacherId}")
	public boolean linkSubject(@PathVariable Long subjectId, @PathVariable Long teacherId) throws Exception {
		TeacherSubject teacherSubject = teacherSubjectRepository.findByTeacherSubjectIdTeachIdAndTeacherSubjectIdSubId(teacherId, subjectId);
		if (ObjectUtils.isEmpty(teacherSubject)) {
			teacherSubject = new TeacherSubject(teacherId, subjectId);
			return !ObjectUtils.isEmpty(teacherSubjectRepository.save(teacherSubject));
		}
		throw new Exception("Teacher is already linked with Subject");
	}
	
	@DeleteMapping("/teacher/{subjectId}/{teacherId}")
	public boolean delinkSubject(@PathVariable Long subjectId, @PathVariable Long teacherId) throws Exception {
		TeacherSubject teacherSubject = teacherSubjectRepository.findByTeacherSubjectIdTeachIdAndTeacherSubjectIdSubId(teacherId, subjectId);
		if (ObjectUtils.isEmpty(teacherSubject)) {
			teacherSubject = new TeacherSubject(teacherId, subjectId);
			throw new Exception("No linking present");
		}
		teacherSubjectRepository.delete(teacherSubject);
		return true;
	}
}
