package com.renato.agendamentosalao.service.strategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class RestricaoDiaTest {

	private final RestricaoDia restricaoDia = new RestricaoDia();
	
	@Test
	public void testIsRestritoWhenDayisSunday() {
		LocalDateTime sunday = LocalDateTime.of(2024, 6, 2, 1 , 0);
		assertTrue(restricaoDia.isRestrito(sunday));
	}
	@Test
	public void testIsRestritoWhenDayisSaturday() {
		LocalDateTime sunday = LocalDateTime.of(2024, 6, 1, 1 , 0);
		assertTrue(restricaoDia.isRestrito(sunday));
	}
	
	@Test
	public void testIsRestritoWhenDayIsWeekDay() {
		LocalDateTime segunda = LocalDateTime.of(2024, 6, 3, 1,0);
		LocalDateTime terca = LocalDateTime.of(2024, 6, 4, 1,0);
		LocalDateTime quarta = LocalDateTime.of(2024, 6, 5, 1,0);
		LocalDateTime quinta = LocalDateTime.of(2024, 6, 6, 1,0);
		LocalDateTime sexta = LocalDateTime.of(2024, 6, 7, 1,0);
		
		assertFalse(restricaoDia.isRestrito(segunda));
		assertFalse(restricaoDia.isRestrito(terca));
		assertFalse(restricaoDia.isRestrito(quarta));
		assertFalse(restricaoDia.isRestrito(quinta));
		assertFalse(restricaoDia.isRestrito(sexta));
	}
}
