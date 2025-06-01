package com.lugiaweather.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lugiaweather.api.model.User;
import com.lugiaweather.api.repository.UserRepository;

@Service
public class ClienteCashingService {

	
	@Autowired
	private UserRepository repU;
	
	
	@Cacheable(value= "User")
	public List<User> findAll(){
		return repU.findAll();
	}
	
	@Cacheable(value = "BuscaPorId", key= "#id_gerente")
	public Optional<User> findById(Integer id){
		return repU.findById(id);
	}
	
	@Cacheable(value = "buscaPaginada", key = "#req")
	public Page<User> findAll(PageRequest req) {
		return repU.findAll(req);
	}
	
}
