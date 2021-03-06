package com.nelioalves.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.services.PedidoService;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(Integer id) throws ObjectNotFoundException {
		if (id == null) {
			return null;
		}
		return ResponseEntity.ok().body(pedidoService.find(id));
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public  ResponseEntity<?> insert(@Valid @RequestBody PedidoDTO obj) {
//		return ResponseEntity.ok().body(pedidoService.insert(pedidoService.fromDTO(obj)));
//	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = pedidoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(
													@RequestParam(name="page",defaultValue="0") Integer page,
													@RequestParam(name="linesPerPage",defaultValue="24") Integer linesPerPage,
													@RequestParam(name="orderBy",defaultValue="instante") String orderBy,
													@RequestParam(name="direction",defaultValue="DESC")String direction) throws ObjectNotFoundException {
		Page<Pedido> list = pedidoService.findPage(page, linesPerPage, orderBy, direction);
		return   ResponseEntity.ok().body(list);
	} 
    


}
