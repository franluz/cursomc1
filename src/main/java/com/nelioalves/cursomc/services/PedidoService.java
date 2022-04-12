package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) throws ObjectNotFoundException{
		if(id==null) {
			return null;
		}
		 Optional<Pedido> ped = pedidoRepository.findById(id);
		 return ped.orElseThrow(()-> new ObjectNotFoundException("Erro ao achar o pedido"));
	}
	
	public Page<Pedido> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pagRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return pedidoRepository.findAll(pagRequest);
		
	}
}
