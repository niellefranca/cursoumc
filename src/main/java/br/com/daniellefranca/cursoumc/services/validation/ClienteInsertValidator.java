package br.com.daniellefranca.cursoumc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.daniellefranca.cursoumc.domain.Cliente;
import br.com.daniellefranca.cursoumc.domain.enums.TipoCliente;
import br.com.daniellefranca.cursoumc.dto.ClienteNewDTO;
import br.com.daniellefranca.cursoumc.repositories.ClienteRepository;
import br.com.daniellefranca.cursoumc.repositories.exception.FieldMessage;
import br.com.daniellefranca.cursoumc.services.validation.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		if(objDto.getTipo() == TipoCliente.PESSOAFISICA.getCod() && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfouCnpj", "Cpf Inválido"));
		}
		if(objDto.getTipo() == TipoCliente.PESSOAJURIDICA.getCod() && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfouCnpj", "Cnpj Inválido"));
		}

		Cliente clienteaux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(clienteaux != null) {
			list.add(new FieldMessage("email", "Email já existe cadastrado"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}