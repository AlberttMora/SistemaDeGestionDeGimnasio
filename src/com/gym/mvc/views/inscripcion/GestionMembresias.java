package com.gym.mvc.views.inscripcion;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class GestionMembresias extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO_OSCURO = new Color(18, 18, 18);
	private static final Color FONDO_PANEL = new Color(28, 28, 28);
	private static final Color DORADO = new Color(212, 175, 55);
	private static final Color TEXTO_GRIS = new Color(160, 160, 160);
	private static final Color SEPARADOR = new Color(55, 55, 55);

	private JTextField txtId;
	private JTextField txtTipo;
	private JTextField txtPrecio;
	private JTextField txtDuracion;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JTable tablaMembresias;
	private DefaultTableModel modeloTabla;

	public GestionMembresias() {
		setBackground(FONDO_OSCURO);
		setLayout(new BorderLayout(20, 20));
		setBorder(new EmptyBorder(30, 40, 30, 40));

		JLabel lblTitulo = new JLabel("GESTION DE MEMBRESIAS");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setForeground(DORADO);
		add(lblTitulo, BorderLayout.NORTH);

		JPanel contenedorCentral = new JPanel(new GridBagLayout());
		contenedorCentral.setOpaque(false);

		JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 15));
		panelForm.setBackground(FONDO_PANEL);
		panelForm.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR),
			new EmptyBorder(20, 20, 20, 20)));

		JLabel lblId = new JLabel("ID Membresia:");
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblId.setForeground(TEXTO_GRIS);
		panelForm.add(lblId);

		txtId = new JTextField();
		txtId.setBackground(FONDO_OSCURO);
		txtId.setForeground(Color.WHITE);
		txtId.setCaretColor(DORADO);
		txtId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtId.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(5, 8, 5, 8)));
		txtId.setEditable(false);
		txtId.setToolTipText("Autoincrementable en Base de Datos");
		panelForm.add(txtId);

		JLabel lblTipo = new JLabel("Tipo/Nombre:");
		lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTipo.setForeground(TEXTO_GRIS);
		panelForm.add(lblTipo);

		txtTipo = new JTextField();
		txtTipo.setBackground(FONDO_OSCURO);
		txtTipo.setForeground(Color.WHITE);
		txtTipo.setCaretColor(DORADO);
		txtTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtTipo.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(5, 8, 5, 8)));
		panelForm.add(txtTipo);

		JLabel lblPrecio = new JLabel("Precio ($):");
		lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPrecio.setForeground(TEXTO_GRIS);
		panelForm.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setBackground(FONDO_OSCURO);
		txtPrecio.setForeground(Color.WHITE);
		txtPrecio.setCaretColor(DORADO);
		txtPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPrecio.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(5, 8, 5, 8)));
		panelForm.add(txtPrecio);

		JLabel lblDuracion = new JLabel("Duracion (Dias):");
		lblDuracion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDuracion.setForeground(TEXTO_GRIS);
		panelForm.add(lblDuracion);

		txtDuracion = new JTextField();
		txtDuracion.setBackground(FONDO_OSCURO);
		txtDuracion.setForeground(Color.WHITE);
		txtDuracion.setCaretColor(DORADO);
		txtDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtDuracion.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(5, 8, 5, 8)));
		panelForm.add(txtDuracion);

		GridBagConstraints gbcForm = new GridBagConstraints();
		gbcForm.gridx = 0;
		gbcForm.gridy = 0;
		gbcForm.weightx = 0.35;
		gbcForm.weighty = 0.8;
		gbcForm.fill = GridBagConstraints.BOTH;
		contenedorCentral.add(panelForm, gbcForm);

		String[] columnas = { "ID", "Tipo Plan", "Precio", "Duracion (Dias)" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tablaMembresias = new JTable(modeloTabla);
		tablaMembresias.setBackground(FONDO_PANEL);
		tablaMembresias.setForeground(Color.WHITE);
		tablaMembresias.getTableHeader().setBackground(FONDO_OSCURO);
		tablaMembresias.getTableHeader().setForeground(DORADO);
		tablaMembresias.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablaMembresias.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tablaMembresias.setGridColor(SEPARADOR);
		tablaMembresias.setRowHeight(24);
		tablaMembresias.setSelectionBackground(DORADO);
		tablaMembresias.setSelectionForeground(FONDO_OSCURO);

		JScrollPane scroll = new JScrollPane(tablaMembresias);
		scroll.getViewport().setBackground(FONDO_PANEL);
		scroll.setBorder(BorderFactory.createLineBorder(SEPARADOR));

		GridBagConstraints gbcScroll = new GridBagConstraints();
		gbcScroll.gridx = 1;
		gbcScroll.gridy = 0;
		gbcScroll.weightx = 0.65;
		gbcScroll.weighty = 0.8;
		gbcScroll.fill = GridBagConstraints.BOTH;
		gbcScroll.insets = new Insets(0, 20, 0, 0);
		contenedorCentral.add(scroll, gbcScroll);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		panelBotones.setOpaque(false);

		btnGuardar = new JButton("Guardar Plan");
		btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnGuardar.setBackground(FONDO_PANEL);
		btnGuardar.setForeground(DORADO);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(8, 16, 8, 16)));
		panelBotones.add(btnGuardar);

		btnEliminar = new JButton("Eliminar Plan");
		btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEliminar.setBackground(FONDO_PANEL);
		btnEliminar.setForeground(DORADO);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(8, 16, 8, 16)));
		panelBotones.add(btnEliminar);

		btnLimpiar = new JButton("Limpiar Campos");
		btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLimpiar.setBackground(FONDO_PANEL);
		btnLimpiar.setForeground(DORADO);
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(8, 16, 8, 16)));
		panelBotones.add(btnLimpiar);

		GridBagConstraints gbcBotones = new GridBagConstraints();
		gbcBotones.gridx = 0;
		gbcBotones.gridy = 1;
		gbcBotones.gridwidth = 2;
		gbcBotones.weighty = 0.2;
		gbcBotones.fill = GridBagConstraints.HORIZONTAL;
		gbcBotones.insets = new Insets(20, 0, 0, 0);
		contenedorCentral.add(panelBotones, gbcBotones);

		add(contenedorCentral, BorderLayout.CENTER);
	}

	public String getTipo() {
		return txtTipo.getText();
	}

	public String getPrecio() {
		return txtPrecio.getText();
	}

	public String getDuracion() {
		return txtDuracion.getText();
	}

	public String getIdSeleccionado() {
		return txtId.getText();
	}

	public void limpiarFormulario() {
		txtId.setText("");
		txtTipo.setText("");
		txtPrecio.setText("");
		txtDuracion.setText("");
	}

	public void cargarDatosPlan(String id, String tipo, String precio, String duracion) {
		txtId.setText(id);
		txtTipo.setText(tipo);
		txtPrecio.setText(precio);
		txtDuracion.setText(duracion);
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public JTable getTabla() {
		return tablaMembresias;
	}

	public void alGuardar(ActionListener l) {
		btnGuardar.addActionListener(l);
	}

	public void alEliminar(ActionListener l) {
		btnEliminar.addActionListener(l);
	}

	public void alLimpiar(ActionListener l) {
		btnLimpiar.addActionListener(l);
	}

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarAdvertencia(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Validación", JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmarAccion(String msg) {
        return JOptionPane.showConfirmDialog(this, msg, "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}