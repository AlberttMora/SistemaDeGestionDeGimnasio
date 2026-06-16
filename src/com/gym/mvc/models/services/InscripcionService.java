package com.gym.mvc.models.services;

import java.util.List;

import com.gym.mvc.models.Inscripcion;
import com.gym.mvc.models.repositories.InscripcionRepository;

public class InscripcionService implements IService<Inscripcion> {

	private InscripcionRepository repo;

	public InscripcionService() {
		repo = new InscripcionRepository();
	}

	@Override
	public void store(Inscripcion i) {
		validarInscripcion(i);
		repo.create(i);
	}

	@Override
	public List<Inscripcion> getAll() {
		return repo.readAll();
	}

	@Override
	public Inscripcion findById(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}

		return repo.findById(id).orElse(null);
	}

	@Override
	public void update(Inscripcion i) {
		if (i.getIdInscripcion() <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}

		validarInscripcion(i);
		repo.update(i);
	}

	@Override
	public void destroy(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}

		repo.delete(id);
	}

	private void validarInscripcion(Inscripcion i) {
		if (i == null) {
			throw new IllegalArgumentException("La inscripcion no puede ser nula");
		}
		if (i.getIdCliente() <= 0) {
			throw new IllegalArgumentException("Cliente invalido");
		}
		if (i.getIdMembresia() <= 0) {
			throw new IllegalArgumentException("Membresia invalida");
		}
		if (i.getFechaInicio() == null) {
			throw new IllegalArgumentException("La fecha de inicio es obligatoria");
		}
		if (i.getEstado() == null || i.getEstado().trim().isEmpty()) {
			throw new IllegalArgumentException("El estado es obligatorio");
		}
	}
}