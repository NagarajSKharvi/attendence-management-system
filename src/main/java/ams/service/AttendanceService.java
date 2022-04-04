package ams.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ams.entity.Attendance;
import ams.entity.ClassPeriod;
import ams.entity.ClassSection;
import ams.entity.Percentage;
import ams.entity.SectionStudent;
import ams.entity.SectionStudentId;
import ams.entity.SectionSubject;
import ams.entity.Semester;
import ams.entity.Student;
import ams.entity.Teacher;
import ams.repository.AttendanceRepository;
import ams.repository.ClassPeriodRepository;
import ams.repository.ClassSectionRepository;
import ams.repository.SectionStudentRepository;
import ams.repository.SectionSubjectRepository;
import ams.repository.StudClassRepository;
import ams.repository.StudentRepository;
import ams.repository.TeacherRepository;
import ams.request.AttendenceRequest;
import ams.request.StudentAttendanceRequest;
import ams.response.AttendancePercentage;
import ams.response.AttendanceResponse;
import ams.response.SAttendanceResponse;
import ams.response.StudentAttendanceResponse;
import ams.util.CommonUtil;

@Service
public class AttendanceService {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudClassRepository studClassRepository;
	@Autowired
	private ClassSectionRepository classSectionRepository;
	@Autowired
	private SectionSubjectRepository sectionSubjectRepository;
	@Autowired
	private SectionStudentRepository sectionStudentRepository;
	@Autowired
	private ClassPeriodRepository classPeriodRepository;
	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	
	public List<SAttendanceResponse> getStudentAttendance(Long studentId) {
		SectionStudent sectionStudent = sectionStudentRepository.findAllBySectionStudentIdStudId(studentId);
		List<SectionSubject> subjects = sectionSubjectRepository.findAllBySectionId(sectionStudent.getSectionStudentId().getSectionId());
		Set<Long> subjectIds = subjects.stream().map(SectionSubject::getSubjectId).collect(Collectors.toSet());
		List<Attendance> attendances = attendanceRepository.findBySubjectIdIn(subjectIds);

		Set<Long> periodIds = attendances.stream().map(Attendance::getPeriodId).collect(Collectors.toSet());
		List<ClassPeriod> classPeriods = classPeriodRepository.findAllByPeriodIdIn(periodIds);
		
		subjectIds = attendances.stream().map(Attendance::getSubjectId).collect(Collectors.toSet());
		List<SectionSubject> sectionSubjects = sectionSubjectRepository.findAllBySubjectIdIn(subjectIds);
		
		Set<Long> teacherIds = attendances.stream().map(Attendance::getTeachId).collect(Collectors.toSet());
		List<Teacher> teachers = teacherRepository.findAllByIdIn(teacherIds);
		
		List<SAttendanceResponse> sAttendanceResponses = new ArrayList<>();
		attendances.stream().forEach(a -> {
			List<Long> resp = CommonUtil.getResponseFromString(a.getJson());
			String isPresent = "No";
			if (resp.stream().anyMatch(r -> resp.contains(studentId))) {
				isPresent = "Yes";
			}
			ClassPeriod classPeriod = classPeriods.stream().filter(cp -> cp.getPeriodId().equals(a.getPeriodId())).findAny().orElse(null);
			SectionSubject sectionSubject = sectionSubjects.stream().filter(ss -> ss.getSubjectId().equals(a.getSubjectId())).findAny().orElse(null);
			Teacher teacher = teachers.stream().filter(ss -> ss.getId().equals(a.getTeachId())).findAny().orElse(null);
			sAttendanceResponses.add(new SAttendanceResponse(a.getAttendanceId(), a.getDate(), classPeriod.getFromTime(),
					sectionSubject.getSubjectName(), teacher.getName(), isPresent));
		});
		return sAttendanceResponses;
	}
	
	public String getStudentAttendancePercentage(Long studentId) {
		SectionStudent sectionStudent = sectionStudentRepository.findAllBySectionStudentIdStudId(studentId);
		List<SectionSubject> subjects = sectionSubjectRepository.findAllBySectionId(sectionStudent.getSectionStudentId().getSectionId());
		
		ClassSection classSection = classSectionRepository.findBySectionId(sectionStudent.getSectionStudentId().getSectionId());
		Set<Long> subjectIds = subjects.stream().map(SectionSubject::getSubjectId).collect(Collectors.toSet());
		List<Attendance> attendances = attendanceRepository.findBySubjectIdIn(subjectIds);
		
		LocalDate endDate = LocalDate.now().isBefore(classSection.getStudClass().getEndDate()) ? LocalDate.now() : classSection.getStudClass().getEndDate();
		long totalDays = ChronoUnit.DAYS.between(classSection.getStudClass().getStartDate(), endDate);
		long totalWeeks = totalDays / 7;
		long extraDays = totalDays - (totalWeeks * 7);
		long actualDays = (totalWeeks * 6) + extraDays;
		long counter = 0;
		for(Attendance a : attendances) {
			List<Long> studIds = CommonUtil.getResponseFromString(a.getJson());
			if (studIds.contains(studentId)) {
				counter++;
			}
		}
		if (classSection.getClassId() < 5) {
			counter = counter/2;
		} else {
			counter = counter/4;
		}
		float percentage = (float) (counter * 100) / actualDays;
		return String.format("%.2f", percentage);
	}
	
	public AttendancePercentage getStudentAttendancePercentageBySem(Long studentId) {
		SectionStudent sectionStudent = sectionStudentRepository.findAllBySectionStudentIdStudId(studentId);
		List<SectionSubject> subjects = sectionSubjectRepository.findAllBySectionId(sectionStudent.getSectionStudentId().getSectionId());
		
		Set<Long> subjectIds = subjects.stream().map(SectionSubject::getSubjectId).collect(Collectors.toSet());
		List<Attendance> attendances = attendanceRepository.findBySubjectIdIn(subjectIds);
		
		List<Percentage> list = new ArrayList<>();
		Set<Semester> semesters = subjects.stream().map(SectionSubject::getSemester).collect(Collectors.toSet());
		for (Semester semester : semesters) {
			List<SectionSubject> semSubjects = subjects.stream().filter(ss -> ss.getSemId().equals(semester.getSemId())).collect(Collectors.toList());
			Set<Long> semSubjectIds = semSubjects.stream().map(SectionSubject :: getSubjectId).collect(Collectors.toSet());
			List<Attendance> semAttendances = attendances.stream().filter(a -> semSubjectIds.contains(a.getSubjectId())).collect(Collectors.toList());
			long totalDays = ChronoUnit.DAYS.between(semester.getStartDate(), semester.getEndDate()) + 2;
			long totalWeeks = totalDays / 7;
			long extraDays = totalDays - (totalWeeks * 7);
			long actualDays = (totalWeeks * 6) + extraDays;
			long counter = semAttendances.stream().filter(s -> CommonUtil.getResponseFromString(s.getJson()).contains(studentId)).count();
			counter = counter/4;
			float percentage = (float) (counter * 100) / actualDays;
			list.add(new Percentage(semester.getSemesterName(), String.format("%.2f", percentage), new ArrayList<>()));
		}
		for (SectionSubject subject : subjects) {
			List<Attendance> subAttendances = attendances.stream().filter(a -> subject.getSubjectId().equals(a.getSubjectId())).collect(Collectors.toList());
			long totalDays = ChronoUnit.DAYS.between(subject.getSemester().getStartDate(), subject.getSemester().getEndDate()) + 2;
			long totalWeeks = totalDays / 7;
			long extraDays = totalDays - (totalWeeks * 7);
			long actualDays = (totalWeeks * 6) + extraDays;
			long totalSubClasses = (actualDays * 4) / 7;
			
			long counter = subAttendances.stream().filter(s -> CommonUtil.getResponseFromString(s.getJson()).contains(studentId)).count();
			float percentage = (float) (counter * 100) / totalSubClasses;
			for (Percentage p : list) {
				if (p.getType().equals(subject.getSemester().getSemesterName())) {
					p.getPercentages().add(new Percentage(subject.getSubjectName(), String.format("%.2f", percentage)));
				}
			}
		}
		List<Percentage> monthPercentages = new ArrayList<>();
		Iterator<Semester> iterator = semesters.iterator();
		LocalDate start = iterator.next().getStartDate();
		for (int i = start.getMonthValue(); i <= 12; i ++) {
			LocalDate startDate = start;
			LocalDate end = start.plusMonths(1);
			List<Attendance> monthAtt = attendances.stream().filter(a -> startDate.isBefore(a.getDate()) && a.getDate().isBefore(end)).collect(Collectors.toList());
			long days = monthAtt.size()/4;
			
			Long weekDays = getWeekDays(start.getYear(), start.getMonthValue());
			float percentage = (float) (days * 100) / weekDays;
			monthPercentages.add(new Percentage(start.getMonth().toString(), String.format("%.2f", percentage)));
			start = end;
		}
		return new AttendancePercentage(list, monthPercentages);
	}
	
	public List<AttendanceResponse> list(AttendenceRequest attendenceRequest) {
		List<Attendance> attendances = getAttendence(attendenceRequest);
		return getAttendanceResponses(attendances);
	}
	
	public AttendanceResponse get(Long attendanceId) {
		Attendance attendance = attendanceRepository.findById(attendanceId).orElse(null);
		ClassPeriod classPeriod = classPeriodRepository.findById(attendance.getPeriodId()).orElse(null);
		SectionSubject sectionSubject = sectionSubjectRepository.findById(attendance.getSubjectId()).orElse(null);
		Teacher teacher = teacherRepository.findById(attendance.getTeachId()).orElse(null);
		return mapAttendanceResponse(attendanceId, attendance, classPeriod, sectionSubject, teacher, getStudentAttendanceResponses(attendance));
	}

	public AttendanceResponse getAttendanceResourceBySubject(Long subjectId) {
		SectionSubject sectionSubject = sectionSubjectRepository.findAllBySubjectId(subjectId);
		AttendanceResponse attendanceResponse = new AttendanceResponse();
		attendanceResponse.setDate(LocalDate.now());
		List<StudentAttendanceResponse> studentAttendanceResponses = new ArrayList<>();
		List<SectionStudent> sectionStudents = sectionStudentRepository.findAllBySectionStudentIdSectionId(sectionSubject.getSectionId());
		Set<Long> studentIds = sectionStudents.stream().map(SectionStudent::getSectionStudentId).map(SectionStudentId::getStudId)
				.collect(Collectors.toSet());
		List<Student> students = studentRepository.findAllByIdIn(studentIds);
		students.stream().forEach(s -> {
			studentAttendanceResponses.add(new StudentAttendanceResponse(s.getId(), s.getRollNumber()));
		});
		attendanceResponse.setStudentAttendanceResponses(studentAttendanceResponses);
		return attendanceResponse;
	}
	
	public Attendance create(AttendenceRequest attendenceRequest) throws Exception {
		validate(attendenceRequest);
		List<Long> studentIds = attendenceRequest.getStudentAttendanceRequests().stream().filter(a -> a.isPresent()).map(StudentAttendanceRequest::getStudentId)
			.collect(Collectors.toList());
		Attendance attendance = new Attendance(attendenceRequest.getPeriodId(), attendenceRequest.getSubjectId(),
			attendenceRequest.getTeachId(), attendenceRequest.getDate(), studentIds.toString());
		return attendanceRepository.save(attendance);
	}
	
	private void validate(AttendenceRequest attendenceRequest) throws Exception {
		LocalDate localDate = LocalDate.now();
		
		if (attendenceRequest.getDate().isAfter(localDate)) {
			throw new Exception("Cannot add Attendance for future dates");
		} else if (attendenceRequest.getDate().isBefore(localDate.minusDays(31))) {
			throw new Exception("Attendance can be only added for today or past 31 days");
		}
			
		List<Attendance> list = attendanceRepository.findByPeriodIdAndSubjectIdAndTeachIdAndDate(attendenceRequest.getPeriodId(), attendenceRequest.getSubjectId(),
				attendenceRequest.getTeachId(), attendenceRequest.getDate());
		if (!list.isEmpty()) {
			throw new Exception("Attendance already exists for the date, period, section and subject");
		}
		
	}

	private List<Attendance> getAttendence(AttendenceRequest attendenceRequest) {
		Long periodId = attendenceRequest.getPeriodId();
		Long subjectId = attendenceRequest.getSubjectId();
		Long teachId = attendenceRequest.getTeachId();
		LocalDate date = attendenceRequest.getDate();
		List<Attendance> attendances = new ArrayList<>();
		if (!ObjectUtils.isEmpty(periodId) && !ObjectUtils.isEmpty(subjectId) &&
				!ObjectUtils.isEmpty(teachId) && !ObjectUtils.isEmpty(date)) {
			attendances = attendanceRepository.findByPeriodIdAndSubjectIdAndTeachIdAndDate(periodId, subjectId, teachId, date);
		} else if (!ObjectUtils.isEmpty(periodId) && !ObjectUtils.isEmpty(subjectId) && !ObjectUtils.isEmpty(teachId)) {
			attendances = attendanceRepository.findByPeriodIdAndSubjectIdAndTeachId(periodId, subjectId, teachId);
		} else if (!ObjectUtils.isEmpty(periodId) && !ObjectUtils.isEmpty(subjectId) &&
				!ObjectUtils.isEmpty(teachId) && !ObjectUtils.isEmpty(date)) {
			attendances = attendanceRepository.findByPeriodIdAndSubjectIdAndDate(periodId, subjectId, date);
		} else if (!ObjectUtils.isEmpty(periodId) && !ObjectUtils.isEmpty(teachId) && !ObjectUtils.isEmpty(date)) {
			attendances = attendanceRepository.findByPeriodIdAndTeachIdAndDate(periodId, teachId, date);
		} else if (!ObjectUtils.isEmpty(subjectId) && !ObjectUtils.isEmpty(teachId) && !ObjectUtils.isEmpty(date)) {
			attendances = attendanceRepository.findBySubjectIdAndTeachIdAndDate(subjectId, teachId, date);
		} else if (!ObjectUtils.isEmpty(periodId) && !ObjectUtils.isEmpty(subjectId)) {
			attendances = attendanceRepository.findByPeriodIdAndSubjectId(periodId, subjectId);
		} else if (!ObjectUtils.isEmpty(periodId) && !ObjectUtils.isEmpty(teachId)) {
			attendances = attendanceRepository.findByPeriodIdAndTeachId(periodId, teachId);
		} else if (!ObjectUtils.isEmpty(periodId) && !ObjectUtils.isEmpty(date)) {
			attendances = attendanceRepository.findByPeriodIdAndDate(periodId, date);
		} else if (!ObjectUtils.isEmpty(subjectId) && !ObjectUtils.isEmpty(teachId)) {
			attendances = attendanceRepository.findBySubjectIdAndTeachId(subjectId, teachId);
		} else if (!ObjectUtils.isEmpty(subjectId) && !ObjectUtils.isEmpty(date)) {
			attendances = attendanceRepository.findBySubjectIdAndDate(subjectId, date);
		} else if (!ObjectUtils.isEmpty(teachId) && !ObjectUtils.isEmpty(date)) {
			attendances = attendanceRepository.findByTeachIdAndDate(teachId, date);
		} else if (!ObjectUtils.isEmpty(periodId)) {
			attendances = attendanceRepository.findByPeriodId(periodId);
		} else if (!ObjectUtils.isEmpty(subjectId)) {
			attendances = attendanceRepository.findBySubjectId(subjectId);
		} else if (!ObjectUtils.isEmpty(teachId)) {
			attendances = attendanceRepository.findByTeachId(teachId);
		} else if (!ObjectUtils.isEmpty(date)) {
			attendances = attendanceRepository.findByDate(date);
		} 
		return attendances;
	}

	private List<AttendanceResponse> getAttendanceResponses(List<Attendance> attendances) {
		Set<Long> periodIds = attendances.stream().map(Attendance::getPeriodId).collect(Collectors.toSet());
		List<ClassPeriod> classPeriods = classPeriodRepository.findAllByPeriodIdIn(periodIds);
		
		Set<Long> subjectIds = attendances.stream().map(Attendance::getSubjectId).collect(Collectors.toSet());
		List<SectionSubject> sectionSubjects = sectionSubjectRepository.findAllBySubjectIdIn(subjectIds);
		
		Set<Long> teacherIds = attendances.stream().map(Attendance::getTeachId).collect(Collectors.toSet());
		List<Teacher> teachers = teacherRepository.findAllByIdIn(teacherIds);
		
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
		
		Teacher teacher = teachers.stream().filter(ss -> ss.getId().equals(attendance.getTeachId())).findAny().orElse(null);
		attendanceResponse.setTeacher(teacher);
		
//		try {
//			List<StudentAttendanceResponse> resp = new ObjectMapper().readValue(attendance.getJson(), new TypeReference<List<StudentAttendanceResponse>>(){});
//			attendanceResponse.setStudentAttendanceResponses(resp);
//		} catch (JsonMappingException e) {
//		} catch (JsonProcessingException e) {
//		}
		return attendanceResponse;
	}
	
	private List<StudentAttendanceResponse> getStudentAttendanceResponses(Attendance attendance) {
		List<Long> resp = new ArrayList<>();
		try {
			resp = new ObjectMapper().readValue(attendance.getJson(), new TypeReference<List<Long>>(){});
		} catch (JsonMappingException e) {
		} catch (JsonProcessingException e) {
		}
		SectionSubject sectionSubject = sectionSubjectRepository.findById(attendance.getSubjectId()).orElse(null);
		List<SectionStudent> sectionStudents = sectionStudentRepository.findAllBySectionStudentIdSectionId(sectionSubject.getSectionId());
		Set<Long> studentIds = sectionStudents.stream().map(SectionStudent::getSectionStudentId).map(SectionStudentId::getStudId).collect(Collectors.toSet());
		List<Student> studentsInDb = studentRepository.findAllByIdIn(studentIds);
		List<StudentAttendanceResponse> result = new ArrayList<>();
		for (Student student : studentsInDb) {
			boolean isPresent = resp.stream().anyMatch(r -> r.equals(student.getId()));
			result.add(new StudentAttendanceResponse(student.getId(), student.getRollNumber(), isPresent));
		}
		return result;
	}
	
	private AttendanceResponse mapAttendanceResponse(Long attendanceId, Attendance attendance, ClassPeriod classPeriod,
			SectionSubject sectionSubject, Teacher teacher, List<StudentAttendanceResponse> resp) {
		AttendanceResponse attendanceResponse = new AttendanceResponse();
		attendanceResponse.setAttendanceId(attendanceId);
		attendanceResponse.setDate(attendance.getDate());
		attendanceResponse.setClassPeriod(classPeriod);
		attendanceResponse.setSectionSubject(sectionSubject);
		attendanceResponse.setTeacher(teacher);
		attendanceResponse.setStudentAttendanceResponses(resp);
		return attendanceResponse;
	}

	static Long getWeekDays(int year, int month) {
        LocalDate firstDateOfTheMonth = YearMonth.of(year, month).atDay(1);
        
        return firstDateOfTheMonth
                .datesUntil(firstDateOfTheMonth.plusMonths(1))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .count();
    }
}
