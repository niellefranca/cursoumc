package br.com.daniellefranca.cursoumc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.daniellefranca.cursoumc.domain.Pedido;
import br.com.daniellefranca.cursoumc.repositories.PedidoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepositorio;

	public Pedido find(Integer id) throws ObjectNotFoundException {

		Optional<Pedido> obj = pedidoRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhum pedido encontrado Id:" + id + " Classe: " + Pedido.class.getName()));

	}

}
