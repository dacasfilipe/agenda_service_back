package com.agenda_service_back.usuarios;

import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.endereco.EnderecoService;
import com.agenda_service_back.usuarios.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agenda_service_back.usuarios.UsuarioMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository<Usuario, Long> usuarioRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    //buscar todas as usuarios
    public List<UsuarioDTO> findAll(){
        List<Usuario> usuarios =
                usuarioRepository.findAll();
        System.out.println(usuarios);
//        return usuarios.stream()
//                .map(usuarioConverter::toDTO)
//                .collect(Collectors.toList());
        return usuarioMapper.toDTOList(usuarios);
    }
    //buscar uma usuario pelo id
    public UsuarioDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Usuario nâo encontrada"));
        return usuarioMapper.toDTO(usuario);
    }
    //criando uma nova usuario
    @Transactional
    public UsuarioDTO create(Usuario usuario){
        System.out.println(usuario);
        usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }
    //update usuario
    public UsuarioDTO update(Long id,UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Usuario não encontrado"));
        //usuario recebe os dados do usuarioDTO vindos do frontend
        System.out.println(usuario);
        usuarioDTO.setUsuario_id(usuario.getUsuario_id());
        Endereco endereco = usuario.getEndereco();
        usuarioDTO.setEndereco(endereco);
        System.out.println("dto "+usuarioDTO);
        usuario = usuarioMapper.updateEntity(usuarioDTO, usuario);
        usuario = usuarioRepository.save(usuario);
        usuarioDTO = usuarioMapper.toDTO(usuario);
        //metodo para salvar o usuario no banco de dados

        //retorna o usuario entidade convertido em DTO
        return usuarioDTO;
    }
    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }

//    public Saudacao setNovaSaudacao(Saudacao novaSaudacao, String autorizacao) {
//        String email = jwtService.getUsuarioId(autorizacao);
//        novaSaudacao.setUsuario(usuarioService.getUsuario(email));
//        saudacoesDAO.save(novaSaudacao);
//        return ultimaSaudacaoAlternativa();
//    }
}


