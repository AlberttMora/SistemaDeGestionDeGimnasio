package com.gym.mvc.models.repositories;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.Equipo;
import com.gym.mvc.models.db.ConexionDB;

public class EquipoRepository implements IRepository<Equipo> {
	private Connection conn;

	public EquipoRepository() {
		// TODO Auto-generated constructor stub
		conn = ConexionDB.getInstancia().getConexion();
	}

	// EquipoRepository
	@Override
	public void create(Equipo e) {
		String sql = "INSERT INTO equipo (id_tipo, nombre, estado, foto_ruta, fecha_adquisicion) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getIdTipo());
			ps.setString(2, e.getNombre());
			ps.setString(3, e.getEstado());
			ps.setString(4, e.getFotoRuta());
			ps.setDate(5, new java.sql.Date(e.getFechaAdquisicion().getTime()));
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public List<Equipo> readAll() {
		List<Equipo> lista = new ArrayList<>();
		String sql = "SELECT * FROM equipo";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Equipo e = new Equipo(rs.getInt("id_equipo"), rs.getInt("id_tipo"), rs.getString("nombre"),
						rs.getString("estado"), rs.getString("foto_ruta"), rs.getDate("fecha_adquisicion"));
				lista.add(e);
			}
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return lista;
	}

	@Override
	public Optional<Equipo> findById(int id) {
		String sql = "SELECT * FROM equipo WHERE id_equipo = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Equipo e = new Equipo(rs.getInt("id_equipo"), rs.getInt("id_tipo"), rs.getString("nombre"),
						rs.getString("estado"), rs.getString("foto_ruta"), rs.getDate("fecha_adquisicion"));
				ps.close();
				return Optional.of(e);
			}
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void update(Equipo e) {
		String sql = "UPDATE equipo SET id_tipo=?, nombre=?, estado=?, foto_ruta=?, fecha_adquisicion=? WHERE id_equipo=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getIdTipo());
			ps.setString(2, e.getNombre());
			ps.setString(3, e.getEstado());
			ps.setString(4, e.getFotoRuta());
			ps.setDate(5, new java.sql.Date(e.getFechaAdquisicion().getTime()));
			ps.setInt(6, e.getIdEquipo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM equipo WHERE id_equipo = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
