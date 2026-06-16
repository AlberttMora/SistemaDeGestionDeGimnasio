package com.gym.mvc.models.services;

import java.util.Date;
import java.util.List;

import com.gym.mvc.models.Cliente;
import com.gym.mvc.models.repositories.ClienteRepository;

public class ClienteService implements IService<Cliente> {

	private ClienteRepository repo;

	public ClienteService() {
		repo = new ClienteRepository();
	}

	@Override
	public void store(Cliente c) {
		validarCliente(c);
		repo.create(c);
	}

	@Override
	public List<Cliente> getAll() {
		return repo.readAll();
	}

	@Override
	public Cliente findById(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("El id debe ser mayor que cero");
		}

		return repo.findById(id).orElse(null);
	}

	@Override
	public void update(Cliente c) {
		if (c.getIdCliente() <= 0) {
			throw new IllegalArgumentException("Id de cliente invalido");
		}

		validarCliente(c);
		repo.update(c);
	}

	@Override
	public void destroy(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("El id debe ser mayor que cero");
		}

		repo.delete(id);
	}

	private void validarCliente(Cliente c) {

		if (c == null) {
			throw new IllegalArgumentException("El cliente no puede ser nulo");
		}

		if (c.getNombre() == null || c.getNombre().trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre es obligatorio");
		}

		if (c.getApellido() == null || c.getApellido().trim().isEmpty()) {
			throw new IllegalArgumentException("El apellido es obligatorio");
		}

		if (c.getEmail() == null || c.getEmail().trim().isEmpty()) {
			throw new IllegalArgumentException("El correo es obligatorio");
		}

		if (!c.getEmail().contains("@")) {
			throw new IllegalArgumentException("Formato de correo electronico invalido");
		}

		if (c.getTelefono() == null || c.getTelefono().trim().isEmpty()) {
			throw new IllegalArgumentException("El telefono es obligatorio");
		}

		if (c.getFechaNac() == null) {
			throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
		}

		if (c.getFechaNac().after(new Date())) {
			throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
		}
	}
}