package com.lugiaweather.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lugiaweather.api.model.User;



public interface UserRepository extends JpaRepository<User, Integer>{

}
