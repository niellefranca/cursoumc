package br.com.daniellefranca.cursoumc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.daniellefranca.cursoumc.domain.Categoria;
import br.com.daniellefranca.cursoumc.repositories.CategoriaRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository catRepositorio;

	public Categoria buscar(Integer id) throws ObjectNotFoundException {

		Optional<Categoria> obj = catRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhuma categoria encontrada Id:" + id + " Classe: " + Categoria.class.getName()));

	}

	public Categoria insert(Categoria obj) {
		return catRepositorio.save(obj);
	}

	/*public List<Categoria> buscarAll(){
		
		List<Categoria> listaCategoria = catRepositorio.findAll();
		
		return listaCategoria;
	}*/
	
}
