package com.javanauta.agendador_horarios.infrastructure.exceptions;

public class ConflitoDeAgendamento extends RuntimeException {
    public ConflitoDeAgendamento(String message) {
        super(message);
    }
}
