package com.gym.mvc.models.services;

import java.util.Date;
import java.util.List;
import com.gym.mvc.models.BitacoraMantenimiento;
import com.gym.mvc.models.repositories.BitacoraRepository;

public class BitacoraService implements IService<BitacoraMantenimiento> {
	private BitacoraRepository repo;

	public BitacoraService() {
		repo = new BitacoraRepository();
	}

	@Override
	public void store(BitacoraMantenimiento b) {
		validarBitacora(b);
		repo.create(b);
	}

	@Override
	public List<BitacoraMantenimiento> getAll() {
		return repo.readAll();
	}

	@Override
	public BitacoraMantenimiento findById(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		return repo.findById(id).orElse(null);
	}

	@Override
	public void update(BitacoraMantenimiento b) {
		if (b.getIdBitacora() <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}
		validarBitacora(b);
		repo.update(b);
	}

	@Override
	public void destroy(int id) {
		// Bitacora no se debe de poder modificar
	}

	private void validarBitacora(BitacoraMantenimiento b) {
		if (b == null) {
			throw new IllegalArgumentException("La bitacora no puede ser nula");
		}
		if (b.getIdEquipo() <= 0) {
			throw new IllegalArgumentException("Equipo invalido");
		}
		if (b.getIdPersonal() <= 0) {
			throw new IllegalArgumentException("Personal invalido");
		}
		if (b.getFecha() == null) {
			throw new IllegalArgumentException("La fecha es obligatoria");
		}
		if (b.getFecha().after(new Date())) {
			throw new IllegalArgumentException("La fecha no puede ser futura");
		}
		if (b.getDescripcion() == null || b.getDescripcion().trim().isEmpty()) {
			throw new IllegalArgumentException("La descripcion es obligatoria");
		}
	}
}