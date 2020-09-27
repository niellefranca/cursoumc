package br.com.daniellefranca.cursoumc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.daniellefranca.cursoumc.domain.Categoria;
import br.com.daniellefranca.cursoumc.domain.Cidade;
import br.com.daniellefranca.cursoumc.domain.Estado;
import br.com.daniellefranca.cursoumc.domain.Produto;
import br.com.daniellefranca.cursoumc.repositories.CategoriaRepository;
import br.com.daniellefranca.cursoumc.repositories.CidadeRepository;
import br.com.daniellefranca.cursoumc.repositories.EstadoRepository;
import br.com.daniellefranca.cursoumc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoumcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository catRepositorio;
	@Autowired
	ProdutoRepository prodRepositorio;
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoumcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");

		Produto p1 = new Produto(1, "Computador", 1399);
		Produto p2 = new Produto(2, "Impressora", 3999);
		Produto p3 = new Produto(3, "Mouse", 3799);

		prodRepositorio.saveAll(Arrays.asList(p1,p2, p3));
		
		// associo os produtos as categorias
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		catRepositorio.saveAll(Arrays.asList(cat1, cat2));
		prodRepositorio.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Paraíba");
		
		Cidade cid1 = new Cidade(null, "Recife", est1);
		Cidade cid2 = new Cidade(null, "João Pessoa", est2);
		Cidade cid3 = new Cidade(null, "Jaboatão dos Guararapes", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1,cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		

	}
}
