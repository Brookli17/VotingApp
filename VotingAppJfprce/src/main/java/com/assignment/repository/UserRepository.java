package com.assignment.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUsernameAndPassword(String username, String password);
	
	Optional<User> findByUsername(String username);
}
