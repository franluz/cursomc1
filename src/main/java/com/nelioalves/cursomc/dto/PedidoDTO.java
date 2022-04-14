package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pagamento;

public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private  Date instante;
	private Pagamento pagamento;
	
	public PedidoDTO(Date instante, Pagamento pagamento, Cliente cliente, Endereco enderecoDeEntrega,
			Set<ItemPedido> itens) {
		super();
		this.instante = instante;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.itens = itens;
	}
	private Cliente cliente;
	private Endereco enderecoDeEntrega;
	private Set<ItemPedido> itens= new HashSet<ItemPedido>();
	public Date getInstante() {
		return instante;
	}
	public void setInstante(Date instante) {
		this.instante = instante;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}
	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	public Set<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
}
