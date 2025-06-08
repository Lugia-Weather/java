package com.lugiaweather.api.dto;

import org.springframework.hateoas.RepresentationModel;

import com.lugiaweather.api.model.Endereco;
import com.lugiaweather.api.model.Telefone;
import com.lugiaweather.api.model.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;




public class UserDTO extends RepresentationModel<User> {
	private long id;
	

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
	private String nome;
    
    @NotNull(message = "O telefone é obrigatório")
    @Valid
	private Telefone telefone;
    
	
	@Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
	private String email;
	
	@NotBlank(message = "A senha é obrigatória")
	private String senha;
	
	@NotNull(message = "Um endereço válido é necessário")
	@Valid
	private Endereco endereco;
	
	
	

	
	


	public UserDTO(long id, String nome, Telefone telefone, String email, String senha,
			Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
	
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}


	
	
	

	public UserDTO() {
		super();
	}






	public UserDTO(User i) {
	    this.id = i.getId_usuario();
	    this.nome = i.getNome();
	    this.telefone = i.getTelefone();
	    this.email = i.getEmail();
	    this.senha = i.getSenha();
	    this.endereco = i.getEndereco();
	}






	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", senha="
				+ senha + ", endereco=" + endereco + "]";
	}






	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
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

}
