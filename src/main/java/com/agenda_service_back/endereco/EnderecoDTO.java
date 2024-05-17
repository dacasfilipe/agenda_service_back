package com.agenda_service_back.endereco;

import com.agenda_service_back.prestador.Prestador;
import com.agenda_service_back.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Long endereco_id;

    @NotNull(message = "o campo RUA é requerido.")
    private String endereco_rua;

    @NotNull(message = "o campo CEP é requerido.")
    private String endereco_cep;

    @NotNull(message = "o campo Numero é requerido.")
    private int endereco_numero;

    private String endereco_complemento;

    @NotNull(message = "o campo CIDADE é requerido.")
    private String endereco_cidade;

    @NotNull(message = "o campo ESTADO é requerido.")
    private String endereco_estado;

    @NotNull(message = "o campo BAIRRO é requerido.")
    private String endereco_bairro;

    @JsonIgnore
    private List<Usuario> usuarios = new ArrayList<>();
//
//    @JsonIgnore
//    private List<Prestador> prestadores;

    @Override
    public String toString() {
        return "EnderecoDTO{" +
                "endereco_id=" + endereco_id +
                ", endereco_rua='" + endereco_rua + '\'' +
                ", endereco_cep='" + endereco_cep + '\'' +
                ", endereco_numero=" + endereco_numero +
                ", endereco_complemento='" + endereco_complemento + '\'' +
                ", endereco_cidade='" + endereco_cidade + '\'' +
                ", endereco_estado='" + endereco_estado + '\'' +
                ", endereco_bairro='" + endereco_bairro + '\'' +
                '}';
    }
}
