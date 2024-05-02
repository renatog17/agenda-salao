package com.renato.agendamentosalao.controller.dto.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = { ValidacaoHoraValidator.class })
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidacaoHora {

	String message() default "A hora deve terminar em 00 ou 30 minutos";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
