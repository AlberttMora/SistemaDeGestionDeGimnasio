package com.gym.mvc.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.gym.mvc.models.Administrador;
import com.gym.mvc.models.Entrenador;
import com.gym.mvc.models.Limpieza;
import com.gym.mvc.models.Personal;
import com.gym.mvc.models.db.ConexionDB;

public class PersonalRepository {
	private Connection conn;

	public PersonalRepository() {
		conn = ConexionDB.getInstancia().getConexion();
	}

	public Optional<Personal> findByEmailAndContrasena(String email, String contrasena) {
		String sql = "SELECT p.id_personal, p.nombre, p.apellido, p.email, p.telefono, p.contrasena, "
				+ "a.nivel_acceso, e.especialidad, e.certificacion, l.turno, l.area_asignada, CASE "
				+ "  WHEN a.id_personal IS NOT NULL THEN 'ADMIN' "
				+ "  WHEN e.id_personal IS NOT NULL THEN 'ENTRENADOR' "
				+ "  WHEN l.id_personal IS NOT NULL THEN 'LIMPIEZA' ELSE 'SIN ROL' END AS tipo_rol "
				+ "FROM personal p LEFT JOIN administrador a ON a.id_personal = p.id_personal "
				+ "LEFT JOIN entrenador e ON e.id_personal = p.id_personal "
				+ "LEFT JOIN limpieza ssl ON l.id_personal = p.id_personal WHERE p.email = ? "
				+ "AND p.contrasena = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, contrasena);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id_personal");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String correo = rs.getString("email");
				String telefono = rs.getString("telefono");
				String pass = rs.getString("contrasena");
				String tipoRol = rs.getString("tipo_rol");

				Personal p;
				switch (tipoRol) {
				case "ADMIN":
					p = new Administrador(id, nombre, apellido, correo, telefono, pass, rs.getString("nivel_acceso"));
					break;
				case "ENTRENADOR":
					p = new Entrenador(id, nombre, apellido, correo, telefono, pass, rs.getString("especialidad"),
							rs.getString("certificacion"));
					break;
				case "LIMPIEZA":
					p = new Limpieza(id, nombre, apellido, correo, telefono, pass, rs.getString("turno"),
							rs.getString("area_asignada"));
					break;
				default:
					p = new Personal(id, nombre, apellido, correo, telefono, pass);
				}
				ps.close();
				return Optional.of(p);
			}
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return Optional.empty();
	}
}