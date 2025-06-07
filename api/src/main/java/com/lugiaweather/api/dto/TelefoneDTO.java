package com.lugiaweather.api.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TelefoneDTO {

	private Integer id_telefone;
	
	@NotNull(message = "O DDD é obrigatório")
	private String ddd;
	
	@Pattern(regexp = "\\d{9}", message = "O telefone deve conter 9 dígitos numéricos")
	private String numero;
	
	
	public TelefoneDTO(Integer id_telefone,String ddd, String numero) {
		super();
		this.id_telefone= id_telefone;
		this.ddd = ddd;
		this.numero = numero;
	}
	
	public TelefoneDTO() {
		super();
	}
	
	
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getTelefone() {
		return numero;
	}
	public void setTelefone(String numero) {
		this.numero = numero;
	}
	
	public Integer getId_telefone() {
		return id_telefone;
	}

	public void setId_telefone(Integer id_telefone) {
		this.id_telefone = id_telefone;
	}

	@Override
	public String toString() {
		return "TelefoneDTO [id_telefone=" + id_telefone + ", ddd=" + ddd + ", telefone=" + numero + "]";
	}

	
	
	
	
	
}
