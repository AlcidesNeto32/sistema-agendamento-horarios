package com.javanauta.agendador_horarios.infrastructure.exceptions;

public class AtendimentoInesistente extends RuntimeException {
    public AtendimentoInesistente(String message) {
        super(message);
    }
}
