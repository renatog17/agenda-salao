package com.renato.agendamentosalao.service.strategy;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.renato.agendamentosalao.service.AgendamentoRestricao;

public class RestricaoDia implements AgendamentoRestricao{

	@Override
	public Boolean isRestrito(LocalDateTime horaData) {
		DayOfWeek diaDaSemana = horaData.getDayOfWeek();
		if(diaDaSemana != DayOfWeek.SUNDAY && diaDaSemana != DayOfWeek.SATURDAY)
			return false;
		return true;
	}

}
