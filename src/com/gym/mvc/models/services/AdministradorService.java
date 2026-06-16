package com.gym.mvc.models.services;

import java.util.List;
import com.gym.mvc.models.Administrador;
import com.gym.mvc.models.repositories.AdministradorRepository;

public class AdministradorService implements IService<Administrador> {
	private AdministradorRepository repo;

	public AdministradorService() {
		repo = new AdministradorRepository();
	}

	@Override
	public void store(Administrador a) {
		validarAdministrador(a);
		repo.create(a);
	}

	@Override
	public List<Administrador> getAll() {
		return repo.readAll();
	}

	@Override
	public Administrador findById(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		return repo.findById(id).orElse(null);
	}

	@Override
	public void update(Administrador a) {
		if (a.getIdPersonal() <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		validarAdministrador(a);
		repo.update(a);
	}

	@Override
	public void destroy(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		repo.delete(id);
	}

	private void validarAdministrador(Administrador a) {
		if (a == null) {
			throw new IllegalArgumentException("El administrador no puede ser nulo");
		}
		if (a.getNombre() == null || a.getNombre().trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre es obligatorio");
		}
		if (a.getApellido() == null || a.getApellido().trim().isEmpty()) {
			throw new IllegalArgumentException("El apellido es obligatorio");
		}
		if (a.getEmail() == null || a.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("El correo es obligatorio");
		}
		if (!a.getEmail().contains("@")) {
			throw new IllegalArgumentException("Formato de correo electronico invalido");
		}
		if (a.getTelefono() == null || a.getTelefono().trim().isEmpty()) {
			throw new IllegalArgumentException("El telefono es obligatorio");
		}
		if (a.getContrasena() == null || a.getContrasena().trim().isEmpty()) {
			throw new IllegalArgumentException("La contrasena es obligatoria");
		}
		if (a.getNivelAcceso() == null || a.getNivelAcceso().trim().isEmpty()) {
			throw new IllegalArgumentException("El nivel de acceso es obligatorio");
		}
	}
}