package com.compilador.exceptions;

import com.compilador.enums.ErroEnum;

public class Error {
	
	private ErroEnum tipoErro;
	private String lexema;
	private String mensagem;
	private Long linha;
	private Long coluna;
	
	public Error(ErroEnum tipoErro, String lexema, String mensagem, Long linha, Long coluna) {
		this.tipoErro = tipoErro;
		this.lexema = lexema;
		this.linha = linha;
		this.coluna = coluna;
		this.mensagem = mensagem;
	}

	@Override
	public String toString()
	{
		return mensagem + " do tipo " + tipoErro + ", no lexema= " + lexema + ", na posição: (" + linha + ", " + coluna + ")";
	}

	public ErroEnum getTipoErro() { 
		return tipoErro; 
	}
	
	public void setErrorType(ErroEnum tipoErro) 	{
		this.tipoErro = tipoErro; 
	}

	public String getMensagem() 	{ 
		return mensagem; 
	}

	public String getLexema() { 
		return lexema; 
	}

	public Long getLinha() 	{ 
		return linha; 
	}

	public Long getColuna(){ 
		return coluna; 
	}
}
