package com.gym.mvc.models.services;
import java.util.List;
import com.gym.mvc.models.Entrenador;
import com.gym.mvc.models.repositories.EntrenadorRepository;

public class EntrenadorService implements IService<Entrenador> {
    private EntrenadorRepository repo;

    public EntrenadorService() {
        repo = new EntrenadorRepository();
    }

    @Override
    public void store(Entrenador e) {
        validarEntrenador(e);
        repo.create(e);
    }

    @Override
    public List<Entrenador> getAll() {
        return repo.readAll();
    }

    @Override
    public Entrenador findById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id invalido");
        }
        return repo.findById(id).orElse(null);
    }

    @Override
    public void update(Entrenador e) {
        if (e.getIdPersonal() <= 0) {
            throw new IllegalArgumentException("Id invalido");
        }
        validarEntrenador(e);
        repo.update(e);
    }

    @Override
    public void destroy(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id invalido");
        }
        repo.delete(id);
    }

    private void validarEntrenador(Entrenador e) {
        if (e == null) {
            throw new IllegalArgumentException("El entrenador no puede ser nulo");
        }
        if (e.getNombre() == null || e.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (e.getApellido() == null || e.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if (e.getEmail() == null || e.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio");
        }
        if (!e.getEmail().contains("@")) {
            throw new IllegalArgumentException("Formato de correo electronico invalido");
        }
        if (e.getTelefono() == null || e.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono es obligatorio");
        }
        if (e.getContrasena() == null || e.getContrasena().trim().isEmpty()) {
            throw new IllegalArgumentException("La contrasena es obligatoria");
        }
        if (e.getEspecialidad() == null || e.getEspecialidad().trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (e.getCertificacion() == null || e.getCertificacion().trim().isEmpty()) {
            throw new IllegalArgumentException("La certificacion es obligatoria");
        }
    }
}