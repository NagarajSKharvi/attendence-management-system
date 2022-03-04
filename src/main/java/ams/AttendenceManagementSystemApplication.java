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
				new StudClass("Class 7", 2022));
		studClassRepository.saveAll(studClasses);
		
		List<ClassSection> classSections = List.of(new ClassSection("Section A", 1l),
				new ClassSection("Section B", 1l),
				new ClassSection("Section A", 2l),
				new ClassSection("Section B", 2l),
				new ClassSection("Section A", 3l));
		classSectionRepository.saveAll(classSections);
		
		List<SectionSubject> sectionSubjects = List.of(new SectionSubject("English", 1l),
				new SectionSubject("Hindi", 1l),
				new SectionSubject("Kannada", 1l),
				new SectionSubject("English", 2l),
				new SectionSubject("Hindi", 2l),
				new SectionSubject("Sanskrit", 2l),
				new SectionSubject("English", 3l),
				new SectionSubject("Hindi", 3l),
				new SectionSubject("Telugu", 3l));
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

		List<Attendance> attendances = List.of(new Attendance(1l, 1l, 1l, LocalDate.of(2022, 02, 23), jsonString1),
				new Attendance(2l, 3l, 1l, LocalDate.of(2022, 02, 23), jsonString2));
		attendanceRepository.saveAll(attendances);
		
		List<Student> students = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			Student student = new Student();
			student.setRollNumber(String.valueOf("13GAEC" + 904 + i));
			student.setFirstName("First Name " + i);
			student.setMiddleName("Middle Name " + i);
			student.setLastName("Last Name " + i);
			if (i % 2 == 0) {
				student.setGender("Male");
			} else {
				student.setGender("Female");
			}
			student.setDob(LocalDate.now().plusDays(i));
			student.setMobileNumber(Long.valueOf("720492984" + i));
			student.setProfile(null);
			students.add(student);
		}
		students = studentRepository.saveAll(students);
		List<SectionStudent> sectionStudents = new ArrayList<>(); 
		for (int i = 1; i <= students.size(); i++) {
			Long section = 1l;
			if (i > 15) {
				section = 3l;
			} else if (i > 10) {
				section = 2l;
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
				new TeacherSubject(1l, 7l));
		teacherSubjectRepository.saveAll(teacherSubjects);
		
		List<User> users = new ArrayList<>();
		users.add(new User(1l, "kharvi", "123", "student", true));
		users.add(new User(2l, "kharvi2", "123", "student", true));
		userRepository.saveAll(users);
	}
}