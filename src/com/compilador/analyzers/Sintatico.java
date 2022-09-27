package com.compilador.analyzers;

import java.io.IOException;

import com.compilador.config.Symbology;
import com.compilador.enums.TokenEnum;
import com.compilador.exceptions.Errors;
import com.compilador.model.Token;


public class Sintatico {
	
	private Lexico analisadorLexico;
	private Token token = null;

	public Sintatico(String nomeArquivo) throws IOException
	{
		analisadorLexico = new Lexico(nomeArquivo);
	}
	
	public Object[][] views() {
		
		int value = 1000;
		
		Object [][] obj = new Object[value][2];
		int x = 0;
		int y = 0;
		do  { 
			token = analisadorLexico.next(); 
			obj[x][y] = token.obj().getToken();
			y++;
			obj[x][y] = token.obj().getLexema();
			y = 0;
			x++;
		} while (token.getTokenEnum() != TokenEnum.FIM);
		return obj;
	}
	
	public Object[][] viewss(){
		return Symbology.getInstancia().printTab();
	}
	
	public void erros() {
		Errors.getInstancia().gerarTabela();
	}
}
