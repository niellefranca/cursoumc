package br.com.daniellefranca.cursoumc.resources;

import java.beans.PersistenceDelegate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.daniellefranca.cursoumc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public Categoria listar() {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("configbanco");
		EntityManager em = emf.createEntityManager();
		Categoria c1 = em.find(Categoria.class, 1);
		
		return c1;
	}
}
