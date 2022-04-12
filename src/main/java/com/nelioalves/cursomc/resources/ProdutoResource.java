package com.nelioalves.cursomc.resources;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.dto.ProdutoDTO;
import com.nelioalves.cursomc.resources.utils.URL;
import com.nelioalves.cursomc.services.ProdutoService;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(Integer id) throws ObjectNotFoundException {
		if (id == null) {
			return null;
		}
		return ResponseEntity.ok().body(produtoService.buscar(id));
	}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
													@RequestParam(name="nome",defaultValue="") String nome,
													@RequestParam(name="categorias",defaultValue="") String categorias,
													@RequestParam(name="page",defaultValue="0") Integer page,
													@RequestParam(name="linesPerPage",defaultValue="24") Integer linesPerPage,
													@RequestParam(name="orderBy",defaultValue="nome") String orderBy,
													@RequestParam(name="direction",defaultValue="ASC")String direction)
															throws ObjectNotFoundException, UnsupportedEncodingException {
		Page<Produto> list = produtoService.search(URL.decodeParam(nome),URL.decodeIntList(categorias), page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj-> new ProdutoDTO(obj));
		return   ResponseEntity.ok().body(listDTO);
	} 
}
