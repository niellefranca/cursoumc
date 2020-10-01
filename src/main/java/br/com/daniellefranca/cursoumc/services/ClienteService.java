package br.com.daniellefranca.cursoumc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.daniellefranca.cursoumc.domain.Categoria;
import br.com.daniellefranca.cursoumc.domain.Cliente;
import br.com.daniellefranca.cursoumc.repositories.ClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepositorio;

	public Cliente buscar(Integer id) throws ObjectNotFoundException {

		Optional<Cliente> obj = clienteRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhum cliente encontrado Id:" + id + " Classe: " + Cliente.class.getName()));

	}


}
