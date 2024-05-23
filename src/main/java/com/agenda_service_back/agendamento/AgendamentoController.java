package com.agenda_service_back.agendamento;

import com.agenda_service_back.agendamento.Agendamento;
import com.agenda_service_back.agendamento.AgendamentoDTO;
import com.agenda_service_back.agendamento.AgendamentoMapper;
import com.agenda_service_back.agendamento.AgendamentoService;
import com.agenda_service_back.agendamento.exception.BusinessException;
import com.agenda_service_back.agendamento.exception.ResourceNotFoundException;
import com.agenda_service_back.prestador.PrestadorDTO;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.servico.ServicoService;
import com.agenda_service_back.usuarios.Usuario;
import com.agenda_service_back.usuarios.UsuarioDTO;
import com.agenda_service_back.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ServicoService servicoService;
    @Autowired
    private AgendamentoMapper agendamentoMapper;
    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> getAllAgendamentos() {
        List<AgendamentoDTO> AgendamentosDTO = agendamentoService.getAllAgendamentos();
        return ResponseEntity.ok(AgendamentosDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> getAgendamentoById(@PathVariable Long id) {
        AgendamentoDTO agendamentoDTO = agendamentoService.getAgendamentoById(id);
        return ResponseEntity.ok(agendamentoDTO);
    }
    @PostMapping
    public ResponseEntity<AgendamentoDTO> createAgendamento(@RequestBody Agendamento agendamento) {
        AgendamentoDTO createAgendamentoDTO = agendamentoService.createAgendamento(agendamento);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(createAgendamentoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> updateAgendamento(@PathVariable Long id,  @RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO updateAgendamentoDTO = agendamentoService.updateAgendamento(id,agendamentoDTO);
        return ResponseEntity.ok(updateAgendamentoDTO);
        /*Agendamento existingAgendamento = agendamentoService.getAgendamentoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento not found with id: " + id));

        Usuario usuario = usuarioService.getUsuarioById(agendamentoDTO.getUsuario().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id: " + agendamentoDTO.getUsuario().getId()));
        Servico servico = servicoService.getServicoById(agendamentoDTO.getServico().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Servico not found with id: " + agendamentoDTO.getServico().getId()));

        agendamentoMapper.toEntity(agendamentoDTO, existingAgendamento, usuario, servico);
        try {
            existingAgendamento = agendamentoService.updateAgendamento(existingAgendamento);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(agendamentoMapper.toDTO(existingAgendamento));*/
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        agendamentoService.deleteAgendamentoById(id);
        return ResponseEntity.noContent().build();
    }

   /* @GetMapping("/search")
    public List<AgendamentoDTO> searchAgendamentos(@RequestParam(required = false) LocalDate dataInicio,
                                                   @RequestParam(required = false) LocalDate dataFim,
                                                   @RequestParam(required = false) Long usuarioId,
                                                   @RequestParam(required = false) Long servicoId) {
        // Implement search logic using AgendamentoService methods
        List<Agendamento> agendamentos = agendamentoService.searchAgendamentos(dataInicio, dataFim, usuarioId, servicoId);
        return agendamentos.stream()
                .map(agendamentoMapper::toDTO)
                .collect(Collectors.toList());
    }*/
}