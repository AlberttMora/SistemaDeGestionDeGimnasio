package com.gym.mvc.models.repositories;

		
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.Membresia;
import com.gym.mvc.models.db.ConexionDB;



public class MembresiaRepository implements IRepository<Membresia> {
    private Connection conn;
    
    
    public MembresiaRepository() {
    	// TODO Auto-generated constructor stub
        conn = ConexionDB.getInstancia().getConexion();
    }
    
    
    @Override
    public void create(Membresia m) {
        String sql = "INSERT INTO membresia (tipo, precio, duracionDias) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getTipo());
            ps.setDouble(2, m.getPrecio());
            ps.setInt(3, m.getDuracion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public List<Membresia> readAll() {
        List<Membresia> lista = new ArrayList<>();
        String sql = "SELECT * FROM membresia";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Membresia m = new Membresia(rs.getInt("idMembresia"), rs.getString("tipo"), rs.getDouble("precio"), rs.getInt("duracionDias"));
                lista.add(m);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
    @Override
    public Optional<Membresia> findById(int id) {
        String sql = "SELECT * FROM membresia WHERE idMembresia = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Membresia m = new Membresia(rs.getInt("idMembresia"), rs.getString("tipo"), rs.getDouble("precio"), rs.getInt("duracionDias"));
                ps.close();
                return Optional.of(m);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    @Override
    public void update(Membresia m) {
        String sql = "UPDATE membresia SET tipo=?, precio=?, duracionDias=? WHERE idMembresia=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getTipo());
            ps.setDouble(2, m.getPrecio());
            ps.setInt(3, m.getDuracion());
            ps.setInt(4, m.getIdMembresia());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM membresia WHERE idMembresia = ?";
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