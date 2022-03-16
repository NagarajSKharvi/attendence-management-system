package ams;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ams.entity.Attendance;
import ams.entity.ClassPeriod;
import ams.entity.ClassSection;
import ams.entity.SectionStudent;
import ams.entity.SectionStudentId;
import ams.entity.SectionSubject;
import ams.entity.StudClass;
import ams.entity.Student;
import ams.entity.Teacher;
import ams.entity.TeacherSubject;
import ams.entity.User;
import ams.repository.AttendanceRepository;
import ams.repository.ClassPeriodRepository;
import ams.repository.ClassSectionRepository;
import ams.repository.SectionStudentRepository;
import ams.repository.SectionSubjectRepository;
import ams.repository.StudClassRepository;
import ams.repository.StudentRepository;
import ams.repository.TeacherRepository;
import ams.repository.TeacherSubjectRepository;
import ams.repository.UserRepository;

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
	private SectionStudentRepository sectionStudentRepository;
	@Autowired
	private TeacherSubjectRepository teacherSubjectRepository;
	@Autowired
	private ClassPeriodRepository classPeriodRepository;
	@Autowired
	private AttendanceRepository attendanceRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AttendenceManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<StudClass> studClasses = List.of(new StudClass("Class 1", 2022),
				new StudClass("Class 2", 2022),
				new StudClass("Class 3", 2022),
				new StudClass("Class 4", 2022),
				new StudClass("Class 5", 2022),
				new StudClass("Class 6", 2022),
				new StudClass("Class 7", 2022),
				new StudClass("Class 8", 2022),
				new StudClass("Class 9", 2022),
				new StudClass("Class 10", 2022));
		studClassRepository.saveAll(studClasses);
		
		List<ClassSection> classSections = List.of(new ClassSection("Section A", 1l),
				new ClassSection("Section A", 2l),
				new ClassSection("Section A", 3l),
				new ClassSection("Section A", 4l),
				new ClassSection("Section A", 5l),
				new ClassSection("Section B", 5l),
				new ClassSection("Section A", 6l),
				new ClassSection("Section B", 6l),
				new ClassSection("Section A", 7l),
				new ClassSection("Section B", 7l),
				new ClassSection("Section A", 8l),
				new ClassSection("Section B", 8l),
				new ClassSection("Section A", 9l),
				new ClassSection("Section B", 9l),
				new ClassSection("Section A", 10l),
				new ClassSection("Section B", 10l));
		classSectionRepository.saveAll(classSections);
		
		List<SectionSubject> sectionSubjects = List.of(new SectionSubject("English", 1l),
				new SectionSubject("Environmental Science", 1l),
				new SectionSubject("Mathematics", 1l),
				new SectionSubject("English", 2l),
				new SectionSubject("Environmental Science", 2l),
				new SectionSubject("Mathematics", 2l),
				new SectionSubject("English", 3l),
				new SectionSubject("Environmental Science", 3l),
				new SectionSubject("Mathematics", 3l),
				new SectionSubject("English", 4l),
				new SectionSubject("Environmental Science", 4l),
				new SectionSubject("Mathematics", 4l),
				new SectionSubject("English", 5l),
				new SectionSubject("Kannada", 5l),
				new SectionSubject("Hindi", 5l),
				new SectionSubject("Science", 5l),
				new SectionSubject("Social Science", 5l),
				new SectionSubject("Mathematics", 5l),
				new SectionSubject("English", 6l),
				new SectionSubject("Kannada", 6l),
				new SectionSubject("Sanskrit", 6l),
				new SectionSubject("Science", 6l),
				new SectionSubject("Social Science", 6l),
				new SectionSubject("Mathematics", 6l),
				new SectionSubject("English", 7l),
				new SectionSubject("Kannada", 7l),
				new SectionSubject("Hindi", 7l),
				new SectionSubject("Science", 7l),
				new SectionSubject("Social Science", 7l),
				new SectionSubject("Mathematics", 7l),
				new SectionSubject("English", 8l),
				new SectionSubject("Kannada", 8l),
				new SectionSubject("Sanskrit", 8l),
				new SectionSubject("Science", 8l),
				new SectionSubject("Social Science", 8l),
				new SectionSubject("Mathematics", 8l),
				new SectionSubject("English", 9l),
				new SectionSubject("Kannada", 9l),
				new SectionSubject("Hindi", 9l),
				new SectionSubject("Science", 9l),
				new SectionSubject("Social Science", 9l),
				new SectionSubject("Mathematics", 9l),
				new SectionSubject("English", 10l),
				new SectionSubject("Kannada", 10l),
				new SectionSubject("Sanskrit", 10l),
				new SectionSubject("Science", 10l),
				new SectionSubject("Social Science", 10l),
				new SectionSubject("Mathematics", 10l),
				new SectionSubject("English", 11l),
				new SectionSubject("Kannada", 11l),
				new SectionSubject("Hindi", 11l),
				new SectionSubject("Science", 11l),
				new SectionSubject("Social Science", 11l),
				new SectionSubject("Mathematics", 11l),
				new SectionSubject("English", 12l),
				new SectionSubject("Kannada", 12l),
				new SectionSubject("Sanskrit", 12l),
				new SectionSubject("Science", 12l),
				new SectionSubject("Social Science", 12l),
				new SectionSubject("Mathematics", 12l),
				new SectionSubject("English", 13l),
				new SectionSubject("Kannada", 13l),
				new SectionSubject("Hindi", 13l),
				new SectionSubject("Science", 13l),
				new SectionSubject("Social Science", 13l),
				new SectionSubject("Mathematics", 13l),
				new SectionSubject("English", 14l),
				new SectionSubject("Kannada", 14l),
				new SectionSubject("Sanskrit", 14l),
				new SectionSubject("Science", 14l),
				new SectionSubject("Social Science", 14l),
				new SectionSubject("Mathematics", 14l),
				new SectionSubject("English", 15l),
				new SectionSubject("Kannada", 15l),
				new SectionSubject("Hindi", 15l),
				new SectionSubject("Science", 15l),
				new SectionSubject("Social Science", 15l),
				new SectionSubject("Mathematics", 15l),
				new SectionSubject("English", 16l),
				new SectionSubject("Kannada", 16l),
				new SectionSubject("Sanskrit", 16l),
				new SectionSubject("Science", 16l),
				new SectionSubject("Social Science", 16l),
				new SectionSubject("Mathematics", 16l));
		sectionSubjectRepository.saveAll(sectionSubjects);
		
		List<ClassPeriod> classPeriods = List.of(new ClassPeriod(LocalTime.of(8, 00), LocalTime.of(9, 50)),
				new ClassPeriod(LocalTime.of(10, 00), LocalTime.of(11, 50)),
				new ClassPeriod(LocalTime.of(1, 00), LocalTime.of(2, 50)),
				new ClassPeriod(LocalTime.of(3, 00), LocalTime.of(4, 50)));
		classPeriodRepository.saveAll(classPeriods);
		
		String jsonString1 = "["
				+ "    {"
				+ "        \"studentId\": 1,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 2,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 3,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 4,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 5,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 6,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 7,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 8,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 9"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 10"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 11"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 12"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 13"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 14"
				+ "    }"
				+ "]";
		
		String jsonString2 = "["
				+ "    {"
				+ "        \"studentId\": 1,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 5,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 6,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 7,"
				+ "        \"present\": true"
				+ "    },"
				+ "    {"
				+ "        \"studentId\": 14"
				+ "    }"
				+ "]";

		List<Attendance> attendances = List.of(new Attendance(1l, 1l, 1l, LocalDate.now(), jsonString1),
				new Attendance(2l, 3l, 1l, LocalDate.of(2022, 02, 23), jsonString2));
		attendanceRepository.saveAll(attendances);
		
		List<Student> students = new ArrayList<>();
		for (int i = 1; i <= 600; i++) {
			Student student = new Student();
			student.setRollNumber(String.valueOf("AE" + 0 + String.format("%03d" , i)));
			student.setFirstName("First Name " + i);
			student.setMiddleName("Middle Name " + i);
			student.setLastName("Last Name " + i);
			if (i % 2 != 0) {
				student.setGender("Male");
			} else {
				student.setGender("Female");
			}
			student.setDob(LocalDate.of(1996, 05, 01).plusDays(i));
			student.setMobileNumber(Long.valueOf("7204929" + String.format("%03d" , i)));
			student.setProfile(null);
			student.setEmail("email" + i + "@gmail.com");
			students.add(student);
		}
		students = studentRepository.saveAll(students);
		List<SectionStudent> sectionStudents = new ArrayList<>(); 
		for (int i = 1; i <= students.size(); i++) {
			Long section = 16l;
			if (i <= 60) {
				section = 1l;
			} else if (i <= 120) {
				section = 2l;
			} else if (i <= 180) {
				section = 3l;
			} else if (i <= 240) {
				section = 4l;
			} else if (i <= 270) {
				section = 5l;
			} else if (i <= 300) {
				section = 6l;
			} else if (i <= 330) {
				section = 7l;
			} else if (i <= 360) {
				section = 8l;
			} else if (i <= 390) {
				section = 9l;
			} else if (i <= 420) {
				section = 10l;
			} else if (i <= 450) {
				section = 11l;
			} else if (i <= 480) {
				section = 12l;
			} else if (i <= 510) {
				section = 13l;
			} else if (i <= 540) {
				section = 14l;
			} else if (i <= 570) {
				section = 15l;
			}
			sectionStudents.add(new SectionStudent(new SectionStudentId(section, students.get(i-1).getId())));
		}
		sectionStudentRepository.saveAll(sectionStudents);
		
		List<Teacher> teachers = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			Teacher teacher = new Teacher();
			teacher.setTeacherNumber(String.valueOf("GAEC" + 904 + i));
			teacher.setFirstName("T First Name " + i);
			teacher.setMiddleName("T Middle Name " + i);
			teacher.setLastName("T Last Name " + i);
			if (i % 2 == 0) {
				teacher.setGender("Male");
			} else {
				teacher.setGender("Female");
			}
			teacher.setDob(LocalDate.now().plusDays(i));
			teacher.setMobileNumber(Long.valueOf("720492984" + i));
			teachers.add(teacher);
		}
		teacherRepository.saveAll(teachers);
		
		List<TeacherSubject> teacherSubjects = List.of(new TeacherSubject(1l, 1l),
				new TeacherSubject(1l, 4l),
				new TeacherSubject(1l, 7l),
				new TeacherSubject(1l, 10l),
				new TeacherSubject(2l, 2l),
				new TeacherSubject(2l, 5l),
				new TeacherSubject(2l, 8l),
				new TeacherSubject(2l, 11l),
				new TeacherSubject(3l, 3l),
				new TeacherSubject(2l, 6l),
				new TeacherSubject(2l, 9l),
				new TeacherSubject(2l, 12l),
				new TeacherSubject(3l, 13l));
		teacherSubjectRepository.saveAll(teacherSubjects);
		
		List<User> users = new ArrayList<>();
		users.add(new User(1l, "a", "a", "admin", true));
		users.add(new User(2l, "t", "t", "teacher", true));
		users.add(new User(3l, "s", "s", "student", true));
		
		userRepository.saveAll(users);
	}
}