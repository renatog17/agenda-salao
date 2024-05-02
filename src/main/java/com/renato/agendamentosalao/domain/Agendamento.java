package com.renato.agendamentosalao.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "hora_data_inicio")
	private Timestamp horaDataInicio;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Agendamento() {
		// TODO Auto-generated constructor stub
	}

	public Agendamento(LocalDateTime horaDataInicio, Cliente cliente) {
		super();
		this.horaDataInicio = Timestamp.valueOf(horaDataInicio);
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}
	
	public LocalDateTime getHoraDataInicio() {
		return horaDataInicio.toLocalDateTime();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		return Objects.equals(id, other.id);
	}

}
