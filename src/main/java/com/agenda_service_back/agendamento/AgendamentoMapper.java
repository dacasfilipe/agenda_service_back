package com.agenda_service_back.agendamento;

import com.agenda_service_back.agendamento.Agendamento;
import com.agenda_service_back.agendamento.AgendamentoDTO;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.usuarios.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    AgendamentoMapper INSTANCE = Mappers.getMapper(AgendamentoMapper.class);

    @Mapping(source = "agendamento_id", target = "id")
    AgendamentoDTO toDTO(Agendamento agendamento);

    @Mapping(source = "agendamentoDTO.id", target = "agendamento_id")
    Agendamento toEntity(AgendamentoDTO agendamentoDTO, Usuario usuario, Servico servico);

    @Mappings({
            @Mapping(source = "agendamentoDTO.id", target = "agendamento_id"),
            @Mapping(source = "agendamentoDTO.data", target = "agendamento_data"),
            @Mapping(source = "agendamentoDTO.hora", target = "agendamento_hora"),
            @Mapping(source = "agendamentoDTO.observacao", target = "agendamento_observacao"),
            @Mapping(source = "agendamentoDTO.status", target = "agendamento_status"),
            @Mapping(source = "agendamentoDTO.usuario", target = "usuario"),
            @Mapping(source = "agendamentoDTO.servico", target = "servico"),
    })
    Agendamento updateEntity(AgendamentoDTO agendamentoDTO, Agendamento agendamento);

    default List<AgendamentoDTO> toDTOList(List<Agendamento> agendamentoList) {
        List<AgendamentoDTO> dtoList = new ArrayList<>();
        for (Agendamento agendamento : agendamentoList) {
            dtoList.add(toDTO(agendamento));
        }
        return dtoList;
    }
}

//package com.agenda_service_back.agendamento;
//
//import com.agenda_service_back.prestador.Prestador;
//import com.agenda_service_back.servico.Servico;
//import com.agenda_service_back.usuarios.Usuario;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.factory.Mappers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface AgendamentoMapper {
//
//    AgendamentoMapper INSTANCE = Mappers.getMapper(AgendamentoMapper.class);
//
//    @Mapping(source = "agendamento_id", target = "id")
//    AgendamentoDTO toDTO(Agendamento agendamento);
//
//    @Mapping(source = "agendamentoDTO.id", target = "agendamento_id")
//    Agendamento toEntity(AgendamentoDTO agendamentoDTO, Usuario usuario, Servico servico);
//
//    @Mappings({
//            @Mapping(source = "agendamentoDTO.id", target = "agendamento_id"),
//            @Mapping(source = "agendamentoDTO.data", target = "agendamento_data"),
//            @Mapping(source = "agendamentoDTO.hora", target = "agendamento_hora"),
//            @Mapping(source = "agendamentoDTO.observacao", target = "agendamento_observacao"),
//            @Mapping(source = "agendamentoDTO.status", target = "agendamento_status"),
//            @Mapping(source = "agendamentoDTO.usuario", target = "usuario"),
//            @Mapping(source = "agendamentoDTO.servico", target = "servico"),
//    })
//    Agendamento updateEntity(AgendamentoDTO agendamentoDTO, Agendamento agendamento);
//
//    // Use MapStruct's built-in list mapping
//    default List<AgendamentoDTO> toDTOList(List<Agendamento> agendamentoList) {
//        List<AgendamentoDTO> dtoList = new ArrayList<>();
//        for (Agendamento agendamento : agendamentoList) {
//            dtoList.add(toDTO(agendamento));
//        }
//        return dtoList;
//    }
//}