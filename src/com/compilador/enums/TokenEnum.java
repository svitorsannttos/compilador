package com.compilador.enums;

public enum TokenEnum {

	THEN(20),OPLOG(13),TYPE(14),PROGRAM(15),BEGIN(17),END(18),IF(19),OPNEG(34),COM(35),FIM(0),STRING(3),ELSE(21),FOR(22),WHILE(23),TO(25),INTEGER(26),DO(27),READ(28),VAR(29),WRITE(30),ATRIB(8),PVIG(9),ABPAR(10),FPAR(11), BOOLEAN(12),OPREL(31),VIG(32),PONTO(33),		
	ID(4),RELOP(5),OPAD(6),OPMULT(7)
	;	
	
	private int cod;

	private TokenEnum(int cod){
		this.cod = cod;
	}

	public int getCod(){
		return this.cod;
	}
	
	public static TokenEnum tipoEnum(int cod){
		for (TokenEnum tipoToken : TokenEnum.values()){
			if(cod == tipoToken.getCod()) {
				return tipoToken;
				}
		}
		throw new IllegalArgumentException("Erro encontrado no Token de Código: " + cod);
	}
}
