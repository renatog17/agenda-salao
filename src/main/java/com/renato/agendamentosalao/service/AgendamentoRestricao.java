package com.renato.agendamentosalao.service;

import java.time.LocalDateTime;

public interface AgendamentoRestricao {

	Boolean isRestrito(LocalDateTime horaData);
}
