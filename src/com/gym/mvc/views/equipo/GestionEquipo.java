package com.gym.mvc.views.equipo;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
public class GestionEquipo extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO_OSCURO = new Color(18, 18, 18);
	private static final Color FONDO_PANEL = new Color(28, 28, 28);
	private static final Color FONDO_CAMPO = new Color(38, 38, 38);
	private static final Color DORADO = new Color(212, 175, 55);
	private static final Color TEXTO_BLANCO = new Color(240, 240, 240);
	private static final Color TEXTO_GRIS = new Color(160, 160, 160);
	private static final Color SEPARADOR = new Color(55, 55, 55);
	private static final Color VERDE = new Color(60, 160, 80);

	private JTextField txtIdEquipo;
	private JTextField txtNombre;
	private JTextField txtEstado;
	private JLabel lblVistaPreviaImagen;
	private File archivoImagenSeleccionado;
	private JButton btnRegistrar;
	private JButton btnLimpiar;
	private JTable tablaEquipos;
	private DefaultTableModel modeloTabla;
	private JComboBox<String> comboTipo;

	public GestionEquipo() {
		setBackground(FONDO_OSCURO);
		setLayout(new BorderLayout(20, 20));
		setBorder(new EmptyBorder(30, 40, 30, 40));

		JLabel lblTitulo = new JLabel("PANEL DE CONTROL Y DISPONIBILIDAD DE EQUIPOS");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitulo.setForeground(DORADO);
		add(lblTitulo, BorderLayout.NORTH);

		JPanel cuerpo = new JPanel(new GridBagLayout());
		cuerpo.setOpaque(false);

		JPanel panelForm = new JPanel(new GridBagLayout());
		panelForm.setBackground(FONDO_PANEL);
		panelForm.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR),
			new EmptyBorder(20, 20, 20, 20)));

		JLabel lblIdEquipo = new JLabel("ID Equipo (Autonumerico o Codigo):");
		lblIdEquipo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblIdEquipo.setForeground(TEXTO_GRIS);

		GridBagConstraints gbcLblId = new GridBagConstraints();
		gbcLblId.fill = GridBagConstraints.HORIZONTAL;
		gbcLblId.insets = new Insets(6, 0, 6, 0);
		gbcLblId.weightx = 1.0;
		gbcLblId.gridx = 0;
		gbcLblId.gridy = 0;
		panelForm.add(lblIdEquipo, gbcLblId);

		txtIdEquipo = new JTextField();
		txtIdEquipo.setBackground(FONDO_CAMPO);
		txtIdEquipo.setForeground(Color.WHITE);
		txtIdEquipo.setCaretColor(DORADO);
		txtIdEquipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtIdEquipo.setPreferredSize(new Dimension(0, 36));
		txtIdEquipo.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 8, 4, 8)));

		GridBagConstraints gbcTxtId = new GridBagConstraints();
		gbcTxtId.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtId.insets = new Insets(6, 0, 6, 0);
		gbcTxtId.weightx = 1.0;
		gbcTxtId.gridx = 0;
		gbcTxtId.gridy = 1;
		panelForm.add(txtIdEquipo, gbcTxtId);

		JLabel lblNombre = new JLabel("Nombre del Equipo / Maquina:");
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNombre.setForeground(TEXTO_GRIS);

		GridBagConstraints gbcLblNombre = new GridBagConstraints();
		gbcLblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbcLblNombre.insets = new Insets(6, 0, 6, 0);
		gbcLblNombre.weightx = 1.0;
		gbcLblNombre.gridx = 0;
		gbcLblNombre.gridy = 2;
		panelForm.add(lblNombre, gbcLblNombre);

		txtNombre = new JTextField();
		txtNombre.setBackground(FONDO_CAMPO);
		txtNombre.setForeground(Color.WHITE);
		txtNombre.setCaretColor(DORADO);
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNombre.setPreferredSize(new Dimension(0, 36));
		txtNombre.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 8, 4, 8)));

		GridBagConstraints gbcTxtNombre = new GridBagConstraints();
		gbcTxtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtNombre.insets = new Insets(6, 0, 6, 0);
		gbcTxtNombre.weightx = 1.0;
		gbcTxtNombre.gridx = 0;
		gbcTxtNombre.gridy = 3;
		panelForm.add(txtNombre, gbcTxtNombre);

		JLabel lblEstado = new JLabel("Estado (Disponible, Mantenimiento):");
		lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblEstado.setForeground(TEXTO_GRIS);

		GridBagConstraints gbcLblEstado = new GridBagConstraints();
		gbcLblEstado.fill = GridBagConstraints.HORIZONTAL;
		gbcLblEstado.insets = new Insets(6, 0, 6, 0);
		gbcLblEstado.weightx = 1.0;
		gbcLblEstado.gridx = 0;
		gbcLblEstado.gridy = 4;
		panelForm.add(lblEstado, gbcLblEstado);

		txtEstado = new JTextField();
		txtEstado.setBackground(FONDO_CAMPO);
		txtEstado.setForeground(Color.WHITE);
		txtEstado.setCaretColor(DORADO);
		txtEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtEstado.setPreferredSize(new Dimension(0, 36));
		txtEstado.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(4, 8, 4, 8)));

		GridBagConstraints gbcTxtEstado = new GridBagConstraints();
		gbcTxtEstado.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtEstado.insets = new Insets(6, 0, 6, 0);
		gbcTxtEstado.weightx = 1.0;
		gbcTxtEstado.gridx = 0;
		gbcTxtEstado.gridy = 5;
		panelForm.add(txtEstado, gbcTxtEstado);
		
		JLabel lblTipo = new JLabel("Tipo de Equipo:");
		lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblTipo.setForeground(TEXTO_GRIS);
		GridBagConstraints gbcLblTipo = new GridBagConstraints();
		gbcLblTipo.fill = GridBagConstraints.HORIZONTAL;
		gbcLblTipo.insets = new Insets(6, 0, 6, 0);
		gbcLblTipo.gridx = 0; gbcLblTipo.gridy = 6; 
		panelForm.add(lblTipo, gbcLblTipo);
		
		comboTipo = new JComboBox<>();
		comboTipo.setBackground(FONDO_CAMPO);
		comboTipo.setForeground(Color.WHITE);
		comboTipo.setPreferredSize(new Dimension(0, 36));
		GridBagConstraints gbcCombo = new GridBagConstraints();
		gbcCombo.fill = GridBagConstraints.HORIZONTAL;
		gbcCombo.insets = new Insets(6, 0, 6, 0);
		gbcCombo.gridx = 0; gbcCombo.gridy = 7; 
		panelForm.add(comboTipo, gbcCombo);

		JLabel lblArrastrar = new JLabel("Arrastra la imagen del equipo aqui:");
		lblArrastrar.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblArrastrar.setForeground(TEXTO_GRIS);

		GridBagConstraints gbcLblArrastrar = new GridBagConstraints();
		gbcLblArrastrar.fill = GridBagConstraints.HORIZONTAL;
		gbcLblArrastrar.insets = new Insets(6, 0, 6, 0);
		gbcLblArrastrar.weightx = 1.0;
		gbcLblArrastrar.gridx = 0;
		gbcLblArrastrar.gridy = 8;
		panelForm.add(lblArrastrar, gbcLblArrastrar);

		lblVistaPreviaImagen = new JLabel("Arrastra archivo de imagen aqui (.png, .jpg)", SwingConstants.CENTER);
		lblVistaPreviaImagen.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		lblVistaPreviaImagen.setForeground(TEXTO_GRIS);
		lblVistaPreviaImagen.setBackground(FONDO_OSCURO);
		lblVistaPreviaImagen.setOpaque(true);
		lblVistaPreviaImagen.setPreferredSize(new Dimension(0, 140));
		lblVistaPreviaImagen.setBorder(new AbstractBorder() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setColor(DORADO);
				float[] dashed = { 4f, 4f };
				g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashed, 0.0f));
				g2d.drawRect(x + 2, y + 2, width - 5, height - 5);
				g2d.dispose();
			}

			@Override
			public Insets getBorderInsets(Component c) {
				return new Insets(4, 4, 4, 4);
			}
		});

		GridBagConstraints gbcVistaPrevia = new GridBagConstraints();
		gbcVistaPrevia.insets = new Insets(6, 0, 6, 0);
		gbcVistaPrevia.weightx = 1.0;
		gbcVistaPrevia.weighty = 1.0;
		gbcVistaPrevia.gridx = 0;
		gbcVistaPrevia.gridy = 9;
		gbcVistaPrevia.fill = GridBagConstraints.BOTH;
		panelForm.add(lblVistaPreviaImagen, gbcVistaPrevia);

		JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		panelAcciones.setOpaque(false);

		btnRegistrar = new JButton("Registrar Equipo");
		btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnRegistrar.setBackground(VERDE);
		btnRegistrar.setForeground(Color.GRAY);
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrar.setPreferredSize(new Dimension(130, 36));
		panelAcciones.add(btnRegistrar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLimpiar.setBackground(FONDO_CAMPO);
		btnLimpiar.setForeground(TEXTO_GRIS);
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setPreferredSize(new Dimension(130, 36));
		panelAcciones.add(btnLimpiar);

		GridBagConstraints gbcAcciones = new GridBagConstraints();
		gbcAcciones.fill = GridBagConstraints.HORIZONTAL;
		gbcAcciones.insets = new Insets(15, 0, 0, 0);
		gbcAcciones.weightx = 1.0;
		gbcAcciones.gridx = 0;
		gbcAcciones.gridy = 10;
		panelForm.add(panelAcciones, gbcAcciones);

		String[] columnas = { "ID", "Nombre Equipo", "Estado Disponibilidad" };
		modeloTabla = new DefaultTableModel(columnas, 0);
		tablaEquipos = new JTable(modeloTabla);
		tablaEquipos.setBackground(FONDO_PANEL);
		tablaEquipos.setForeground(Color.WHITE);
		tablaEquipos.getTableHeader().setBackground(FONDO_OSCURO);
		tablaEquipos.getTableHeader().setForeground(DORADO);
		tablaEquipos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablaEquipos.setGridColor(SEPARADOR);
		tablaEquipos.setRowHeight(28);
		tablaEquipos.setSelectionBackground(DORADO);
		tablaEquipos.setSelectionForeground(FONDO_OSCURO);

		JScrollPane scroll = new JScrollPane(tablaEquipos);
		scroll.getViewport().setBackground(FONDO_PANEL);
		scroll.setBorder(BorderFactory.createLineBorder(SEPARADOR));

		GridBagConstraints gbcForm = new GridBagConstraints();
		gbcForm.fill = GridBagConstraints.BOTH;
		gbcForm.insets = new Insets(0, 10, 0, 10);
		gbcForm.gridx = 0;
		gbcForm.gridy = 0;
		gbcForm.weightx = 0.4;
		gbcForm.weighty = 1.0;
		cuerpo.add(panelForm, gbcForm);

		GridBagConstraints gbcScroll = new GridBagConstraints();
		gbcScroll.fill = GridBagConstraints.BOTH;
		gbcScroll.insets = new Insets(0, 10, 0, 10);
		gbcScroll.gridx = 1;
		gbcScroll.gridy = 0;
		gbcScroll.weightx = 0.6;
		gbcScroll.weighty = 1.0;
		cuerpo.add(scroll, gbcScroll);

		add(cuerpo, BorderLayout.CENTER);

		configurarDragAndDropPasivo();
	}

	private void configurarDragAndDropPasivo() {
		lblVistaPreviaImagen.setTransferHandler(new TransferHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean canImport(TransferSupport support) {
				return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
			}

			@Override
			@SuppressWarnings("unchecked")
			public boolean importData(TransferSupport support) {
				if (!canImport(support)) {
					return false;
				}
				try {
					Transferable t = support.getTransferable();
					List<File> archivos = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
					if (archivos != null && !archivos.isEmpty()) {
						archivoImagenSeleccionado = archivos.get(0);
						mostrarVistaPreviaTemporal(archivoImagenSeleccionado);
						return true;
					}
				} catch (Exception e) {
					return false;
				}
				return false;
			}
		});
	}

	private void mostrarVistaPreviaTemporal(File archivo) {
		try {
			ImageIcon iconoOriginal = new ImageIcon(archivo.getAbsolutePath());
			Image imagenOriginal = iconoOriginal.getImage();

			int anchoLabel = lblVistaPreviaImagen.getWidth();
			int altoLabel = lblVistaPreviaImagen.getHeight();
			if (anchoLabel <= 0) {
				anchoLabel = 260;
			}
			if (altoLabel <= 0) {
				altoLabel = 140;
			}

			Image imagenEscalada = imagenOriginal.getScaledInstance(anchoLabel - 10, altoLabel - 10, Image.SCALE_SMOOTH);
			lblVistaPreviaImagen.setIcon(new ImageIcon(imagenEscalada));
			lblVistaPreviaImagen.setText("");
		} catch (Exception e) {
			lblVistaPreviaImagen.setIcon(null);
			lblVistaPreviaImagen.setText("Archivo: " + archivo.getName());
		}
	}

	public Map<String, Object> getDatosFormulario() {
	    Map<String, Object> datos = new HashMap<String, Object>();
	    datos.put("id", txtIdEquipo.getText().trim());
	    datos.put("nombre", txtNombre.getText().trim());
	    datos.put("estado", txtEstado.getText().trim());
	    datos.put("tipo", comboTipo.getSelectedItem()); 
	    datos.put("archivoImagen", archivoImagenSeleccionado);
	    return datos;
	}

	public void setControlador(ActionListener l, MouseListener m) {
		btnRegistrar.addActionListener(l);
		btnRegistrar.setActionCommand("REGISTRAR_EQUIPO");

		btnLimpiar.addActionListener(l);
		btnLimpiar.setActionCommand("LIMPIAR_FORMULARIO");

		tablaEquipos.addMouseListener(m);
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public JTable getTablaEquipos() {
		return tablaEquipos;
	}

	public JComboBox<String> getComboTipo() {
	    return comboTipo;
	}
	
	public void mostrarImagenEnVistaPrevia(byte[] bytesImagen) {
		if (bytesImagen != null && bytesImagen.length > 0) {
			ImageIcon icono = new ImageIcon(bytesImagen);
			Image img = icono.getImage().getScaledInstance(
				lblVistaPreviaImagen.getWidth() - 10,
				lblVistaPreviaImagen.getHeight() - 10,
				Image.SCALE_SMOOTH);
			lblVistaPreviaImagen.setIcon(new ImageIcon(img));
			lblVistaPreviaImagen.setText("");
		} else {
			lblVistaPreviaImagen.setIcon(null);
			lblVistaPreviaImagen.setText("Sin imagen disponible");
		}
	}

	public void limpiarFormulario() {
		txtIdEquipo.setText("");
		txtNombre.setText("");
		txtEstado.setText("");
		comboTipo.setSelectedIndex(-1);
		archivoImagenSeleccionado = null;
		lblVistaPreviaImagen.setIcon(null);
		lblVistaPreviaImagen.setText("Arrastra archivo de imagen aqui (.png, .jpg)");
	}
	

	public void mostrarError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarAdvertencia(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Validación", JOptionPane.WARNING_MESSAGE);
	}

	public void mostrarMensaje(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
	}
}