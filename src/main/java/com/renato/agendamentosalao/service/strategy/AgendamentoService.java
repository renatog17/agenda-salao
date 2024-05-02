package com.renato.agendamentosalao.service.strategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.agendamentosalao.repository.AgendamentoRepository;
import com.renato.agendamentosalao.service.AgendamentoRestricao;

@Service
public class AgendamentoService {

	@Autowired
	AgendamentoRepository agendamentoRepository;
	
	public Boolean verificarDisponibilidade(LocalDateTime dataHora) throws RestricaoErro {
		AgendamentoRestricao restricaoDia = new RestricaoDia();
		AgendamentoRestricao restricaoExpediente = new RestricaoExpediente();
		AgendamentoRestricao restricaoConflito = new RestricaoConflito();
		
		List<AgendamentoRestricao> restricoes = new ArrayList<AgendamentoRestricao>();
		restricoes.add(restricaoDia);
		restricoes.add(restricaoExpediente);
		restricoes.add(restricaoConflito);
		
		for (AgendamentoRestricao agendamentoRestricao : restricoes) {
			if(agendamentoRestricao.isRestrito(dataHora)) {
				throw new RestricaoErro(agendamentoRestricao.getClass().getSimpleName());
//				return false;
			}
		}
		return true;
	}

	
}
