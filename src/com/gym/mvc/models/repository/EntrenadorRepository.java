package com.gym.mvc.models.repository;

		
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.Entrenador;
import com.gym.mvc.models.db.ConexionDB;


public class EntrenadorRepository implements IRepository<Entrenador> {
    private Connection conn;
    
    
    public EntrenadorRepository() {
    	// TODO Auto-generated constructor stub
        conn = ConexionDB.getInstancia().getConexion();
    }
    
    
    @Override
    public void create(Entrenador e) {
        String sql = "INSERT INTO personal (nombre, apellido, email, telefono, tipoRol, contrasena, especialidad, certificacion) VALUES (?, ?, ?, ?, 'Entrenador', ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getTelefono());
            ps.setString(5, e.getContrasena());
            ps.setString(6, e.getEspecialidad());
            ps.setString(7, e.getCertificacion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public List<Entrenador> readAll() {
        List<Entrenador> lista = new ArrayList<>();
        String sql = "SELECT * FROM personal WHERE tipoRol = 'Entrenador'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Entrenador(rs.getInt("idPersonal"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"), rs.getString("tipoRol"), rs.getString("contrasena"), rs.getString("especialidad"), rs.getString("certificacion")));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    @Override
    public Optional<Entrenador> findById(int id) {
        String sql = "SELECT * FROM personal WHERE idPersonal = ? AND tipoRol = 'Entrenador'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Entrenador e = new Entrenador(rs.getInt("idPersonal"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"), rs.getString("tipoRol"), rs.getString("contrasena"), rs.getString("especialidad"), rs.getString("certificacion"));
                ps.close();
                return Optional.of(e);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public void update(Entrenador e) {
        String sql = "UPDATE personal SET nombre=?, apellido=?, email=?, telefono=?, contrasena=?, especialidad=?, certificacion=? WHERE idPersonal=? AND tipoRol='Entrenador'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getTelefono());
            ps.setString(5, e.getContrasena());
            ps.setString(6, e.getEspecialidad());
            ps.setString(7, e.getCertificacion());
            ps.setInt(8, e.getIdPersonal());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM personal WHERE idPersonal = ? AND tipoRol = 'Entrenador'";
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
	

