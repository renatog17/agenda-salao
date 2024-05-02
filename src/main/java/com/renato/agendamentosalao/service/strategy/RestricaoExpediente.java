package com.renato.agendamentosalao.service.strategy;

import java.time.LocalDateTime;

import com.renato.agendamentosalao.service.AgendamentoRestricao;

public class RestricaoExpediente implements AgendamentoRestricao{

	@Override
	public Boolean isRestrito(LocalDateTime horaData) {
		Integer tempoEmMinutos = (horaData.getHour() * 60) + horaData.getMinute();
		Integer inicioExpediente = 9 * 60;
		Integer fimExpediente = (15*60)-1;
		
		if(tempoEmMinutos>=inicioExpediente && tempoEmMinutos<=fimExpediente)
			return false;
		
		return true;
	}

}
