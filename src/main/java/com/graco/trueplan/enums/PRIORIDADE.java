package com.graco.trueplan.enums;

public enum PRIORIDADE {
	URGENTE(0), ALTA(1), MEDIA(2), BAIXA(3);
	
	private final int value;

    PRIORIDADE(int value) {
        this.value = value;
    }

    public static PRIORIDADE fromValue(int value) {
        for (PRIORIDADE priority : PRIORIDADE.values()) {
            if (priority.value == value) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Valor inválido para PRIORIDADE: " + value);
    }
}
