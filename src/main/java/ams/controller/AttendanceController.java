package ams.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.entity.Attendance;
import ams.entity.ClassPeriod;
import ams.entity.SectionSubject;
import ams.entity.Student;
import ams.entity.Teacher;
import ams.repository.AttendanceRepository;
import ams.repository.ClassPeriodRepository;
import ams.repository.ClassSectionRepository;
import ams.repository.SectionSubjectRepository;
import ams.repository.StudClassRepository;
import ams.repository.StudentRepository;
import ams.repository.TeacherRepository;
import ams.response.AttendanceResponse;
import ams.response.StudentAttendanceResponse;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "http://localhost:19006")
public class AttendanceController {
	
	@Autowired
	private StudentRepository studentRepository;
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
	@Autowired
	private TeacherRepository teacherRepository;
	
	@GetMapping("/{attendanceId}")
	public AttendanceResponse getAttendance(@PathVariable Long attendanceId) {
		Attendance attendance = attendanceRepository.findById(attendanceId).orElse(null);
		ClassPeriod classPeriod = classPeriodRepository.findById(attendance.getPeriodId()).orElse(null);
		SectionSubject sectionSubject = sectionSubjectRepository.findById(attendance.getSubjectId()).orElse(null);
		Teacher teacher = teacherRepository.findById(attendance.getTeachId()).orElse(null);
		List<Student> students = studentRepository.findAllByIdIn(attendance.getSIds());
		
		List<StudentAttendanceResponse> studentAttendanceResponses = new ArrayList<>();
		attendance.getSIds().forEach(a -> {
			Student student = students.stream().filter(ss -> ss.getId().equals(a)).findAny().orElse(null);
			StudentAttendanceResponse studentAttendanceResponse = new StudentAttendanceResponse();
			studentAttendanceResponse.setStudentId(a);
			studentAttendanceResponse.setRollNumber(student.getRollNumber());
			studentAttendanceResponse.setPresent(true);
			studentAttendanceResponses.add(studentAttendanceResponse);
		});
		
		AttendanceResponse attendanceResponse = new AttendanceResponse();
		attendanceResponse.setAttendanceId(attendanceId);
		attendanceResponse.setDate(attendance.getDate());
		attendanceResponse.setClassPeriod(classPeriod);
		attendanceResponse.setSectionSubject(sectionSubject);
		attendanceResponse.setTeacher(teacher);
		attendanceResponse.setStudentAttendanceResponses(studentAttendanceResponses);
		
		
		return attendanceResponse;
	}
	
	@GetMapping("")
	public List<AttendanceResponse> listAttendance() {
		List<Attendance> attendances = attendanceRepository.findAll();
		
		Set<Long> periodIds = attendances.stream().map(Attendance::getPeriodId).collect(Collectors.toSet());
		List<ClassPeriod> classPeriods = classPeriodRepository.findAllByPeriodIdIn(periodIds);
		
		Set<Long> subjectIds = attendances.stream().map(Attendance::getSubjectId).collect(Collectors.toSet());
		List<SectionSubject> sectionSubjects = sectionSubjectRepository.findAllBySectionIdIn(subjectIds);
		
		Set<Long> teacherIds = attendances.stream().map(Attendance::getTeachId).collect(Collectors.toSet());
		List<Teacher> teachers = teacherRepository.findAllByTeachIdIn(teacherIds);
		
		List<AttendanceResponse> list = new ArrayList<>();
		attendances.stream().forEach(a -> {
			list.add(mapAttendanceResponse(a, classPeriods, sectionSubjects, teachers));
		});
		return list;
	}
	
	private AttendanceResponse mapAttendanceResponse(Attendance attendance, List<ClassPeriod> classPeriods,
			List<SectionSubject> sectionSubjects, List<Teacher> teachers) {
		AttendanceResponse attendanceResponse = new AttendanceResponse();
		attendanceResponse.setAttendanceId(attendance.getAttendanceId());
		attendanceResponse.setDate(attendance.getDate());
		ClassPeriod classPeriod = classPeriods.stream().filter(cp -> cp.getPeriodId().equals(attendance.getAttendanceId())).findAny().orElse(null);
		attendanceResponse.setClassPeriod(classPeriod);
		
		SectionSubject sectionSubject = sectionSubjects.stream().filter(ss -> ss.getSubjectId().equals(attendance.getSubjectId())).findAny().orElse(null);
		attendanceResponse.setSectionSubject(sectionSubject);
		
		Teacher teacher = teachers.stream().filter(ss -> ss.getTeachId().equals(attendance.getTeachId())).findAny().orElse(null);
		attendanceResponse.setTeacher(teacher);
		return attendanceResponse;
	}
	
}
