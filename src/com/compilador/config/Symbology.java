package com.compilador.config;

import java.util.HashMap;
import java.util.Map;

import com.compilador.enums.TokenEnum;
import com.compilador.model.Token;

public class Symbology {
	
	public Object[][] printTab() {
		int value = 100;
		
		Object [][] obj = new Object[value][2];
		int x = 0;
		int y = 0;
		
		for(Map.Entry<String, Token> aux : tabela.entrySet()) {
			obj[x][y] = aux.getValue().getTokenEnum().toString();
			y++;
			obj[x][y] = aux.getKey();
			y = 0;
			x++;
		}
		
		/*tabela.forEach((lexema, token) -> {
			obj[x][y] = token.getTokenEnum().toString();
			y++;
			obj[x][y] = lexema;
			y = 0;
			x++;
		});*/

		return obj;
	}
	
	public Token adicionarToken(String lexema, long linha, long coluna) {
		Token token = null;

		if (tabela.containsKey(lexema)) {
			token = tabela.get(lexema);
			token.setLinha(linha);
			token.setColuna(coluna);
		} else {
			token = new Token(TokenEnum.ID, lexema, linha, coluna);
			tabela.put(lexema, token);
		}

		return token;
	}
	
	private static Symbology instancia = new Symbology();
	
	private Symbology() {
		tabela = new HashMap<String, Token>();

		tabela.put("WHILE", new Token(TokenEnum.WHILE, "WHILE"));
		tabela.put("to", new Token(TokenEnum.TO, "to"));
		tabela.put("INTEGER", new Token(TokenEnum.INTEGER, "INTEGER"));
		tabela.put("DO", new Token(TokenEnum.DO, "DO"));
		tabela.put("bool", new Token(TokenEnum.TYPE, "bool"));
		tabela.put("PROGRAM", new Token(TokenEnum.PROGRAM, "PROGRAM"));
		tabela.put("BEGIN", new Token(TokenEnum.BEGIN, "BEGIN"));
		tabela.put("END", new Token(TokenEnum.END, "END"));
		tabela.put("WRITE", new Token(TokenEnum.WRITE, "WRITE"));
		tabela.put("STRING", new Token(TokenEnum.STRING, "STRING"));
		tabela.put("if", new Token(TokenEnum.IF, "if"));
		tabela.put("then", new Token(TokenEnum.THEN, "then"));
		tabela.put("else", new Token(TokenEnum.ELSE, "else"));
		tabela.put("for", new Token(TokenEnum.FOR, "for"));
		tabela.put("READ", new Token(TokenEnum.READ, "READ"));
		tabela.put("true", new Token(TokenEnum.BOOLEAN, "true"));
		tabela.put("false", new Token(TokenEnum.BOOLEAN, "false"));
		tabela.put("not", new Token(TokenEnum.OPLOG, "not"));
		tabela.put("and", new Token(TokenEnum.OPLOG, "and"));
		tabela.put("or", new Token(TokenEnum.OPLOG, "or"));
		tabela.put("VAR", new Token(TokenEnum.VAR, "VAR"));
		
	}
	
	private Map<String, Token> tabela;
	
	public static Symbology getInstancia() {
		return instancia;
	}
	

}

