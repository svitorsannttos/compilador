package com.compilador.model;

import com.compilador.enums.TokenEnum;

public class Token {

	private String lexema;       
	private Long linha;         
	private Long coluna;
	private TokenEnum tokenEnum; 
	
	public Token(TokenEnum tokenEnum, String lexema, Long linha, Long coluna) {
		this.lexema = lexema;
		this.linha = linha;
		this.coluna = coluna;
		this.tokenEnum = tokenEnum;
	}
	
	public Token(TokenEnum tokenEnum, String lexema) {
		this(tokenEnum, lexema, 0L, 0L);
	}
	
	public Token(TokenEnum tokenEnum) {
		this(tokenEnum, "");
	}


	public TokenEnum getTokenEnum() {
		return this.tokenEnum;
	}

	public String getLexema() {
		return this.lexema;
	}

	public Long getLinha() {
		return this.linha;
	}

	public Long getColuna() {
		return this.coluna;
	}

	public void setLinha(Long linha) {
		this.linha = linha;
	}

	public void setColuna(Long coluna) {
		this.coluna = coluna;
	}
	
	public void print() {
		String tokenFormato = "[%3s,%3s]| %-10s | %-21s |\n";
		System.out.printf(tokenFormato,this.coluna,this.linha,tokenEnum.toString(), this.lexema);	
	}
	
	public TokenDTO obj() {
		return new TokenDTO(tokenEnum.toString(), this.lexema);
	}

}
