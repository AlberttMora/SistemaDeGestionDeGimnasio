package com.gym.mvc.models.services;

import com.gym.mvc.models.TipoEquipo;
import com.gym.mvc.models.repositories.TipoEquipoRepository; // Importa el correcto
import java.util.List;

public class TipoService {
    private TipoEquipoRepository repository;

    public TipoService() {
        this.repository = new TipoEquipoRepository();
    }

    public List<TipoEquipo> getAll() {
        return repository.readAll();
    }
}