package com.gym.mvc.views.cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ListaClientes extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color FONDO_OSCURO = new Color(18, 18, 18);
    private static final Color FONDO_PANEL  = new Color(28, 28, 28);
    private static final Color FONDO_TABLA  = new Color(30, 30, 30);
    private static final Color DORADO       = new Color(212, 175, 55);
    private static final Color TEXTO_BLANCO = new Color(240, 240, 240);
    private static final Color TEXTO_GRIS   = new Color(160, 160, 160);
    private static final Color SEPARADOR    = new Color(55, 55, 55);
    private static final Color ROJO_SUAVE   = new Color(180, 60, 60);

    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblTotal;
    private JTextField txtBuscar;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnElim;

    public ListaClientes() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(FONDO_OSCURO);

        // --- HEADER ---
        JPanel panelHeader = new JPanel(new GridLayout(2, 1, 0, 4));
        panelHeader.setBackground(FONDO_PANEL);
        panelHeader.setBorder(new EmptyBorder(20, 32, 16, 32));

        JLabel lblTitulo = new JLabel("LISTA DE CLIENTES");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(DORADO);

        JLabel lblSubtitulo = new JLabel("Consulta y busqueda de clientes registrados");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(TEXTO_GRIS);

        panelHeader.add(lblTitulo);
        panelHeader.add(lblSubtitulo);
        add(panelHeader, BorderLayout.NORTH);

        // --- CONTENIDO ---
        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(FONDO_OSCURO);
        contenido.setBorder(new EmptyBorder(20, 32, 20, 32));

        // --- BARRA SUPERIOR ---
        JPanel barraTop = new JPanel(new BorderLayout(10, 0));
        barraTop.setOpaque(false);
        barraTop.setBorder(new EmptyBorder(0, 0, 14, 0));

        JPanel panelBusq = new JPanel(new BorderLayout(8, 0));
        panelBusq.setBackground(FONDO_PANEL);
        panelBusq.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(8, 12, 8, 12)
        ));

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblBuscar.setForeground(TEXTO_GRIS);

        txtBuscar = new JTextField();
        txtBuscar.setBackground(FONDO_PANEL);
        txtBuscar.setForeground(TEXTO_BLANCO);
        txtBuscar.setCaretColor(DORADO);
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createEmptyBorder());
        txtBuscar.setToolTipText("Buscar por nombre, apellido o email");

        panelBusq.add(lblBuscar, BorderLayout.WEST);
        panelBusq.add(txtBuscar, BorderLayout.CENTER);

        btnNuevo = new JButton("+ Nuevo Cliente");
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevo.setBackground(DORADO);
        btnNuevo.setForeground(Color.BLACK);
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBorderPainted(false);
        btnNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNuevo.setPreferredSize(new Dimension(150, 38));

        JPanel rightBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        rightBar.setOpaque(false);
        rightBar.add(btnNuevo);

        barraTop.add(panelBusq, BorderLayout.CENTER);
        barraTop.add(rightBar, BorderLayout.EAST);

        // --- TABLA ---
        String[] columnas = {"ID", "Nombre", "Apellido", "Email", "Telefono", "Fecha Nac.", "Foto"};
        modelo = new DefaultTableModel(null, columnas) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setBackground(FONDO_TABLA);
        tabla.setForeground(TEXTO_BLANCO);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(34);
        tabla.setGridColor(SEPARADOR);
        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(false);
        tabla.setIntercellSpacing(new Dimension(0, 1));
        tabla.setSelectionBackground(new Color(60, 50, 20));
        tabla.setSelectionForeground(DORADO);

        JTableHeader tableHeader = tabla.getTableHeader();
        tableHeader.setBackground(new Color(40, 40, 40));
        tableHeader.setForeground(DORADO);
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, DORADO));
        tableHeader.setReorderingAllowed(false);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(110);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(180);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(140);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(FONDO_TABLA);
        scroll.setBorder(BorderFactory.createLineBorder(SEPARADOR));

        // --- PIE ---
        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);
        pie.setBorder(new EmptyBorder(10, 0, 0, 0));

        lblTotal = new JLabel("Total de clientes: 0");
        lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTotal.setForeground(TEXTO_GRIS);

        btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEditar.setBackground(DORADO);
        btnEditar.setForeground(Color.BLACK);
        btnEditar.setFocusPainted(false);
        btnEditar.setBorderPainted(false);
        btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEditar.setPreferredSize(new Dimension(130, 34));

        btnElim = new JButton("Eliminar");
        btnElim.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnElim.setBackground(ROJO_SUAVE);
        btnElim.setForeground(Color.BLACK);
        btnElim.setFocusPainted(false);
        btnElim.setBorderPainted(false);
        btnElim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnElim.setPreferredSize(new Dimension(130, 34));

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        acciones.setOpaque(false);
        acciones.add(btnEditar);
        acciones.add(btnElim);

        pie.add(lblTotal, BorderLayout.WEST);
        pie.add(acciones, BorderLayout.EAST);

        contenido.add(barraTop, BorderLayout.NORTH);
        contenido.add(scroll,   BorderLayout.CENTER);
        contenido.add(pie,      BorderLayout.SOUTH);

        add(contenido, BorderLayout.CENTER);
    }

    public void setControlador(ActionListener listener) {
        btnNuevo.setActionCommand("NUEVO");
        btnNuevo.addActionListener(listener);
        btnEditar.setActionCommand("EDITAR");
        btnEditar.addActionListener(listener);
        btnElim.setActionCommand("ELIMINAR");
        btnElim.addActionListener(listener);
    }

    public void setEscuchadorBusqueda(KeyListener listener) {
        txtBuscar.addKeyListener(listener);
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTable getTabla() {
        return tabla;
    }

    public int getFilaSeleccionada() {
        return tabla.getSelectedRow();
    }

    public String getTextoBusqueda() {
        return txtBuscar.getText().trim();
    }

    public void actualizarTotal() {
        lblTotal.setText("Total de clientes: " + modelo.getRowCount());
    }
}