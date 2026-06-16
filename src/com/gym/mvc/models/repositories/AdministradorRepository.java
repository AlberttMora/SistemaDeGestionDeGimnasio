package com.gym.mvc.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.Administrador;
import com.gym.mvc.models.db.ConexionDB;

public class AdministradorRepository implements IRepository<Administrador> {
    private Connection conn;

    public AdministradorRepository() {
        conn = ConexionDB.getInstancia().getConexion();
    }

    @Override
    public void create(Administrador a) {
        String sqlPersonal       = "INSERT INTO personal (nombre, apellido, email, telefono, contrasena) VALUES (?, ?, ?, ?, ?)";
        String sqlAdministrador  = "INSERT INTO administrador (id_personal, nivel_acceso) VALUES (?, ?)";
        try {
            PreparedStatement ps1 = conn.prepareStatement(sqlPersonal, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, a.getNombre());
            ps1.setString(2, a.getApellido());
            ps1.setString(3, a.getEmail());
            ps1.setString(4, a.getTelefono());
            ps1.setString(5, a.getContrasena());
            ps1.executeUpdate();

            ResultSet keys = ps1.getGeneratedKeys();
            if (keys.next()) {
                int idGenerado = keys.getInt(1);
                PreparedStatement ps2 = conn.prepareStatement(sqlAdministrador);
                ps2.setInt(1, idGenerado);
                ps2.setString(2, a.getNivelAcceso());
                ps2.executeUpdate();
                ps2.close();
            }
            ps1.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Administrador> readAll() {
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT p.id_personal, p.nombre, p.apellido, p.email, p.telefono, p.contrasena, " +
                     "a.nivel_acceso " +
                     "FROM personal p JOIN administrador a ON a.id_personal = p.id_personal";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Administrador(
                    rs.getInt("id_personal"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("contrasena"),
                    rs.getString("nivel_acceso")
                ));
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Optional<Administrador> findById(int id) {
        String sql = "SELECT p.id_personal, p.nombre, p.apellido, p.email, p.telefono, p.contrasena, " +
                     "a.nivel_acceso " +
                     "FROM personal p JOIN administrador a ON a.id_personal = p.id_personal " +
                     "WHERE p.id_personal = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Administrador adm = new Administrador(
                    rs.getInt("id_personal"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getString("contrasena"),
                    rs.getString("nivel_acceso")
                );
                ps.close();
                return Optional.of(adm);
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Administrador a) {
        String sqlPersonal      = "UPDATE personal SET nombre=?, apellido=?, email=?, telefono=?, contrasena=? WHERE id_personal=?";
        String sqlAdministrador = "UPDATE administrador SET nivel_acceso=? WHERE id_personal=?";
        try {
            PreparedStatement ps1 = conn.prepareStatement(sqlPersonal);
            ps1.setString(1, a.getNombre());
            ps1.setString(2, a.getApellido());
            ps1.setString(3, a.getEmail());
            ps1.setString(4, a.getTelefono());
            ps1.setString(5, a.getContrasena());
            ps1.setInt(6, a.getIdPersonal());
            ps1.executeUpdate();
            ps1.close();

            PreparedStatement ps2 = conn.prepareStatement(sqlAdministrador);
            ps2.setString(1, a.getNivelAcceso());
            ps2.setInt(2, a.getIdPersonal());
            ps2.executeUpdate();
            ps2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sqlAdministrador = "DELETE FROM administrador WHERE id_personal = ?";
        String sqlPersonal      = "DELETE FROM personal WHERE id_personal = ?";
        try {
            PreparedStatement ps1 = conn.prepareStatement(sqlAdministrador);
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
}
//creo que todo esta bien jiji

