package ams.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ams.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query(value = "SELECT s.* FROM student s INNER JOIN section_student ss ON s.id = ss.stud_id", nativeQuery = true)
	List<Student> findAllStudents();
	List<Student> findAllByIdIn(Set<Long> studentIds);
	Student findByRollNumber(String rollNumber);
}