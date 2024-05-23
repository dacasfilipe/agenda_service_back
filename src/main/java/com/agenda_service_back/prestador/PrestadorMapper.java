package com.agenda_service_back.prestador;

import com.agenda_service_back.prestador.Prestador;
import com.agenda_service_back.prestador.PrestadorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrestadorMapper {
    @Mapping(source = "prestador_id", target = "prestador_id")
    PrestadorDTO toDTO(Prestador prestador);

    @Mapping(source = "prestadorDTO.prestador_id", target = "prestador_id")
    Prestador toEntity(PrestadorDTO prestadorDTO);

    List<PrestadorDTO> toDTOList(List<Prestador> prestador);

    @Mappings({
            @Mapping(source = "prestadorDTO.prestador_id", target = "prestador_id"),
            @Mapping(source = "prestadorDTO.prestador_nome", target = "prestador_nome"),
            @Mapping(source = "prestadorDTO.prestador_cpf", target = "prestador_cpf"),
            @Mapping(source = "prestadorDTO.prestador_cnpj", target = "prestador_cnpj"),
            @Mapping(source = "prestadorDTO.prestador_email", target = "prestador_email"),
            @Mapping(source = "prestadorDTO.prestador_senha", target = "prestador_senha"),
            @Mapping(source = "prestadorDTO.prestador_razao_social", target = "prestador_razao_social"),
            @Mapping(source = "prestadorDTO.endereco", target = "endereco"),
            @Mapping(source = "prestadorDTO.telefones", target = "telefones"),
            @Mapping(source = "prestadorDTO.servicos", target = "servicos")

    })
    Prestador updateEntity(PrestadorDTO prestadorDTO, Prestador Prestador);
}
