package com.agenda_service_back.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UsuarioRepository<T, ID extends Serializable> extends JpaRepository<Usuario,Long> {
}
