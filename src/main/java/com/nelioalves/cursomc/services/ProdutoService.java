package com.nelioalves.cursomc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	public Page<Produto> search(String nome,List<Integer> ids,Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pagRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pagRequest);
	}
	public Produto insert(Produto produto) {
		return repo.save(produto);
	}
	public Produto buscar(Integer id) throws ObjectNotFoundException{
		if(id==null) {
			return null;
		}
		 Optional<Produto> prod = repo.findById(id);
		 return prod.orElseThrow(()-> new ObjectNotFoundException("Erro ao achar o pedido"));
	}
	public Page<Produto> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pagRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return repo.findAll(pagRequest);
		
	}

}
