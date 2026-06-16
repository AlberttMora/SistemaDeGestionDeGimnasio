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

public class ClienteRepository implements IRepository<Cliente> {
	private Connection conn;

	public ClienteRepository() {
		conn = ConexionDB.getInstancia().getConexion();
	}

	@Override
	public void create(Cliente c) {
		String sql = "INSERT INTO cliente (nombre, apellido, email, telefono, fecha_nacimiento, foto_ruta) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getApellido());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getTelefono());
			ps.setDate(5, new Date(c.getFechaNac().getTime()));
			ps.setString(6, c.getFotoRuta());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cliente> readAll() {
		List<Cliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM cliente";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("email"), rs.getString("telefono"), rs.getDate("fecha_nacimiento"),
						rs.getString("foto_ruta"));
				lista.add(c);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Optional<Cliente> findById(int id) {
		String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Cliente c = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getString("email"), rs.getString("telefono"), rs.getDate("fecha_nacimiento"),
						rs.getString("foto_ruta"));
				ps.close();
				return Optional.of(c);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void update(Cliente c) {
		String sql = "UPDATE cliente SET nombre=?, apellido=?, email=?, telefono=?, fecha_nacimiento=?, foto_ruta=? WHERE id_cliente=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getNombre());
			ps.setString(2, c.getApellido());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getTelefono());
			ps.setDate(5, new Date(c.getFechaNac().getTime()));
			ps.setString(6, c.getFotoRuta());
			ps.setInt(7, c.getIdCliente());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM cliente WHERE id_cliente = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<Cliente> findByEmail(String email) {
        String sql = "SELECT * FROM cliente WHERE email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"), rs.getDate("fecha_nacimiento"), rs.getString("foto_ruta"));
                ps.close();
                return Optional.of(c);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return Optional.empty();
	}
}