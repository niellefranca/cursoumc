package br.com.daniellefranca.cursoumc.domain;

import javax.persistence.Entity;

import br.com.daniellefranca.cursoumc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numeroParcelas;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
		super(id, estadoPagamento, pedido);
		this.setNumeroParcelas(numeroParcelas);
		// TODO Auto-generated constructor stub
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
}
