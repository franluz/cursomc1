package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Double preco;

	private ProdutoDTO(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public ProdutoDTO(Produto obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public static class ProdutoDTOBulder {
		private Integer id;
		private String nome;
		private Double preco;

		public ProdutoDTOBulder() {
			super();
		}

		public ProdutoDTOBulder id(Integer id) {
			this.id = id;
			return this;
		}

		public ProdutoDTOBulder preco(Double preco) {
			this.preco = preco;
			return this;
		}

		public ProdutoDTOBulder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public ProdutoDTO build() {
			return new ProdutoDTO(id, nome, preco);
		}
	}
}
