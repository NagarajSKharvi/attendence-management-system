package ams.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ams.entity.Attendance;
import ams.request.AttendenceRequest;
import ams.response.AttendancePercentage;
import ams.response.AttendanceResponse;
import ams.response.SAttendanceResponse;
import ams.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceService;
	
	@GetMapping("/student/{studentId}")
	public List<SAttendanceResponse> getStudentAttendance(@PathVariable Long studentId) {
		return attendanceService.getStudentAttendance(studentId);
	}
	
	@GetMapping("/student/pecentage/{studentId}")
	public String getStudentAttendancePercentage(@PathVariable Long studentId) {
		return attendanceService.getStudentAttendancePercentage(studentId);
	}
	
	@GetMapping("/student/pecentage/all/{studentId}")
	public AttendancePercentage getStudentAttendancePercentageBySem(@PathVariable Long studentId) {
		return attendanceService.getStudentAttendancePercentageBySem(studentId);
	}
	
	@GetMapping("/add/{subjectId}")
	public AttendanceResponse getAttendanceResourceBySubject(@PathVariable Long subjectId) {
		return attendanceService.getAttendanceResourceBySubject(subjectId);
	}
	
	@PostMapping("")
	public Attendance create(@RequestBody AttendenceRequest attendenceRequest) throws Exception {
		return attendanceService.create(attendenceRequest);
	}
	
	@GetMapping("/{attendanceId}")
	public AttendanceResponse get(@PathVariable Long attendanceId) {
		return attendanceService.get(attendanceId);
	}
	
	@PostMapping("/list")
	public List<AttendanceResponse> list(@RequestBody AttendenceRequest attendenceRequest) {
		return attendanceService.list(attendenceRequest);
	}
}
