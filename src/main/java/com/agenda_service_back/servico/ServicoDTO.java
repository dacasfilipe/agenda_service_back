package com.agenda_service_back.servico;

import com.agenda_service_back.categoria.Categoria;
import com.agenda_service_back.prestador.Prestador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long servicoId;
    private String servicoNome;
    private String servicoInformacoesExtras;
    private String servicoDescricao;
    private Double servicoPreco;
    private Categoria categoria;
    private Prestador prestador;

    @Override
    public String toString() {
        return "ServicoDTO{" +
                "servicoId=" + servicoId +
                ", servicoNome='" + servicoNome + '\'' +
                ", servicoInformacoesExtras='" + servicoInformacoesExtras + '\'' +
                ", servicoDescricao='" + servicoDescricao + '\'' +
                ", servicoPreco=" + servicoPreco +
                ", categoria=" + categoria +
                ", prestador=" + prestador +
                '}';
    }
}
