package com.lugiaweather.api.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="TBL_ENDERECO")
public class Endereco extends RepresentationModel<Endereco> {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id_endereco;
	  	private String cep;
	    private String logradouro;
	    private String complemento;
	    private String bairro;
	    private String localidade; 
	    private String uf;
	    
	    
	    
	    
		public Long getId_endereco() {
			return id_endereco;
		}
		public void setId_endereco(Long id_endereco) {
			this.id_endereco = id_endereco;
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
		public String getBairro() {
			return bairro;
		}
		public void setBairro(String bairro) {
			this.bairro = bairro;
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
	    
	    
	    
	    
	    
	    
	
	
	}
	

