package com.agenda_service_back.prestador;

import com.agenda_service_back.endereco.Endereco;
import com.agenda_service_back.servico.Servico;
import com.agenda_service_back.telefone.Telefone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestadorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long prestador_id;
    private String prestador_nome;
    private String prestador_cpf;
    private String prestador_cnpj;
    private String prestador_email;
    private String prestador_senha;
    private String prestador_razao_social;
    private Endereco endereco;
    private List<Telefone> telefones;
    private List<Servico> servicos;
}
