package com.lugiaweather.api.dto;

import org.springframework.hateoas.RepresentationModel;

import com.lugiaweather.api.model.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class EnderecoDTO extends RepresentationModel<Endereco > {
	
	@NotBlank(message = "O CEP é obrigatório")
	@Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos numéricos")
	private String cep;
	
	private String logradouro;
	private String complemento;
	private String localidade;
	private String uf;
	private String estado;
	private String regiao;
	
	
	
	
	
	
	
	
	
	
	
	
	public EnderecoDTO() {
		super();
	}

	
	


	public EnderecoDTO(String cep, String logradouro, String complemento, String localidade, String uf, String estado,
			String regiao) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.localidade = localidade;
		this.uf = uf;
		this.estado = estado;
		this.regiao = regiao;
	}





	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento + ", localidade="
				+ localidade + ", uf=" + uf + ", estado=" + estado + ", regiao=" + regiao + "]";
	}
	
	
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	

	
	
	
	
}
