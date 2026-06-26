package com.gym.mvc.views.inscripcion;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.toedter.calendar.JDateChooser;

public class RegistroPagos extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO_OSCURO = new Color(18, 18, 18);
	private static final Color FONDO_PANEL = new Color(28, 28, 28);
	private static final Color FONDO_CAMPO = new Color(38, 38, 38);
	private static final Color DORADO = new Color(212, 175, 55);
	private static final Color TEXTO_BLANCO = new Color(240, 240, 240);
	private static final Color TEXTO_GRIS = new Color(160, 160, 160);
	private static final Color SEPARADOR = new Color(55, 55, 55);
	private static final Color VERDE = new Color(60, 160, 80);

	private JTextField txtIdInscripcion;
	private JTextField txtNombreCliente;
	private JTextField txtMonto;
	private JDateChooser jdFechaPago;
	private JComboBox cmbMetodoPago;
	private JComboBox cmbEstadoInscripcion;
	private JLabel lblResumen;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JButton btnRegistrar;

	public RegistroPagos() {
		setLayout(new BorderLayout());

		JPanel header = new JPanel(new GridLayout(2, 1, 0, 4));
		header.setBackground(new Color(28, 28, 28));
		header.setBorder(new EmptyBorder(20, 32, 16, 32));

		JLabel lblHeaderTitulo = new JLabel("REGISTRO DE PAGOS");
		lblHeaderTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblHeaderTitulo.setForeground(DORADO);

		JLabel lblHeaderSub = new JLabel("Registrar pagos asociados a inscripciones de membresia");
		lblHeaderSub.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		lblHeaderSub.setForeground(TEXTO_GRIS);

		header.add(lblHeaderTitulo);
		header.add(lblHeaderSub);
		add(header, BorderLayout.NORTH);

		JPanel contenido = new JPanel(new BorderLayout());
		contenido.setBackground(FONDO_OSCURO);
		contenido.setBorder(new EmptyBorder(20, 32, 16, 32));

		JPanel panelInscripcion = new JPanel();
		panelInscripcion.setBackground(FONDO_PANEL);
		panelInscripcion.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder(BorderFactory.createLineBorder(SEPARADOR), "1.  Identificar Inscripcion",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), DORADO),
			new EmptyBorder(10, 12, 12, 12)));
		panelInscripcion.setLayout(new GridBagLayout());

		txtIdInscripcion = new JTextField();
		txtIdInscripcion.setBackground(FONDO_CAMPO);
		txtIdInscripcion.setForeground(TEXTO_BLANCO);
		txtIdInscripcion.setCaretColor(DORADO);
		txtIdInscripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtIdInscripcion.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));
		txtIdInscripcion.setPreferredSize(new Dimension(0, 36));

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBuscar.setBackground(DORADO);
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setPreferredSize(new Dimension(110, 36));

		txtNombreCliente = new JTextField();
		txtNombreCliente.setBackground(new Color(25, 25, 25));
		txtNombreCliente.setForeground(DORADO);
		txtNombreCliente.setCaretColor(DORADO);
		txtNombreCliente.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtNombreCliente.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));
		txtNombreCliente.setPreferredSize(new Dimension(0, 36));
		txtNombreCliente.setEditable(false);

		JPanel pIdInscripcion = new JPanel(new GridLayout(2, 1, 0, 4));
		pIdInscripcion.setOpaque(false);
		JLabel lblIdInscripcion = new JLabel("ID Inscripcion:");
		lblIdInscripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblIdInscripcion.setForeground(TEXTO_GRIS);
		pIdInscripcion.add(lblIdInscripcion);
		pIdInscripcion.add(txtIdInscripcion);

		GridBagConstraints gbcIdInscripcion = new GridBagConstraints();
		gbcIdInscripcion.fill = GridBagConstraints.HORIZONTAL;
		gbcIdInscripcion.insets = new Insets(6, 6, 6, 6);
		gbcIdInscripcion.gridx = 0;
		gbcIdInscripcion.gridy = 0;
		gbcIdInscripcion.weightx = 0.8;
		panelInscripcion.add(pIdInscripcion, gbcIdInscripcion);

		JPanel wrapBtn = new JPanel(new BorderLayout());
		wrapBtn.setOpaque(false);
		JLabel espacio = new JLabel(" ");
		espacio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		wrapBtn.add(espacio, BorderLayout.NORTH);
		wrapBtn.add(btnBuscar, BorderLayout.CENTER);

		GridBagConstraints gbcBtnBuscar = new GridBagConstraints();
		gbcBtnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbcBtnBuscar.insets = new Insets(6, 6, 6, 6);
		gbcBtnBuscar.gridx = 1;
		gbcBtnBuscar.gridy = 0;
		gbcBtnBuscar.weightx = 0.2;
		panelInscripcion.add(wrapBtn, gbcBtnBuscar);

		JPanel pCliente = new JPanel(new GridLayout(2, 1, 0, 4));
		pCliente.setOpaque(false);
		JLabel lblCliente = new JLabel("Cliente asociado:");
		lblCliente.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCliente.setForeground(TEXTO_GRIS);
		pCliente.add(lblCliente);
		pCliente.add(txtNombreCliente);

		GridBagConstraints gbcCliente = new GridBagConstraints();
		gbcCliente.fill = GridBagConstraints.HORIZONTAL;
		gbcCliente.insets = new Insets(6, 6, 6, 6);
		gbcCliente.gridx = 0;
		gbcCliente.gridy = 1;
		gbcCliente.gridwidth = 2;
		gbcCliente.weightx = 1.0;
		panelInscripcion.add(pCliente, gbcCliente);

		JPanel panelPago = new JPanel();
		panelPago.setBackground(FONDO_PANEL);
		panelPago.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder(BorderFactory.createLineBorder(SEPARADOR),
				"2.  Datos del Pago  -  Pago(idInscripcion, monto, fechaPago, metodoPago)",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), DORADO),
			new EmptyBorder(10, 12, 12, 12)));
		panelPago.setLayout(new GridBagLayout());

		txtMonto = new JTextField();
		txtMonto.setBackground(FONDO_CAMPO);
		txtMonto.setForeground(TEXTO_BLANCO);
		txtMonto.setCaretColor(DORADO);
		txtMonto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtMonto.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));
		txtMonto.setPreferredSize(new Dimension(0, 36));

		jdFechaPago = new JDateChooser();
		jdFechaPago.setDateFormatString("yyyy-MM-dd");
		jdFechaPago.setDate(new Date());
		jdFechaPago.setPreferredSize(new Dimension(0, 36));
		jdFechaPago.getComponent(0).setBackground(FONDO_CAMPO);
		jdFechaPago.getComponent(1).setBackground(FONDO_CAMPO);
		jdFechaPago.getComponent(1).setForeground(TEXTO_BLANCO);
		((JTextField) jdFechaPago.getComponent(1)).setCaretColor(DORADO);
		jdFechaPago.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(2, 2, 2, 2)));

		String[] metodosPago = new String[] { "Efectivo", "Tarjeta de debito", "Tarjeta de credito", "SINPE Movil" };
		cmbMetodoPago = new JComboBox(metodosPago);
		cmbMetodoPago.setBackground(FONDO_CAMPO);
		cmbMetodoPago.setForeground(TEXTO_BLANCO);
		cmbMetodoPago.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbMetodoPago.setPreferredSize(new Dimension(0, 36));

		JPanel pMonto = new JPanel(new GridLayout(2, 1, 0, 4));
		pMonto.setOpaque(false);
		JLabel lblMonto = new JLabel("Monto (colones):");
		lblMonto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMonto.setForeground(TEXTO_GRIS);
		pMonto.add(lblMonto);
		pMonto.add(txtMonto);

		GridBagConstraints gbcMonto = new GridBagConstraints();
		gbcMonto.fill = GridBagConstraints.HORIZONTAL;
		gbcMonto.insets = new Insets(6, 6, 6, 6);
		gbcMonto.gridx = 0;
		gbcMonto.gridy = 0;
		gbcMonto.weightx = 0.5;
		panelPago.add(pMonto, gbcMonto);

		JPanel pFechaPago = new JPanel(new GridLayout(2, 1, 0, 4));
		pFechaPago.setOpaque(false);
		JLabel lblFechaPago = new JLabel("Fecha de pago:");
		lblFechaPago.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblFechaPago.setForeground(TEXTO_GRIS);
		pFechaPago.add(lblFechaPago);
		pFechaPago.add(jdFechaPago);

		GridBagConstraints gbcFechaPago = new GridBagConstraints();
		gbcFechaPago.fill = GridBagConstraints.HORIZONTAL;
		gbcFechaPago.insets = new Insets(6, 6, 6, 6);
		gbcFechaPago.gridx = 1;
		gbcFechaPago.gridy = 0;
		gbcFechaPago.weightx = 0.5;
		panelPago.add(pFechaPago, gbcFechaPago);

		JPanel pMetodoPago = new JPanel(new GridLayout(2, 1, 0, 4));
		pMetodoPago.setOpaque(false);
		JLabel lblMetodoPago = new JLabel("Metodo de pago:");
		lblMetodoPago.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMetodoPago.setForeground(TEXTO_GRIS);
		pMetodoPago.add(lblMetodoPago);
		pMetodoPago.add(cmbMetodoPago);

		GridBagConstraints gbcMetodoPago = new GridBagConstraints();
		gbcMetodoPago.fill = GridBagConstraints.HORIZONTAL;
		gbcMetodoPago.insets = new Insets(6, 6, 6, 6);
		gbcMetodoPago.gridx = 0;
		gbcMetodoPago.gridy = 1;
		gbcMetodoPago.gridwidth = 2;
		gbcMetodoPago.weightx = 1.0;
		panelPago.add(pMetodoPago, gbcMetodoPago);

		JPanel panelEstado = new JPanel();
		panelEstado.setBackground(FONDO_PANEL);
		panelEstado.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder(BorderFactory.createLineBorder(SEPARADOR),
				"3.  Estado de la Inscripcion  -  Inscripcion(estado)",
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), DORADO),
			new EmptyBorder(10, 12, 12, 12)));
		panelEstado.setLayout(new GridBagLayout());

		String[] estadosInscripcion = new String[] { "activa", "vencida", "cancelada" };
		cmbEstadoInscripcion = new JComboBox(estadosInscripcion);
		cmbEstadoInscripcion.setBackground(FONDO_CAMPO);
		cmbEstadoInscripcion.setForeground(TEXTO_BLANCO);
		cmbEstadoInscripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cmbEstadoInscripcion.setPreferredSize(new Dimension(0, 36));

		JPanel pEstado = new JPanel(new GridLayout(2, 1, 0, 4));
		pEstado.setOpaque(false);
		JLabel lblEstado = new JLabel("Estado de la inscripcion:");
		lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblEstado.setForeground(TEXTO_GRIS);
		pEstado.add(lblEstado);
		pEstado.add(cmbEstadoInscripcion);

		GridBagConstraints gbcEstado = new GridBagConstraints();
		gbcEstado.fill = GridBagConstraints.HORIZONTAL;
		gbcEstado.insets = new Insets(6, 6, 6, 6);
		gbcEstado.gridx = 0;
		gbcEstado.gridy = 0;
		gbcEstado.weightx = 1.0;
		panelEstado.add(pEstado, gbcEstado);

		JPanel panelResumen = new JPanel(new BorderLayout());
		panelResumen.setBackground(new Color(40, 35, 10));
		panelResumen.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(DORADO), new EmptyBorder(12, 16, 12, 16)));

		lblResumen = new JLabel("<html><b style='color:#D4AF37'>Resumen:</b> Busque una inscripcion y complete los datos del pago.</html>");
		lblResumen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblResumen.setForeground(TEXTO_BLANCO);
		panelResumen.add(lblResumen);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		panelBotones.setBackground(FONDO_OSCURO);
		panelBotones.setBorder(new EmptyBorder(12, 0, 0, 0));

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLimpiar.setBackground(new Color(55, 55, 55));
		btnLimpiar.setForeground(TEXTO_GRIS);
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setPreferredSize(new Dimension(130, 38));

		btnRegistrar = new JButton("Registrar Pago");
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRegistrar.setBackground(VERDE);
		btnRegistrar.setForeground(Color.WHITE);
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrar.setPreferredSize(new Dimension(160, 38));

		panelBotones.add(btnLimpiar);
		panelBotones.add(btnRegistrar);

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		centro.setOpaque(false);
		centro.add(panelInscripcion);
		centro.add(Box.createVerticalStrut(12));
		centro.add(panelPago);
		centro.add(Box.createVerticalStrut(12));
		centro.add(panelEstado);
		centro.add(Box.createVerticalStrut(12));
		centro.add(panelResumen);

		contenido.add(centro, BorderLayout.CENTER);
		contenido.add(panelBotones, BorderLayout.SOUTH);
		add(contenido, BorderLayout.CENTER);

		setPreferredSize(new Dimension(660, 620));
	}

	public void setControlador(ActionListener listener, FocusListener focusListener) {
		btnBuscar.addActionListener(listener);
		btnBuscar.setActionCommand("BUSCAR_INSCRIPCION");

		btnLimpiar.addActionListener(listener);
		btnLimpiar.setActionCommand("LIMPIAR");

		btnRegistrar.addActionListener(listener);
		btnRegistrar.setActionCommand("REGISTRAR_PAGO");

		txtMonto.addFocusListener(focusListener);
	}

	public Map<String, Object> getDatosFormulario() {
		Map<String, Object> datos = new HashMap<String, Object>();
		datos.put("idInscripcion", txtIdInscripcion.getText().trim());
		datos.put("nombreCliente", txtNombreCliente.getText().trim());
		datos.put("monto", txtMonto.getText().trim());
		datos.put("metodoPago", cmbMetodoPago.getSelectedItem());
		datos.put("estadoInscripcion", cmbEstadoInscripcion.getSelectedItem());
		if (jdFechaPago.getDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			datos.put("fechaPago", sdf.format(jdFechaPago.getDate()));
		} else {
			datos.put("fechaPago", "");
		}
		return datos;
	}

	public void setNombreCliente(String nombre) {
		txtNombreCliente.setText(nombre);
	}

	public void actualizarResumen(String id, String cliente, String monto, String metodo, String estado) {
	    lblResumen.setText("<html><b style='color:#D4AF37'>Resumen:</b> Inscripción: " + id 
	                + " | Cliente: " + cliente 
	                + " | Monto: " + monto 
	                + " | Método: " + metodo 
	                + " | Estado: " + estado + "</html>");
	}

	public void limpiarFormulario() {
		txtIdInscripcion.setText("");
		txtNombreCliente.setText("");
		txtMonto.setText("");
		jdFechaPago.setDate(new Date());
		cmbMetodoPago.setSelectedIndex(0);
		cmbEstadoInscripcion.setSelectedIndex(0);
		lblResumen.setText("<html><b style='color:#D4AF37'>Resumen:</b> Busque una inscripcion y complete los datos del pago.</html>");
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