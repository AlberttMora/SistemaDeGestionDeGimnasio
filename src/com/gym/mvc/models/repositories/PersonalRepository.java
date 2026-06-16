package com.gym.mvc.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.gym.mvc.models.Personal;
import com.gym.mvc.models.db.ConexionDB;

public class PersonalRepository {
	private Connection conn;

	public PersonalRepository() {
		conn = ConexionDB.getInstancia().getConexion();
	}

	public Optional<Personal> findByEmailAndContrasena(String email, String contrasena) {
		String sql = "SELECT p.id_personal, p.nombre, p.apellido, p.email, p.telefono, p.contrasena, " + "CASE "
				+ "  WHEN a.id_personal IS NOT NULL THEN 'ADMIN' "
				+ "  WHEN e.id_personal IS NOT NULL THEN 'ENTRENADOR' "
				+ "  WHEN l.id_personal IS NOT NULL THEN 'LIMPIEZA' " + "  ELSE 'SIN ROL' " + "END AS tipo_rol "
				+ "FROM personal p " + "LEFT JOIN administrador a ON a.id_personal = p.id_personal "
				+ "LEFT JOIN entrenador    e ON e.id_personal = p.id_personal "
				+ "LEFT JOIN limpieza      l ON l.id_personal = p.id_personal "
				+ "WHERE p.email = ? AND p.contrasena = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, contrasena);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Personal p = new Personal(rs.getInt("id_personal"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("email"), rs.getString("telefono"), rs.getString("contrasena"));
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