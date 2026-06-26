package com.gym.mvc.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.Limpieza;
import com.gym.mvc.models.db.ConexionDB;

public class LimpiezaRepository implements IRepository<Limpieza> {
    private Connection conn;

    public LimpiezaRepository() {
        conn = ConexionDB.getInstancia().getConexion();
    }

    @Override
    public void create(Limpieza l) {
        String sqlPersonal  = "INSERT INTO personal (nombre, apellido, email, telefono, contrasena) VALUES (?, ?, ?, ?, ?)";
        String sqlLimpieza  = "INSERT INTO limpieza (id_personal, turno, area_asignada) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps1 = conn.prepareStatement(sqlPersonal, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, l.getNombre());
            ps1.setString(2, l.getApellido());
            ps1.setString(3, l.getEmail());
            ps1.setString(4, l.getTelefono());
            ps1.setString(5, l.getContrasena());
            ps1.executeUpdate();

            ResultSet keys = ps1.getGeneratedKeys();
            if (keys.next()) {
                int idGenerado = keys.getInt(1);
                PreparedStatement ps2 = conn.prepareStatement(sqlLimpieza);
                ps2.setInt(1, idGenerado);
                ps2.setString(2, l.getTurno());
                ps2.setString(3, l.getAreaAsignada());
                ps2.executeUpdate();
                ps2.close();
            }
            ps1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Limpieza> readAll() {
        List<Limpieza> lista = new ArrayList<>();
        String sql = "SELECT p.id_personal, p.nombre, p.apellido, p.email, p.telefono, p.contrasena, " +
                     "l.turno, l.area_asignada " +
                     "FROM personal p JOIN limpieza l ON l.id_personal = p.id_personal";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Limpieza(
                    rs.getInt("id_personal"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("contrasena"),
                    rs.getString("turno"),
                    rs.getString("area_asignada")
                ));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Optional<Limpieza> findById(int id) {
        String sql = "SELECT p.id_personal, p.nombre, p.apellido, p.email, p.telefono, p.contrasena, " +
                     "l.turno, l.area_asignada " +
                     "FROM personal p JOIN limpieza l ON l.id_personal = p.id_personal " +
                     "WHERE p.id_personal = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Limpieza lim = new Limpieza(
                    rs.getInt("id_personal"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("contrasena"),
                    rs.getString("turno"),
                    rs.getString("area_asignada")
                );
                ps.close();
                return Optional.of(lim);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Limpieza l) {
        String sqlPersonal = "UPDATE personal SET nombre=?, apellido=?, email=?, telefono=?, contrasena=? WHERE id_personal=?";
        String sqlLimpieza = "UPDATE limpieza SET turno=?, area_asignada=? WHERE id_personal=?";
        try {
            PreparedStatement ps1 = conn.prepareStatement(sqlPersonal);
            ps1.setString(1, l.getNombre());
            ps1.setString(2, l.getApellido());
            ps1.setString(3, l.getEmail());
            ps1.setString(4, l.getTelefono());
            ps1.setString(5, l.getContrasena());
            ps1.setInt(6, l.getIdPersonal());
            ps1.executeUpdate();
            ps1.close();

            PreparedStatement ps2 = conn.prepareStatement(sqlLimpieza);
            ps2.setString(1, l.getTurno());
            ps2.setString(2, l.getAreaAsignada());
            ps2.setInt(3, l.getIdPersonal());
            ps2.executeUpdate();
            ps2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sqlLimpieza = "DELETE FROM limpieza WHERE id_personal = ?";
        String sqlPersonal = "DELETE FROM personal WHERE id_personal = ?";
        try {
            PreparedStatement ps1 = conn.prepareStatement(sqlLimpieza);
            ps1.setInt(1, id);
            ps1.executeUpdate();
            ps1.close();

            PreparedStatement ps2 = conn.prepareStatement(sqlPersonal);
            ps2.setInt(1, id);
            ps2.executeUpdate();
            ps2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void asignarPersonalALimpieza(int idPersonal, String turno, String area) {
        String sql = "INSERT INTO limpieza (id_personal, turno, area_asignada) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPersonal);
            ps.setString(2, turno);
            ps.setString(3, area);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}