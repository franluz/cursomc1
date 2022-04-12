package com.nelioalves.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.services.PedidoService;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


 
 
 
@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(Integer id) throws ObjectNotFoundException {
		if (id == null) {
			return null;
		}
		return ResponseEntity.ok().body(pedidoService.buscar(id));
	}
 
	
}
