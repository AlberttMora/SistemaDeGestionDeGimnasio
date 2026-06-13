package com.gym.mvc.models.repository;

		
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.Administrador;
import com.gym.mvc.models.db.ConexionDB;


public class AdministradorRepository implements IRepository<Administrador> {
    private Connection conn;
    
    
    public AdministradorRepository() {
    	// TODO Auto-generated constructor stub
        conn = ConexionDB.getInstancia().getConexion();
    }
    
    
    @Override
    public void create(Administrador a) {
        String sql = "INSERT INTO personal (nombre, apellido, email, telefono, tipoRol, contrasena, nivelAcceso) VALUES (?, ?, ?, ?, 'Administrador', ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getTelefono());
            ps.setString(5, a.getContrasena());
            ps.setString(6, a.getNivelAcceso());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public List<Administrador> readAll() {
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT * FROM personal WHERE tipoRol= 'Administrador'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Administrador(rs.getInt("idPersonal"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"), rs.getString("tipoRol"), rs.getString("contrasena"), rs.getString("nivelAcceso")));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    
    @Override
    public Optional<Administrador> findById(int id) {
        String sql = "SELECT * FROM personal WHERE idPersonal = ? AND tipoRol = 'Administrador'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Administrador a = new Administrador(rs.getInt("idPersonal"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"), rs.getString("tipoRol"), rs.getString("contrasena"), rs.getString("nivelAcceso"));
                ps.close();
                return Optional.of(a);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public void update(Administrador a) {
        String sql = "UPDATE personal SET nombre=?, apellido=?, email=?, telefono=?, contrasena=?, nivelAcceso=? WHERE idPersonal=? AND tipoRol='Administrador'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getTelefono());
            ps.setString(5, a.getContrasena());
            ps.setString(6, a.getNivelAcceso());
            ps.setInt(7, a.getIdPersonal());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM personal WHERE idPersonal = ? AND tipoRol = 'Administrador'";
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
//creo que todo esta bien jiji

