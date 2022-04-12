package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4403826626041585064L;
	private Integer id;
	@NotEmpty
	@Length(min=5,max=80,message="O tamanho deve ter entre 5 e 80 caracteres")
	private String nome;
	@NotEmpty(message="Preenchimento obrigatorio")
	@Email(message="Email invalido")
	private String email;
	public ClienteDTO(Integer id, String nome, String email, String cpfOuCnpj, Integer tipoCliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	public ClienteDTO(Cliente cli) {
		super();
		this.id = cli.getId();
		this.nome = cli.getNome();
		this.email = cli.getEmail();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
