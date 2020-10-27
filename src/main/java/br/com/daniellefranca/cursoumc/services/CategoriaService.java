package br.com.daniellefranca.cursoumc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.daniellefranca.cursoumc.domain.Categoria;
import br.com.daniellefranca.cursoumc.domain.Cliente;
import br.com.daniellefranca.cursoumc.dto.CategoriaDTO;
import br.com.daniellefranca.cursoumc.repositories.CategoriaRepository;
import br.com.daniellefranca.cursoumc.services.exception.DataIntegrityException;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository catRepositorio;

	public Categoria find(Integer id) throws ObjectNotFoundException {

		Optional<Categoria> obj = catRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Nenhuma categoria encontrada Id:" + id + " Classe: " + Categoria.class.getName()));

	}
	
	public Categoria insert(Categoria obj) {
		return catRepositorio.save(obj);
	}

	public Categoria update(Categoria obj) throws ObjectNotFoundException {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return catRepositorio.save(obj);
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);
		try {
			catRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException dtv) {
			throw new DataIntegrityException("Não é possíve excluir porque há entidades relacionadas.");
		}

	}

	public List<Categoria> findAll() {
		List<Categoria> obj = catRepositorio.findAll();
		return obj;
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageResquest = PageRequest.of(page, linesPerPage, Direction.fromString(direction), orderBy);
		return catRepositorio.findAll(pageResquest);
		
	}
	
	public Categoria fromDto(CategoriaDTO categoriaDto) {
		return new Categoria(categoriaDto.getId(), categoriaDto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
