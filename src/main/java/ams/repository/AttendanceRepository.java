package ams.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	
	List<Attendance> findByDate(LocalDate date);
	
	List<Attendance> findBySubjectId(Long subjectId);
	
	List<Attendance> findByTeachId(Long teachId);
	
	List<Attendance> findByPeriodId(Long periodId);
	
	List<Attendance> findByDateAndSubjectId(LocalDate date, Long subjectId);
	
}