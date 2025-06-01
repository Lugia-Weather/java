package com.lugiaweather.api.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TelefoneDTO {

	private Integer id_telefone;
	
	@NotNull(message = "O DDD é obrigatório")
	private Integer ddd;
	
	@Pattern(regexp = "\\d{9}", message = "O telefone deve conter 9 dígitos numéricos")
	private String telefone;
	
	
	public TelefoneDTO(Integer id_telefone,Integer ddd, String telefone) {
		super();
		this.id_telefone= id_telefone;
		this.ddd = ddd;
		this.telefone = telefone;
	}
	
	public TelefoneDTO() {
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
		return "TelefoneDTO [id_telefone=" + id_telefone + ", ddd=" + ddd + ", telefone=" + telefone + "]";
	}

	
	
	
	
	
}
