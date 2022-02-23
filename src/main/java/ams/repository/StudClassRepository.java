package ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.StudClass;

@Repository
public interface StudClassRepository extends JpaRepository<StudClass, Long> {
	
}