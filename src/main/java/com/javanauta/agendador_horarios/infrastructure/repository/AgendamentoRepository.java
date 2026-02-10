package com.javanauta.agendador_horarios.infrastructure.repository;

import com.javanauta.agendador_horarios.infrastructure.entities.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
    Agendamento findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraIncioAtendimento,
                                                           LocalDateTime dataHoraFimAtendimento);
    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

    boolean existsByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento,String cliente);

    List<Agendamento> findByDataHoraAgendamentoBetween(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal);

    Agendamento findByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);
}