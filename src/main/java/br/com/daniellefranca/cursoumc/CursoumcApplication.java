package br.com.daniellefranca.cursoumc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.daniellefranca.cursoumc.domain.Categoria;
import br.com.daniellefranca.cursoumc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoumcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository catRepositorio;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoumcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(0, "Departamento Pessoal");
		Categoria cat2 = new Categoria(0, "Recursos Humanos");
		
		catRepositorio.saveAll(Arrays.asList(cat1, cat2));
		
		
		
	}

}
