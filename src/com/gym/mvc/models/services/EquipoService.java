package com.gym.mvc.models.services;

import java.util.Date;
import java.util.List;

import com.gym.mvc.models.Equipo;
import com.gym.mvc.models.repositories.EquipoRepository;

public class EquipoService implements IService<Equipo> {

	private EquipoRepository repo;

	public EquipoService() {
		repo = new EquipoRepository();
	}

	@Override
	public void store(Equipo e) {
		validarEquipo(e);
		repo.create(e);
	}

	@Override
	public List<Equipo> getAll() {
		return repo.readAll();
	}

	@Override
	public Equipo findById(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}

		return repo.findById(id).orElse(null);
	}

	@Override
	public void update(Equipo e) {
		if (e.getIdEquipo() <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}

		validarEquipo(e);
		repo.update(e);
	}

	@Override
	public void destroy(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}

		repo.delete(id);
	}

	private void validarEquipo(Equipo e) {
		if (e == null) {
			throw new IllegalArgumentException("El equipo no puede ser nulo");
		}

		if (e.getIdTipo() <= 0) {
			throw new IllegalArgumentException("Debe seleccionar un tipo de equipo");
		}

		if (e.getNombre() == null || e.getNombre().trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre es obligatorio");
		}

		if (e.getEstado() == null || e.getEstado().trim().isEmpty()) {
			throw new IllegalArgumentException("El estado es obligatorio");
		}

		if (e.getFechaAdquisicion() == null) {
			throw new IllegalArgumentException("La fecha de adquisicion es obligatoria");
		}

		if (e.getFechaAdquisicion().after(new Date())) {
			throw new IllegalArgumentException("La fecha de adquisicion no puede ser futura");
		}
	}

}