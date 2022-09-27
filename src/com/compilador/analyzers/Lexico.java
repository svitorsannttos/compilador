package com.compilador.analyzers;

import java.io.EOFException;
import java.io.IOException;

import com.compilador.config.FileConfig;
import com.compilador.config.Symbology;
import com.compilador.enums.ErroEnum;
import com.compilador.enums.TokenEnum;
import com.compilador.exceptions.Errors;
import com.compilador.model.Token;

public class Lexico {
	
	private FileConfig carregarArquivo;
	
	private Token string() throws IOException {
		char c = proximoCaractere();
		try {
			while (c != '"' && c != '\n') {
				c = proximoCaractere();
			}
		} catch (EOFException eof) {
			lexema.append(" ");
		}

		if (c != '"') {
			ultimoCaractereReiniciar();
			Errors.getInstancia().erroCompilador(ErroEnum.LEXICO, lexema.toString(), "Erro",
					tokenLinha, tokenColuna);
			return null;
		}

		return new Token(TokenEnum.STRING, lexema.toString(), tokenLinha, tokenColuna);
	}

	
	private void comentario() throws IOException {
		try {
			char c = proximoCaractere();
			
			do {
				c = proximoCaractere();
			} while (c != '#');
			c = proximoCaractere();
		
		} catch (EOFException e) {
			Errors.getInstancia().erroCompilador(ErroEnum.LEXICO, lexema.toString(),
					"Erro no comentário não encerrado.", tokenLinha, tokenColuna);
			carregarArquivo.lastCharacterReset();
		}
	}

	private Token relop() throws IOException {
		char c = proximoCaractere();

		try {
			switch (c) {
			case 'l':
				c = proximoCaractere();

				if (c != 't' && c != 'e') {
					ultimoCaractereReiniciar();
					return new Token(TokenEnum.RELOP, lexema.toString(), tokenLinha, tokenColuna);
				}
				break;

			case 'g':
				c = proximoCaractere();

				if (c != 't' && c != 'e') {
					ultimoCaractereReiniciar();
					return new Token(TokenEnum.RELOP, lexema.toString(), tokenLinha, tokenColuna);
				}
				break;

			case 'e':
				c = proximoCaractere();

				if (c != 'q') {
					ultimoCaractereReiniciar();
					return new Token(TokenEnum.RELOP, lexema.toString(), tokenLinha, tokenColuna);
				}
				break;

			case 'd':
				c = proximoCaractere();

				if (c != 'f') {
					ultimoCaractereReiniciar();
					return new Token(TokenEnum.RELOP, lexema.toString(), tokenLinha, tokenColuna);
				}
				break;

			default:

				if (Character.isWhitespace(c)) {
					Errors.getInstancia().erroCompilador(ErroEnum.LEXICO, lexema.toString(),
							"O Relop está incorreto. Valores válidos: $lt|$gt|$ge|$le|$eq|$df", tokenLinha, tokenColuna);
				}

				ultimoCaractereReiniciar();
				return new Token(TokenEnum.RELOP, lexema.toString(), tokenLinha, tokenColuna);
			}
		} catch (EOFException eofError) {
			carregarArquivo.lastCharacterReset();
			lexema.append(" ");
		}

		return new Token(TokenEnum.RELOP, lexema.toString(), tokenLinha, tokenColuna);
	}

	private Token assign() throws IOException {
		char c = proximoCaractere();
		if (c != '=') {
			// Registra Erro
			Errors.getInstancia().erroCompilador(ErroEnum.LEXICO, lexema.toString(), "Operador inválido",
					tokenLinha, tokenColuna);
			return null;
		}
		return new Token(TokenEnum.ATRIB, lexema.toString(), tokenLinha, tokenColuna);
	}
	
	private char proximoCaractere() throws IOException {
		char c = carregarArquivo.getNext();
		lexema.append(c);
		return c;
	}

	private void ultimoCaractereReiniciar() throws IOException {
		carregarArquivo.lastCharacterReset();
		lexema.deleteCharAt(lexema.length() - 1);
	}
	
	private Token simboloIgual() throws IOException {
		char c = proximoCaractere();
		if (c != '=') {
			Errors.getInstancia().erroCompilador(ErroEnum.LEXICO, lexema.toString(), "Operador inválido", tokenLinha, tokenColuna);
			return null;
		}
		
		return new Token(TokenEnum.ATRIB, lexema.toString(), tokenLinha, tokenColuna);
	}
	
	private Token simboloMenorQue() throws IOException {
		char c = proximoCaractere();
		
		if (c == '=') {
			return new Token(TokenEnum.ATRIB, lexema.toString(), tokenLinha, tokenColuna);
		}
		
		return null;
	}
	
	private StringBuilder lexema = null;
	private char caracter;
	private long tokenLinha;
	private long tokenColuna;

	private Token numero() throws IOException {
		char c = proximoCaractere();

		try {
			while (Character.isDigit(c)) {
				c = proximoCaractere();
			}

			
			if (c != 'E') {
				ultimoCaractereReiniciar();
				return new Token(TokenEnum.INTEGER, lexema.toString(), tokenLinha, tokenColuna);
			}

			c = proximoCaractere();

			if (c != '+') {
				ultimoCaractereReiniciar();
				Errors.getInstancia().erroCompilador(ErroEnum.LEXICO, lexema.toString(),
						"Número inteiro inválido. `+` é válido após:  `" + lexema.toString() + '`', tokenLinha, tokenColuna);
				return null;
			}

			do {
				c = proximoCaractere();
			} while (Character.isDigit(c));

			ultimoCaractereReiniciar();

			return new Token(TokenEnum.INTEGER, lexema.toString(), tokenLinha, tokenColuna);
		} catch (EOFException eofError) {
			lexema.append(" ");
		}

		return null;
	}
	
	public Lexico(String nomeArquivo) throws IOException {
		carregarArquivo = new FileConfig(nomeArquivo);
	}

	public Token next() {
		Token token = null;

		try {
			while (token == null) {
				do {
					caracter = carregarArquivo.getNext();
				} while (Character.isWhitespace(caracter));

				lexema = new StringBuilder();
				tokenLinha = carregarArquivo.getLinha();
				tokenColuna = carregarArquivo.getColuna();

				lexema.append(caracter);

				switch (caracter) {
				case ';':
					token = new Token(TokenEnum.PVIG, lexema.toString(), tokenLinha, tokenColuna);
					break;
				case '-':
					token = new Token(TokenEnum.OPAD, lexema.toString(), tokenLinha, tokenColuna);
					break;
				case '*':
					token = new Token(TokenEnum.OPMULT, lexema.toString(), tokenLinha, tokenColuna);
					break;
				case '/':
					token = new Token(TokenEnum.OPMULT, lexema.toString(), tokenLinha, tokenColuna);
					break;
				case '+':
					token = new Token(TokenEnum.OPAD, lexema.toString(), tokenLinha, tokenColuna);
					break;
				case '=':
					simboloIgual();
					break;
				case '<':
					token = new Token(TokenEnum.OPREL, lexema.toString(), tokenLinha, tokenColuna);
					simboloMenorQue();
					break;
				case '>':
					token = new Token(TokenEnum.OPREL, lexema.toString(), tokenLinha, tokenColuna);
					break;
				case ',':
					token = new Token(TokenEnum.VIG, lexema.toString(), tokenLinha, tokenColuna);
					break;
				case '(':
					token = new Token(TokenEnum.ABPAR, lexema.toString(), tokenLinha, tokenColuna);
					break;
				case ')':
					token = new Token(TokenEnum.FPAR, lexema.toString(), tokenLinha, tokenColuna);
					break;
				case '$':
					token = relop();
					break;
				case ':':
					token = assign();
					break;
				case '#':
					comentario();
					break;
				case '"':
					token = string();
					break;
				case '.':
					token = new Token(TokenEnum.PONTO, lexema.toString(), tokenLinha, tokenColuna);
					break;
				default:
					if (Character.isLetter(caracter) || caracter == '_') {
						token = id();
						break;
					}
					if (Character.isDigit(caracter) || caracter == '-') {
						token = numero();
						break;
					}

					Errors.getInstancia().erroCompilador(ErroEnum.LEXICO, lexema.toString(),
							"PALAVRA INEXISTENTE: ", tokenLinha, tokenColuna);
				}
			}

		} catch (EOFException eof) {
			token = new Token(TokenEnum.FIM);
		} catch (IOException io) {
			// Registrando erro (ao compilar)
			Errors.getInstancia().erroCompilador(ErroEnum.COMPILACAO, "", "Falha ao acessar o arquivo",
					tokenLinha, tokenColuna);
			token = new Token(TokenEnum.FIM, "Erro de compilação");
		}
		return token;
	}

	private Token id() throws IOException {
		Token token = null;
		try {
			char c = proximoCaractere();
			while (Character.isLetter(c) || c == '_' || Character.isDigit(c)) {
				c = proximoCaractere();
			}
			ultimoCaractereReiniciar();
		} catch (EOFException e) {
			carregarArquivo.lastCharacterReset();
		}

		token = Symbology.getInstancia().adicionarToken(lexema.toString(), tokenLinha, tokenColuna);
		return token;
	}
}

