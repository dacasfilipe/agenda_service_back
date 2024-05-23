package com.agenda_service_back.servico;

import com.agenda_service_back.servico.ServicoDTO;
import com.agenda_service_back.servico.ServicoService;
import com.agenda_service_back.usuarios.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> getAllServicos() {
        List<ServicoDTO> servicos = servicoService.getAllServicos();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable Long id) {
        ServicoDTO servico = servicoService.getServicoById(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> createServico(@RequestBody Servico servico) {
        ServicoDTO createServicoDTO = servicoService.create(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(createServicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> updateServico(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) {
        System.out.println("servicoDTO "+ servicoDTO);
        ServicoDTO updatedServicoDTO = servicoService.updateServico(id, servicoDTO);

        return ResponseEntity.ok(updatedServicoDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) {
        servicoService.deleteServico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}