package com.gym.mvc.models.repository;

		
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.Limpieza;
import com.gym.mvc.models.db.ConexionDB;


public class LimpiezaRepository implements IRepository<Limpieza> {
    private Connection conn;
    
    
    public LimpiezaRepository() {
    	// TODO Auto-generated constructor stub
        conn = ConexionDB.getInstancia().getConexion();
    }
    
    @Override
    public void create(Limpieza l) {
        String sql = "INSERT INTO personal (nombre, apellido, email, telefono, tipoRol, contrasena, turno, areaAsignada) VALUES (?, ?, ?, ?, 'Limpieza', ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, l.getNombre());
            ps.setString(2, l.getApellido());
            ps.setString(3, l.getEmail());
            ps.setString(4, l.getTelefono());
            ps.setString(5, l.getContrasena());
            ps.setString(6, l.getTurno());
            ps.setString(7, l.getAreaAsignada());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public List<Limpieza> readAll() {
        List<Limpieza> lista = new ArrayList<>();
        String sql = "SELECT * FROM personal WHERE tipoRol = 'Limpieza'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Limpieza(rs.getInt("idPersonal"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"), rs.getString("tipoRol"), rs.getString("contrasena"), rs.getString("turno"), rs.getString("areaAsignada")));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    
    @Override
    public Optional<Limpieza> findById(int id) {
        String sql = "SELECT * FROM personal WHERE idPersonal = ? AND tipoRol = 'Limpieza'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Limpieza l = new Limpieza(rs.getInt("idPersonal"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"), rs.getString("tipoRol"), rs.getString("contrasena"), rs.getString("turno"), rs.getString("areaAsignada"));
                ps.close();
                return Optional.of(l);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
    
    @Override
    public void update(Limpieza l) {
        String sql = "UPDATE personal SET nombre=?, apellido=?, email=?, telefono=?, contrasena=?, turno=?, areaAsignada=? WHERE idPersonal=? AND tipoRol='Limpieza'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, l.getNombre());
            ps.setString(2, l.getApellido());
            ps.setString(3, l.getEmail());
            ps.setString(4, l.getTelefono());
            ps.setString(5, l.getContrasena());
            ps.setString(6, l.getTurno());
            ps.setString(7, l.getAreaAsignada());
            ps.setInt(8, l.getIdPersonal());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM personal WHERE idPersonal = ? AND tipoRol = 'Limpieza'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
	