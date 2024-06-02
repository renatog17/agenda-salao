package com.renato.agendamentosalao.service.strategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class RestricaoExpedienteTest {

	private final RestricaoExpediente restricaoExpediente = new RestricaoExpediente();
	
	@Test
	public void testIsRestritoWhenHourIsBefore() {
		LocalDateTime dateTime = LocalDateTime.of(2024, 6, 3, 8, 59);
		assertTrue(restricaoExpediente.isRestrito(dateTime));
		
	}
	
	@Test
	public void testIsRestritoWhenHourIsStart() {
		LocalDateTime dateTime = LocalDateTime.of(2024, 6, 3, 9, 00);
		assertFalse(restricaoExpediente.isRestrito(dateTime));
	}
	
	@Test
	public void testIsRestritoWHenHourIsEnd() {
		LocalDateTime dateTime = LocalDateTime.of(2024, 6, 3, 14, 59);
		assertFalse(restricaoExpediente.isRestrito(dateTime));
	}
	
	@Test
	public void testIsRestritoWhenHoursIsAfter() {
		LocalDateTime dateTime = LocalDateTime.of(2024, 6, 3, 15, 00);
		assertTrue(restricaoExpediente.isRestrito(dateTime));
	}
}
