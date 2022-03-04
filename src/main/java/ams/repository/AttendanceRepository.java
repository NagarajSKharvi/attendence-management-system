package ams.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	
	List<Attendance> findByDate(LocalDate date);
	List<Attendance> findBySubjectId(Long subjectId);
	List<Attendance> findBySubjectIdIn(Set<Long> subjectIds);
	List<Attendance> findByTeachId(Long teachId);
	List<Attendance> findByPeriodId(Long periodId);
	
	List<Attendance> findByPeriodIdAndSubjectId(Long periodId, Long subjectId);
	List<Attendance> findByPeriodIdAndTeachId(Long periodId, Long teachId);
	List<Attendance> findByPeriodIdAndDate(Long periodId, LocalDate date);
	List<Attendance> findBySubjectIdAndTeachId(Long subjectId, Long teachId);
	List<Attendance> findBySubjectIdAndDate(Long subjectId, LocalDate date);
	List<Attendance> findByTeachIdAndDate(Long teachId, LocalDate date);
	List<Attendance> findByPeriodIdAndSubjectIdAndTeachId(Long periodId, Long subjectId, Long teachId);
	List<Attendance> findByPeriodIdAndSubjectIdAndDate(Long periodId, Long subjectId, LocalDate date);
	List<Attendance> findByPeriodIdAndTeachIdAndDate(Long periodId, Long teachId, LocalDate date);
	List<Attendance> findBySubjectIdAndTeachIdAndDate(Long subjectId, Long teachId, LocalDate date);
	List<Attendance> findByPeriodIdAndSubjectIdAndTeachIdAndDate(Long periodId, Long subjectId, Long teachId, LocalDate date);
}