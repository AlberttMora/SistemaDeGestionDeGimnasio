package com.gym.mvc.models.repositories;
		

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.BitacoraMantenimiento;
import com.gym.mvc.models.db.ConexionDB;


public class BitacoraRepository implements IRepository<BitacoraMantenimiento> {
    private Connection conn;
    
    
    public BitacoraRepository() {
    	// TODO Auto-generated constructor stub
        conn = ConexionDB.getInstancia().getConexion();
    }
    
    
    @Override
    public void create(BitacoraMantenimiento b) {
        String sql = "INSERT INTO bitacora_mantenimiento (idEquipo, idPersonal, fecha, descripcion) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, b.getIdEquipo());
            ps.setInt(2, b.getIdPersonal());
            ps.setDate(3, new java.sql.Date(b.getFecha().getTime()));
            ps.setString(4, b.getDescripcion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<BitacoraMantenimiento> readAll() {
        List<BitacoraMantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM bitacora_mantenimiento";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new BitacoraMantenimiento(rs.getInt("idBitacora"), rs.getInt("idEquipo"), rs.getInt("idPersonal"), rs.getDate("fecha"), rs.getString("descripcion")));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
    @Override
    public Optional<BitacoraMantenimiento> findById(int id) {
        String sql = "SELECT * FROM bitacora_mantenimiento WHERE idBitacora = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BitacoraMantenimiento b = new BitacoraMantenimiento(rs.getInt("idBitacora"), rs.getInt("idEquipo"), rs.getInt("idPersonal"), rs.getDate("fecha"), rs.getString("descripcion"));
                ps.close();
                return Optional.of(b);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    
    @Override
    public void update(BitacoraMantenimiento b) {
        String sql = "UPDATE bitacora_mantenimiento SET idEquipo=?, idPersonal=?, fecha=?, descripcion=? WHERE idBitacora=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, b.getIdEquipo());
            ps.setInt(2, b.getIdPersonal());
            ps.setDate(3, new java.sql.Date(b.getFecha().getTime()));
            ps.setString(4, b.getDescripcion());
            ps.setInt(5, b.getIdBitacora());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM bitacora_mantenimiento WHERE idBitacora = ?";
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
