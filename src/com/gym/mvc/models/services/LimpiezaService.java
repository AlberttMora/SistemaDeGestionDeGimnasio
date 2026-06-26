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
		if (l.getTurno() == null || l.getTurno().trim().isEmpty()) {
	        throw new IllegalArgumentException("El turno es obligatorio");
	    }
	    if (l.getAreaAsignada() == null || l.getAreaAsignada().trim().isEmpty()) {
	        throw new IllegalArgumentException("El area asignada es obligatoria");
	    }
	}
	
	public void registrarAsignacion(int idPersonal, String area, String turno) {
	    if (idPersonal <= 0) {
	        throw new IllegalArgumentException("ID de personal inválido");
	    }
	    if (area == null || area.trim().isEmpty()) {
	        throw new IllegalArgumentException("El área es obligatoria");
	    }
	    if (turno == null || turno.trim().isEmpty()) {
	        throw new IllegalArgumentException("El turno es obligatorio");
	    }
	    repo.asignarPersonalALimpieza(idPersonal, turno, area);
	}
}