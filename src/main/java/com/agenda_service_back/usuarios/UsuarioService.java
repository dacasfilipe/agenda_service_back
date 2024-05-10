package com.agenda_service_back.usuarios;

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
    private UsuarioRepository usuarioRepository;

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
    public UsuarioDTO create(UsuarioDTO usuarioDTO){
        System.out.println(usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        System.out.println(usuario);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }
    //update usuario
    public UsuarioDTO update(Long id,UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Usuario não encontrado"));
        //usuario recebe os dados do usuarioDTO vindos do frontend
        usuarioDTO.setUsuario_id(usuario.getUsuario_id());
        usuario = usuarioMapper.updateEntity(usuarioDTO,usuario);
        //metodo para salvar o usuario no banco de dados
        usuario = usuarioRepository.save(usuario);
        //retorna o usuario entidade convertido em DTO
        return usuarioMapper.toDTO(usuario);
    }
    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }
}


