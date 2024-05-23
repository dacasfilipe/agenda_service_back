package com.agenda_service_back.prestador;

import com.agenda_service_back.prestador.Prestador;
import com.agenda_service_back.prestador.PrestadorDTO;
import com.agenda_service_back.prestador.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {
    @Autowired
    private PrestadorService prestadorService;

    @GetMapping //retorna uma lista de prestadors
    public ResponseEntity<List<PrestadorDTO>> getAllPrestadors(){
        List<PrestadorDTO> prestadorsDTO = prestadorService.findAll();
        return ResponseEntity.ok(prestadorsDTO);
    }
    @GetMapping("/{id}") //localhost:8080/prestadors/1    pegar por id
    public ResponseEntity<PrestadorDTO> getPrestadorById(@PathVariable Long id){
        PrestadorDTO prestadorDTO = prestadorService.findById(id);
        return ResponseEntity.ok(prestadorDTO);
    }
    @PostMapping //salvar uma categoria no banco de dados
    public ResponseEntity<PrestadorDTO> createPrestador(@RequestBody Prestador prestador){
        System.out.println(prestador);
        PrestadorDTO createPrestadorDTO = prestadorService.create(prestador);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(createPrestadorDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PrestadorDTO> updatePrestador(@PathVariable Long id,
                                                    @RequestBody PrestadorDTO prestadorDTO){
        PrestadorDTO updatePrestadorDTO = prestadorService.update(id,prestadorDTO);
        return ResponseEntity.ok(updatePrestadorDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestador(@PathVariable Long id){
        prestadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
