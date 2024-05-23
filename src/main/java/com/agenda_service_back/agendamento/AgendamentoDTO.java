package com.agenda_service_back.agendamento;

import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.usuarios.Usuario;
import com.agenda_service_back.usuarios.UsuarioDTO;
import com.agenda_service_back.servico.ServicoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id; // Using Long instead of long for potential null values
    @DateTimeFormat(pattern="yyyy-MD-dd")
    private LocalDate data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime hora;
    private String observacao;
    private AgendamentoStatus status;
    private Usuario usuario;
    private Servico servico;
}
