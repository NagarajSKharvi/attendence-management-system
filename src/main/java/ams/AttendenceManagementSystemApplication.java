package ams;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ams.entity.Attendance;
import ams.entity.ClassPeriod;
import ams.entity.ClassSection;
import ams.entity.SectionSubject;
import ams.entity.StudClass;
import ams.entity.Student;
import ams.entity.Teacher;
import ams.repository.AttendanceRepository;
import ams.repository.ClassPeriodRepository;
import ams.repository.ClassSectionRepository;
import ams.repository.SectionSubjectRepository;
import ams.repository.StudClassRepository;
import ams.repository.StudentRepository;
import ams.repository.TeacherRepository;

@SpringBootApplication
public class AttendenceManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(AttendenceManagementSystemApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		List<StudClass> studClasses = List.of(new StudClass("Class 1"),
				new StudClass("Class 2"),
				new StudClass("Class 3"),
				new StudClass("Class 4"),
				new StudClass("Class 5"),
				new StudClass("Class 6"),
				new StudClass("Class 7"));
		studClassRepository.saveAll(studClasses);
		
		List<ClassSection> classSections = List.of(new ClassSection("Section A", 1l),
				new ClassSection("Section B", 1l),
				new ClassSection("Section C", 1l),
				new ClassSection("Section A", 2l),
				new ClassSection("Section A", 3l));
		classSectionRepository.saveAll(classSections);
		
		List<SectionSubject> sectionSubjects = List.of(new SectionSubject("English", 1l),
				new SectionSubject("Hindi", 1l),
				new SectionSubject("Kannada", 1l),
				new SectionSubject("English", 2l),
				new SectionSubject("Hindi", 2l),
				new SectionSubject("Kannada", 2l));
		sectionSubjectRepository.saveAll(sectionSubjects);
		
		List<ClassPeriod> classPeriods = List.of(new ClassPeriod(LocalTime.of(8, 00), LocalTime.of(9, 50)),
				new ClassPeriod(LocalTime.of(10, 00), LocalTime.of(11, 50)),
				new ClassPeriod(LocalTime.of(1, 00), LocalTime.of(2, 50)),
				new ClassPeriod(LocalTime.of(3, 00), LocalTime.of(4, 50)));
		classPeriodRepository.saveAll(classPeriods);
		
		List<Attendance> attendances = List.of(new Attendance(1l, 1l, 1l, LocalDate.of(2022, 02, 23), Set.of(1l, 2l, 3l))
				);
		attendanceRepository.saveAll(attendances);
		
		List<Student> students = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			Student student = new Student();
			student.setRollNumber(String.valueOf("13GAEC" + 904 + i));
			student.setFirstName("First Name " + i);
			student.setMiddleName("Middle Name " + i);
			student.setLastName("Last Name " + i);
			student.setDob(LocalDate.now().plusDays(i));
			student.setMobileNumber(Long.valueOf("720492984" + i));
			students.add(student);
		}
		studentRepository.saveAll(students);
		
		List<Teacher> teachers = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			Teacher teacher = new Teacher();
			teacher.setTeacherNumber(String.valueOf("GAEC" + 904 + i));
			teacher.setFirstName("T First Name " + i);
			teacher.setMiddleName("T Middle Name " + i);
			teacher.setLastName("T Last Name " + i);
			teacher.setDob(LocalDate.now().plusDays(i));
			teacher.setMobileNumber(Long.valueOf("720492984" + i));
			teachers.add(teacher);
		}
		teacherRepository.saveAll(teachers);
	}
}