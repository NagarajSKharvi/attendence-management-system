package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.ClassSection;

@Repository
public interface ClassSectionRepository extends JpaRepository<ClassSection, Long> {
	
	List<ClassSection> findByClassId(Long classId);
}