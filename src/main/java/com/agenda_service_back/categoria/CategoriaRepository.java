package com.agenda_service_back.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    // cria uma interface para ser implementada

}
