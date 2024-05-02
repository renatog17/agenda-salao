package com.renato.agendamentosalao.service.strategy;

public class RestricaoErro extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RestricaoErro() {
		super();
	}
	
	public RestricaoErro(String mensagem) {
        super(mensagem);
    }

  
}
