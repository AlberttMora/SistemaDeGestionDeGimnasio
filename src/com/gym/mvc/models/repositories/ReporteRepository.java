package com.gym.mvc.models.repositories;

import com.gym.mvc.models.db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteRepository {

	private Connection conn;

	public ReporteRepository() {
		conn = ConexionDB.getInstancia().getConexion();
	}

	public List<Object[]> totalPagadoPorCliente() {
		String sql = "SELECT c.nombre, c.apellido, SUM(p.monto) AS total " + "FROM pago p "
				+ "JOIN inscripcion i ON p.id_inscripcion = i.id_inscripcion "
				+ "JOIN cliente c ON i.id_cliente = c.id_cliente " + "GROUP BY c.id_cliente, c.nombre, c.apellido "
				+ "ORDER BY total DESC";
		return ejecutarConsulta(sql, 3);
	}

	public List<Object[]> entrenadorConMasAsignaciones() {
		String sql = "SELECT per.nombre, per.apellido, COUNT(*) AS total " + "FROM asignacion_entrenador a "
				+ "JOIN personal per ON a.id_entrenador = per.id_personal "
				+ "GROUP BY a.id_entrenador, per.nombre, per.apellido " + "ORDER BY total DESC";
		return ejecutarConsulta(sql, 3);
	}

	public List<Object[]> equiposPorCategoriaYEstado() {
		String sql = "SELECT t.nombre, e.estado, COUNT(*) AS total " + "FROM equipo e "
				+ "JOIN tipo_equipo t ON e.id_tipo = t.id_tipo " + "GROUP BY t.id_tipo, t.nombre, e.estado "
				+ "ORDER BY t.nombre, e.estado";
		return ejecutarConsulta(sql, 3);
	}

	public List<Object[]> clientesSinPagosRecientes() {
		String sql = "SELECT c.nombre, c.apellido, c.email " + "FROM cliente c " + "WHERE c.id_cliente NOT IN ( "
				+ "SELECT i.id_cliente FROM inscripcion i " + "JOIN pago p ON p.id_inscripcion = i.id_inscripcion "
				+ "WHERE p.fecha_pago >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) " + ") ORDER BY c.apellido";
		return ejecutarConsulta(sql, 3);
	}

	public List<Object[]> promedioPesoPorCategoria() {
		String sql = "SELECT t.nombre, AVG(d.peso_kg) AS promedio, COUNT(*) AS equipos " + "FROM detalle_peso d "
				+ "JOIN equipo e ON d.id_equipo = e.id_equipo " + "JOIN tipo_equipo t ON e.id_tipo = t.id_tipo "
				+ "GROUP BY t.id_tipo, t.nombre " + "ORDER BY promedio DESC";
		return ejecutarConsulta(sql, 3);
	}

	private List<Object[]> ejecutarConsulta(String sql, int columnas) {
		List<Object[]> resultado = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Object[] fila = new Object[columnas];
				for (int i = 0; i < columnas; i++) {
					fila[i] = rs.getObject(i + 1);
				}
				resultado.add(fila);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}