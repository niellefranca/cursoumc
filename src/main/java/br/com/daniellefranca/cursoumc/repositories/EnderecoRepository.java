package br.com.daniellefranca.cursoumc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.daniellefranca.cursoumc.domain.Categoria;
import br.com.daniellefranca.cursoumc.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	
}
