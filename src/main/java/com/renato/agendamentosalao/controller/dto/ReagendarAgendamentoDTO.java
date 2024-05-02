package com.renato.agendamentosalao.controller.dto;

import java.time.LocalDateTime;

import com.renato.agendamentosalao.controller.dto.validacao.ValidacaoHora;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record ReagendarAgendamentoDTO(
		@FutureOrPresent
		@ValidacaoHora
		@NotNull
		LocalDateTime horaData) {

}
