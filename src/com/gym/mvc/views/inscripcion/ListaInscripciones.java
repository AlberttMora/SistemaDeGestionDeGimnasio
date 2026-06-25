package com.gym.mvc.views.inscripcion;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class ListaInscripciones extends JFrame {

    private static final long serialVersionUID = 1L;

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
    private JTextField txtBuscar;
    private JComboBox<String> cmbEstado;
    private JButton btnNueva;
    private JButton btnPagar;
    private JButton btnEditar;
    private JButton btnElim;

    public ListaInscripciones() {
        initComponents();
    }

    private void initComponents() {
        setTitle("PowerFit Gym — Inscripciones");
        setSize(860, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(FONDO_OSCURO);
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(crearHeader(), BorderLayout.NORTH);
        getContentPane().add(crearContenido(), BorderLayout.CENTER);
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
        iconoBusq.setForeground(Color.GRAY);
        iconoBusq.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        txtBuscar = new JTextField();
        txtBuscar.setBackground(FONDO_PANEL); txtBuscar.setForeground(TEXTO_BLANCO);
        txtBuscar.setCaretColor(DORADO); txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createEmptyBorder());
        txtBuscar.setToolTipText("Buscar por ID cliente o ID inscripción");
        panelBusq.add(iconoBusq, BorderLayout.WEST);
        panelBusq.add(txtBuscar, BorderLayout.CENTER);

        String[] estados = new String[]{"Todos", "activa", "vencida", "cancelada"};
        cmbEstado = new JComboBox<>();
        cmbEstado.setModel(new DefaultComboBoxModel<>(estados));
        cmbEstado.setBackground(FONDO_CAMPO); cmbEstado.setForeground(Color.BLACK);
        cmbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmbEstado.setPreferredSize(new Dimension(130, 38));

        btnNueva = new JButton("＋  Nueva Inscripción");
        btnNueva.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNueva.setBackground(DORADO); btnNueva.setForeground(Color.BLACK);
        btnNueva.setFocusPainted(false); btnNueva.setBorderPainted(false);
        btnNueva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNueva.setPreferredSize(new Dimension(170, 38));

        JPanel rightBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        rightBar.setOpaque(false);
        rightBar.add(cmbEstado);
        rightBar.add(btnNueva);

        barraTop.add(panelBusq, BorderLayout.CENTER);
        barraTop.add(rightBar, BorderLayout.EAST);

        String[] columnas = {"ID Inscripción", "ID Cliente", "Cliente", "ID Membresía", "Tipo Membresía", "Fecha Inicio", "Estado"};
        
        modelo = new DefaultTableModel(null, columnas) {
            private static final long serialVersionUID = 1L;
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabla = new JTable(modelo) {
            private static final long serialVersionUID = 1L;
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

        lblTotal = new JLabel("Total: 0 inscripciones");
        lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTotal.setForeground(TEXTO_GRIS);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        acciones.setOpaque(false);

        btnPagar  = crearBtn("💰  Registrar Pago", new Color(50, 120, 60), Color.WHITE);
        btnEditar = crearBtn("📝  Editar",          DORADO, Color.BLACK);
        btnElim   = crearBtn("🗑  Eliminar",        ROJO_SUAVE, Color.WHITE);

        acciones.add(btnPagar); acciones.add(btnEditar); acciones.add(btnElim);
        pie.add(lblTotal, BorderLayout.WEST);
        pie.add(acciones, BorderLayout.EAST);

        contenido.add(barraTop, BorderLayout.NORTH);
        contenido.add(scroll, BorderLayout.CENTER);
        contenido.add(pie, BorderLayout.SOUTH);
        return contenido;
    }

    private Color estadoColor(String estado) {
         switch (estado) {
            case "activa": return VERDE;
            case "vencida": return NARANJA;
            case "cancelada": return ROJO_SUAVE;
            default: return TEXTO_BLANCO;
        }
    }

    private JButton crearBtn(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg); btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false); btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(155, 34));
        return btn;
    }

    public JButton getBtnNueva() { return btnNueva; }
    public JButton getBtnPagar() { return btnPagar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnEliminar() { return btnElim; }
    public JComboBox<String> getCmbEstado() { return cmbEstado; }
    public JTextField getTxtBuscar() { return txtBuscar; }
    public DefaultTableModel getModelo() { return modelo; }
    public JTable getTabla() { return tabla; }

    public int getFilaSeleccionada() { return tabla.getSelectedRow(); }
    public String getTextoBusqueda() { return txtBuscar.getText().trim(); }
    public String getEstadoSeleccionado() { return cmbEstado.getSelectedItem().toString(); }

    public void actualizarTotal(int total) {
        lblTotal.setText("Total: " + total + " inscripciones");
    }
}