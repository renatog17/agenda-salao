package com.renato.agendamentosalao.controller.dto;

import java.time.LocalDateTime;

import com.renato.agendamentosalao.domain.Agendamento;

public record DadosAgendamentoDTO(
		String cliente,
		String celular,
		LocalDateTime agendamento) {

	DadosAgendamentoDTO(Agendamento agendamento){
		this(agendamento.getCliente().getNome(), agendamento.getCliente().getCelular(), agendamento.getHoraDataInicio());
	}
	
}
