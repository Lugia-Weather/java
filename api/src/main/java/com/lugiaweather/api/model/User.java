package com.lugiaweather.api.model;


import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Pattern;


@Entity
@Table(name ="TBL_USUARIO")
public class User extends RepresentationModel<User>  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id_usuario;
	
	
	
	
	
	
	
	
	@NotEmpty(message= "Não é permitido a inserção de um usuario sem seu nome.")
	@Column(name="nome")
	private String nome;
	
	
	
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_telefone")
	private Telefone telefone;
	
	
	
	
	
	@NotEmpty(message= "Não é permitido a inserção de cliente com email vazio.")
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Formato de email inválido.")
	@Column(name="email")
	private String email;
	
	
	
	
	
	@NotEmpty(message= "Não é permitido a inserção de um gerente sem senha.")
	@Pattern(
	   regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
	   message = "A senha deve ter no mínimo 8 caracteres, com ao menos uma letra maiúscula, uma minúscula, um número e um caractere especial."
	    )
	@Column(name="senha")
	private String senha;
	
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;



	
	
	
	
	
	
	

	public Long getId_usuario() {
		return id_usuario;
	}




	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public Telefone getTelefone() {
		return telefone;
	}




	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getSenha() {
		return senha;
	}




	public void setSenha(String senha) {
		this.senha = senha;
	}




	public Endereco getEndereco() {
		return endereco;
	}




	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}




	@Override
	public String toString() {
		return "User [id_usuario=" + id_usuario + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email
				+ ", senha=" + senha + ", endereco=" + endereco + "]";
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
}
