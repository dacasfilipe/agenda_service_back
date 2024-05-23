package com.agenda_service_back.agendamento;

import com.agenda_service_back.agendamento.exception.BusinessException;
import com.agenda_service_back.agendamento.exception.ResourceNotFoundException;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.servico.ServicoService;
import com.agenda_service_back.usuarios.Usuario;
import com.agenda_service_back.usuarios.UsuarioDTO;
import com.agenda_service_back.usuarios.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AgendamentoMapper agendamentoMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ServicoService servicoService;

    public List<AgendamentoDTO> getAllAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        System.out.println(agendamentos);
        return agendamentoMapper.toDTOList(agendamentos);
    }

    public AgendamentoDTO getAgendamentoById(Long id) {
         Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento not found with id: " + id));
        return agendamentoMapper.toDTO(agendamento);
    }

    public AgendamentoDTO createAgendamento(Agendamento agendamento) {
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDTO(agendamento);
    }

    @Transactional
    public AgendamentoDTO updateAgendamento(Long id, AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        //prestador recebe os dados do prestadorDTO vindos do frontend
        System.out.println("agendamento "+ agendamento);
        agendamentoDTO.setId(id);
        Servico servico = agendamento.getServico();
        agendamentoDTO.setServico(servico);
        Usuario usuario = agendamento.getUsuario();
        agendamentoDTO.setUsuario(usuario);
        System.out.println("dto " + agendamentoDTO);
        agendamento = agendamentoMapper.updateEntity(agendamentoDTO,agendamento);
        agendamento = agendamentoRepository.save(agendamento);
        agendamentoDTO = agendamentoMapper.toDTO(agendamento);
        //metodo para salvar o prestador no banco de dados

        //retorna o prestador entidade convertido em DTO
        return agendamentoDTO;
    }

    public void deleteAgendamentoById(Long id) {
        agendamentoRepository.deleteById(id);
    }

    /*public List<AgendamentoDTO> searchAgendamentos(LocalDate dataInicio, LocalDate dataFim, Long usuarioId, Long servicoId) {
        // Build query predicate based on provided parameters
        Predicate<Agendamento> predicate = buildSearchPredicate(dataInicio, dataFim, usuarioId, servicoId);

        // Find all matching agendamentos
        List<Agendamento> agendamentos = agendamentoRepository.findAll(predicate);

        // Convert Agendamento entities to AgendamentoDTO objects
        return agendamentos.stream()
                .map(agendamentoMapper::toDTO)
                .collect(Collectors.toList());
    }*/
   /* private Predicate<Agendamento> buildSearchPredicate(LocalDate dataInicio, LocalDate dataFim, Long usuarioId, Long servicoId) {
        Predicate<Agendamento> predicate = Predicate.alwaysTrue();

        if (dataInicio != null) {
            predicate = predicate.and(agendamento -> agendamento.getAgendamento_data().isAfter(dataInicio.minusDays(1)));
        }

        if (dataFim != null) {
            predicate = predicate.and(agendamento -> agendamento.getAgendamento_hora().toLocalDate().isBefore(dataFim.plusDays(1)));
        }

        if (usuarioId != null) {
            predicate = predicate.and(agendamento -> agendamento.getUsuario().getUsuario_id().equals(usuarioId));
        }

        if (servicoId != null) {
            predicate = predicate.and(agendamento -> agendamento.getServico().getServico_id().equals(servicoId));
        }

        return predicate;
    }*/
}
