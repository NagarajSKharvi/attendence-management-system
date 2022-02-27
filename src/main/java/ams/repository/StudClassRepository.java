package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.StudClass;

@Repository
public interface StudClassRepository extends JpaRepository<StudClass, Long> {
	
	List<StudClass> findByClassYear(Integer classYear);
}