package com.lms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
