package ams.attendence.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.attendence.management.system.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}