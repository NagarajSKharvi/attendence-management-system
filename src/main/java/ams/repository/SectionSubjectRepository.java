package ams.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.SectionSubject;

@Repository
public interface SectionSubjectRepository extends JpaRepository<SectionSubject, Long> {
	
	List<SectionSubject> findAllBySectionId(Long sectionId);
	
	SectionSubject findAllBySubjectId(Long subjectId);
	List<SectionSubject> findAllBySubjectIdIn(Set<Long> subjectIds);
}