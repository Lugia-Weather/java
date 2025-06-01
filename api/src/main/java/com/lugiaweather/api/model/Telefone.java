package com.lugiaweather.api.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Embeddable;

@Embeddable
public class Telefone extends RepresentationModel<Telefone>{

	private Integer id_telefone;
	private Integer ddd;
	private String telefone;
	
	
	

	
	
	public Telefone(Integer id_telefone, Integer ddd, String telefone) {
		super();
		this.id_telefone = id_telefone;
		this.ddd = ddd;
		this.telefone = telefone;
	}
	
	
	
	public Telefone() {
		super();
	}



	public Integer getDdd() {
		return ddd;
	}
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Integer getId_telefone() {
		return id_telefone;
	}
	public void setId_telefone(Integer id_telefone) {
		this.id_telefone = id_telefone;
	}

	
	
	
	

	@Override
	public String toString() {
		return "Telefone [id_telefone=" + id_telefone + ", ddd=" + ddd + ", telefone=" + telefone + "]";
	}
	
	
	
	
	
	
	
}
