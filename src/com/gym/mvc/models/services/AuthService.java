package com.gym.mvc.models.services;

import java.util.Optional;
import com.gym.mvc.models.Personal;
import com.gym.mvc.models.repositories.PersonalRepository;

public class AuthService {
	private PersonalRepository personalRepo;

	public AuthService() {
		personalRepo = new PersonalRepository();
	}

	public Personal autenticar(String email, String contrasena) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("El correo es obligatorio");
		}
		if (contrasena == null || contrasena.trim().isEmpty()) {
			throw new IllegalArgumentException("La contrasena es obligatoria");
		}

		Optional<Personal> resultado = personalRepo.findByEmailAndContrasena(email, contrasena);
		if (!resultado.isPresent()) {
			throw new IllegalArgumentException("Credenciales invalidas");
		}

		Personal p = resultado.get();
		UserSession.getInstance().setLoggedUser(p);
		return p;
	}

	public void cerrarSesion() {
		UserSession.getInstance().clear();
	}
}