package com.compilador.enums;

public enum ErroEnum {
	
    LEXICO(1),
    SINTATICO(2),
    COMPILACAO(3);

    private int cod;

    private ErroEnum(int cod) {
        this.cod = cod;
    }

    public int getCod(){
        return cod;
    }

    public static ErroEnum tipoEnum(int cod) {
        for (ErroEnum erro : ErroEnum.values()){
            if(cod == erro.getCod()) { return erro; }
        }
        throw new IllegalArgumentException("Erro encontrado com Código: " + cod + "\nTipo: " + ErroEnum.values());
    }
}
