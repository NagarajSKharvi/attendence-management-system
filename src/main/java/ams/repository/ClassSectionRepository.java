package ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.ClassSection;

@Repository
public interface ClassSectionRepository extends JpaRepository<ClassSection, Long> {
	
}