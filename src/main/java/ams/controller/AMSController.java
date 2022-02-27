package ams.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.entity.Attendance;
import ams.entity.ClassPeriod;
import ams.entity.ClassSection;
import ams.entity.SectionSubject;
import ams.entity.StudClass;
import ams.repository.AttendanceRepository;
import ams.repository.ClassPeriodRepository;
import ams.repository.ClassSectionRepository;
import ams.repository.SectionSubjectRepository;
import ams.repository.StudClassRepository;

@RestController
@RequestMapping("/ams")
@CrossOrigin(origins = "http://localhost:19006")
public class AMSController {
	
	@Autowired
	private StudClassRepository studClassRepository;
	
	@Autowired
	private ClassSectionRepository classSectionRepository;
	
	@Autowired
	private SectionSubjectRepository sectionSubjectRepository;
	
	@Autowired
	private ClassPeriodRepository classPeriodRepository;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@GetMapping("/class")
	public List<StudClass> listClass() {
		return studClassRepository.findByClassYear(LocalDate.now().getYear());
	}
	
	@GetMapping("/section/{classId}")
	public List<ClassSection> listSection(@PathVariable Long classId) {
		return classSectionRepository.findByClassId(classId);
	}
	
	@GetMapping("/subject/{sectionId}")
	public List<SectionSubject> listSubject(@PathVariable Long sectionId) {
		return sectionSubjectRepository.findAllBySectionId(sectionId);
	}
	
	@GetMapping("/period")
	public List<ClassPeriod> listPeriod() {
		return classPeriodRepository.findAll();
	}
	
	@GetMapping("/attendance")
	public List<Attendance> listAttendance() {
		return attendanceRepository.findAll();
	}
}
