package com.gym.mvc.views;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroPagos extends JFrame {

    private static final Color FONDO_OSCURO = new Color(18, 18, 18);
    private static final Color FONDO_PANEL  = new Color(28, 28, 28);
    private static final Color FONDO_CAMPO  = new Color(38, 38, 38);
    private static final Color DORADO       = new Color(212, 175, 55);
    private static final Color TEXTO_BLANCO = new Color(240, 240, 240);
    private static final Color TEXTO_GRIS   = new Color(160, 160, 160);
    private static final Color SEPARADOR    = new Color(55, 55, 55);
    private static final Color VERDE        = new Color(60, 160, 80);

   
    private JTextField txtIdInscripcion, txtNombreCliente, txtMonto, txtFechaPago;
    private JComboBox<String> cmbMetodoPago, cmbEstadoInscripcion;
    private JLabel lblResumen;

    public RegistroPagos() {
        initComponents();
    }

    private void initComponents() {
        setTitle("PowerFit Gym — Registro de Pagos");
        setSize(660, 620);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(FONDO_OSCURO);
        setLayout(new BorderLayout());

        add(crearHeader(), BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(FONDO_OSCURO);
        contenido.setBorder(new EmptyBorder(20, 32, 16, 32));

        JPanel panelInscripcion = crearSeccion("1.  Identificar Inscripción");
        panelInscripcion.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 6, 6, 6);

        txtIdInscripcion = crearCampo("ID de inscripción...");
        JButton btnBuscar = crearBoton("🔍  Buscar", DORADO, Color.BLACK);
        btnBuscar.setPreferredSize(new Dimension(110, 36));
        btnBuscar.addActionListener(e -> buscarInscripcion());

        txtNombreCliente = crearCampo("");
        txtNombreCliente.setEditable(false);
        txtNombreCliente.setBackground(new Color(25, 25, 25));
        txtNombreCliente.setForeground(DORADO);
        txtNombreCliente.setFont(new Font("Segoe UI", Font.BOLD, 13));

        gbc.gridx=0; gbc.gridy=0; gbc.weightx=0.8;
        panelInscripcion.add(wrapLabel("ID Inscripción:", txtIdInscripcion), gbc);
        gbc.gridx=1; gbc.weightx=0.2;
        JPanel wrapBtn = new JPanel(new BorderLayout());
        wrapBtn.setOpaque(false);
        JLabel espacio = new JLabel(" "); espacio.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        wrapBtn.add(espacio, BorderLayout.NORTH);
        wrapBtn.add(btnBuscar, BorderLayout.CENTER);
        panelInscripcion.add(wrapBtn, gbc);

        gbc.gridx=0; gbc.gridy=1; gbc.gridwidth=2; gbc.weightx=1.0;
        panelInscripcion.add(wrapLabel("Cliente asociado:", txtNombreCliente), gbc);

        JPanel panelPago = crearSeccion("2.  Datos del Pago  —  Pago(idInscripcion, monto, fechaPago, metodoPago)");
        panelPago.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.insets = new Insets(6, 6, 6, 6);

        txtMonto      = crearCampo("Monto en colones");
        txtFechaPago  = crearCampo("DD/MM/AAAA");
        txtFechaPago.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        cmbMetodoPago = crearCombo(new String[]{"Efectivo", "Tarjeta de débito", "Tarjeta de crédito", "SINPE Móvil"});

        gbc2.gridx=0; gbc2.gridy=0; gbc2.weightx=0.5;
        panelPago.add(wrapLabel("Monto (₡):", txtMonto), gbc2);
        gbc2.gridx=1;
        panelPago.add(wrapLabel("Fecha de pago:", txtFechaPago), gbc2);
        gbc2.gridx=0; gbc2.gridy=1; gbc2.gridwidth=2; gbc2.weightx=1.0;
        panelPago.add(wrapLabel("Método de pago:", cmbMetodoPago), gbc2);

        JPanel panelEstado = crearSeccion("3.  Estado de la Inscripción  —  Inscripcion(estado)");
        panelEstado.setLayout(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.insets = new Insets(6, 6, 6, 6);

        cmbEstadoInscripcion = crearCombo(new String[]{"activa", "vencida", "cancelada"});
        gbc3.gridx=0; gbc3.gridy=0; gbc3.weightx=1.0;
        panelEstado.add(wrapLabel("Estado de la inscripción:", cmbEstadoInscripcion), gbc3);

        JPanel panelResumen = new JPanel(new BorderLayout());
        panelResumen.setBackground(new Color(40, 35, 10));
        panelResumen.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(DORADO),
            new EmptyBorder(12, 16, 12, 16)
        ));
        lblResumen = new JLabel("<html><b style='color:#D4AF37'>Resumen:</b> Busque una inscripción y complete los datos del pago.</html>");
        lblResumen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblResumen.setForeground(TEXTO_BLANCO);
        panelResumen.add(lblResumen);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(FONDO_OSCURO);
        panelBotones.setBorder(new EmptyBorder(12, 0, 0, 0));

        JButton btnLimpiar   = crearBoton("↺  Limpiar",        new Color(55, 55, 55), TEXTO_GRIS);
        JButton btnRegistrar = crearBoton("💾  Registrar Pago", VERDE, Color.WHITE);
        btnRegistrar.setPreferredSize(new Dimension(160, 38));

        btnLimpiar.addActionListener(e -> limpiar());
        btnRegistrar.addActionListener(e -> registrar());
        txtMonto.addFocusListener(new FocusAdapter() { @Override public void focusLost(FocusEvent e) { actualizarResumen(); } });

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
    }


    private JPanel wrapLabel(String etiqueta, JComponent campo) {
        JPanel p = new JPanel(new GridLayout(2, 1, 0, 4));
        p.setOpaque(false);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setForeground(TEXTO_GRIS);
        p.add(lbl); p.add(campo);
        return p;
    }

    private JPanel crearSeccion(String titulo) {
        JPanel panel = new JPanel();
        panel.setBackground(FONDO_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(SEPARADOR), titulo,
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 11), DORADO),
            new EmptyBorder(10, 12, 12, 12)
        ));
        return panel;
    }

    private JTextField crearCampo(String ph) {
        JTextField tf = new JTextField();
        tf.setBackground(FONDO_CAMPO); tf.setForeground(TEXTO_BLANCO);
        tf.setCaretColor(DORADO); tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));
        tf.setPreferredSize(new Dimension(0, 36));
        tf.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DORADO), new EmptyBorder(6, 8, 6, 8))); }
            @Override public void focusLost(FocusEvent e)   { tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8))); }
        });
        return tf;
    }

    private JComboBox<String> crearCombo(String[] ops) {
        JComboBox<String> c = new JComboBox<String>(ops);
        c.setBackground(FONDO_CAMPO); c.setForeground(TEXTO_BLANCO);
        c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        c.setPreferredSize(new Dimension(0, 36));
        return c;
    }

    private JButton crearBoton(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg); btn.setForeground(fg);
        btn.setFocusPainted(false); btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130, 38));
        return btn;
    }

    private JPanel crearHeader() {
        JPanel h = new JPanel(new GridLayout(2, 1, 0, 4));
        h.setBackground(new Color(28, 28, 28));
        h.setBorder(new EmptyBorder(20, 32, 16, 32));
        JLabel t = new JLabel("▸  REGISTRO DE PAGOS");
        t.setFont(new Font("Segoe UI", Font.BOLD, 18)); t.setForeground(DORADO);
        JLabel s = new JLabel("Registrar pagos asociados a inscripciones de membresía");
        s.setFont(new Font("Segoe UI Light", Font.PLAIN, 12)); s.setForeground(TEXTO_GRIS);
        h.add(t); h.add(s);
        return h;
    }


    private void buscarInscripcion() {
        String id = txtIdInscripcion.getText().trim();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese un ID de inscripción."); return; }
        // TODO: InscripcionRepository.findById(Integer.parseInt(id)) → cargar cliente
        txtNombreCliente.setText("Ana González  [Membresía: Mensual]");
        actualizarResumen();
    }

    private void actualizarResumen() {
        String cliente = txtNombreCliente.getText().isEmpty() ? "—" : txtNombreCliente.getText();
        String monto   = txtMonto.getText().isEmpty() ? "—" : "₡" + txtMonto.getText();
        String metodo  = (String) cmbMetodoPago.getSelectedItem();
        lblResumen.setText("<html><b style='color:#D4AF37'>Resumen:</b>  Cliente: <b style='color:white'>"
            + cliente + "</b>  |  Monto: <b style='color:#D4AF37'>" + monto + "</b>  |  " + metodo + "</html>");
    }

    private void limpiar() {
        txtIdInscripcion.setText(""); txtNombreCliente.setText("");
        txtMonto.setText("");
        txtFechaPago.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        cmbMetodoPago.setSelectedIndex(0); cmbEstadoInscripcion.setSelectedIndex(0);
        lblResumen.setText("<html><b style='color:#D4AF37'>Resumen:</b> Busque una inscripción y complete los datos del pago.</html>");
    }

    private void registrar() {
        if (txtNombreCliente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Busque una inscripción válida primero.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtMonto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el monto del pago.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
       
        JOptionPane.showMessageDialog(this, "Pago registrado correctamente.", "PowerFit Gym", JOptionPane.INFORMATION_MESSAGE);
        limpiar();
    }
}
