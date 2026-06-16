package com.gym.mvc.models.services;

import java.util.List;

import com.gym.mvc.models.Pago;
import com.gym.mvc.models.repositories.PagoRepository;

public class PagoService implements IService<Pago> {

	private PagoRepository repo;

	public PagoService() {
		repo = new PagoRepository();
	}

	@Override
	public void store(Pago p) {
		validarPago(p);
		repo.create(p);
	}

	@Override
	public List<Pago> getAll() {
		return repo.readAll();
	}

	@Override
	public Pago findById(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}

		return repo.findById(id).orElse(null);
	}

	@Override
	public void update(Pago p) {
	// Un pago no se debe de poder modificar
	}

	@Override
	public void destroy(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id invalido");
		}

		repo.delete(id);
	}

	private void validarPago(Pago p) {
		if (p == null) {
			throw new IllegalArgumentException("El pago no puede ser nulo");
		}

		if (p.getIdInscripcion() <= 0) {
			throw new IllegalArgumentException("Inscripcion invalida");
		}

		if (p.getMonto() <= 0) {
			throw new IllegalArgumentException("El monto debe ser mayor que cero");
		}

		if (p.getFechaPago() == null) {
			throw new IllegalArgumentException("La fecha de pago es obligatoria");
		}

		if (p.getMetodoPago() == null || p.getMetodoPago().trim().isEmpty()) {
			throw new IllegalArgumentException("El metodo de pago es obligatorio");
		}
	}
}