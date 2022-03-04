package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.TeacherSubject;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
	
	List<TeacherSubject> findByTeacherSubjectIdTeachId(Long teachId);
}