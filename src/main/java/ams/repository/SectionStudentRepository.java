package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.SectionStudent;

@Repository
public interface SectionStudentRepository extends JpaRepository<SectionStudent, Long> {
	
	SectionStudent findAllBySectionStudentIdStudId(Long studId);
	List<SectionStudent> findAllBySectionStudentIdSectionId(Long sectionId);
}