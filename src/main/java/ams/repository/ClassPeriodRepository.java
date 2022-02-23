package ams.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.ClassPeriod;

@Repository
public interface ClassPeriodRepository extends JpaRepository<ClassPeriod, Long> {

	List<ClassPeriod> findAllByPeriodIdIn(Set<Long> periodIds);
}