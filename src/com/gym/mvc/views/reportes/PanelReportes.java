package com.gym.mvc.views.reportes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PanelReportes extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO_OSCURO = new Color(18, 18, 18);
	private static final Color FONDO_PANEL = new Color(28, 28, 28);
	private static final Color DORADO = new Color(212, 175, 55);
	private static final Color TEXTO_GRIS = new Color(160, 160, 160);
	private static final Color SEPARADOR = new Color(55, 55, 55);

	private DefaultTableModel modeloPagosPorCliente;
	private DefaultTableModel modeloAsignacionesPorEntrenador;
	private DefaultTableModel modeloEquiposPorCategoria;
	private DefaultTableModel modeloClientesSinPagos;
	private DefaultTableModel modeloPesoPorCategoria;

	private JButton btnActualizar;

	public PanelReportes() {
		setLayout(new BorderLayout());
		setBackground(FONDO_OSCURO);
		setBorder(new EmptyBorder(20, 30, 20, 30));

		JLabel lblTitulo = new JLabel("Reportes y Consultas Avanzadas");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitulo.setForeground(DORADO);
		lblTitulo.setBorder(new EmptyBorder(0, 0, 16, 0));

		JPanel cabecera = new JPanel(new BorderLayout());
		cabecera.setOpaque(false);

		btnActualizar = new JButton("Actualizar Reportes");
		btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnActualizar.setBackground(FONDO_PANEL);
		btnActualizar.setForeground(DORADO);
		btnActualizar.setFocusPainted(false);
		btnActualizar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(8, 16, 8, 16)));
		btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		cabecera.add(lblTitulo, BorderLayout.WEST);
		cabecera.add(btnActualizar, BorderLayout.EAST);

		add(cabecera, BorderLayout.NORTH);

		JPanel grid = new JPanel(new GridLayout(0, 2, 16, 16));
		grid.setOpaque(false);

		modeloPagosPorCliente = crearModelo(new String[] { "Nombre", "Apellido", "Total Pagado" });
		grid.add(crearBloque("Total Pagado por Cliente", modeloPagosPorCliente));

		modeloAsignacionesPorEntrenador = crearModelo(new String[] { "Nombre", "Apellido", "Asignaciones" });
		grid.add(crearBloque("Entrenadores con Mas Asignaciones", modeloAsignacionesPorEntrenador));

		modeloEquiposPorCategoria = crearModelo(new String[] { "Categoria", "Estado", "Cantidad" });
		grid.add(crearBloque("Equipos por Categoria y Estado", modeloEquiposPorCategoria));

		modeloClientesSinPagos = crearModelo(new String[] { "Nombre", "Apellido", "Email" });
		grid.add(crearBloque("Clientes sin Pagos en los Ultimos 30 Dias", modeloClientesSinPagos));

		modeloPesoPorCategoria = crearModelo(new String[] { "Categoria", "Peso Promedio (kg)", "Equipos" });
		grid.add(crearBloque("Promedio de Peso Registrado por Categoria", modeloPesoPorCategoria));

		JScrollPane scrollGeneral = new JScrollPane(grid);
		scrollGeneral.setBorder(null);
		scrollGeneral.getViewport().setBackground(FONDO_OSCURO);

		add(scrollGeneral, BorderLayout.CENTER);
	}

	private DefaultTableModel crearModelo(String[] columnas) {
		return new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
	}

	private JPanel crearBloque(String titulo, DefaultTableModel modelo) {
		JPanel bloque = new JPanel(new BorderLayout());
		bloque.setBackground(FONDO_PANEL);
		bloque.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(SEPARADOR, 1), new EmptyBorder(12, 12, 12, 12)));

		JLabel lbl = new JLabel(titulo);
		lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lbl.setForeground(DORADO);
		lbl.setBorder(new EmptyBorder(0, 0, 8, 0));

		JTable tabla = new JTable(modelo);
		tabla.setBackground(FONDO_PANEL);
		tabla.setForeground(java.awt.Color.WHITE);
		tabla.setGridColor(SEPARADOR);
		tabla.setRowHeight(22);

		JTableHeader th = tabla.getTableHeader();
		th.setBackground(FONDO_OSCURO);
		th.setForeground(DORADO);
		th.setFont(new Font("Segoe UI", Font.BOLD, 11));

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new java.awt.Dimension(0, 160));
		scroll.getViewport().setBackground(FONDO_PANEL);

		bloque.add(lbl, BorderLayout.NORTH);
		bloque.add(scroll, BorderLayout.CENTER);
		return bloque;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public DefaultTableModel getModeloPagosPorCliente() {
		return modeloPagosPorCliente;
	}

	public DefaultTableModel getModeloAsignacionesPorEntrenador() {
		return modeloAsignacionesPorEntrenador;
	}

	public DefaultTableModel getModeloEquiposPorCategoria() {
		return modeloEquiposPorCategoria;
	}

	public DefaultTableModel getModeloClientesSinPagos() {
		return modeloClientesSinPagos;
	}

	public DefaultTableModel getModeloPesoPorCategoria() {
		return modeloPesoPorCategoria;
	}
}