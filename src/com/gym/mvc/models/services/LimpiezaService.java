package com.gym.mvc.models.services;

import java.util.List;
import com.gym.mvc.models.Limpieza;
import com.gym.mvc.models.repositories.LimpiezaRepository;

public class LimpiezaService implements IService<Limpieza> {
	private LimpiezaRepository repo;

	public LimpiezaService() {
		repo = new LimpiezaRepository();
	}

	@Override
	public void store(Limpieza l) {
		validarLimpieza(l);
		repo.create(l);
	}

	@Override
	public List<Limpieza> getAll() {
		return repo.readAll();
	}

	@Override
	public Limpieza findById(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		return repo.findById(id).orElse(null);
	}

	@Override
	public void update(Limpieza l) {
		if (l.getIdPersonal() <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		validarLimpieza(l);
		repo.update(l);
	}

	@Override
	public void destroy(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		repo.delete(id);
	}

	private void validarLimpieza(Limpieza l) {
		if (l == null) {
			throw new IllegalArgumentException("El empleado de limpieza no puede ser nulo");
		}
		if (l.getNombre() == null || l.getNombre().trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre es obligatorio");
		}
		if (l.getApellido() == null || l.getApellido().trim().isEmpty()) {
			throw new IllegalArgumentException("El apellido es obligatorio");
		}
		if (l.getEmail() == null || l.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("El correo es obligatorio");
		}
		if (!l.getEmail().contains("@")) {
			throw new IllegalArgumentException("Formato de correo electronico invalido");
		}
		if (l.getTelefono() == null || l.getTelefono().trim().isEmpty()) {
			throw new IllegalArgumentException("El telefono es obligatorio");
		}
		if (l.getContrasena() == null || l.getContrasena().trim().isEmpty()) {
			throw new IllegalArgumentException("La contrasena es obligatoria");
		}
		if (l.getTurno() == null || l.getTurno().trim().isEmpty()) {
			throw new IllegalArgumentException("El turno es obligatorio");
		}
		if (l.getAreaAsignada() == null || l.getAreaAsignada().trim().isEmpty()) {
			throw new IllegalArgumentException("El area asignada es obligatoria");
		}
	}
}