package com.gym.mvc.models.services;

import java.util.List;
import com.gym.mvc.models.AsignacionEntrenador;
import com.gym.mvc.models.repositories.AsignacionRepository;

public class AsignacionService implements IService<AsignacionEntrenador> {
	private AsignacionRepository repo;

	public AsignacionService() {
		repo = new AsignacionRepository();
	}

	@Override
	public void store(AsignacionEntrenador a) {
		validarAsignacion(a);
		repo.create(a);
	}

	@Override
	public List<AsignacionEntrenador> getAll() {
		return repo.readAll();
	}

	@Override
	public AsignacionEntrenador findById(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		return repo.findById(id).orElse(null);
	}

	@Override
	public void update(AsignacionEntrenador a) {
		// Una Asignacion no se debe de poder modificar
	}

	@Override
	public void destroy(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		repo.delete(id);
	}

	private void validarAsignacion(AsignacionEntrenador a) {
		if (a == null) {
			throw new IllegalArgumentException("La asignacion no puede ser nula");
		}
		if (a.getIdEntrenador() <= 0) {
			throw new IllegalArgumentException("Entrenador invalido");
		}
		if (a.getIdCliente() <= 0) {
			throw new IllegalArgumentException("Cliente invalido");
		}
		if (a.getFechaInicio() == null) {
			throw new IllegalArgumentException("La fecha de inicio es obligatoria");
		}
		if (a.getFechaFin() == null) {
			throw new IllegalArgumentException("La fecha de fin es obligatoria");
		}
		if (a.getFechaFin().before(a.getFechaInicio())) {
			throw new IllegalArgumentException("La fecha de fin no puede ser menor que la fecha de inicio");
		}
	}
}