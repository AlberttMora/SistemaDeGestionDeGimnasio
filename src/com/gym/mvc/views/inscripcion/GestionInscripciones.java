package com.gym.mvc.views.inscripcion;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

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

public class GestionInscripciones extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO_OSCURO = new Color(18, 18, 18);
	private static final Color FONDO_PANEL = new Color(28, 28, 28);
	private static final Color DORADO = new Color(212, 175, 55);
	private static final Color TEXTO_GRIS = new Color(160, 160, 160);
	private static final Color SEPARADOR = new Color(55, 55, 55);

	private JComboBox<String> cmbCliente;
	private JComboBox<String> cmbMembresia;
	private JComboBox<String> cmbEstado;
	private JDateChooser dcFechaInicio;

	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnLimpiar;

	public GestionInscripciones() {

		setBackground(FONDO_OSCURO);
		setLayout(new BorderLayout(20, 20));
		setBorder(new EmptyBorder(30, 40, 30, 40));

		JLabel lblTitulo = new JLabel("GESTION DE INSCRIPCIONES");
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

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblCliente.setForeground(TEXTO_GRIS);
		panelForm.add(lblCliente);

		cmbCliente = new JComboBox<>();
		panelForm.add(cmbCliente);

		JLabel lblMembresia = new JLabel("Membresia:");
		lblMembresia.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblMembresia.setForeground(TEXTO_GRIS);
		panelForm.add(lblMembresia);

		cmbMembresia = new JComboBox<>();
		panelForm.add(cmbMembresia);

		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblFechaInicio.setForeground(TEXTO_GRIS);
		panelForm.add(lblFechaInicio);

		dcFechaInicio = new JDateChooser();
		dcFechaInicio.setDateFormatString("dd/MM/yyyy");
		panelForm.add(dcFechaInicio);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblEstado.setForeground(TEXTO_GRIS);
		panelForm.add(lblEstado);

		cmbEstado = new JComboBox<String>();
		cmbEstado.setModel(new DefaultComboBoxModel<String>(
				new String[] { "activa", "vencida", "cancelada" }));
		panelForm.add(cmbEstado);

		GridBagConstraints gbcForm = new GridBagConstraints();
		gbcForm.gridx = 0;
		gbcForm.gridy = 0;
		gbcForm.weightx = 1;
		gbcForm.weighty = 1;
		gbcForm.fill = GridBagConstraints.HORIZONTAL;

		contenedorCentral.add(panelForm, gbcForm);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		panelBotones.setOpaque(false);

		btnGuardar = crearBoton("Guardar Inscripcion");
		btnEliminar = crearBoton("Eliminar Inscripcion");
		btnLimpiar = crearBoton("Limpiar Campos");

		panelBotones.add(btnGuardar);
		panelBotones.add(btnEliminar);
		panelBotones.add(btnLimpiar);

		GridBagConstraints gbcBotones = new GridBagConstraints();
		gbcBotones.gridx = 0;
		gbcBotones.gridy = 1;
		gbcBotones.insets = new Insets(20, 0, 0, 0);

		contenedorCentral.add(panelBotones, gbcBotones);

		add(contenedorCentral, BorderLayout.CENTER);
	}

	private JButton crearBoton(String texto) {

		JButton btn = new JButton(texto);

		btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btn.setBackground(FONDO_PANEL);
		btn.setForeground(DORADO);
		btn.setFocusPainted(false);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btn.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(8, 16, 8, 16)));

		return btn;
	}

	public void limpiarFormulario() {

		cmbCliente.setSelectedIndex(-1);
		cmbMembresia.setSelectedIndex(-1);
		cmbEstado.setSelectedIndex(0);
		dcFechaInicio.setDate(null);
	}

	public JComboBox<String> getCmbCliente() {
		return cmbCliente;
	}

	public JComboBox<String> getCmbMembresia() {
		return cmbMembresia;
	}

	public JComboBox<String> getCmbEstado() {
		return cmbEstado;
	}

	public JDateChooser getDcFechaInicio() {
		return dcFechaInicio;
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
		JOptionPane.showMessageDialog(this, msg, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostrarAdvertencia(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Validacion", JOptionPane.WARNING_MESSAGE);
	}

	public void mostrarError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public boolean confirmarAccion(String msg) {
		return JOptionPane.showConfirmDialog(
				this,
				msg,
				"Confirmar",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}
}