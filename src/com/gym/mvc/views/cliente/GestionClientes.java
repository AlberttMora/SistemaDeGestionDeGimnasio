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
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

public class GestionClientes extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color FONDO_OSCURO = new Color(18, 18, 18);
    private static final Color FONDO_PANEL  = new Color(28, 28, 28);
    private static final Color FONDO_CAMPO  = new Color(38, 38, 38);
    private static final Color DORADO       = new Color(212, 175, 55);
    private static final Color TEXTO_BLANCO = new Color(240, 240, 240);
    private static final Color TEXTO_GRIS   = new Color(160, 160, 160);
    private static final Color SEPARADOR    = new Color(55, 55, 55);
    private static final Color ROJO         = new Color(200, 60, 60);
    private static final Color VERDE        = new Color(60, 160, 80);

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
        panelBusqueda.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(12, 16, 12, 16)
        ));

        JLabel lblBuscar = new JLabel("Buscar por ID:");
        lblBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblBuscar.setForeground(TEXTO_GRIS);
        lblBuscar.setPreferredSize(new Dimension(100, 36));

        txtBuscarId = new JTextField();
        txtBuscarId.setBackground(FONDO_CAMPO);
        txtBuscarId.setForeground(TEXTO_BLANCO);
        txtBuscarId.setCaretColor(DORADO);
        txtBuscarId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscarId.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(6, 8, 6, 8)
        ));

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

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(FONDO_PANEL);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(20, 24, 20, 24)
        ));

        Dimension dimCampo = new Dimension(150, 36);

        txtIdCliente = new JTextField();
        txtIdCliente.setBackground(FONDO_CAMPO);
        txtIdCliente.setForeground(TEXTO_GRIS);
        txtIdCliente.setCaretColor(DORADO);
        txtIdCliente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtIdCliente.setEditable(false);
        txtIdCliente.setPreferredSize(dimCampo);
        txtIdCliente.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));

        txtNombre = new JTextField();
        txtNombre.setBackground(FONDO_CAMPO);
        txtNombre.setForeground(TEXTO_BLANCO);
        txtNombre.setCaretColor(DORADO);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNombre.setPreferredSize(dimCampo);
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));

        txtApellido = new JTextField();
        txtApellido.setBackground(FONDO_CAMPO);
        txtApellido.setForeground(TEXTO_BLANCO);
        txtApellido.setCaretColor(DORADO);
        txtApellido.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtApellido.setPreferredSize(dimCampo);
        txtApellido.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));

        txtEmail = new JTextField();
        txtEmail.setBackground(FONDO_CAMPO);
        txtEmail.setForeground(TEXTO_BLANCO);
        txtEmail.setCaretColor(DORADO);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtEmail.setPreferredSize(dimCampo);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));

        txtTelefono = new JTextField();
        txtTelefono.setBackground(FONDO_CAMPO);
        txtTelefono.setForeground(TEXTO_BLANCO);
        txtTelefono.setCaretColor(DORADO);
        txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTelefono.setPreferredSize(dimCampo);
        txtTelefono.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));

        txtFotoRuta = new JTextField();
        txtFotoRuta.setBackground(FONDO_CAMPO);
        txtFotoRuta.setForeground(TEXTO_BLANCO);
        txtFotoRuta.setCaretColor(DORADO);
        txtFotoRuta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtFotoRuta.setPreferredSize(dimCampo);
        txtFotoRuta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));

        jdFechaNac = new JDateChooser();
        jdFechaNac.setDateFormatString("yyyy-MM-dd");
        jdFechaNac.setPreferredSize(dimCampo);
        jdFechaNac.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(2, 2, 2, 2)));

        JPanel pId = new JPanel(new GridLayout(2, 1, 0, 4));
        pId.setOpaque(false);
        JLabel lId = new JLabel("ID");
        lId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lId.setForeground(TEXTO_GRIS);
        pId.add(lId);
        pId.add(txtIdCliente);

        GridBagConstraints gbcId = new GridBagConstraints();
        gbcId.fill = GridBagConstraints.HORIZONTAL;
        gbcId.insets = new Insets(6, 6, 6, 6);
        gbcId.gridx = 0;
        gbcId.gridy = 0;
        gbcId.weightx = 0.3;
        formPanel.add(pId, gbcId);

        JPanel pNombre = new JPanel(new GridLayout(2, 1, 0, 4));
        pNombre.setOpaque(false);
        JLabel lNombre = new JLabel("Nombre *");
        lNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lNombre.setForeground(TEXTO_GRIS);
        pNombre.add(lNombre);
        pNombre.add(txtNombre);

        GridBagConstraints gbcNombre = new GridBagConstraints();
        gbcNombre.fill = GridBagConstraints.HORIZONTAL;
        gbcNombre.insets = new Insets(6, 6, 6, 6);
        gbcNombre.gridx = 1;
        gbcNombre.gridy = 0;
        gbcNombre.weightx = 0.7;
        formPanel.add(pNombre, gbcNombre);

        JPanel pApellido = new JPanel(new GridLayout(2, 1, 0, 4));
        pApellido.setOpaque(false);
        JLabel lApellido = new JLabel("Apellido *");
        lApellido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lApellido.setForeground(TEXTO_GRIS);
        pApellido.add(lApellido);
        pApellido.add(txtApellido);

        GridBagConstraints gbcApellido = new GridBagConstraints();
        gbcApellido.fill = GridBagConstraints.HORIZONTAL;
        gbcApellido.insets = new Insets(6, 6, 6, 6);
        gbcApellido.gridx = 0;
        gbcApellido.gridy = 1;
        gbcApellido.weightx = 0.5;
        formPanel.add(pApellido, gbcApellido);

        JPanel pEmail = new JPanel(new GridLayout(2, 1, 0, 4));
        pEmail.setOpaque(false);
        JLabel lEmail = new JLabel("Correo electronico *");
        lEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lEmail.setForeground(TEXTO_GRIS);
        pEmail.add(lEmail);
        pEmail.add(txtEmail);

        GridBagConstraints gbcEmail = new GridBagConstraints();
        gbcEmail.fill = GridBagConstraints.HORIZONTAL;
        gbcEmail.insets = new Insets(6, 6, 6, 6);
        gbcEmail.gridx = 1;
        gbcEmail.gridy = 1;
        gbcEmail.weightx = 0.5;
        formPanel.add(pEmail, gbcEmail);

        JPanel pTelefono = new JPanel(new GridLayout(2, 1, 0, 4));
        pTelefono.setOpaque(false);
        JLabel lTelefono = new JLabel("Telefono");
        lTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lTelefono.setForeground(TEXTO_GRIS);
        pTelefono.add(lTelefono);
        pTelefono.add(txtTelefono);

        GridBagConstraints gbcTelefono = new GridBagConstraints();
        gbcTelefono.fill = GridBagConstraints.HORIZONTAL;
        gbcTelefono.insets = new Insets(6, 6, 6, 6);
        gbcTelefono.gridx = 0;
        gbcTelefono.gridy = 2;
        gbcTelefono.weightx = 0.5;
        formPanel.add(pTelefono, gbcTelefono);

        JPanel pFecha = new JPanel(new GridLayout(2, 1, 0, 4));
        pFecha.setOpaque(false);
        JLabel lFecha = new JLabel("Fecha de nacimiento");
        lFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lFecha.setForeground(TEXTO_GRIS);
        pFecha.add(lFecha);
        pFecha.add(jdFechaNac);

        GridBagConstraints gbcFecha = new GridBagConstraints();
        gbcFecha.fill = GridBagConstraints.HORIZONTAL;
        gbcFecha.insets = new Insets(6, 6, 6, 6);
        gbcFecha.gridx = 1;
        gbcFecha.gridy = 2;
        gbcFecha.weightx = 0.5;
        formPanel.add(pFecha, gbcFecha);

        JPanel pFoto = new JPanel(new GridLayout(2, 1, 0, 4));
        pFoto.setOpaque(false);
        JLabel lFoto = new JLabel("Ruta de foto");
        lFoto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lFoto.setForeground(TEXTO_GRIS);
        pFoto.add(lFoto);
        pFoto.add(txtFotoRuta);

        GridBagConstraints gbcFoto = new GridBagConstraints();
        gbcFoto.fill = GridBagConstraints.HORIZONTAL;
        gbcFoto.insets = new Insets(6, 6, 6, 6);
        gbcFoto.gridx = 0;
        gbcFoto.gridy = 3;
        gbcFoto.weightx = 1.0;
        gbcFoto.gridwidth = 2;
        formPanel.add(pFoto, gbcFoto);

        JLabel lblNota = new JLabel("* Campos obligatorios");
        lblNota.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblNota.setForeground(TEXTO_GRIS);

        GridBagConstraints gbcNota = new GridBagConstraints();
        gbcNota.fill = GridBagConstraints.HORIZONTAL;
        gbcNota.insets = new Insets(6, 6, 6, 6);
        gbcNota.gridx = 0;
        gbcNota.gridy = 4;
        gbcNota.weightx = 1.0;
        gbcNota.gridwidth = 2;
        formPanel.add(lblNota, gbcNota);

        centro.add(formPanel);
        contenido.add(centro, BorderLayout.CENTER);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminar.setBackground(ROJO);
        btnEliminar.setForeground(Color.GRAY);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEliminar.setMargin(new Insets(8, 18, 8, 18));

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLimpiar.setBackground(new Color(60, 60, 60));
        btnLimpiar.setForeground(Color.GRAY);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLimpiar.setMargin(new Insets(8, 18, 8, 18));

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setBackground(VERDE);
        btnGuardar.setForeground(Color.GRAY);
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

    public void setControlador(ActionListener listener) {
        btnBuscar.setActionCommand("BUSCAR");
        btnBuscar.addActionListener(listener);
        btnGuardar.setActionCommand("GUARDAR");
        btnGuardar.addActionListener(listener);
        btnEliminar.setActionCommand("ELIMINAR");
        btnEliminar.addActionListener(listener);
        btnLimpiar.setActionCommand("LIMPIAR");
        btnLimpiar.addActionListener(listener);
    }

    public String getIdBusqueda() {
        return txtBuscarId.getText().trim();
    }

    public Map<String, String> getDatosFormulario() {
        Map<String, String> datos = new HashMap<>();
        datos.put("id",       txtIdCliente.getText().trim());
        datos.put("nombre",   txtNombre.getText().trim());
        datos.put("apellido", txtApellido.getText().trim());
        datos.put("email",    txtEmail.getText().trim());
        datos.put("telefono", txtTelefono.getText().trim());
        datos.put("fotoRuta", txtFotoRuta.getText().trim());
        if (jdFechaNac.getDate() != null) {
            datos.put("fechaNac", new SimpleDateFormat("yyyy-MM-dd").format(jdFechaNac.getDate()));
        } else {
            datos.put("fechaNac", "");
        }
        return datos;
    }

    public void setDatosFormulario(Map<String, String> datos) {
        txtIdCliente.setText(datos.getOrDefault("id",       ""));
        txtNombre.setText(   datos.getOrDefault("nombre",   ""));
        txtApellido.setText( datos.getOrDefault("apellido", ""));
        txtEmail.setText(    datos.getOrDefault("email",    ""));
        txtTelefono.setText( datos.getOrDefault("telefono", ""));
        txtFotoRuta.setText( datos.getOrDefault("fotoRuta", ""));
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

    public void limpiar() {
        txtBuscarId.setText("");
        txtIdCliente.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtFotoRuta.setText("");
        jdFechaNac.setDate(null);
    }
}