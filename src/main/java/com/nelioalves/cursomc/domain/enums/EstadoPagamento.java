package com.nelioalves.cursomc.domain.enums;

public enum EstadoPagamento {
     PENDENTE(1,"Pendente"),QUITADO(2,"Quitado"),CANCELADO(1,"Cancelado");
	private int id;
	private String nome;
	/**
	 * @param id
	 * @param nome
	 */
	private EstadoPagamento(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (EstadoPagamento pag : EstadoPagamento.values()) {
			if (id.equals(pag.getId())) {
				return pag;
			}
		}
		throw new IllegalArgumentException("Enum Nao encontrado");

	}

	public int getId() {
		return id;
	}

	
	public String getNome() {
		return nome;
	}

	
}
