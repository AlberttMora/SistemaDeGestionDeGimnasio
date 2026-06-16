package com.gym.mvc.models.repositories;

	
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gym.mvc.models.*;
import com.gym.mvc.models.db.ConexionDB;


public class PagoRepository implements IRepository<Pago> {
    private Connection conn;
    
    public PagoRepository() {
		// TODO Auto-generated constructor stub
        conn = ConexionDB.getInstancia().getConexion();
    }
    
 // PagoRepository
    @Override
    public void create(Pago p) {
        String sql = "INSERT INTO pago (id_inscripcion, monto, fecha_pago, metodo_pago) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getIdInscripcion());
            ps.setDouble(2, p.getMonto());
            ps.setDate(3, new Date(p.getFechaPago().getTime()));
            ps.setString(4, p.getMetodoPago());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pago> readAll() {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Pago(rs.getInt("id_pago"), rs.getInt("id_inscripcion"), rs.getDouble("monto"), rs.getDate("fecha_pago"), rs.getString("metodo_pago")));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Optional<Pago> findById(int id) {
        String sql = "SELECT * FROM pago WHERE id_pago = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pago p = new Pago(rs.getInt("id_pago"), rs.getInt("id_inscripcion"), rs.getDouble("monto"), rs.getDate("fecha_pago"), rs.getString("metodo_pago"));
                ps.close();
                return Optional.of(p);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Pago p) {
        String sql = "UPDATE pago SET id_inscripcion=?, monto=?, fecha_pago=?, metodo_pago=? WHERE id_pago=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getIdInscripcion());
            ps.setDouble(2, p.getMonto());
            ps.setDate(3, new Date(p.getFechaPago().getTime()));
            ps.setString(4, p.getMetodoPago());
            ps.setInt(5, p.getIdPago());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM pago WHERE id_pago = ?";
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
