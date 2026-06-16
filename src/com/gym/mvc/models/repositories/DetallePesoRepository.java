package com.gym.mvc.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.DetallePeso;
import com.gym.mvc.models.db.ConexionDB;

public class DetallePesoRepository implements IRepository<DetallePeso> {
	private Connection conn;

	public DetallePesoRepository() {
		conn = ConexionDB.getInstancia().getConexion();
	}

	@Override
	public void create(DetallePeso dp) {
		String sql = "INSERT INTO detalle_peso (id_equipo, peso_kg) VALUES (?, ?) ON DUPLICATE KEY UPDATE peso_kg = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, dp.getIdEquipo());
			ps.setDouble(2, dp.getPesoKg());
			ps.setDouble(3, dp.getPesoKg());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<DetallePeso> readAll() {
		List<DetallePeso> lista = new ArrayList<>();
		String sql = "SELECT * FROM detalle_peso";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(new DetallePeso(rs.getInt("id_equipo"), rs.getDouble("peso_kg")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Optional<DetallePeso> findById(int idEquipo) {
		String sql = "SELECT * FROM detalle_peso WHERE id_equipo = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idEquipo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				DetallePeso dp = new DetallePeso(rs.getInt("id_equipo"), rs.getDouble("peso_kg"));
				ps.close();
				return Optional.of(dp);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void update(DetallePeso dp) {
		String sql = "UPDATE detalle_peso SET peso_kg=? WHERE id_equipo=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, dp.getPesoKg());
			ps.setInt(2, dp.getIdEquipo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int idEquipo) {
		String sql = "DELETE FROM detalle_peso WHERE id_equipo = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idEquipo);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}