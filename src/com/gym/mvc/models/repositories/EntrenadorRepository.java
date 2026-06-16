package com.gym.mvc.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.Entrenador;
import com.gym.mvc.models.db.ConexionDB;

public class EntrenadorRepository implements IRepository<Entrenador> {
	private Connection conn;

	public EntrenadorRepository() {
		conn = ConexionDB.getInstancia().getConexion();
	}

	@Override
	public void create(Entrenador e) {
		String sqlPersonal = "INSERT INTO personal (nombre, apellido, email, telefono, contrasena) VALUES (?, ?, ?, ?, ?)";
		String sqlEntrenador = "INSERT INTO entrenador (id_personal, especialidad, certificacion) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps1 = conn.prepareStatement(sqlPersonal, Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1, e.getNombre());
			ps1.setString(2, e.getApellido());
			ps1.setString(3, e.getEmail());
			ps1.setString(4, e.getTelefono());
			ps1.setString(5, e.getContrasena());
			ps1.executeUpdate();

			ResultSet keys = ps1.getGeneratedKeys();
			if (keys.next()) {
				int idGenerado = keys.getInt(1);
				PreparedStatement ps2 = conn.prepareStatement(sqlEntrenador);
				ps2.setInt(1, idGenerado);
				ps2.setString(2, e.getEspecialidad());
				ps2.setString(3, e.getCertificacion());
				ps2.executeUpdate();
				ps2.close();
			}
			ps1.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Entrenador> readAll() {
		List<Entrenador> lista = new ArrayList<>();
		String sql = "SELECT p.id_personal, p.nombre, p.apellido, p.email, p.telefono, p.contrasena, "
				+ "e.especialidad, e.certificacion "
				+ "FROM personal p JOIN entrenador e ON e.id_personal = p.id_personal";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(new Entrenador(rs.getInt("id_personal"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("email"), rs.getString("telefono"), rs.getString("contrasena"),
						rs.getString("especialidad"), rs.getString("certificacion")));
			}
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}

	@Override
	public Optional<Entrenador> findById(int id) {
		String sql = "SELECT p.id_personal, p.nombre, p.apellido, p.email, p.telefono, p.contrasena, "
				+ "e.especialidad, e.certificacion "
				+ "FROM personal p JOIN entrenador e ON e.id_personal = p.id_personal " + "WHERE p.id_personal = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Entrenador ent = new Entrenador(rs.getInt("id_personal"), rs.getString("nombre"),
						rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"),
						rs.getString("contrasena"), rs.getString("especialidad"), rs.getString("certificacion"));
				ps.close();
				return Optional.of(ent);
			}
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void update(Entrenador e) {
		String sqlPersonal = "UPDATE personal SET nombre=?, apellido=?, email=?, telefono=?, contrasena=? WHERE id_personal=?";
		String sqlEntrenador = "UPDATE entrenador SET especialidad=?, certificacion=? WHERE id_personal=?";
		try {
			PreparedStatement ps1 = conn.prepareStatement(sqlPersonal);
			ps1.setString(1, e.getNombre());
			ps1.setString(2, e.getApellido());
			ps1.setString(3, e.getEmail());
			ps1.setString(4, e.getTelefono());
			ps1.setString(5, e.getContrasena());
			ps1.setInt(6, e.getIdPersonal());
			ps1.executeUpdate();
			ps1.close();

			PreparedStatement ps2 = conn.prepareStatement(sqlEntrenador);
			ps2.setString(1, e.getEspecialidad());
			ps2.setString(2, e.getCertificacion());
			ps2.setInt(3, e.getIdPersonal());
			ps2.executeUpdate();
			ps2.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sqlEntrenador = "DELETE FROM entrenador WHERE id_personal = ?";
		String sqlPersonal = "DELETE FROM personal WHERE id_personal = ?";
		try {
			PreparedStatement ps1 = conn.prepareStatement(sqlEntrenador);
			ps1.setInt(1, id);
			ps1.executeUpdate();
			ps1.close();

			PreparedStatement ps2 = conn.prepareStatement(sqlPersonal);
			ps2.setInt(1, id);
			ps2.executeUpdate();
			ps2.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}