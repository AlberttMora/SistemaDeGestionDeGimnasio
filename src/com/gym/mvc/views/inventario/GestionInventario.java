package com.gym.mvc.views.inventario;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
import java.io.File;
import java.util.Date;

public class GestionInventario extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO_OSCURO = new Color(18, 18, 18);
	private static final Color FONDO_PANEL = new Color(28, 28, 28);
	private static final Color DORADO = new Color(212, 175, 55);
	private static final Color TEXTO_GRIS = new Color(160, 160, 160);
	private static final Color SEPARADOR = new Color(55, 55, 55);

	private JTextField txtIdEquipo;
	private JTextField txtNombre;
	private JTextField txtFotoRuta;
	private JDateChooser dateChooserAdquisicion;
	private JComboBox<String> cbTipoEquipo;
	private JComboBox<String> cbEstado;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JButton btnExaminarFoto;
	private JLabel lblPreviewFoto;
	private JButton btnGestionarTipos;
	private JTable tablaEquipos;
	private DefaultTableModel modeloTabla;

	public GestionInventario() {
		setBackground(FONDO_OSCURO);
		setLayout(new BorderLayout(20, 20));
		setBorder(new EmptyBorder(30, 40, 30, 40));

		JLabel lblTitulo = new JLabel("CATALOGO DE ACTIVOS Y EQUIPAMIENTO");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setForeground(DORADO);
		add(lblTitulo, BorderLayout.NORTH);

		JPanel cuerpo = new JPanel(new GridBagLayout());
		cuerpo.setOpaque(false);

		JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 12));
		panelForm.setBackground(FONDO_PANEL);
		panelForm.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(15, 15, 15, 15)));

		JLabel lblIdEquipo = new JLabel("ID Equipo:");
		lblIdEquipo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblIdEquipo.setForeground(TEXTO_GRIS);
		panelForm.add(lblIdEquipo);

		txtIdEquipo = new JTextField();
		txtIdEquipo.setBackground(FONDO_OSCURO);
		txtIdEquipo.setForeground(Color.WHITE);
		txtIdEquipo.setCaretColor(DORADO);
		txtIdEquipo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(4, 6, 4, 6)));
		txtIdEquipo.setEditable(false);
		panelForm.add(txtIdEquipo);

		JLabel lblTipoEquipo = new JLabel("Categoria (Tipo):");
		lblTipoEquipo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblTipoEquipo.setForeground(TEXTO_GRIS);
		panelForm.add(lblTipoEquipo);

		cbTipoEquipo = new JComboBox<>();
		cbTipoEquipo.setBackground(FONDO_OSCURO);
		cbTipoEquipo.setForeground(Color.WHITE);
		panelForm.add(cbTipoEquipo);

		JLabel lblNombre = new JLabel("Nombre Maquina:");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNombre.setForeground(TEXTO_GRIS);
		panelForm.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBackground(FONDO_OSCURO);
		txtNombre.setForeground(Color.WHITE);
		txtNombre.setCaretColor(DORADO);
		txtNombre.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(4, 6, 4, 6)));
		panelForm.add(txtNombre);

		JLabel lblEstado = new JLabel("Estado Operativo:");
		lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblEstado.setForeground(TEXTO_GRIS);
		panelForm.add(lblEstado);

		String[] estados = new String[] { "Excelente", "Mantenimiento Preventivo", "Fuera de Servicio" };
		cbEstado = new JComboBox<>();
		cbEstado.setModel(new DefaultComboBoxModel<>(estados));
		cbEstado.setBackground(FONDO_OSCURO);
		cbEstado.setForeground(Color.WHITE);
		panelForm.add(cbEstado);

		JLabel lblFotoRuta = new JLabel("Ruta de Fotografia:");
		lblFotoRuta.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblFotoRuta.setForeground(TEXTO_GRIS);
		panelForm.add(lblFotoRuta);

		JPanel panelFotoInput = new JPanel(new BorderLayout(5, 0));
		panelFotoInput.setOpaque(false);

		txtFotoRuta = new JTextField();
		txtFotoRuta.setBackground(FONDO_OSCURO);
		txtFotoRuta.setForeground(Color.WHITE);
		txtFotoRuta.setCaretColor(DORADO);
		txtFotoRuta.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(4, 6, 4, 6)));

		btnExaminarFoto = new JButton("...");
		btnExaminarFoto.setBackground(FONDO_OSCURO);
		btnExaminarFoto.setForeground(DORADO);

		panelFotoInput.add(txtFotoRuta, BorderLayout.CENTER);
		panelFotoInput.add(btnExaminarFoto, BorderLayout.EAST);
		panelForm.add(panelFotoInput);

		JLabel lblFechaAdquisicion = new JLabel("Fecha Compra:");
		lblFechaAdquisicion.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblFechaAdquisicion.setForeground(TEXTO_GRIS);
		panelForm.add(lblFechaAdquisicion);

		dateChooserAdquisicion = new JDateChooser();
		dateChooserAdquisicion.setDateFormatString("yyyy-MM-dd");
		dateChooserAdquisicion.setBackground(FONDO_PANEL);

		if (dateChooserAdquisicion.getDateEditor() instanceof JTextFieldDateEditor) {
			JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooserAdquisicion.getDateEditor();
			editor.setBackground(FONDO_OSCURO);
			editor.setForeground(Color.WHITE);
			editor.setCaretColor(DORADO);
			editor.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
					new EmptyBorder(4, 6, 4, 6)));
		}
		panelForm.add(dateChooserAdquisicion);

		GridBagConstraints gbcForm = new GridBagConstraints();
		gbcForm.gridx = 0;
		gbcForm.gridy = 0;
		gbcForm.weightx = 0.30;
		gbcForm.weighty = 0.8;
		gbcForm.fill = GridBagConstraints.BOTH;
		cuerpo.add(panelForm, gbcForm);

		JPanel panelPreview = new JPanel(new BorderLayout());
		panelPreview.setOpaque(false);
		panelPreview.setBorder(BorderFactory.createLineBorder(SEPARADOR));

		lblPreviewFoto = new JLabel("Sin imagen", SwingConstants.CENTER);
		lblPreviewFoto.setForeground(TEXTO_GRIS);
		lblPreviewFoto.setPreferredSize(new Dimension(150, 150));
		panelPreview.add(lblPreviewFoto, BorderLayout.CENTER);

		GridBagConstraints gbcPreview = new GridBagConstraints();
		gbcPreview.gridx = 1;
		gbcPreview.gridy = 0;
		gbcPreview.weightx = 0.12;
		gbcPreview.weighty = 0.8;
		gbcPreview.fill = GridBagConstraints.BOTH;
		gbcPreview.insets = new Insets(0, 20, 0, 20);
		cuerpo.add(panelPreview, gbcPreview);

		String[] columnas = { "ID", "Categoria", "Nombre", "Estado", "Adquisicion" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		tablaEquipos = new JTable(modeloTabla);
		tablaEquipos.setBackground(FONDO_PANEL);
		tablaEquipos.setForeground(Color.WHITE);
		tablaEquipos.setGridColor(SEPARADOR);
		tablaEquipos.setRowHeight(24);
		tablaEquipos.setSelectionBackground(DORADO);
		tablaEquipos.setSelectionForeground(FONDO_OSCURO);

		JTableHeader th = tablaEquipos.getTableHeader();
		th.setBackground(FONDO_OSCURO);
		th.setForeground(DORADO);
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JScrollPane scroll = new JScrollPane(tablaEquipos);
		scroll.getViewport().setBackground(FONDO_PANEL);
		scroll.setBorder(BorderFactory.createLineBorder(SEPARADOR));

		GridBagConstraints gbcScroll = new GridBagConstraints();
		gbcScroll.gridx = 2;
		gbcScroll.gridy = 0;
		gbcScroll.weightx = 0.58;
		gbcScroll.weighty = 0.8;
		gbcScroll.fill = GridBagConstraints.BOTH;
		gbcScroll.insets = new Insets(0, 0, 0, 0);
		cuerpo.add(scroll, gbcScroll);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		panelBotones.setOpaque(false);

		btnGuardar = new JButton("Registrar Equipo");
		btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnGuardar.setBackground(FONDO_PANEL);
		btnGuardar.setForeground(DORADO);
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(8, 16, 8, 16)));
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBotones.add(btnGuardar);

		btnEliminar = new JButton("Dar de Baja");
		btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEliminar.setBackground(FONDO_PANEL);
		btnEliminar.setForeground(DORADO);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(8, 16, 8, 16)));
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBotones.add(btnEliminar);

		btnGestionarTipos = new JButton("Configurar Categorias");
		btnGestionarTipos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnGestionarTipos.setBackground(FONDO_PANEL);
		btnGestionarTipos.setForeground(DORADO);
		btnGestionarTipos.setFocusPainted(false);
		btnGestionarTipos.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR),
				new EmptyBorder(8, 16, 8, 16)));
		btnGestionarTipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelBotones.add(btnGestionarTipos);

		GridBagConstraints gbcBotones = new GridBagConstraints();
		gbcBotones.gridx = 0;
		gbcBotones.gridy = 1;
		gbcBotones.gridwidth = 3;
		gbcBotones.weighty = 0.2;
		gbcBotones.fill = GridBagConstraints.HORIZONTAL;
		gbcBotones.insets = new Insets(20, 0, 0, 0);
		cuerpo.add(panelBotones, gbcBotones);

		add(cuerpo, BorderLayout.CENTER);
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnExaminarFoto() {
		return btnExaminarFoto;
	}

	public JButton getBtnGestionarTipos() {
		return btnGestionarTipos;
	}

	public JComboBox<String> getComboTipos() {
		return cbTipoEquipo;
	}

	public JComboBox<String> getComboEstado() {
		return cbEstado;
	}

	public JTable getTabla() {
		return tablaEquipos;
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public String obtenerIdEquipo() {
		return txtIdEquipo.getText().trim();
	}

	public String obtenerNombre() {
		return txtNombre.getText().trim();
	}

	public String obtenerEstado() {
		return (String) cbEstado.getSelectedItem();
	}

	public String obtenerFotoRuta() {
		return txtFotoRuta.getText().trim();
	}

	public Date obtenerFecha() {
		return dateChooserAdquisicion.getDate();
	}

	public String obtenerTipoSeleccionado() {
		return (String) cbTipoEquipo.getSelectedItem();
	}

	public void establecerIdEquipo(String id) {
		txtIdEquipo.setText(id);
	}

	public void establecerNombre(String nombre) {
		txtNombre.setText(nombre);
	}

	public void establecerFotoRuta(String ruta) {
		txtFotoRuta.setText(ruta);
		actualizarPreview(ruta);
	}

	public void establecerFecha(Date fecha) {
		dateChooserAdquisicion.setDate(fecha);
	}

	public void limpiarFormulario() {
		txtIdEquipo.setText("");
		txtNombre.setText("");
		txtFotoRuta.setText("");
		dateChooserAdquisicion.setDate(null);
		if (cbTipoEquipo.getItemCount() > 0)
			cbTipoEquipo.setSelectedIndex(0);
		if (cbEstado.getItemCount() > 0)
			cbEstado.setSelectedIndex(0);
		actualizarPreview("");
	}

	public void actualizarPreview(String ruta) {
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

		ImageIcon icono = new ImageIcon(ruta);
		Image escalada = icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		lblPreviewFoto.setText("");
		lblPreviewFoto.setIcon(new ImageIcon(escalada));
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
}