package com.compilador.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.compilador.enums.ErroEnum;

public class Errors {

	private static Errors instancia = new Errors();
	private List<Error> erros = new ArrayList<Error>();

	public void erroCompilador(Error error)
	{
		erros.add(error);
	}
	
	public void erroCompilador(ErroEnum tipoError, String lexema, String mensagem, long linha, long coluna) {
		Error e =  new Error(tipoError, lexema, mensagem, linha, coluna);
		erroCompilador(e);
	}

	public void gerarTabela()
	{
		if (erros.size() != 0) tituloTabelaLexico();

		for (Error erro : erros)
		{
			System.out.println(erro.toString());
		}
	}

	private void tituloTabelaLexico()
	{
		System.out.printf("\nErrors: ");
		System.out.printf("\n");
	}

	public static Errors getInstancia() {
		return instancia;
	}
}
