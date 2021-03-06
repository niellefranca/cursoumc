package br.com.daniellefranca.cursoumc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.daniellefranca.cursoumc.domain.Cidade;
import br.com.daniellefranca.cursoumc.domain.Cliente;
import br.com.daniellefranca.cursoumc.domain.Endereco;
import br.com.daniellefranca.cursoumc.domain.enums.TipoCliente;
import br.com.daniellefranca.cursoumc.dto.ClienteDTO;
import br.com.daniellefranca.cursoumc.dto.ClienteNewDTO;
import br.com.daniellefranca.cursoumc.repositories.ClienteRepository;
import br.com.daniellefranca.cursoumc.repositories.EnderecoRepository;
import br.com.daniellefranca.cursoumc.services.exception.DataIntegrityException;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepositorio;
	
	@Autowired
	private EnderecoRepository enderecoRepositorio;

	public Cliente find(Integer id) throws ObjectNotFoundException {

		Optional<Cliente> obj = clienteRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhum cliente encontrado Id:" + id + " Classe: " + Cliente.class.getName()));

	}
	
	public Cliente insert(Cliente obj) {
		obj = clienteRepositorio.save(obj);
		enderecoRepositorio.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) throws ObjectNotFoundException {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return clienteRepositorio.save(obj);
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			clienteRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException dtv) {
			throw new DataIntegrityException("Não é possíve excluir uma categoria que possui produtos");
		}

	}

	public List<Cliente> findAll() {
		List<Cliente> obj = clienteRepositorio.findAll();
		return obj;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageResquest = PageRequest.of(page, linesPerPage, Direction.fromString(direction), orderBy);
		return clienteRepositorio.findAll(pageResquest);
		
	}
	
	public Cliente fromDto(ClienteDTO clienteDto) throws ObjectNotFoundException {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);	
	}
	
	public Cliente fromDto(ClienteNewDTO clienteNewDto) throws ObjectNotFoundException {
		Cliente cliente = new Cliente(null, clienteNewDto.getNome(), clienteNewDto.getEmail(), clienteNewDto.getCpfOuCnpj(),
				                      TipoCliente.toEnum(clienteNewDto.getTipo()));
		
		Cidade cidade = new Cidade(clienteNewDto.getCidadeId(), null, null);
		
		Endereco endereco = new Endereco(null, clienteNewDto.getLogradouro(), clienteNewDto.getNumero(), clienteNewDto.getComplemento(),
				clienteNewDto.getBairro(), clienteNewDto.getCep(), cliente, cidade);
				
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteNewDto.getTelefone1());
		
		return cliente;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
