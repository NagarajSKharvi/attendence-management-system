package ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ams.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsernameIgnoreCase(String username);
	User findByUsernameAndPassword(String username, String password);
}