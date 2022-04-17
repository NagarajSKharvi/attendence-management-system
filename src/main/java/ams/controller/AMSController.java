package ams.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.entity.Attendance;
import ams.entity.ClassPeriod;
import ams.entity.ClassSection;
import ams.entity.SectionStudent;
import ams.entity.SectionStudentId;
import ams.entity.SectionSubject;
import ams.entity.Semester;
import ams.entity.StudClass;
import ams.entity.Student;
import ams.entity.TeacherSubject;
import ams.repository.AttendanceRepository;
import ams.repository.ClassPeriodRepository;
import ams.repository.ClassSectionRepository;
import ams.repository.SectionStudentRepository;
import ams.repository.SectionSubjectRepository;
import ams.repository.SemesterRepository;
import ams.repository.StudClassRepository;
import ams.repository.StudentRepository;
import ams.repository.TeacherSubjectRepository;

@RestController
@RequestMapping("/ams")
public class AMSController {
	
	@Autowired
	private StudClassRepository studClassRepository;
	
	@Autowired
	private ClassSectionRepository classSectionRepository;
	
	@Autowired
	private SectionSubjectRepository sectionSubjectRepository;
	
	@Autowired
	private SectionStudentRepository sectionStudentRepository;
	
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepository;
	
	@Autowired
	private ClassPeriodRepository classPeriodRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SemesterRepository semesterRepository;
	
	@GetMapping("/class")
	public List<StudClass> listClass() {
		return studClassRepository.findByClassYear(LocalDate.now().getYear());
	}
	
	@GetMapping("/section/{classId}")
	public List<ClassSection> listSection(@PathVariable Long classId) {
		return classSectionRepository.findByClassId(classId);
	}
	
	@GetMapping("/section")
	public List<ClassSection> listSection() {
		return classSectionRepository.findAll();
	}
	
	@GetMapping("/subject/{sectionId}")
	public List<SectionSubject> listSubject(@PathVariable Long sectionId) {
		return sectionSubjectRepository.findAllBySectionId(sectionId);
	}
	
	@GetMapping("/subject")
	public List<SectionSubject> listSubject() {
		return sectionSubjectRepository.findAll();
	}
	
	@GetMapping("/student/{sectionId}")
	public List<Student> listStudent(@PathVariable Long sectionId) {
		List<SectionStudent> sectionStudents = sectionStudentRepository.findAllBySectionStudentIdSectionId(sectionId);
		Set<Long> studentIds = sectionStudents.stream().map(SectionStudent::getSectionStudentId).map(SectionStudentId::getStudId)
			.collect(Collectors.toSet());
		return studentRepository.findAllByIdIn(studentIds);
	}
	
	@GetMapping("/period")
	public List<ClassPeriod> listPeriod() {
		return classPeriodRepository.findAll();
	}
	
	@GetMapping("/semester")
	public List<Semester> listsSemester() {
		return semesterRepository.findAll();
	}
	
	@GetMapping("/teacher-subject/{teacherId}")
	public List<TeacherSubject> listTeacherSubject(@PathVariable Long teacherId) {
		List<TeacherSubject> findByTeacherSubjectIdTeachId = teacherSubjectRepository.findByTeacherSubjectIdTeachId(teacherId);
		return findByTeacherSubjectIdTeachId;
	}
	
	@GetMapping("/attendance")
	public List<Attendance> listAttendance() {
		return attendanceRepository.findAll();
	}
	
}
