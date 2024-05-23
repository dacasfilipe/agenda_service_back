package com.agenda_service_back.endereco;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    EnderecoConverter enderecoConverter;

    //buscar todas as enderecos
    public List<EnderecoDTO> findAll(){
        List<Endereco> enderecos =
                enderecoRepository.findAll();
        System.out.println(enderecos);
//        return enderecos.stream()
//                .map(enderecoConverter::toDTO)
//                .collect(Collectors.toList());
        return enderecoMapper.toDTOList(enderecos);
    }
    //buscar uma endereco pelo id
    public EnderecoDTO findById(Long id){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Endereco nâo encontrada"));
        return enderecoConverter.toDTO(endereco);
    }
    //criando uma nova endereco
    @Transactional
    public EnderecoDTO create(EnderecoDTO enderecoDTO){
        System.out.println(enderecoDTO);
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        System.out.println(endereco);
        endereco = enderecoRepository.save(endereco);
        return enderecoMapper.toDTO(endereco);
    }
    @Transactional
    //update endereco
    public EnderecoDTO update(Long id,EnderecoDTO enderecoDTO){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Endereco não encontrado"));
        //endereco recebe os dados do enderecoDTO vindos do frontend
        enderecoDTO.setEndereco_id(endereco.getEndereco_id());
        endereco = enderecoMapper.updateEntity(enderecoDTO,endereco);
        //metodo para salvar o endereco no banco de dados
        endereco = enderecoRepository.save(endereco);
        //retorna o endereco entidade convertido em DTO
        return enderecoMapper.toDTO(endereco);
    }
    public void deleteById(Long id){
        enderecoRepository.deleteById(id);
    }
}

