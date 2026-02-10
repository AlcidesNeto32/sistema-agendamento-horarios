package com.javanauta.agendador_horarios.service;

import com.javanauta.agendador_horarios.infrastructure.entities.Agendamento;
import com.javanauta.agendador_horarios.infrastructure.exceptions.AtendimentoInesistente;
import com.javanauta.agendador_horarios.infrastructure.exceptions.ConflitoDeAgendamento;
import com.javanauta.agendador_horarios.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService implements Serializable {
    private static final long serialVersionUID = 1L;

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {
        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime fimDoAtendimento = agendamento.getDataHoraAgendamento().plusHours(1);
        Agendamento agendado = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(), horaAgendamento, fimDoAtendimento);
        if (Objects.nonNull(agendado)) {
            throw new ConflitoDeAgendamento("Esse horário já está ocupado!");
        }
        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente) {
        boolean existeAgendamento = agendamentoRepository.existsByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
        if (!existeAgendamento) {
            throw new AtendimentoInesistente("Esse atendimento não está cadastrado!");
        }
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
    }

    public List<Agendamento> buscarAgendamentosDia(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59, 59);
        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, horaFinalDia);
    }

    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento) {
        Agendamento agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
        if (Objects.isNull(agenda)) {
            throw new AtendimentoInesistente("Horário não está preenchido");
        }
        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);
    }


}
