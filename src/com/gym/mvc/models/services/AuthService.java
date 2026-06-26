package com.gym.mvc.models.services;

import com.gym.mvc.models.Cliente;
import com.gym.mvc.models.Personal;
import com.gym.mvc.models.db.ConexionDB;
import com.gym.mvc.models.repositories.PersonalRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthService {

	private final PersonalRepository personalRepo;
	private final Connection conn;

	public AuthService() {
		personalRepo = new PersonalRepository();
		conn = ConexionDB.getInstancia().getConexion();
	}

	public Personal autenticar(String email, String contrasena) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("El correo es obligatorio");
		}
		if (contrasena == null || contrasena.trim().isEmpty()) {
			throw new IllegalArgumentException("La contrasena es obligatoria");
		}

		Optional<Personal> resultadoPersonal = personalRepo.findByEmailAndContrasena(email, contrasena);
		if (resultadoPersonal.isPresent()) {
			Personal p = resultadoPersonal.get();
			UserSession.getInstance().setLoggedPersonal(p);
			return p;
		}

		Cliente cliente = buscarCliente(email, contrasena);
		if (cliente != null) {
			UserSession.getInstance().setLoggedCliente(cliente);
			return null;
		}

		throw new IllegalArgumentException("Credenciales invalidas");
	}

	private Cliente buscarCliente(String email, String contrasena) {
		String sql = "SELECT id_cliente, nombre, apellido, email, telefono "
				+ "FROM cliente WHERE email = ? AND contrasena = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, contrasena);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Cliente c = new Cliente();
				c.setIdCliente(rs.getInt("id_cliente"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellido"));
				c.setEmail(rs.getString("email"));
				c.setTelefono(rs.getString("telefono"));
				ps.close();
				return c;
			}
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void cerrarSesion() {
		UserSession.getInstance().clear();
	}
}