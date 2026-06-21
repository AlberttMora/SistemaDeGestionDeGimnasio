package com.gym.mvc.views;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GestionClientes extends JFrame {

    private static final Color FONDO_OSCURO = new Color(18, 18, 18);
    private static final Color FONDO_PANEL  = new Color(28, 28, 28);
    private static final Color FONDO_CAMPO  = new Color(38, 38, 38);
    private static final Color DORADO       = new Color(212, 175, 55);
    private static final Color TEXTO_BLANCO = new Color(240, 240, 240);
    private static final Color TEXTO_GRIS   = new Color(160, 160, 160);
    private static final Color SEPARADOR    = new Color(55, 55, 55);
    private static final Color ROJO         = new Color(200, 60, 60);
    private static final Color VERDE        = new Color(60, 160, 80);

    private static final Font FUENTE_LABEL  = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font FUENTE_CAMPO  = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 18);
    private static final Font FUENTE_BTN    = new Font("Segoe UI", Font.BOLD, 12);

    private JTextField txtIdCliente, txtNombre, txtApellido, txtEmail, txtTelefono, txtFechaNac, txtFotoRuta;
    private JButton btnGuardar, btnBuscar, btnLimpiar, btnEliminar;

    public GestionClientes() {
        initComponents();
    }

    private void initComponents() {
        setTitle("PowerFit Gym — Gestión de Clientes");
        setSize(680, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(FONDO_OSCURO);
        setLayout(new BorderLayout());

        add(crearHeader("GESTIÓN DE CLIENTES", "Registrar, editar y eliminar clientes"), BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(FONDO_OSCURO);
        contenido.setBorder(new EmptyBorder(24, 32, 16, 32));

        JPanel panelBusqueda = new JPanel(new BorderLayout(10, 0));
        panelBusqueda.setBackground(FONDO_PANEL);
        panelBusqueda.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(12, 16, 12, 16)
        ));

        JLabel lblBuscar = new JLabel("Buscar por ID:");
        lblBuscar.setFont(FUENTE_LABEL);
        lblBuscar.setForeground(TEXTO_GRIS);
        lblBuscar.setPreferredSize(new Dimension(100, 36));

        txtIdCliente = crearCampo("ID del cliente...");
        btnBuscar = crearBoton("🔍  Buscar", DORADO, Color.BLACK);
        btnBuscar.setPreferredSize(new Dimension(120, 36));
        btnBuscar.addActionListener(e -> buscarCliente());

        panelBusqueda.add(lblBuscar, BorderLayout.WEST);
        panelBusqueda.add(txtIdCliente, BorderLayout.CENTER);
        panelBusqueda.add(btnBuscar, BorderLayout.EAST);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(FONDO_PANEL);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(20, 24, 20, 24)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 6, 6, 6);

        txtNombre    = crearCampo("Nombre del cliente");
        txtApellido  = crearCampo("Apellido del cliente");
        txtEmail     = crearCampo("correo@ejemplo.com");
        txtTelefono  = crearCampo("Ej: 8888-8888");
        txtFechaNac  = crearCampo("DD/MM/AAAA");
        txtFotoRuta  = crearCampo("Ruta de la foto (ej: /fotos/cliente.jpg)");

        gbc.gridx=0; gbc.gridy=0; gbc.weightx=0.5;
        formPanel.add(wrapLabel("Nombre *", txtNombre), gbc);
        gbc.gridx=1;
        formPanel.add(wrapLabel("Apellido *", txtApellido), gbc);

        gbc.gridx=0; gbc.gridy=1; gbc.gridwidth=2; gbc.weightx=1.0;
        formPanel.add(wrapLabel("Correo electrónico *", txtEmail), gbc);

        gbc.gridwidth=1;
        gbc.gridx=0; gbc.gridy=2; gbc.weightx=0.5;
        formPanel.add(wrapLabel("Teléfono", txtTelefono), gbc);
        gbc.gridx=1;
        formPanel.add(wrapLabel("Fecha de nacimiento", txtFechaNac), gbc);

        gbc.gridx=0; gbc.gridy=3; gbc.gridwidth=2; gbc.weightx=1.0;
        formPanel.add(wrapLabel("Ruta de foto (foto_ruta)", txtFotoRuta), gbc);

        gbc.gridy=4;
        JLabel lblNota = new JLabel("* Campos obligatorios");
        lblNota.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblNota.setForeground(TEXTO_GRIS);
        formPanel.add(lblNota, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(FONDO_OSCURO);
        panelBotones.setBorder(new EmptyBorder(16, 0, 8, 0));

        btnEliminar = crearBoton("🗑  Eliminar", ROJO, Color.WHITE);
        btnLimpiar  = crearBoton("↺  Limpiar",  new Color(60, 60, 60), TEXTO_GRIS);
        btnGuardar  = crearBoton("💾  Guardar",  VERDE, Color.WHITE);

        btnLimpiar.addActionListener(e -> limpiar());
        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnGuardar);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setOpaque(false);
        centro.add(panelBusqueda);
        centro.add(Box.createVerticalStrut(16));
        centro.add(formPanel);

        contenido.add(centro, BorderLayout.CENTER);
        contenido.add(panelBotones, BorderLayout.SOUTH);
        add(contenido, BorderLayout.CENTER);
    }


    private JPanel wrapLabel(String etiqueta, JComponent campo) {
        JPanel p = new JPanel(new GridLayout(2, 1, 0, 4));
        p.setOpaque(false);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(FUENTE_LABEL);
        lbl.setForeground(TEXTO_GRIS);
        p.add(lbl); p.add(campo);
        return p;
    }

    private JTextField crearCampo(String placeholder) {
        JTextField tf = new JTextField() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && !isFocusOwner()) {
                    g.setColor(new Color(100, 100, 100));
                    g.setFont(getFont().deriveFont(Font.ITALIC));
                    g.drawString(placeholder, 8, getHeight() / 2 + 5);
                }
            }
        };
        tf.setBackground(FONDO_CAMPO);
        tf.setForeground(TEXTO_BLANCO);
        tf.setCaretColor(DORADO);
        tf.setFont(FUENTE_CAMPO);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(6, 8, 6, 8)
        ));
        tf.setPreferredSize(new Dimension(0, 36));
        tf.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DORADO), new EmptyBorder(6, 8, 6, 8))); }
            @Override public void focusLost(FocusEvent e)   { tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8))); }
        });
        return tf;
    }

    private JButton crearBoton(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setFont(FUENTE_BTN);
        btn.setBackground(bg); btn.setForeground(fg);
        btn.setFocusPainted(false); btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 38));
        return btn;
    }

    private JPanel crearHeader(String titulo, String subtitulo) {
        JPanel h = new JPanel(new GridLayout(2, 1, 0, 4));
        h.setBackground(new Color(28, 28, 28));
        h.setBorder(new EmptyBorder(20, 32, 16, 32));
        JLabel t = new JLabel("▸  " + titulo);
        t.setFont(FUENTE_TITULO); t.setForeground(DORADO);
        JLabel s = new JLabel(subtitulo);
        s.setFont(new Font("Segoe UI Light", Font.PLAIN, 12)); s.setForeground(TEXTO_GRIS);
        h.add(t); h.add(s);
        return h;
    }


    private void buscarCliente() {
        String id = txtIdCliente.getText().trim();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese un ID.", "Campo requerido", JOptionPane.WARNING_MESSAGE); return; }
        JOptionPane.showMessageDialog(this, "Buscar cliente con ID: " + id + "\n(conectar con ClienteRepository.findById)", "PowerFit Gym", JOptionPane.INFORMATION_MESSAGE);
    }

    private void guardar() {
        if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos obligatorios.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Cliente guardado correctamente.", "PowerFit Gym", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminar() {
        String id = txtIdCliente.getText().trim();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(this, "Busque un cliente primero.", "Campo requerido", JOptionPane.WARNING_MESSAGE); return; }
        int r = JOptionPane.showConfirmDialog(this, "¿Eliminar cliente con ID " + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (r == JOptionPane.YES_OPTION)
            JOptionPane.showMessageDialog(this, "Cliente eliminado.", "PowerFit Gym", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiar() {
        txtIdCliente.setText(""); txtNombre.setText(""); txtApellido.setText("");
        txtEmail.setText(""); txtTelefono.setText(""); txtFechaNac.setText(""); txtFotoRuta.setText("");
    }
}
