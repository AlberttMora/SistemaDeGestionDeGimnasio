package com.gym.mvc.views.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

public class GestionClientes extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO_OSCURO = new Color(18, 18, 18);
	private static final Color FONDO_PANEL = new Color(28, 28, 28);
	private static final Color FONDO_CAMPO = new Color(38, 38, 38);
	private static final Color DORADO = new Color(212, 175, 55);
	private static final Color TEXTO_BLANCO = new Color(240, 240, 240);
	private static final Color TEXTO_GRIS = new Color(160, 160, 160);
	private static final Color SEPARADOR = new Color(55, 55, 55);
	private static final Color ROJO = new Color(200, 60, 60);
	private static final Color VERDE = new Color(60, 160, 80);

	private JTextField txtBuscarId;
	private JTextField txtIdCliente;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JTextField txtTelefono;
	private JTextField txtFotoRuta;
	private JDateChooser jdFechaNac;
	private JButton btnGuardar;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JButton btnEliminar;
	private JButton btnExaminarFoto;
	private JLabel lblPreviewFoto;

	public GestionClientes() {
		setLayout(new BorderLayout());
		setBackground(FONDO_OSCURO);

		JPanel panelHeader = new JPanel(new GridLayout(2, 1, 0, 4));
		panelHeader.setBackground(FONDO_PANEL);
		panelHeader.setBorder(new EmptyBorder(20, 32, 16, 32));

		JLabel lblTitulo = new JLabel("GESTION DE CLIENTES");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblTitulo.setForeground(DORADO);

		JLabel lblSubtitulo = new JLabel("Registrar, editar y eliminar clientes");
		lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblSubtitulo.setForeground(TEXTO_GRIS);

		panelHeader.add(lblTitulo);
		panelHeader.add(lblSubtitulo);
		add(panelHeader, BorderLayout.NORTH);

		JPanel contenido = new JPanel(new BorderLayout());
		contenido.setBackground(FONDO_OSCURO);
		contenido.setBorder(new EmptyBorder(24, 32, 16, 32));

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		centro.setOpaque(false);

		JPanel panelBusqueda = new JPanel(new BorderLayout(10, 0));
		panelBusqueda.setBackground(FONDO_PANEL);
		panelBusqueda.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(12, 16, 12, 16)));

		JLabel lblBuscar = new JLabel("Buscar por ID:");
		lblBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblBuscar.setForeground(TEXTO_GRIS);
		lblBuscar.setPreferredSize(new Dimension(100, 36));

		txtBuscarId = new JTextField();
		txtBuscarId.setBackground(FONDO_CAMPO);
		txtBuscarId.setForeground(TEXTO_BLANCO);
		txtBuscarId.setCaretColor(DORADO);
		txtBuscarId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtBuscarId.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(6, 8, 6, 8)));

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBuscar.setBackground(DORADO);
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setBorderPainted(false);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setPreferredSize(new Dimension(120, 36));

		panelBusqueda.add(lblBuscar, BorderLayout.WEST);
		panelBusqueda.add(txtBuscarId, BorderLayout.CENTER);
		panelBusqueda.add(btnBuscar, BorderLayout.EAST);

		centro.add(panelBusqueda);
		centro.add(Box.createVerticalStrut(16));

		JPanel wrapperFormFoto = new JPanel(new GridBagLayout());
		wrapperFormFoto.setBackground(FONDO_PANEL);
		wrapperFormFoto.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(20, 24, 20, 24)));

		Dimension dimCampo = new Dimension(150, 36);

		txtIdCliente = crearCampo(dimCampo, false);
		txtNombre = crearCampo(dimCampo, true);
		txtApellido = crearCampo(dimCampo, true);
		txtEmail = crearCampo(dimCampo, true);
		txtTelefono = crearCampo(dimCampo, true);
		txtFotoRuta = crearCampo(dimCampo, true);

		jdFechaNac = new JDateChooser();
		jdFechaNac.setDateFormatString("yyyy-MM-dd");
		jdFechaNac.setPreferredSize(dimCampo);
		jdFechaNac.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(2, 2, 2, 2)));

		JPanel panelForm = new JPanel(new GridBagLayout());
		panelForm.setOpaque(false);

		agregarFilaCampo(panelForm, "ID", txtIdCliente, 0, false);
		agregarFilaCampo(panelForm, "Nombre *", txtNombre, 1, false);
		agregarFilaCampo(panelForm, "Apellido *", txtApellido, 2, false);
		agregarFilaCampo(panelForm, "Correo electronico *", txtEmail, 3, false);
		agregarFilaCampo(panelForm, "Telefono", txtTelefono, 4, false);
		agregarFilaCampo(panelForm, "Fecha de nacimiento", jdFechaNac, 5, false);

		JPanel panelFotoInput = new JPanel(new BorderLayout(5, 0));
		panelFotoInput.setOpaque(false);
		panelFotoInput.add(txtFotoRuta, BorderLayout.CENTER);

		btnExaminarFoto = new JButton("...");
		btnExaminarFoto.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnExaminarFoto.setBackground(FONDO_CAMPO);
		btnExaminarFoto.setForeground(DORADO);
		btnExaminarFoto.setFocusPainted(false);
		btnExaminarFoto.setBorder(BorderFactory.createLineBorder(SEPARADOR));
		btnExaminarFoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExaminarFoto.setPreferredSize(new Dimension(36, 36));
		btnExaminarFoto.setToolTipText("Seleccionar foto del cliente");
		panelFotoInput.add(btnExaminarFoto, BorderLayout.EAST);

		agregarFilaCampo(panelForm, "Foto del cliente", panelFotoInput, 6, false);

		JLabel lblNota = new JLabel("* Campos obligatorios");
		lblNota.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblNota.setForeground(TEXTO_GRIS);
		GridBagConstraints gbcNota = new GridBagConstraints();
		gbcNota.gridx = 0;
		gbcNota.gridy = 7;
		gbcNota.gridwidth = 2;
		gbcNota.fill = GridBagConstraints.HORIZONTAL;
		gbcNota.insets = new Insets(8, 0, 0, 0);
		panelForm.add(lblNota, gbcNota);

		GridBagConstraints gbcFormCol = new GridBagConstraints();
		gbcFormCol.gridx = 0;
		gbcFormCol.gridy = 0;
		gbcFormCol.weightx = 0.65;
		gbcFormCol.weighty = 1.0;
		gbcFormCol.fill = GridBagConstraints.BOTH;
		gbcFormCol.insets = new Insets(0, 0, 0, 20);
		wrapperFormFoto.add(panelForm, gbcFormCol);

		JPanel panelPreview = new JPanel(new BorderLayout(0, 8));
		panelPreview.setOpaque(false);

		JLabel lblFotoTitulo = new JLabel("FOTO DEL CLIENTE");
		lblFotoTitulo.setFont(new Font("Segoe UI", Font.BOLD, 10));
		lblFotoTitulo.setForeground(TEXTO_GRIS);
		lblFotoTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		lblPreviewFoto = new JLabel("Sin imagen", SwingConstants.CENTER);
		lblPreviewFoto.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		lblPreviewFoto.setForeground(TEXTO_GRIS);
		lblPreviewFoto.setBackground(FONDO_CAMPO);
		lblPreviewFoto.setOpaque(true);
		lblPreviewFoto.setPreferredSize(new Dimension(160, 180));
		lblPreviewFoto.setBorder(BorderFactory.createLineBorder(SEPARADOR));

		panelPreview.add(lblFotoTitulo, BorderLayout.NORTH);
		panelPreview.add(lblPreviewFoto, BorderLayout.CENTER);

		GridBagConstraints gbcPreviewCol = new GridBagConstraints();
		gbcPreviewCol.gridx = 1;
		gbcPreviewCol.gridy = 0;
		gbcPreviewCol.weightx = 0.35;
		gbcPreviewCol.weighty = 1.0;
		gbcPreviewCol.fill = GridBagConstraints.BOTH;
		wrapperFormFoto.add(panelPreview, gbcPreviewCol);

		centro.add(wrapperFormFoto);
		contenido.add(centro, BorderLayout.CENTER);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEliminar.setBackground(ROJO);
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setMargin(new Insets(8, 18, 8, 18));

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLimpiar.setBackground(new Color(60, 60, 60));
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setMargin(new Insets(8, 18, 8, 18));

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnGuardar.setBackground(VERDE);
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorderPainted(false);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setMargin(new Insets(8, 18, 8, 18));

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		panelBotones.setBackground(FONDO_OSCURO);
		panelBotones.setBorder(new EmptyBorder(16, 0, 8, 0));
		panelBotones.add(Box.createHorizontalGlue());
		panelBotones.add(btnEliminar);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(btnLimpiar);
		panelBotones.add(Box.createHorizontalStrut(10));
		panelBotones.add(btnGuardar);

		contenido.add(panelBotones, BorderLayout.SOUTH);
		add(contenido, BorderLayout.CENTER);
	}

	private JTextField crearCampo(Dimension dim, boolean editable) {
		JTextField campo = new JTextField();
		campo.setBackground(FONDO_CAMPO);
		campo.setForeground(editable ? TEXTO_BLANCO : TEXTO_GRIS);
		campo.setCaretColor(DORADO);
		campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		campo.setEditable(editable);
		campo.setPreferredSize(dim);
		campo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(6, 8, 6, 8)));
		return campo;
	}

	private void agregarFilaCampo(JPanel panel, String etiqueta, java.awt.Component campo, int fila,
			boolean spanCompleto) {
		JLabel lbl = new JLabel(etiqueta);
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lbl.setForeground(TEXTO_GRIS);

		GridBagConstraints gbcLbl = new GridBagConstraints();
		gbcLbl.gridx = 0;
		gbcLbl.gridy = fila * 2;
		gbcLbl.gridwidth = 2;
		gbcLbl.fill = GridBagConstraints.HORIZONTAL;
		gbcLbl.insets = new Insets(fila == 0 ? 0 : 6, 0, 2, 0);
		panel.add(lbl, gbcLbl);

		GridBagConstraints gbcCampo = new GridBagConstraints();
		gbcCampo.gridx = 0;
		gbcCampo.gridy = fila * 2 + 1;
		gbcCampo.gridwidth = 2;
		gbcCampo.fill = GridBagConstraints.HORIZONTAL;
		gbcCampo.weightx = 1.0;
		gbcCampo.insets = new Insets(0, 0, 0, 0);
		panel.add(campo, gbcCampo);
	}

	public void actualizarPreviewFoto(String ruta) {
		if (ruta == null || ruta.isEmpty()) {
			lblPreviewFoto.setIcon(null);
			lblPreviewFoto.setText("Sin imagen");
			return;
		}
		File archivo = new File(ruta);
		if (!archivo.exists()) {
			lblPreviewFoto.setIcon(null);
			lblPreviewFoto.setText("No encontrada");
			return;
		}
		try {
			ImageIcon icono = new ImageIcon(ruta);
			int w = lblPreviewFoto.getWidth() > 0 ? lblPreviewFoto.getWidth() - 8 : 152;
			int h = lblPreviewFoto.getHeight() > 0 ? lblPreviewFoto.getHeight() - 8 : 172;
			Image escalada = icono.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
			lblPreviewFoto.setIcon(new ImageIcon(escalada));
			lblPreviewFoto.setText("");
		} catch (Exception e) {
			lblPreviewFoto.setIcon(null);
			lblPreviewFoto.setText("Error al cargar");
		}
	}

	public void setControlador(ActionListener listener) {
		btnBuscar.setActionCommand("BUSCAR");
		btnBuscar.addActionListener(listener);
		btnGuardar.setActionCommand("GUARDAR");
		btnGuardar.addActionListener(listener);
		btnEliminar.setActionCommand("ELIMINAR");
		btnEliminar.addActionListener(listener);
		btnLimpiar.setActionCommand("LIMPIAR");
		btnLimpiar.addActionListener(listener);
		btnExaminarFoto.setActionCommand("EXAMINAR_FOTO");
		btnExaminarFoto.addActionListener(listener);
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnExaminarFoto() {
		return btnExaminarFoto;
	}

	public String getIdBusqueda() {
		return txtBuscarId.getText().trim();
	}

	public String getTxtIdCliente() {
		return txtIdCliente.getText().trim();
	}

	public void setDatosFormulario(Map<String, String> datos) {
		txtIdCliente.setText(datos.getOrDefault("id", ""));
		txtNombre.setText(datos.getOrDefault("nombre", ""));
		txtApellido.setText(datos.getOrDefault("apellido", ""));
		txtEmail.setText(datos.getOrDefault("email", ""));
		txtTelefono.setText(datos.getOrDefault("telefono", ""));
		String ruta = datos.getOrDefault("fotoRuta", "");
		txtFotoRuta.setText(ruta);
		actualizarPreviewFoto(ruta);
		try {
			String fNac = datos.get("fechaNac");
			if (fNac != null && !fNac.isEmpty()) {
				jdFechaNac.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(fNac));
			} else {
				jdFechaNac.setDate(null);
			}
		} catch (Exception ex) {
			jdFechaNac.setDate(null);
		}
	}

	public Map<String, Object> getDatosFormulario() {
		Map<String, Object> datos = new HashMap<>();
		datos.put("id", txtIdCliente.getText().trim());
		datos.put("nombre", txtNombre.getText().trim());
		datos.put("apellido", txtApellido.getText().trim());
		datos.put("email", txtEmail.getText().trim());
		datos.put("telefono", txtTelefono.getText().trim());
		datos.put("fotoRuta", txtFotoRuta.getText().trim());
		datos.put("fechaNac", jdFechaNac.getDate());
		return datos;
	}

	public void poblarCampos(int id, String nombre, String apellido, String email, String telefono, String foto,
			Date fecha) {
		txtIdCliente.setText(String.valueOf(id));
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtEmail.setText(email);
		txtTelefono.setText(telefono);
		txtFotoRuta.setText(foto);
		actualizarPreviewFoto(foto);
		jdFechaNac.setDate(fecha);
	}

	public void setFotoRuta(String ruta) {
		txtFotoRuta.setText(ruta);
		actualizarPreviewFoto(ruta);
	}

	public void limpiar() {
		txtBuscarId.setText("");
		txtIdCliente.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtEmail.setText("");
		txtTelefono.setText("");
		txtFotoRuta.setText("");
		jdFechaNac.setDate(null);
		actualizarPreviewFoto("");
	}

	public boolean validarFormulario() {
		if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()
				|| txtEmail.getText().trim().isEmpty()) {
			mostrarAdvertencia("Por favor, complete los campos obligatorios (*)");
			return false;
		}
		return true;
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
		return JOptionPane.showConfirmDialog(this, msg, "Confirmar",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}
}