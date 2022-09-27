package com.compilador.config;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileConfig extends BufferedReader {
    
    private long linha;
    private long coluna;
    private long tamanhoLinhaFinal;
    
    public FileConfig(String nomeArquivo) 
    throws FileNotFoundException {
        this(new File(nomeArquivo));
    }
    
    public FileConfig(File arquivo) 
    throws FileNotFoundException {
        super((new FileReader(arquivo)));
        linha = 1;
        coluna = 0;
        tamanhoLinhaFinal = 0;
    }
    
    public char getNext() throws EOFException, IOException {
        this.mark(1);
        int valorCaractere = this.read();
        if (valorCaractere == -1) throw new EOFException(); 
        coluna++;
        if (valorCaractere == '\n') { 
            linha++;
            tamanhoLinhaFinal = coluna;
            coluna = 0;
        } 
        
        return (char) valorCaractere; 
    }
    
    public void lastCharacterReset() throws IOException {
        this.reset();
        coluna--;
        if (coluna < 0) {
            coluna = tamanhoLinhaFinal;
            linha--;
        }
    }

    public long getLinha() {
        return linha;
    }

    public long getColuna() {
        return coluna;
    }

}

