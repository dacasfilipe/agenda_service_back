package com.agenda_service_back.endereco;

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
        return enderecos.stream()
                .map(enderecoConverter::toDTO)
                .collect(Collectors.toList());
    }
    //buscar uma endereco pelo id
    public EnderecoDTO findById(Long id){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Endereco nâo encontrada"));
        return enderecoConverter.toDTO(endereco);
    }
    //criando uma nova endereco
    public EnderecoDTO create(EnderecoDTO enderecoDTO){
        Endereco endereco = enderecoConverter.toEntity(enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return enderecoConverter.toDTO(endereco);
    }
    //update endereco
    public EnderecoDTO update(Long id,EnderecoDTO enderecoDTO){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Endereco não encontrado"));
        //endereco recebe os dados do enderecoDTO vindos do frontend
        endereco = enderecoConverter.updateEntity(enderecoDTO,endereco);
        //metodo para salvar o endereco no banco de dados
        endereco = enderecoRepository.save(endereco);
        //retorna o endereco entidade convertido em DTO
        return enderecoConverter.toDTO(endereco);
    }
    public void deleteById(Long id){
        enderecoRepository.deleteById(id);
    }
}

