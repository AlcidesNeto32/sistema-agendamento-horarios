package com.javanauta.agendador_horarios.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_agendamento")
public class Agendamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String servico;
    private String profissional;
    private LocalDateTime dataHoraAgendamento;
    private String cliente;
    private String telefoneCliente;
    private LocalDateTime dataInsercao = LocalDateTime.now();
}