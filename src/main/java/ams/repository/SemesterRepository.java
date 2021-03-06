package ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
	
}