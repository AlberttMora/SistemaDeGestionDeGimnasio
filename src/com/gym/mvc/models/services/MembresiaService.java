package com.gym.mvc.models.services;

import java.util.List;
import com.gym.mvc.models.Membresia;
import com.gym.mvc.models.repositories.MembresiaRepository;

public class MembresiaService implements IService<Membresia> {
	private MembresiaRepository repo;

	public MembresiaService() {
		repo = new MembresiaRepository();
	}

	@Override
	public void store(Membresia m) {
		validarMembresia(m);
		repo.create(m);
	}

	@Override
	public List<Membresia> getAll() {
		return repo.readAll();
	}

	@Override
	public Membresia findById(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		return repo.findById(id).orElse(null);
	}

	@Override
	public void update(Membresia m) {
		if (m.getIdMembresia() <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		validarMembresia(m);
		repo.update(m);
	}

	@Override
	public void destroy(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		repo.delete(id);
	}

	private void validarMembresia(Membresia m) {
		if (m == null) {
			throw new IllegalArgumentException("La membresia no puede ser nula");
		}
		if (m.getTipo() == null || m.getTipo().trim().isEmpty()) {
			throw new IllegalArgumentException("El tipo de membresia es obligatorio");
		}
		if (m.getPrecio() <= 0) {
			throw new IllegalArgumentException("El precio debe ser mayor que cero");
		}
		if (m.getDuracion() <= 0) {
			throw new IllegalArgumentException("La duracion debe ser mayor que cero dias");
		}
	}
}