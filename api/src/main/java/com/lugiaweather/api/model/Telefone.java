package com.lugiaweather.api.model;

import org.springframework.hateoas.RepresentationModel;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TBL_TELEFONE")
public class Telefone extends RepresentationModel<Telefone>{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_telefone;
    private String ddd;
    private String numero;
	
	

	
	
	public Telefone(Long id_telefone, String ddd, String numero) {
		super();
		this.id_telefone = id_telefone;
		this.ddd = ddd;
		this.numero = numero;
	}





	public Long getId_telefone() {
		return id_telefone;
	}





	public void setId_telefone(Long id_telefone) {
		this.id_telefone = id_telefone;
	}





	public String getDdd() {
		return ddd;
	}





	public void setDdd(String ddd) {
		this.ddd = ddd;
	}





	public String getNumero() {
		return numero;
	}





	public void setNumero(String numero) {
		this.numero = numero;
	}





	@Override
	public String toString() {
		return "Telefone [id_telefone=" + id_telefone + ", ddd=" + ddd + ", numero=" + numero + "]";
	}







	public Telefone() {
		super();
	}
	
	

	
	
	
	
}
