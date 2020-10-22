package br.com.daniellefranca.cursoumc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.daniellefranca.cursoumc.domain.Categoria;
import br.com.daniellefranca.cursoumc.domain.Cidade;
import br.com.daniellefranca.cursoumc.domain.Cliente;
import br.com.daniellefranca.cursoumc.domain.Endereco;
import br.com.daniellefranca.cursoumc.domain.Estado;
import br.com.daniellefranca.cursoumc.domain.ItemPedido;
import br.com.daniellefranca.cursoumc.domain.Pagamento;
import br.com.daniellefranca.cursoumc.domain.PagamentoComBoleto;
import br.com.daniellefranca.cursoumc.domain.PagamentoComCartao;
import br.com.daniellefranca.cursoumc.domain.Pedido;
import br.com.daniellefranca.cursoumc.domain.Produto;
import br.com.daniellefranca.cursoumc.domain.enums.EstadoPagamento;
import br.com.daniellefranca.cursoumc.domain.enums.TipoCliente;
import br.com.daniellefranca.cursoumc.repositories.CategoriaRepository;
import br.com.daniellefranca.cursoumc.repositories.CidadeRepository;
import br.com.daniellefranca.cursoumc.repositories.ClienteRepository;
import br.com.daniellefranca.cursoumc.repositories.EnderecoRepository;
import br.com.daniellefranca.cursoumc.repositories.EstadoRepository;
import br.com.daniellefranca.cursoumc.repositories.ItemPedidoRepository;
import br.com.daniellefranca.cursoumc.repositories.PagamentoRepository;
import br.com.daniellefranca.cursoumc.repositories.PedidoRepository;
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
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoumcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		Categoria cat3 = new Categoria(3, "Decoração");
		Categoria cat4 = new Categoria(4, "Pés");
		Categoria cat5 = new Categoria(5, "Higiene Pessoal");
		Categoria cat6 = new Categoria(6, "Cabelos");
		

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

		catRepositorio.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6));
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
		
		
		Cliente dani = new Cliente(null, "Danielle", "niellefranca@gmail.com", "07305269417", TipoCliente.PESSOAFISICA);
		Cliente felipe = new Cliente(null, "Kevinn Felipe", "kfeliph@gmail.com", "06902061432", TipoCliente.PESSOAFISICA);
		
		dani.getTelefones().addAll(Arrays.asList("997218622", "34822535"));
		felipe.getTelefones().add("997549332");
		
		Endereco endDani = new Endereco(null, "Rua Carceres", "381", "Bloco 15 Apt 301", "Candeias", "54430170", dani, cid3);
		Endereco endFelipe = new Endereco(null, "Rua Carceres", "381", "Bloco 15 Apt 301", "Candeias", "54430170", felipe, cid3);
	
		dani.getEnderecos().add(endDani);
		felipe.getEnderecos().add(endFelipe);
		
		clienteRepository.saveAll(Arrays.asList(dani, felipe));
		enderecoRepository.saveAll(Arrays.asList(endDani, endFelipe));
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("01/10/2020 17:50"), dani, endDani);
		Pedido ped2 = new Pedido(null, sdf.parse("01/10/2020 17:50"), felipe, endFelipe);
		
		Pagamento pgtoPed1 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped1, sdf.parse("01/10/2020 17:50"), sdf.parse("01/10/2020 17:50"));
		ped1.setPagamento(pgtoPed1);
		Pagamento pgtoPed2 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, ped2, 5);
		ped2.setPagamento(pgtoPed2);
		
		dani.getPedidos().add(ped1);
		felipe.getPedidos().add(ped2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgtoPed1, pgtoPed2));
		
		ItemPedido item1 = new ItemPedido(ped1, p1, 0.00, 1, p1.getPreco());
		ItemPedido item2 = new ItemPedido(ped1, p2, 0.00, 1, p2.getPreco());
		ItemPedido item3 = new ItemPedido(ped2, p3, 0.00, 1, p3.getPreco());
		
		ped1.getItens().addAll(Arrays.asList(item1, item2));
		ped2.getItens().addAll(Arrays.asList(item3));
		
		p1.getItens().addAll(Arrays.asList(item1));
		p2.getItens().addAll(Arrays.asList(item2));
		p3.getItens().addAll(Arrays.asList(item3));
		
		
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));
		

	}
}
