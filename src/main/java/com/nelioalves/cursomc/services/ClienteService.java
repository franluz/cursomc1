package com.nelioalves.cursomc.services;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id){
		Optional<Cliente> obj= clienteRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Cliente nao encontrado com o ID"+id));
	}
	public Cliente update(Cliente obj)  throws ObjectNotFoundException {
		Cliente newObj = find(obj.getId());
		updateDate(newObj,obj);
		return clienteRepository.save(obj);
	}
	public Page<Cliente> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		PageRequest pagRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction),orderBy);
		return clienteRepository.findAll(pagRequest);
		
	}
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}
	public void delete(Integer id)  {
		find(id);
		try {
			clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException ex) {
			 throw new DataIntegrityException("Não é possivel excluir cliente pois o mesmo possui pedidos"); 
		}
	}
	public Cliente fromDTO(ClienteDTO dto) {
		return  new Cliente(dto.getId(),dto.getNome(),dto.getEmail(),null,null);
	}
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cli= new Cliente(null,dto.getNome(),dto.getEmail(),dto.getCpfOuCnpj(),TipoCliente.toEnum(dto.getTipoCliente()));
		Cidade cidade = new Cidade(dto.getCidadeId(),null,null);
		Endereco end  = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(),
				cli, cidade);
		cli.getEnderecos().add(end);
		
		Stream.of(dto.getTelefone1(), dto.getTelefone2(), dto.getTelefone3())
			.filter(Objects::nonNull).forEach(cli.getTelefones()::add);
		return cli;
		
	}
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
}
