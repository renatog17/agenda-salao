package com.renato.agendamentosalao.controller.dto.validacao;

import java.time.LocalDateTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidacaoHoraValidator implements ConstraintValidator<ValidacaoHora, LocalDateTime> {

	@Override
	public void initialize(ValidacaoHora constraintAnnotation) {
	}

	@Override
	public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}

		int minutos = value.getMinute();
		return minutos == 0 || minutos == 30;
	}

}
