package com.compilador.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.compilador.analyzers.Sintatico;

import java.awt.GridLayout;
import java.io.IOException;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JPanel painelFundo;
    JTable tabela;
    JScrollPane barraRolagem;
    
    String [] colunas = {"Token", "Lexema"};

    public View() {
        super("Análise Sintática");
    }
    
    public void criaJanela() throws IOException{
    	
        String arquivo = "src/com/compilador/grammar/Grammar.txt";
		
 		Sintatico analisadorSintatico;
    	
    	analisadorSintatico = new Sintatico(arquivo);
    	
		//Object [][] dados = {analisadorSintatico.views()};

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        tabela = new JTable(analisadorSintatico.views(), colunas);
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 120);
        setVisible(true);
    }
    
}