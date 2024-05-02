package com.renato.agendamentosalao.repository;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renato.agendamentosalao.domain.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

//	
//	@Query("SELECT a FROM Agendamento a WHERE (:inicio BETWEEN a.horaDataInicio AND a.horaDataFim) OR (:fim BETWEEN a.horaDataInicio AND a.horaDataFim)")
//    List<Agendamento> encontrarConflitosDeAgendamento(@Param("inicio") Timestamp inicio, @Param("fim") Timestamp fim);

	Optional<Agendamento> findByHoraDataInicio(Timestamp horaDataInicio);

}
