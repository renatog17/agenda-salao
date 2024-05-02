package com.renato.agendamentosalao.controller;

import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.renato.agendamentosalao.controller.dto.AgendamentoDTO;
import com.renato.agendamentosalao.controller.dto.ReagendarAgendamentoDTO;
import com.renato.agendamentosalao.domain.Agendamento;
import com.renato.agendamentosalao.domain.Cliente;
import com.renato.agendamentosalao.repository.AgendamentoRepository;
import com.renato.agendamentosalao.repository.ClienteRepository;
import com.renato.agendamentosalao.service.strategy.AgendamentoService;
import com.renato.agendamentosalao.service.strategy.RestricaoErro;

import jakarta.transaction.Transactional;
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
	@Transactional
	public ResponseEntity<?> agendar(@RequestBody @Valid AgendamentoDTO agendamentoDTO,
			UriComponentsBuilder uriComponentsBuilder) throws RestricaoErro {
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
		URI uri = uriComponentsBuilder.path("/agendamento/{id}").buildAndExpand(agendamento.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/listar/{idCliente}")
	public ResponseEntity<?> listar(@PathVariable Long idCliente, @RequestParam(defaultValue = "10") Integer size,
			@RequestParam(defaultValue = "0") Integer page) {
		Timestamp horaAtual = Timestamp.valueOf(LocalDateTime.now());

		Sort.Order order = Sort.Order.asc("horaDataInicio");

		Pageable pageable = PageRequest.of(page, size, Sort.by(order));
		Page<Agendamento> agendamentos = agendamentoRepository.findByClienteIdAndHoraDataInicioAfterAndAtivoTrue(idCliente,
				horaAtual, pageable);
		return ResponseEntity.ok(agendamentos);
	}
	
	@PutMapping("/{idAgendamento}")
	@Transactional
	public ResponseEntity<?> reagendar(@PathVariable Long idAgendamento,
			@RequestBody ReagendarAgendamentoDTO reagendarAgendamentoDTO, UriComponentsBuilder uriComponentsBuilder)
			throws RestricaoErro {

		Agendamento agendamento = agendamentoRepository.getReferenceById(idAgendamento);

		Agendamento novoAgendamento = new Agendamento(reagendarAgendamentoDTO.horaData(), agendamento.getCliente());

		if (!agendamentoService.verificarDisponibilidade(reagendarAgendamentoDTO.horaData())) {
			return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
		}

		agendamentoRepository.save(novoAgendamento);
		agendamento.setAtivo(false);
		agendamentoRepository.save(agendamento);

		URI uri = uriComponentsBuilder.path("/agendamento/{id}").buildAndExpand(agendamento.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> cancelar(@PathVariable Long id){
		Agendamento agendamento = agendamentoRepository.getReferenceById(id);
		agendamento.setAtivo(false);
		return ResponseEntity.ok().build();
	}
}
