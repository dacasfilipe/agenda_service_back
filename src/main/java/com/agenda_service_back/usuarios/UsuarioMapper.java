package com.agenda_service_back.usuarios;

import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.endereco.EnderecoDTO;
import com.agenda_service_back.endereco.EnderecoMapper;
import com.agenda_service_back.telefone.Telefone;
//import com.agenda_service_back.telefone.TelefoneDTO;
//import com.agenda_service_back.telefone.TelefoneMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;
//@Mapper(componentModel = "spring", imports = {EnderecoMapper.class, TelefoneMapper.class})
@Mapper(componentModel = "spring", imports = {EnderecoMapper.class})
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mappings({
            @Mapping(source = "usuario_enderecoDTO", target = "endereco"),
//            @Mapping(source = "telefonesDTO", target = "telefones", qualifiedByName = "telefonesDTOToTelefonesEntity"),
//            @Mapping(source = "agendamentosDTO", target = "agendamentos", qualifiedByName = "agendamentosDTOToAgendamentosEntity")
    })
    Usuario toEntity(UsuarioDTO usuarioDTO);

    @Mappings({
            @Mapping(source = "endereco", target = "usuario_enderecoDTO"),
//            @Mapping(source = "telefones", target = "telefonesDTO", qualifiedByName = "telefonesEntityToTelefonesDTO"),
//            @Mapping(source = "agendamentos", target = "agendamentosDTO", qualifiedByName = "agendamentosEntityToAgendamentosDTO")
    })
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(source = "endereco_id", target = "endereco_id")
    EnderecoDTO enderecoToEnderecoDTO(Endereco endereco);

    @Mapping(source = "enderecoDTO.endereco_id", target = "endereco_id")
    Endereco enderecoDTOToEnderecoEntity(EnderecoDTO enderecoDTO);

//    @Mapping(source = "telefone", target = "telefoneDTO")
//    TelefoneDTO telefoneEntityToTelefoneDTO(Telefone telefone);

//    @Mapping(source = "telefoneDTO", target = "telefone")
//    Telefone telefoneDTOToTelefoneEntity(TelefoneDTO telefoneDTO);

    List<UsuarioDTO> toDTOList(List<Usuario> usuarios);

    @Mappings({
            @Mapping(source = "usuarioDTO.usuario_id", target = "usuario_id"),
            @Mapping(source = "usuarioDTO.usuario_cpf", target = "usuario_cpf"),
            @Mapping(source = "usuarioDTO.usuario_email", target = "usuario_email"),
            @Mapping(source = "usuarioDTO.usuario_data_nascimento", target = "usuario_data_nascimento"),
            @Mapping(source = "usuarioDTO.usuario_senha", target = "usuario_senha"),
            @Mapping(source = "usuarioDTO.usuario_enderecoDTO", target = "endereco")
//            @Mapping(source = "usuarioDTO.telefonesDTO", target = "usuario.telefones", qualifiedByName = "telefonesDTOToTelefonesEntity")
    })
    Usuario updateEntity(UsuarioDTO usuarioDTO, Usuario usuario);

//    // Mapping for Agendamento (assuming Agendamento has an ID and relevant fields)
//    @Mapping(source = "agendamento", target = "agendamentoDTO")
//    AgendamentoDTO agendamentoEntityToAgendamentoDTO(Agendamento agendamento);
//
//    @Mapping(source = "agendamentoDTO", target = "agendamento")
//    Agendamento agendamentoDTOToAgendamentoEntity(AgendamentoDTO agendamentoDTO);
//
//    // Mapping for Agendamentos List (assuming Agendamento has an ID and relevant fields)
//    List<AgendamentoDTO> agendamentosEntityToAgendamentosDTO(List<Agendamento> agendamentos) {
//        return agendamentos.stream()
//                .map(this::agendamentoEntityToAgendamentoDTO)
//                .collect(Collectors.toList());
//    }

//    List<Agendamento> agendamentosDTOToAgendamentosEntity(List<AgendamentoDTO> agendamentosDTO) {
//        return agendamentosDTO.stream()
//                .map(agendamentoDTO -> {
//                    Agendamento agendamento = new Agendamento();
//                    // Map AgendamentoDTO properties to Agendamento object
//                    // For example:
//                    agendamento.setAgendamentoId(agendamentoDTO.getAgendamentoId());
//                    agendamento.setAgendamentoData(agendamentoDTO.getAgendamentoData());
//                    agendamento.setAgendamentoHora(agendamentoDTO.getAgendamentoHora());
//                    agendamento.setAgendamentoDescricao(agendamentoDTO.getAgendamentoDescricao());
//                    // ... map other AgendamentoDTO properties
//                    return agendamento;
//                })
//                .collect(Collectors.toList());
//    }
}
