package com.renato.agendamentosalao.service.strategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.renato.agendamentosalao.service.AgendamentoRestricao;

@Service
public class RestricaoConflito implements AgendamentoRestricao {

	public Boolean isRestrito(LocalDateTime horaData) {
		boolean existeAgendamento = false;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_salao", "root", "admin");
			String sql = "SELECT COUNT(*) FROM agendamento WHERE hora_data_inicio = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			// Converter LocalDateTime para String no formato esperado pelo banco de dados
			String dataFormatada = horaData.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			stmt.setString(1, dataFormatada);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					existeAgendamento = true;
				}
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeAgendamento;
	}

}
