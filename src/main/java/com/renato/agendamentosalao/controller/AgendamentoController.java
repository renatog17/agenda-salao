package com.renato.agendamentosalao.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.agendamentosalao.controller.dto.AgendamentoDTO;
import com.renato.agendamentosalao.domain.Agendamento;
import com.renato.agendamentosalao.domain.Cliente;
import com.renato.agendamentosalao.repository.AgendamentoRepository;
import com.renato.agendamentosalao.repository.ClienteRepository;
import com.renato.agendamentosalao.service.strategy.AgendamentoService;
import com.renato.agendamentosalao.service.strategy.RestricaoErro;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	@Autowired
	private AgendamentoService agendamentoService;

	@PostMapping
	public ResponseEntity<?> agendar(@RequestBody @Valid AgendamentoDTO agendamentoDTO) throws RestricaoErro {
		// validação hora, ela tem que ser hh:00 ou hh:30
		// vlaidação numero telefone
		// data precisa ser no futuro
		if (!agendamentoService.verificarDisponibilidade(agendamentoDTO.horaData())) {
			return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
		}

		Optional<Cliente> cliente = clienteRepository.findByCelular(agendamentoDTO.celular());
		Agendamento agendamento;
		if (cliente.isPresent()) {
			agendamento = new Agendamento(agendamentoDTO.horaData(), cliente.get());
		} else {
			Cliente newCliente = new Cliente(agendamentoDTO.nome(), agendamentoDTO.celular());
			clienteRepository.save(newCliente);
			agendamento = new Agendamento(agendamentoDTO.horaData(), newCliente);
		}
		agendamentoRepository.save(agendamento);
		return ResponseEntity.created(null).build();
	}
}
