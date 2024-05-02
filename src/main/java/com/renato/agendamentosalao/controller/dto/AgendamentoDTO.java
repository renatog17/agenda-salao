package com.renato.agendamentosalao.controller.dto;

import java.time.LocalDateTime;

import com.renato.agendamentosalao.controller.dto.validacao.ValidacaoHora;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AgendamentoDTO(
		@NotEmpty
		String nome,
		@NotEmpty
		@Size(min = 11, max = 11, message = "Formato inválido para telefone")
		String celular,
		@NotNull
		@ValidacaoHora
		LocalDateTime horaData) {
}
