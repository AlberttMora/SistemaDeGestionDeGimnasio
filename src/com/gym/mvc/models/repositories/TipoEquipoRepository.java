package com.gym.mvc.models.repositories;
		
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.TipoEquipo;
import com.gym.mvc.models.db.ConexionDB;

public class TipoEquipoRepository implements IRepository<TipoEquipo> {
    private Connection conn;
    
    
    public TipoEquipoRepository() {
    	// TODO Auto-generated constructor stub
        conn = ConexionDB.getInstancia().getConexion();
    }
    @Override
    public void create(TipoEquipo te) {
        String sql = "INSERT INTO tipo_equipo (nombre) VALUES (?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, te.getNombre());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<TipoEquipo> readAll() {
        List<TipoEquipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_equipo";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new TipoEquipo(rs.getInt("id_tipo"), rs.getString("nombre")));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public Optional<TipoEquipo> findById(int id) {
        String sql = "SELECT * FROM tipo_equipo WHERE id_tipo = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TipoEquipo te = new TipoEquipo(rs.getInt("id_tipo"), rs.getString("nombre"));
                ps.close();
                return Optional.of(te);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public void update(TipoEquipo te) {
        String sql = "UPDATE tipo_equipo SET nombre=? WHERE id_tipo=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, te.getNombre());
            ps.setInt(2, te.getIdTipo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tipo_equipo WHERE id_tipo = ?";
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