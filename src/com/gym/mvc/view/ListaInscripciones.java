package com.gym.mvc.views;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class ListaInscripciones extends JFrame {

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
    private static final Color NARANJA        = new Color(200, 120, 0);

    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblTotal;

    public ListaInscripciones() {
        initComponents();
    }

    private void initComponents() {
        setTitle("PowerFit Gym — Inscripciones");
        setSize(860, 560);
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
        JLabel t = new JLabel("▸  INSCRIPCIONES");
        t.setFont(new Font("Segoe UI", Font.BOLD, 18)); t.setForeground(DORADO);
        JLabel s = new JLabel("Gestión de inscripciones de clientes a membresías");
        s.setFont(new Font("Segoe UI Light", Font.PLAIN, 12)); s.setForeground(TEXTO_GRIS);
        h.add(t); h.add(s);
        return h;
    }

    private JPanel crearContenido() {
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(FONDO_OSCURO);
        contenido.setBorder(new EmptyBorder(20, 32, 20, 32));

        JPanel barraTop = new JPanel(new BorderLayout(10, 0));
        barraTop.setOpaque(false);
        barraTop.setBorder(new EmptyBorder(0, 0, 14, 0));

        JPanel panelBusq = new JPanel(new BorderLayout(8, 0));
        panelBusq.setBackground(FONDO_PANEL);
        panelBusq.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(8, 12, 8, 12)
        ));
        JLabel iconoBusq = new JLabel("🔍");
        iconoBusq.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField txtBuscar = new JTextField();
        txtBuscar.setBackground(FONDO_PANEL); txtBuscar.setForeground(TEXTO_BLANCO);
        txtBuscar.setCaretColor(DORADO); txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createEmptyBorder());
        txtBuscar.setToolTipText("Buscar por ID cliente o ID inscripción");
        panelBusq.add(iconoBusq, BorderLayout.WEST);
        panelBusq.add(txtBuscar, BorderLayout.CENTER);

        JComboBox<String> cmbEstado = new JComboBox<String>(new String[]{"Todos", "activa", "vencida", "cancelada"});
        cmbEstado.setBackground(FONDO_CAMPO); cmbEstado.setForeground(TEXTO_BLANCO);
        cmbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmbEstado.setPreferredSize(new Dimension(130, 38));

        JButton btnNueva = new JButton("＋  Nueva Inscripción");
        btnNueva.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNueva.setBackground(DORADO); btnNueva.setForeground(Color.BLACK);
        btnNueva.setFocusPainted(false); btnNueva.setBorderPainted(false);
        btnNueva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNueva.setPreferredSize(new Dimension(170, 38));
        btnNueva.addActionListener(e -> abrirFormInscripcion());

        JPanel rightBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        rightBar.setOpaque(false);
        rightBar.add(cmbEstado);
        rightBar.add(btnNueva);

        barraTop.add(panelBusq, BorderLayout.CENTER);
        barraTop.add(rightBar, BorderLayout.EAST);

        String[] columnas = {"ID Inscripción", "ID Cliente", "Cliente", "ID Membresía", "Tipo Membresía", "Fecha Inicio", "Estado"};
        Object[][] datos = {
            {1, 1, "Ana González",    1, "Mensual",    "01/05/2026", "activa"},
            {2, 2, "Carlos Pérez",    2, "Trimestral", "15/03/2026", "activa"},
            {3, 3, "María Rodríguez", 1, "Mensual",    "01/04/2026", "vencida"},
            {4, 4, "Luis Vargas",     3, "Semestral",  "10/01/2026", "activa"},
            {5, 5, "Sara Solís",      4, "Anual",      "20/06/2025", "cancelada"},
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
                    if (col == 6) c.setForeground(estadoColor((String) getValueAt(row, 6)));
                    else c.setForeground(TEXTO_BLANCO);
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

        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(160);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(110);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(90);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(FONDO_TABLA);
        scroll.setBorder(BorderFactory.createLineBorder(SEPARADOR));

        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);
        pie.setBorder(new EmptyBorder(10, 0, 0, 0));

        lblTotal = new JLabel("Total: " + modelo.getRowCount() + " inscripciones");
        lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTotal.setForeground(TEXTO_GRIS);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        acciones.setOpaque(false);

        JButton btnPagar  = crearBtn("💰  Registrar Pago", new Color(50, 120, 60), Color.WHITE);
        JButton btnEditar = crearBtn("✏  Editar",          DORADO, Color.BLACK);
        JButton btnElim   = crearBtn("🗑  Eliminar",        ROJO_SUAVE, Color.WHITE);

        btnPagar.addActionListener(e -> {
            if (tabla.getSelectedRow() < 0) { JOptionPane.showMessageDialog(this, "Seleccione una inscripción."); return; }
            new RegistroPagos().setVisible(true);
        });
        btnEditar.addActionListener(e -> {
            if (tabla.getSelectedRow() < 0) { JOptionPane.showMessageDialog(this, "Seleccione una inscripción."); return; }
            abrirFormInscripcion();
        });
        btnElim.addActionListener(e -> {
            if (tabla.getSelectedRow() < 0) { JOptionPane.showMessageDialog(this, "Seleccione una inscripción."); return; }
            int r = JOptionPane.showConfirmDialog(this, "¿Eliminar la inscripción seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
            // TODO: InscripcionRepository.delete(id)
            if (r == JOptionPane.YES_OPTION) { modelo.removeRow(tabla.getSelectedRow()); lblTotal.setText("Total: " + modelo.getRowCount() + " inscripciones"); }
        });

        acciones.add(btnPagar); acciones.add(btnEditar); acciones.add(btnElim);
        pie.add(lblTotal, BorderLayout.WEST);
        pie.add(acciones, BorderLayout.EAST);

        contenido.add(barraTop, BorderLayout.NORTH);
        contenido.add(scroll, BorderLayout.CENTER);
        contenido.add(pie, BorderLayout.SOUTH);
        return contenido;
    }

    private Color estadoColor(String estado) {
        return switch (estado) {
            case "activa"    -> VERDE;
            case "vencida"   -> NARANJA;
            case "cancelada" -> ROJO_SUAVE;
            default -> TEXTO_BLANCO;
        };
    }

    private JButton crearBtn(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg); btn.setForeground(fg);
        btn.setFocusPainted(false); btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(155, 34));
        return btn;
    }

    private void abrirFormInscripcion() {
        JOptionPane.showMessageDialog(this, "Formulario de inscripción — conectar con InscripcionRepository.", "PowerFit Gym", JOptionPane.INFORMATION_MESSAGE);
    }
}
