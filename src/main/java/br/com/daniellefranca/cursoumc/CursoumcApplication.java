package br.com.daniellefranca.cursoumc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.daniellefranca.cursoumc.domain.Categoria;

@SpringBootApplication
public class CursoumcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoumcApplication.class, args);
	}

}
