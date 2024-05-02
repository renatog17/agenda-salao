package com.renato.agendamentosalao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.renato.agendamentosalao.controller.dto.validacao.ErroValidacao;
import com.renato.agendamentosalao.service.strategy.RestricaoErro;

@ControllerAdvice
public class ManipuladorExcessoes {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacao>> manipularErroValidacao(MethodArgumentNotValidException ex) {
        List<ErroValidacao> erros = new ArrayList<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.add(new ErroValidacao(erro.getField(), erro.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
	
	@ExceptionHandler(RestricaoErro.class)
    public ResponseEntity<String> handleMinhaExcecao(RestricaoErro ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: "+ ex.getMessage());
    }
}
