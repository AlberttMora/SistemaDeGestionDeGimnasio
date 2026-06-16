package com.gym.mvc.models.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.*;
import com.gym.mvc.models.db.ConexionDB;

public class InscripcionRepository implements IRepository<Inscripcion> {
	private Connection conn;

	public InscripcionRepository() {
		// TODO Auto-generated constructor stub
		conn = ConexionDB.getInstancia().getConexion();
	}

	@Override
	public void create(Inscripcion i) {
		String sql = "INSERT INTO inscripcion (id_cliente, id_membresia, fecha_inicio, estado) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i.getIdCliente());
			ps.setInt(2, i.getIdMembresia());
			ps.setDate(3, new Date(i.getFechaInicio().getTime()));
			ps.setString(4, i.getEstado());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Inscripcion> readAll() {
		List<Inscripcion> lista = new ArrayList<>();
		String sql = "SELECT * FROM inscripcion";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(new Inscripcion(rs.getInt("id_inscripcion"), rs.getInt("id_cliente"),
						rs.getInt("id_membresia"), rs.getDate("fecha_inicio"), rs.getString("estado")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Optional<Inscripcion> findById(int id) {
		String sql = "SELECT * FROM inscripcion WHERE id_inscripcion = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Inscripcion i = new Inscripcion(rs.getInt("id_inscripcion"), rs.getInt("id_cliente"),
						rs.getInt("id_membresia"), rs.getDate("fecha_inicio"), rs.getString("estado"));
				ps.close();
				return Optional.of(i);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void update(Inscripcion i) {
		String sql = "UPDATE inscripcion SET id_cliente=?, id_membresia=?, fecha_inicio=?, estado=? WHERE id_inscripcion=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, i.getIdCliente());
			ps.setInt(2, i.getIdMembresia());
			ps.setDate(3, new Date(i.getFechaInicio().getTime()));
			ps.setString(4, i.getEstado());
			ps.setInt(5, i.getIdInscripcion());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM inscripcion WHERE id_inscripcion = ?";
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
