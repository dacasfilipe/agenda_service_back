package com.agenda_service_back.prestador;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PrestadorRepository extends JpaRepository<Prestador,Long> {
}
