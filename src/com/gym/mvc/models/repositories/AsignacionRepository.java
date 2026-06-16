package com.gym.mvc.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.*;
import com.gym.mvc.models.db.ConexionDB;
import java.sql.Date;

public class AsignacionRepository implements IRepository<AsignacionEntrenador> {
	private Connection conn;

	public AsignacionRepository() {
		conn = ConexionDB.getInstancia().getConexion();
	}

	@Override
	public void create(AsignacionEntrenador a) {
		String sql = "INSERT INTO asignacion_entrenador (id_entrenador, id_cliente, fecha_inicio, fecha_fin, observaciones) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getIdEntrenador());
			ps.setInt(2, a.getIdCliente());
			ps.setDate(3, new Date(a.getFechaInicio().getTime()));
			ps.setDate(4, new Date(a.getFechaFin().getTime()));
			ps.setString(5, a.getObservaciones());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<AsignacionEntrenador> readAll() {
		List<AsignacionEntrenador> lista = new ArrayList<>();
		String sql = "SELECT * FROM asignacion_entrenador";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(new AsignacionEntrenador(rs.getInt("id_asignacion"), rs.getInt("id_entrenador"),
						rs.getInt("id_cliente"), rs.getDate("fecha_inicio"), rs.getDate("fecha_fin"),
						rs.getString("observaciones")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Optional<AsignacionEntrenador> findById(int id) {
		String sql = "SELECT * FROM asignacion_entrenador WHERE id_asignacion = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				AsignacionEntrenador a = new AsignacionEntrenador(rs.getInt("id_asignacion"),
						rs.getInt("id_entrenador"), rs.getInt("id_cliente"), rs.getDate("fecha_inicio"),
						rs.getDate("fecha_fin"), rs.getString("observaciones"));
				ps.close();
				return Optional.of(a);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void update(AsignacionEntrenador a) {
		String sql = "UPDATE asignacion_entrenador SET id_entrenador=?, id_cliente=?, fecha_inicio=?, fecha_fin=?, observaciones=? WHERE id_asignacion=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getIdEntrenador());
			ps.setInt(2, a.getIdCliente());
			ps.setDate(3, new Date(a.getFechaInicio().getTime()));
			ps.setDate(4, new Date(a.getFechaFin().getTime()));
			ps.setString(5, a.getObservaciones());
			ps.setInt(6, a.getIdAsignacion());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM asignacion_entrenador WHERE id_asignacion = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}