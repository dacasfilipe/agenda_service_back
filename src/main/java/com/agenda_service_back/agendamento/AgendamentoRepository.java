package com.agenda_service_back.agendamento;

import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

   /* @Query("SELECT a FROM Agendamento a WHERE a.agendamento_data = :data AND a.agendamento_status = :status")
    List<Agendamento> findByDataAndStatus(LocalDate data, AgendamentoStatus status);

    @Query("SELECT a FROM Agendamento a WHERE a.agendamento_usuario_id = :usuario AND a.agendamento_status = :status")
    List<Agendamento> findByUsuarioAndStatus(Usuario usuario, AgendamentoStatus status);

    @Query("SELECT a FROM Agendamento a WHERE a.agendamento_servico_id = :servico AND a.agendamento_status = :status")
    List<Agendamento> findByServicoAndStatus(Servico servico, AgendamentoStatus status);*/
}
