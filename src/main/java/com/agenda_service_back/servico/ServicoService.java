package com.agenda_service_back.servico;

import com.agenda_service_back.categoria.Categoria;
import com.agenda_service_back.categoria.CategoriaRepository;
import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.prestador.Prestador;
import com.agenda_service_back.prestador.PrestadorRepository;
import com.agenda_service_back.servico.ServicoDTO;
import com.agenda_service_back.servico.ServicoMapper;
import com.agenda_service_back.servico.ServicoRepository;
import com.agenda_service_back.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private ServicoMapper servicoMapper;

    @Transactional
    public ServicoDTO create(Servico servico) {
        servicoRepository.save(servico);
        return servicoMapper.toDTO(servico);
    }

    @Transactional(readOnly = true)
    public ServicoDTO getServicoById(Long id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Usuario nâo encontrada"));
        return servicoMapper.toDTO(servico);
    }

    @Transactional(readOnly = true)
    public List<ServicoDTO> getAllServicos() {
        List<Servico> servicos = servicoRepository.findAll();
        return servicoMapper.toDTOList(servicos);
    }

    @Transactional
    public ServicoDTO updateServico(Long id, ServicoDTO servicoDTO) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Servico não encontrado"));
        servicoDTO.setServicoId(servico.getServico_id());
        Categoria categoria = servico.getCategoria();
        servicoDTO.setCategoria(categoria);
        Prestador prestador = servico.getPrestador();
        servicoDTO.setPrestador(prestador);
        System.out.println("dto "+servicoDTO);
        servico = servicoMapper.updateEntity(servicoDTO, servico);
        System.out.println("entidade "+servico);

        servico = servicoRepository.save(servico);

        return servicoMapper.toDTO(servico);
    }

    @Transactional
    public void deleteServico(Long id) {
        servicoRepository.deleteById(id);
    }


}