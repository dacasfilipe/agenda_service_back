package com.agenda_service_back.endereco;

import com.agenda_service_back.usuarios.Usuario;
import com.agenda_service_back.usuarios.UsuarioDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Usuario> usuarios = endereco.getUsuarios(); // Fetch usuarios explicitly
        EnderecoDTO enderecoDTO = mapEnderecoToDTO(endereco, usuarios);
        return enderecoConverter.toDTO(endereco);
    }
    private EnderecoDTO mapEnderecoToDTO(Endereco endereco, List<Usuario> usuarios) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setEndereco_id(endereco.getEndereco_id());
        enderecoDTO.setEndereco_rua(endereco.getEndereco_rua());
        enderecoDTO.setEndereco_cep(endereco.getEndereco_cep());
        enderecoDTO.setEndereco_cidade(endereco.getEndereco_cidade());
        enderecoDTO.setEndereco_bairro(endereco.getEndereco_bairro());
        enderecoDTO.setEndereco_estado(endereco.getEndereco_estado());
        enderecoDTO.setEndereco_numero(endereco.getEndereco_numero());
        enderecoDTO.setEndereco_complemento(endereco.getEndereco_complemento());

        // Convert usuarios to UsuarioDTOs
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setUsuario_id(usuario.getUsuario_id());
            usuarioDTO.setUsuario_cpf(usuario.getUsuario_cpf());
            usuarioDTO.setUsuario_email(usuario.getUsuario_email());
            usuarioDTO.setUsuario_data_nascimento(usuario.getUsuario_data_nascimento());
            usuarioDTO.setUsuario_senha(usuario.getUsuario_senha());

            usuarioDTOs.add(usuarioDTO);
        }

        enderecoDTO.setUsuarios(usuarioDTOs);
        return enderecoDTO;
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

