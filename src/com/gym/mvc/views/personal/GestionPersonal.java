package com.gym.mvc.views.personal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class GestionPersonal extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO_OSCURO = new Color(18, 18, 18);
	private static final Color FONDO_PANEL = new Color(28, 28, 28);
	private static final Color DORADO = new Color(212, 175, 55);
	private static final Color TEXTO_GRIS = new Color(160, 160, 160);
	private static final Color SEPARADOR = new Color(55, 55, 55);

	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtPass;
	private JTextField txtEspecialidad;
	private JTextField txtCertificacion;
	private JTextField txtNivelAcceso;
	private JTextField txtTurno;
	private JTextField txtArea;
	private JComboBox cbRol;
	private JButton btnContratar;
	private JButton btnDarBaja;
	private JTabbedPane panelPestanas;
	private DefaultTableModel modelAdmins;
	private DefaultTableModel modelEntrenadores;
	private DefaultTableModel modelLimpieza;
	private JTable tAdmin;
	private JTable tEntr;
	private JTable tLimp;

	public GestionPersonal() {
		setBackground(FONDO_OSCURO);
		setLayout(new BorderLayout(20, 20));
		setBorder(new EmptyBorder(30, 40, 30, 40));

		JLabel lblTitulo = new JLabel("RECURSOS HUMANOS - PLANILLA DE OPERACIONES");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setForeground(DORADO);
		add(lblTitulo, BorderLayout.NORTH);

		JPanel contenedorCentral = new JPanel(new BorderLayout(20, 0));
		contenedorCentral.setOpaque(false);

		JPanel panelIzquierdo = new JPanel(new BorderLayout(0, 15));
		panelIzquierdo.setOpaque(false);
		panelIzquierdo.setPreferredSize(new Dimension(320, 0));

		JPanel formBase = new JPanel(new GridLayout(7, 2, 5, 10));
		formBase.setBackground(FONDO_PANEL);
		formBase.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR),
			new EmptyBorder(15, 15, 15, 15)));

		JLabel lblRol = new JLabel("Rol/Puesto:");
		lblRol.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblRol.setForeground(TEXTO_GRIS);
		formBase.add(lblRol);

		String[] roles = new String[] { "Administrador", "Entrenador", "Limpieza" };
		cbRol = new JComboBox(roles);
		cbRol.setBackground(FONDO_OSCURO);
		cbRol.setForeground(Color.WHITE);
		formBase.add(cbRol);

		JLabel lblId = new JLabel("ID Personal:");
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblId.setForeground(TEXTO_GRIS);
		formBase.add(lblId);

		txtId = new JTextField();
		txtId.setBackground(FONDO_OSCURO);
		txtId.setForeground(Color.WHITE);
		txtId.setCaretColor(DORADO);
		txtId.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		txtId.setEditable(false);
		formBase.add(txtId);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNombre.setForeground(TEXTO_GRIS);
		formBase.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBackground(FONDO_OSCURO);
		txtNombre.setForeground(Color.WHITE);
		txtNombre.setCaretColor(DORADO);
		txtNombre.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		formBase.add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblApellido.setForeground(TEXTO_GRIS);
		formBase.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBackground(FONDO_OSCURO);
		txtApellido.setForeground(Color.WHITE);
		txtApellido.setCaretColor(DORADO);
		txtApellido.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		formBase.add(txtApellido);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblEmail.setForeground(TEXTO_GRIS);
		formBase.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBackground(FONDO_OSCURO);
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setCaretColor(DORADO);
		txtEmail.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		formBase.add(txtEmail);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblTelefono.setForeground(TEXTO_GRIS);
		formBase.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBackground(FONDO_OSCURO);
		txtTelefono.setForeground(Color.WHITE);
		txtTelefono.setCaretColor(DORADO);
		txtTelefono.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		formBase.add(txtTelefono);

		JLabel lblPass = new JLabel("Contrasena:");
		lblPass.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblPass.setForeground(TEXTO_GRIS);
		formBase.add(lblPass);

		txtPass = new JTextField();
		txtPass.setBackground(FONDO_OSCURO);
		txtPass.setForeground(Color.WHITE);
		txtPass.setCaretColor(DORADO);
		txtPass.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		formBase.add(txtPass);

		JPanel formEspecifico = new JPanel(new CardLayout());
		formEspecifico.setBackground(FONDO_PANEL);
		formEspecifico.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR),
			new EmptyBorder(15, 15, 15, 15)));

		JPanel pAdmin = new JPanel(new GridLayout(1, 2, 5, 5));
		pAdmin.setOpaque(false);

		JLabel lblNivelAcceso = new JLabel("Nivel Acceso:");
		lblNivelAcceso.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNivelAcceso.setForeground(TEXTO_GRIS);
		pAdmin.add(lblNivelAcceso);

		txtNivelAcceso = new JTextField();
		txtNivelAcceso.setBackground(FONDO_OSCURO);
		txtNivelAcceso.setForeground(Color.WHITE);
		txtNivelAcceso.setCaretColor(DORADO);
		txtNivelAcceso.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		pAdmin.add(txtNivelAcceso);

		JPanel pEntrenador = new JPanel(new GridLayout(2, 2, 5, 5));
		pEntrenador.setOpaque(false);

		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblEspecialidad.setForeground(TEXTO_GRIS);
		pEntrenador.add(lblEspecialidad);

		txtEspecialidad = new JTextField();
		txtEspecialidad.setBackground(FONDO_OSCURO);
		txtEspecialidad.setForeground(Color.WHITE);
		txtEspecialidad.setCaretColor(DORADO);
		txtEspecialidad.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		pEntrenador.add(txtEspecialidad);

		JLabel lblCertificacion = new JLabel("Certificacion:");
		lblCertificacion.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblCertificacion.setForeground(TEXTO_GRIS);
		pEntrenador.add(lblCertificacion);

		txtCertificacion = new JTextField();
		txtCertificacion.setBackground(FONDO_OSCURO);
		txtCertificacion.setForeground(Color.WHITE);
		txtCertificacion.setCaretColor(DORADO);
		txtCertificacion.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		pEntrenador.add(txtCertificacion);

		JPanel pLimpieza = new JPanel(new GridLayout(2, 2, 5, 5));
		pLimpieza.setOpaque(false);

		JLabel lblTurno = new JLabel("Turno:");
		lblTurno.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblTurno.setForeground(TEXTO_GRIS);
		pLimpieza.add(lblTurno);

		txtTurno = new JTextField();
		txtTurno.setBackground(FONDO_OSCURO);
		txtTurno.setForeground(Color.WHITE);
		txtTurno.setCaretColor(DORADO);
		txtTurno.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		pLimpieza.add(txtTurno);

		JLabel lblArea = new JLabel("Area Asignada:");
		lblArea.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblArea.setForeground(TEXTO_GRIS);
		pLimpieza.add(lblArea);

		txtArea = new JTextField();
		txtArea.setBackground(FONDO_OSCURO);
		txtArea.setForeground(Color.WHITE);
		txtArea.setCaretColor(DORADO);
		txtArea.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 6, 4, 6)));
		pLimpieza.add(txtArea);

		formEspecifico.add(pAdmin, "Administrador");
		formEspecifico.add(pEntrenador, "Entrenador");
		formEspecifico.add(pLimpieza, "Limpieza");

		cbRol.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				CardLayout cl = (CardLayout) formEspecifico.getLayout();
				cl.show(formEspecifico, (String) cbRol.getSelectedItem());
			}
		});

		panelIzquierdo.add(formBase, BorderLayout.NORTH);
		panelIzquierdo.add(formEspecifico, BorderLayout.CENTER);
		contenedorCentral.add(panelIzquierdo, BorderLayout.WEST);

		panelPestanas = new JTabbedPane();
		panelPestanas.setBackground(FONDO_PANEL);
		panelPestanas.setForeground(DORADO);

		modelAdmins = new DefaultTableModel(new String[] { "ID", "Nombre", "Email", "Telefono", "Nivel Acceso" }, 0);
		tAdmin = new JTable(modelAdmins);
		tAdmin.setBackground(FONDO_PANEL);
		tAdmin.setForeground(Color.WHITE);
		tAdmin.getTableHeader().setBackground(FONDO_OSCURO);
		tAdmin.getTableHeader().setForeground(DORADO);
		tAdmin.setGridColor(SEPARADOR);
		tAdmin.setRowHeight(24);
		tAdmin.setSelectionBackground(DORADO);
		tAdmin.setSelectionForeground(FONDO_OSCURO);
		panelPestanas.addTab("Administradores", new JScrollPane(tAdmin));

		modelEntrenadores = new DefaultTableModel(
			new String[] { "ID", "Nombre", "Email", "Especialidad", "Certificacion" }, 0);
		tEntr = new JTable(modelEntrenadores);
		tEntr.setBackground(FONDO_PANEL);
		tEntr.setForeground(Color.WHITE);
		tEntr.getTableHeader().setBackground(FONDO_OSCURO);
		tEntr.getTableHeader().setForeground(DORADO);
		tEntr.setGridColor(SEPARADOR);
		tEntr.setRowHeight(24);
		tEntr.setSelectionBackground(DORADO);
		tEntr.setSelectionForeground(FONDO_OSCURO);
		panelPestanas.addTab("Entrenadores", new JScrollPane(tEntr));

		modelLimpieza = new DefaultTableModel(new String[] { "ID", "Nombre", "Telefono", "Turno", "Area Asignada" }, 0);
		tLimp = new JTable(modelLimpieza);
		tLimp.setBackground(FONDO_PANEL);
		tLimp.setForeground(Color.WHITE);
		tLimp.getTableHeader().setBackground(FONDO_OSCURO);
		tLimp.getTableHeader().setForeground(DORADO);
		tLimp.setGridColor(SEPARADOR);
		tLimp.setRowHeight(24);
		tLimp.setSelectionBackground(DORADO);
		tLimp.setSelectionForeground(FONDO_OSCURO);
		panelPestanas.addTab("Personal Limpieza", new JScrollPane(tLimp));

		contenedorCentral.add(panelPestanas, BorderLayout.CENTER);

		JPanel barraBajas = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
		barraBajas.setOpaque(false);

		btnContratar = new JButton("Dar de Alta Empleado");
		btnContratar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnContratar.setBackground(FONDO_PANEL);
		btnContratar.setForeground(DORADO);
		btnContratar.setFocusPainted(false);
		btnContratar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnContratar.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(8, 16, 8, 16)));
		barraBajas.add(btnContratar);

		btnDarBaja = new JButton("Dar de Baja Empleado (Eliminar)");
		btnDarBaja.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDarBaja.setBackground(FONDO_PANEL);
		btnDarBaja.setForeground(DORADO);
		btnDarBaja.setFocusPainted(false);
		btnDarBaja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDarBaja.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(8, 16, 8, 16)));
		barraBajas.add(btnDarBaja);

		add(barraBajas, BorderLayout.SOUTH);
		add(contenedorCentral, BorderLayout.CENTER);
	}

	public String getRolSeleccionado() {
		return (String) cbRol.getSelectedItem();
	}

	public int getPestanaSeleccionada() {
		return panelPestanas.getSelectedIndex();
	}

	public JTable getTablaAdmin() {
		return tAdmin;
	}

	public JTable getTablaEntrenador() {
		return tEntr;
	}

	public JTable getTablaLimpieza() {
		return tLimp;
	}

	public void poblarCampos(String nombre, String apellido, String email, String telefono) {
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtEmail.setText(email);
		txtTelefono.setText(telefono);
	}

	public void limpiarFormulario() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		txtPass.setText("");
		txtNivelAcceso.setText("");
		txtEspecialidad.setText("");
		txtCertificacion.setText("");
		txtTurno.setText("");
		txtArea.setText("");
	}

	public String obtenerNombre() {
		return txtNombre.getText();
	}

	public String obtenerApellido() {
		return txtApellido.getText();
	}

	public String obtenerEmail() {
		return txtEmail.getText();
	}

	public String obtenerTelefono() {
		return txtTelefono.getText();
	}

	public String obtenerContrasena() {
		return txtPass.getText();
	}

	public String obtenerNivelAcceso() {
		return txtNivelAcceso.getText();
	}

	public String obtenerEspecialidad() {
		return txtEspecialidad.getText();
	}

	public String obtenerCertificacion() {
		return txtCertificacion.getText();
	}

	public String obtenerTurno() {
		return txtTurno.getText();
	}

	public String obtenerArea() {
		return txtArea.getText();
	}

	public DefaultTableModel getModelAdmins() {
		return modelAdmins;
	}

	public DefaultTableModel getModelEntrenadores() {
		return modelEntrenadores;
	}

	public DefaultTableModel getModelLimpieza() {
		return modelLimpieza;
	}

	public void alContratar(ActionListener l) {
		btnContratar.addActionListener(l);
	}

	public void alDarBaja(ActionListener l) {
		btnDarBaja.addActionListener(l);
	}
}