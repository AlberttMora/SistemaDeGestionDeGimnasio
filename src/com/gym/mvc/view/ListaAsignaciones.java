package com.gym.mvc.viewsimport javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class ListaAsignaciones extends JFrame {

    private static final Color FONDO_OSCURO   = new Color(18, 18, 18);
    private static final Color FONDO_PANEL    = new Color(28, 28, 28);
    private static final Color FONDO_CAMPO    = new Color(38, 38, 38);
    private static final Color FONDO_TABLA    = new Color(30, 30, 30);
    private static final Color FONDO_FILA_ALT = new Color(34, 34, 34);
    private static final Color DORADO         = new Color(212, 175, 55);
    private static final Color TEXTO_BLANCO   = new Color(240, 240, 240);
    private static final Color TEXTO_GRIS     = new Color(160, 160, 160);
    private static final Color SEPARADOR      = new Color(55, 55, 55);
    private static final Color VERDE          = new Color(60, 160, 80);
    private static final Color ROJO_SUAVE     = new Color(180, 60, 60);

    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblTotal;

    private JTextField txtIdEntrenador, txtIdCliente, txtFechaInicio, txtFechaFin, txtObservaciones;

    public ListaAsignaciones() {
        initComponents();
    }

    private void initComponents() {
        setTitle("PowerFit Gym — Asignación de Entrenadores");
        setSize(960, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(FONDO_OSCURO);
        setLayout(new BorderLayout());

        add(crearHeader(), BorderLayout.NORTH);
        add(crearContenido(), BorderLayout.CENTER);
    }

    private JPanel crearHeader() {
        JPanel h = new JPanel(new GridLayout(2, 1, 0, 4));
        h.setBackground(new Color(28, 28, 28));
        h.setBorder(new EmptyBorder(20, 32, 16, 32));
        JLabel t = new JLabel("▸  ASIGNACIÓN DE ENTRENADORES");
        t.setFont(new Font("Segoe UI", Font.BOLD, 18)); t.setForeground(DORADO);
        JLabel s = new JLabel("Asignar entrenadores a clientes y gestionar asignaciones activas");
        s.setFont(new Font("Segoe UI Light", Font.PLAIN, 12)); s.setForeground(TEXTO_GRIS);
        h.add(t); h.add(s);
        return h;
    }

    private JPanel crearContenido() {
        JPanel contenido = new JPanel(new BorderLayout(0, 0));
        contenido.setBackground(FONDO_OSCURO);
        contenido.setBorder(new EmptyBorder(20, 32, 20, 32));

        JPanel superior = new JPanel(new BorderLayout(16, 0));
        superior.setOpaque(false);
        superior.setBorder(new EmptyBorder(0, 0, 16, 0));

      
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(FONDO_PANEL);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(BorderFactory.createLineBorder(SEPARADOR),
                "Nueva Asignación  —  AsignacionEntrenador(idEntrenador, idCliente, fechaInicio, fechaFin, observaciones)",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 11), DORADO),
            new EmptyBorder(10, 14, 14, 14)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 6, 6, 6);

        txtIdEntrenador  = crearCampo("ID del entrenador");
        txtIdCliente     = crearCampo("ID del cliente");
        txtFechaInicio   = crearCampo("DD/MM/AAAA");
        txtFechaFin      = crearCampo("DD/MM/AAAA");
        txtObservaciones = crearCampo("Observaciones opcionales...");

        gbc.gridx=0; gbc.gridy=0; gbc.weightx=0.5;
        formPanel.add(wrapLabel("ID Entrenador *", txtIdEntrenador), gbc);
        gbc.gridx=1;
        formPanel.add(wrapLabel("ID Cliente *", txtIdCliente), gbc);

        gbc.gridx=0; gbc.gridy=1;
        formPanel.add(wrapLabel("Fecha inicio *", txtFechaInicio), gbc);
        gbc.gridx=1;
        formPanel.add(wrapLabel("Fecha fin", txtFechaFin), gbc);

        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=2; gbc.weightx=1.0;
        formPanel.add(wrapLabel("Observaciones", txtObservaciones), gbc);

        gbc.gridy=3; gbc.gridwidth=2;
        JPanel btnForm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnForm.setOpaque(false);
        JButton btnLimpiar = crearBtn("↺  Limpiar", new Color(55, 55, 55), TEXTO_GRIS);
        JButton btnGuardar = crearBtn("💾  Asignar", VERDE, Color.WHITE);
        btnLimpiar.addActionListener(e -> limpiarForm());
        btnGuardar.addActionListener(e -> guardarAsignacion());
        btnForm.add(btnLimpiar); btnForm.add(btnGuardar);
        formPanel.add(btnForm, gbc);

        superior.add(formPanel, BorderLayout.CENTER);

        
        String[] columnas = {"ID", "ID Entrenador", "Entrenador", "ID Cliente", "Cliente", "Fecha Inicio", "Fecha Fin", "Observaciones"};
        Object[][] datos = {
            {1, 3, "Diego Rojas",   1, "Ana González",    "01/04/2026", "01/07/2026", "Plan de fuerza"},
            {2, 3, "Diego Rojas",   2, "Carlos Pérez",    "15/04/2026", "15/07/2026", "Pérdida de peso"},
            {3, 4, "Marcos Solís",  3, "María Rodríguez", "01/05/2026", "01/08/2026", "Cardio avanzado"},
            {4, 4, "Marcos Solís",  5, "Sara Solís",      "10/05/2026", "10/08/2026", ""},
        };

        modelo = new DefaultTableModel(datos, columnas) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabla = new JTable(modelo) {
            @Override public Component prepareRenderer(TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);
                if (isRowSelected(row)) {
                    c.setBackground(new Color(60, 50, 20)); c.setForeground(DORADO);
                } else {
                    c.setBackground(row % 2 == 0 ? FONDO_TABLA : FONDO_FILA_ALT);
                    c.setForeground(TEXTO_BLANCO);
                }
                return c;
            }
        };

        tabla.setBackground(FONDO_TABLA);
        tabla.setForeground(TEXTO_BLANCO);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(34);
        tabla.setGridColor(SEPARADOR);
        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(false);
        tabla.setIntercellSpacing(new Dimension(0, 1));

        JTableHeader th = tabla.getTableHeader();
        th.setBackground(new Color(40, 40, 40)); th.setForeground(DORADO);
        th.setFont(new Font("Segoe UI", Font.BOLD, 12));
        th.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DORADO));
        th.setReorderingAllowed(false);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(160);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(FONDO_TABLA);
        scroll.setBorder(BorderFactory.createLineBorder(SEPARADOR));

        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);
        pie.setBorder(new EmptyBorder(10, 0, 0, 0));

        lblTotal = new JLabel("Total: " + modelo.getRowCount() + " asignaciones");
        lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTotal.setForeground(TEXTO_GRIS);

        JPanel accionesTabla = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        accionesTabla.setOpaque(false);

        JButton btnElim = crearBtn("🗑  Eliminar", ROJO_SUAVE, Color.WHITE);
        btnElim.addActionListener(e -> {
            if (tabla.getSelectedRow() < 0) { JOptionPane.showMessageDialog(this, "Seleccione una asignación."); return; }
            int r = JOptionPane.showConfirmDialog(this, "¿Eliminar la asignación seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
            // TODO: AsignacionRepository.delete(id)
            if (r == JOptionPane.YES_OPTION) { modelo.removeRow(tabla.getSelectedRow()); lblTotal.setText("Total: " + modelo.getRowCount() + " asignaciones"); }
        });

        accionesTabla.add(btnElim);
        pie.add(lblTotal, BorderLayout.WEST);
        pie.add(accionesTabla, BorderLayout.EAST);

        JPanel tablaPanel = new JPanel(new BorderLayout());
        tablaPanel.setOpaque(false);
        tablaPanel.add(scroll, BorderLayout.CENTER);
        tablaPanel.add(pie, BorderLayout.SOUTH);

        contenido.add(superior, BorderLayout.NORTH);
        contenido.add(tablaPanel, BorderLayout.CENTER);
        return contenido;
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

    private JTextField crearCampo(String ph) {
        JTextField tf = new JTextField() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && !isFocusOwner()) {
                    g.setColor(new Color(100, 100, 100));
                    g.setFont(getFont().deriveFont(Font.ITALIC));
                    g.drawString(ph, 8, getHeight() / 2 + 5);
                }
            }
        };
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

    private JButton crearBtn(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg); btn.setForeground(fg);
        btn.setFocusPainted(false); btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130, 36));
        return btn;
    }


    private void limpiarForm() {
        txtIdEntrenador.setText(""); txtIdCliente.setText("");
        txtFechaInicio.setText(""); txtFechaFin.setText(""); txtObservaciones.setText("");
    }

    private void guardarAsignacion() {
        if (txtIdEntrenador.getText().trim().isEmpty() || txtIdCliente.getText().trim().isEmpty() || txtFechaInicio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete los campos obligatorios (*).", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Asignación guardada correctamente.", "PowerFit Gym", JOptionPane.INFORMATION_MESSAGE);
        limpiarForm();
    }
}
