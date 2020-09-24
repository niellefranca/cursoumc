package br.com.daniellefranca.cursoumc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.daniellefranca.cursoumc.domain.Categoria;
import br.com.daniellefranca.cursoumc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository catRepositorio;

	public Categoria buscar(Integer id) {

		Optional<Categoria> obj = catRepositorio.findById(id);
		return obj.orElse(null);

	}

}
