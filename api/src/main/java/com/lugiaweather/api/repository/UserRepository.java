package com.lugiaweather.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lugiaweather.api.model.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);

}
