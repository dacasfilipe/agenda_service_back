package com.agenda_service_back.usuarios;

import com.agenda_service_back.endereco.EnderecoDTO;
import com.agenda_service_back.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long usuario_id;
    @CPF
    private String usuario_cpf;
    private String usuario_email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate usuario_data_nascimento;
    private String usuario_senha;
    private EnderecoDTO usuario_enderecoDTO;

    public Long getEnderecoId() {
        if (usuario_enderecoDTO != null) {
            return usuario_enderecoDTO.getEndereco_id();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "usuario_id=" + usuario_id +
                ", usuario_cpf='" + usuario_cpf + '\'' +
                ", usuario_email='" + usuario_email + '\'' +
                ", usuario_data_nascimento=" + usuario_data_nascimento +
//                ", usuario_senha='" + usuario_senha + '\'' +
                //pegar ´so o id do endereco do usuario
                ", endereco_id='" + getEnderecoId() + '\'' +
                '}';
    }
}
