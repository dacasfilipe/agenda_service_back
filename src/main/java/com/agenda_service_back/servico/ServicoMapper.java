package com.agenda_service_back.servico;

import com.agenda_service_back.servico.ServicoDTO;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.usuarios.Usuario;
import com.agenda_service_back.usuarios.UsuarioDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoMapper {
    @Mappings({
            @Mapping(source = "servico_id", target = "servicoId"),
            @Mapping(source = "servico_descricao", target = "servicoDescricao"),
            @Mapping(source = "servico_nome", target = "servicoNome"),
            @Mapping(source = "servico_informacoes_extras", target = "servicoInformacoesExtras"),
            @Mapping(source = "servico_preco", target = "servicoPreco"),
            @Mapping(source = "categoria", target = "categoria"),
            @Mapping(source = "prestador", target = "prestador")
    })
    ServicoDTO toDTO(Servico servico);

    @Mapping(source = "servicoDTO.servicoId", target = "servico_id")
    Servico toEntity(ServicoDTO servicoDTO);

    List<ServicoDTO> toDTOList(List<Servico> servicos);

    /*List<Servico> toEntityList(List<ServicoDTO> servicoDTOs);*/
    @Mappings({
            @Mapping(source = "servicoDTO.servicoId", target = "servico_id"),
            @Mapping(source = "servicoDTO.servicoNome", target = "servico_nome"),
            @Mapping(source = "servicoDTO.servicoInformacoesExtras", target = "servico_informacoes_extras"),
            @Mapping(source = "servicoDTO.servicoDescricao", target = "servico_descricao"),
            @Mapping(source = "servicoDTO.servicoPreco", target = "servico_preco"),
            @Mapping(source = "servicoDTO.categoria", target = "categoria"),
            @Mapping(source = "servicoDTO.prestador", target = "prestador")
    })
    Servico updateEntity(ServicoDTO servicoDTO, Servico servico);

}
