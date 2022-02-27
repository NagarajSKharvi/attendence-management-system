package ams.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import ams.request.AttendenceRequest;
import ams.request.StudentAttendanceRequest;
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
		
		List<StudentAttendanceResponse> resp = null;
		try {
			resp = new ObjectMapper().readValue(attendance.getJson(), new TypeReference<List<StudentAttendanceResponse>>(){});
		} catch (JsonMappingException e) {
		} catch (JsonProcessingException e) {
		}
		Set<Long> sIds = resp.stream().map(StudentAttendanceResponse::getStudentId).collect(Collectors.toSet());
		List<Student> students = studentRepository.findAllByIdIn(sIds);
		resp.stream().forEach(res -> {
			Student student = students.stream().filter(s -> s.getId().equals(res.getStudentId())).findAny().orElse(null);
			if (!ObjectUtils.isEmpty(student)) {
				res.setRollNumber(student.getRollNumber());
			}
			
		});
		
		AttendanceResponse attendanceResponse = new AttendanceResponse();
		attendanceResponse.setAttendanceId(attendanceId);
		attendanceResponse.setDate(attendance.getDate());
		attendanceResponse.setClassPeriod(classPeriod);
		attendanceResponse.setSectionSubject(sectionSubject);
		attendanceResponse.setTeacher(teacher);
		attendanceResponse.setStudentAttendanceResponses(resp);
		
		return attendanceResponse;
	}
	
	@GetMapping("")
	public List<AttendanceResponse> listAttendance() {
		List<Attendance> attendances = attendanceRepository.findAll();
		List<AttendanceResponse> list = getAttendanceResponses(attendances);
		return list;
	}
	
	@PostMapping("")
	public List<AttendanceResponse> listAttendance(@RequestBody AttendenceRequest attendenceRequest) {
		List<Attendance> attendances = getAttendence(attendenceRequest);
		List<AttendanceResponse> list = getAttendanceResponses(attendances);
		return list;
	}
	
	@PostMapping("/create")
	public Boolean createAttendance(@RequestBody AttendenceRequest attendenceRequest) {
		Attendance attendance = new Attendance();
		attendance.setDate(attendenceRequest.getDate());
		attendance.setPeriodId(attendenceRequest.getPeriodId());
		attendance.setTeachId(attendenceRequest.getTeachId());
		attendance.setSubjectId(attendenceRequest.getSubjectId());
		try {
			attendance.setJson(new ObjectMapper().writeValueAsString(attendenceRequest.getStudentAttendanceRequests()));
		} catch (JsonProcessingException e) {
		}
		attendanceRepository.save(attendance);
		return true;
	}

	@GetMapping("/get")
	public AttendanceResponse getAttendance() {
		AttendanceResponse attendanceResponse = new AttendanceResponse();
		attendanceResponse.setDate(LocalDate.now());
		List<StudentAttendanceResponse> studentAttendanceResponses = new ArrayList<>();
		List<Student> students = studentRepository.findAll();
		students.stream().forEach(s -> {
			studentAttendanceResponses.add(new StudentAttendanceResponse(s.getId(), s.getRollNumber()));
		});
		attendanceResponse.setStudentAttendanceResponses(studentAttendanceResponses);
		return attendanceResponse;
	}
	
	private List<Attendance> getAttendence(AttendenceRequest attendenceRequest) {
		List<Attendance> attendances = new ArrayList<>();
		if (!ObjectUtils.isEmpty(attendenceRequest.getDate()) && !ObjectUtils.isEmpty(attendenceRequest.getSubjectId())) {
			attendances = attendanceRepository.findByDateAndSubjectId(attendenceRequest.getDate(), attendenceRequest.getSubjectId());
		} else if (!ObjectUtils.isEmpty(attendenceRequest.getDate())) {
			attendances = attendanceRepository.findByDate(attendenceRequest.getDate());
		} else if (!ObjectUtils.isEmpty(attendenceRequest.getSubjectId())) {
			attendances = attendanceRepository.findBySubjectId(attendenceRequest.getSubjectId());
		} else if (!ObjectUtils.isEmpty(attendenceRequest.getTeachId())) {
			attendances = attendanceRepository.findByTeachId(attendenceRequest.getTeachId());
		} else if (!ObjectUtils.isEmpty(attendenceRequest.getPeriodId())) {
			attendances = attendanceRepository.findByPeriodId(attendenceRequest.getPeriodId());
		}
		return attendances;
	}

	private List<AttendanceResponse> getAttendanceResponses(List<Attendance> attendances) {
		Set<Long> periodIds = attendances.stream().map(Attendance::getPeriodId).collect(Collectors.toSet());
		List<ClassPeriod> classPeriods = classPeriodRepository.findAllByPeriodIdIn(periodIds);
		
		Set<Long> subjectIds = attendances.stream().map(Attendance::getSubjectId).collect(Collectors.toSet());
		List<SectionSubject> sectionSubjects = sectionSubjectRepository.findAllBySubjectIdIn(subjectIds);
		
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
		
//		try {
//			List<StudentAttendanceResponse> resp = new ObjectMapper().readValue(attendance.getJson(), new TypeReference<List<StudentAttendanceResponse>>(){});
//			attendanceResponse.setStudentAttendanceResponses(resp);
//		} catch (JsonMappingException e) {
//		} catch (JsonProcessingException e) {
//		}
		
		return attendanceResponse;
	}
	
}
