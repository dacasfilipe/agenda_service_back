package com.agenda_service_back.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    // cria uma interface para ser implementada

}
