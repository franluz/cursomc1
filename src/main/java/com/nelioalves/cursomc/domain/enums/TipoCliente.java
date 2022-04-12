package com.nelioalves.cursomc.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Fisica"), PESSOAJURIDICA(2, "Pessoa Juridica");
	private int cod;
	private String nome;

	/**
	 * @param cod
	 * @param nome
	 */
	private TipoCliente(int cod, String nome) {
		this.cod = cod;
		this.nome = nome;
	}

	public int getCod() {
		return cod;
	}

	
	public String getNome() {
		return nome;
	}

	public static TipoCliente toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for(TipoCliente x:TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido:" + cod);
	}
}
