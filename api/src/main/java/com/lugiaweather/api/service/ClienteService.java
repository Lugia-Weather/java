package com.lugiaweather.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lugiaweather.api.dto.UserDTO;
import com.lugiaweather.api.model.User;





@Service
public class ClienteService {

	


	
	@Autowired
	private ClienteCashingService cacheU;
	
	
	
	@Transactional(readOnly = true)
	public Page<UserDTO>paginar(PageRequest req){
		
		Page<User> usuarios = cacheU.findAll(req);
		
		return usuarios.map(i -> new UserDTO(i));
	}
	
}
