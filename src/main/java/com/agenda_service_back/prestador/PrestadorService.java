package com.agenda_service_back.prestador;

import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.endereco.EnderecoService;
import com.agenda_service_back.prestador.Prestador;
import com.agenda_service_back.prestador.PrestadorDTO;
import com.agenda_service_back.prestador.PrestadorMapper;
import com.agenda_service_back.prestador.PrestadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestadorService {
    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PrestadorMapper prestadorMapper;

    //buscar todas as prestadors
    public List<PrestadorDTO> findAll() {
        List<Prestador> prestadores =
                prestadorRepository.findAll();
        System.out.println(prestadores);
        return prestadorMapper.toDTOList(prestadores);
    }

    //buscar uma prestador pelo id
    public PrestadorDTO findById(Long id) {
        Prestador prestador = prestadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prestador nâo encontrada"));
        return prestadorMapper.toDTO(prestador);
    }

    //criando uma nova prestador
    @Transactional
    public PrestadorDTO create(Prestador prestador) {
        System.out.println(prestador);
        prestadorRepository.save(prestador);
        return prestadorMapper.toDTO(prestador);
    }
    @Transactional
    //update prestador
    public PrestadorDTO update(Long id, PrestadorDTO prestadorDTO) {
        Prestador prestador = prestadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prestador não encontrado"));
        //prestador recebe os dados do prestadorDTO vindos do frontend
        System.out.println("Prestador "+ prestador);
        prestadorDTO.setPrestador_id(prestador.getPrestador_id());
        Endereco endereco = prestador.getEndereco();
        prestadorDTO.setEndereco(endereco);
        System.out.println("dto " + prestadorDTO);
        prestador = prestadorMapper.updateEntity(prestadorDTO, prestador);
        prestador = prestadorRepository.save(prestador);
        prestadorDTO = prestadorMapper.toDTO(prestador);
        //metodo para salvar o prestador no banco de dados

        //retorna o prestador entidade convertido em DTO
        return prestadorDTO;
    }

    public void deleteById(Long id) {
        prestadorRepository.deleteById(id);
    }
}