package br.com.daniellefranca.cursoumc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.daniellefranca.cursoumc.domain.Categoria;
import br.com.daniellefranca.cursoumc.repositories.CategoriaRepository;
import br.com.daniellefranca.cursoumc.services.exception.DataIntegrityException;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository catRepositorio;

	public Categoria find(Integer id) throws ObjectNotFoundException {

		Optional<Categoria> obj = catRepositorio.findById(id);
		return obj.orElseThrow(() -> 
		       new ObjectNotFoundException("Nenhuma categoria encontrada Id:" + id + " Classe: " + Categoria.class.getName()));

	}

	public Categoria insert(Categoria obj) {
		return catRepositorio.save(obj);
	}

	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		find(obj.getId());
		return catRepositorio.save(obj);
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			catRepositorio.deleteById(id);
		}catch(DataIntegrityViolationException dtv) {
			throw new DataIntegrityException("Não é possíve excluir uma categoria que possui produtos");	
		}
		
	}
	
}
