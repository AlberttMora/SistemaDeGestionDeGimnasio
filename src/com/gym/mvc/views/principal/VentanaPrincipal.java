package com.gym.mvc.views.principal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color FONDO_OSCURO  = new Color(18, 18, 18);
    private static final Color FONDO_PANEL   = new Color(28, 28, 28);
    private static final Color FONDO_TARJETA = new Color(38, 38, 38);
    private static final Color DORADO        = new Color(212, 175, 55);
    private static final Color TEXTO_GRIS    = new Color(160, 160, 160);
    private static final Color SEPARADOR     = new Color(55, 55, 55);

    private CardLayout navegador;
    private JPanel contenedorCentral;

    private JPanel tarjetaClientes;
    private JPanel tarjetaPagos;
    private JPanel tarjetaRendimiento;
    private JPanel tarjetaPersonal;
    private JPanel tarjetaInventario;
    private JPanel tarjetaOperaciones;
    private JPanel tarjetaProgreso;

    private JButton btnInicio;

    public VentanaPrincipal() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("PowerFit Gym - Sistema de Gestion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(FONDO_OSCURO);
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(FONDO_PANEL);
        header.setBorder(new EmptyBorder(24, 40, 24, 40));

        JPanel headerLeft = new JPanel(new GridLayout(2, 1, 0, 4));
        headerLeft.setOpaque(false);

        JLabel lblNombre = new JLabel("POWERFIT GYM");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblNombre.setForeground(DORADO);

        JLabel lblSub = new JLabel("Cuida tu mente, Ama tu cuerpo");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(TEXTO_GRIS);

        headerLeft.add(lblNombre);
        headerLeft.add(lblSub);

        btnInicio = new JButton("Inicio");
        btnInicio.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnInicio.setBackground(FONDO_TARJETA);
        btnInicio.setForeground(DORADO);
        btnInicio.setFocusPainted(false);
        btnInicio.setBorder(BorderFactory.createLineBorder(SEPARADOR));
        btnInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnInicio.setPreferredSize(new Dimension(100, 36));

        JPanel headerRight = new JPanel();
        headerRight.setOpaque(false);
        headerRight.add(btnInicio);

        header.add(headerLeft, BorderLayout.WEST);
        header.add(headerRight, BorderLayout.EAST);

        JPanel lineaDorada = new JPanel();
        lineaDorada.setBackground(DORADO);
        lineaDorada.setPreferredSize(new Dimension(0, 2));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(false);
        headerWrapper.add(header, BorderLayout.CENTER);
        headerWrapper.add(lineaDorada, BorderLayout.SOUTH);

        add(headerWrapper, BorderLayout.NORTH);

        navegador = new CardLayout();
        contenedorCentral = new JPanel(navegador);
        contenedorCentral.setOpaque(false);

        JPanel vistaMenu = new JPanel(new BorderLayout());
        vistaMenu.setOpaque(false);
        vistaMenu.setBorder(new EmptyBorder(30, 40, 20, 40));

        JLabel lblMenu = new JLabel("MODULOS DEL SISTEMA");
        lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblMenu.setForeground(TEXTO_GRIS);
        lblMenu.setBorder(new EmptyBorder(0, 0, 16, 0));

        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);

        tarjetaClientes = new JPanel();
        tarjetaClientes.setLayout(new BoxLayout(tarjetaClientes, BoxLayout.Y_AXIS));
        tarjetaClientes.setBackground(FONDO_TARJETA);
        tarjetaClientes.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR, 1),
            new EmptyBorder(20, 16, 20, 16)
        ));
        tarjetaClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel iconoClientes = new JLabel("GC", SwingConstants.CENTER);
        iconoClientes.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        iconoClientes.setForeground(DORADO);
        iconoClientes.setAlignmentX(CENTER_ALIGNMENT);

        JLabel tituloClientes = new JLabel("Modulo de Clientes", SwingConstants.CENTER);
        tituloClientes.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tituloClientes.setForeground(DORADO);
        tituloClientes.setAlignmentX(CENTER_ALIGNMENT);

        JLabel descClientes = new JLabel("<html><div style='text-align:center'>Registro y control de usuarios</div></html>", SwingConstants.CENTER);
        descClientes.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descClientes.setForeground(TEXTO_GRIS);
        descClientes.setAlignmentX(CENTER_ALIGNMENT);

        tarjetaClientes.add(Box.createVerticalGlue());
        tarjetaClientes.add(iconoClientes);
        tarjetaClientes.add(Box.createVerticalStrut(8));
        tarjetaClientes.add(tituloClientes);
        tarjetaClientes.add(Box.createVerticalStrut(4));
        tarjetaClientes.add(descClientes);
        tarjetaClientes.add(Box.createVerticalGlue());

        tarjetaPagos = new JPanel();
        tarjetaPagos.setLayout(new BoxLayout(tarjetaPagos, BoxLayout.Y_AXIS));
        tarjetaPagos.setBackground(FONDO_TARJETA);
        tarjetaPagos.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR, 1),
            new EmptyBorder(20, 16, 20, 16)
        ));
        tarjetaPagos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel iconoPagos = new JLabel("RP", SwingConstants.CENTER);
        iconoPagos.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        iconoPagos.setForeground(DORADO);
        iconoPagos.setAlignmentX(CENTER_ALIGNMENT);

        JLabel tituloPagos = new JLabel("Pagos y Membresias", SwingConstants.CENTER);
        tituloPagos.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tituloPagos.setForeground(DORADO);
        tituloPagos.setAlignmentX(CENTER_ALIGNMENT);

        JLabel descPagos = new JLabel("<html><div style='text-align:center'>Inscripciones y recibos de caja</div></html>", SwingConstants.CENTER);
        descPagos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descPagos.setForeground(TEXTO_GRIS);
        descPagos.setAlignmentX(CENTER_ALIGNMENT);

        tarjetaPagos.add(Box.createVerticalGlue());
        tarjetaPagos.add(iconoPagos);
        tarjetaPagos.add(Box.createVerticalStrut(8));
        tarjetaPagos.add(tituloPagos);
        tarjetaPagos.add(Box.createVerticalStrut(4));
        tarjetaPagos.add(descPagos);
        tarjetaPagos.add(Box.createVerticalGlue());

        tarjetaRendimiento = new JPanel();
        tarjetaRendimiento.setLayout(new BoxLayout(tarjetaRendimiento, BoxLayout.Y_AXIS));
        tarjetaRendimiento.setBackground(FONDO_TARJETA);
        tarjetaRendimiento.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR, 1),
            new EmptyBorder(20, 16, 20, 16)
        ));
        tarjetaRendimiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel iconoRendimiento = new JLabel("SR", SwingConstants.CENTER);
        iconoRendimiento.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        iconoRendimiento.setForeground(DORADO);
        iconoRendimiento.setAlignmentX(CENTER_ALIGNMENT);

        JLabel tituloRendimiento = new JLabel("Salud y Rendimiento", SwingConstants.CENTER);
        tituloRendimiento.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tituloRendimiento.setForeground(DORADO);
        tituloRendimiento.setAlignmentX(CENTER_ALIGNMENT);

        JLabel descRendimiento = new JLabel("<html><div style='text-align:center'>Asignaciones y metricas de peso</div></html>", SwingConstants.CENTER);
        descRendimiento.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descRendimiento.setForeground(TEXTO_GRIS);
        descRendimiento.setAlignmentX(CENTER_ALIGNMENT);

        tarjetaRendimiento.add(Box.createVerticalGlue());
        tarjetaRendimiento.add(iconoRendimiento);
        tarjetaRendimiento.add(Box.createVerticalStrut(8));
        tarjetaRendimiento.add(tituloRendimiento);
        tarjetaRendimiento.add(Box.createVerticalStrut(4));
        tarjetaRendimiento.add(descRendimiento);
        tarjetaRendimiento.add(Box.createVerticalGlue());

        tarjetaProgreso = new JPanel();
        tarjetaProgreso.setLayout(new BoxLayout(tarjetaProgreso, BoxLayout.Y_AXIS));
        tarjetaProgreso.setBackground(FONDO_TARJETA);
        tarjetaProgreso.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR, 1),
            new EmptyBorder(20, 16, 20, 16)
        ));
        tarjetaProgreso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel iconoProgreso = new JLabel("CP", SwingConstants.CENTER);
        iconoProgreso.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        iconoProgreso.setForeground(DORADO);
        iconoProgreso.setAlignmentX(CENTER_ALIGNMENT);

        JLabel tituloProgreso = new JLabel("Control de Progreso", SwingConstants.CENTER);
        tituloProgreso.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tituloProgreso.setForeground(DORADO);
        tituloProgreso.setAlignmentX(CENTER_ALIGNMENT);

        JLabel descProgreso = new JLabel("<html><div style='text-align:center'>Registro de metricas de peso</div></html>", SwingConstants.CENTER);
        descProgreso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descProgreso.setForeground(TEXTO_GRIS);
        descProgreso.setAlignmentX(CENTER_ALIGNMENT);

        tarjetaProgreso.add(Box.createVerticalGlue());
        tarjetaProgreso.add(iconoProgreso);
        tarjetaProgreso.add(Box.createVerticalStrut(8));
        tarjetaProgreso.add(tituloProgreso);
        tarjetaProgreso.add(Box.createVerticalStrut(4));
        tarjetaProgreso.add(descProgreso);
        tarjetaProgreso.add(Box.createVerticalGlue());

        grid.add(tarjetaProgreso);
        
        tarjetaPersonal = new JPanel();
        tarjetaPersonal.setLayout(new BoxLayout(tarjetaPersonal, BoxLayout.Y_AXIS));
        tarjetaPersonal.setBackground(FONDO_TARJETA);
        tarjetaPersonal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR, 1),
            new EmptyBorder(20, 16, 20, 16)
        ));
        tarjetaPersonal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel iconoPersonal = new JLabel("GP", SwingConstants.CENTER);
        iconoPersonal.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        iconoPersonal.setForeground(DORADO);
        iconoPersonal.setAlignmentX(CENTER_ALIGNMENT);

        JLabel tituloPersonal = new JLabel("Gestion de Personal", SwingConstants.CENTER);
        tituloPersonal.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tituloPersonal.setForeground(DORADO);
        tituloPersonal.setAlignmentX(CENTER_ALIGNMENT);

        JLabel descPersonal = new JLabel("<html><div style='text-align:center'>Control de instructores y accesos</div></html>", SwingConstants.CENTER);
        descPersonal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descPersonal.setForeground(TEXTO_GRIS);
        descPersonal.setAlignmentX(CENTER_ALIGNMENT);

        tarjetaPersonal.add(Box.createVerticalGlue());
        tarjetaPersonal.add(iconoPersonal);
        tarjetaPersonal.add(Box.createVerticalStrut(8));
        tarjetaPersonal.add(tituloPersonal);
        tarjetaPersonal.add(Box.createVerticalStrut(4));
        tarjetaPersonal.add(descPersonal);
        tarjetaPersonal.add(Box.createVerticalGlue());

        tarjetaInventario = new JPanel();
        tarjetaInventario.setLayout(new BoxLayout(tarjetaInventario, BoxLayout.Y_AXIS));
        tarjetaInventario.setBackground(FONDO_TARJETA);
        tarjetaInventario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR, 1),
            new EmptyBorder(20, 16, 20, 16)
        ));
        tarjetaInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel iconoInventario = new JLabel("EQ", SwingConstants.CENTER);
        iconoInventario.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        iconoInventario.setForeground(DORADO);
        iconoInventario.setAlignmentX(CENTER_ALIGNMENT);

        JLabel tituloInventario = new JLabel("Equipos e Inventarios", SwingConstants.CENTER);
        tituloInventario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tituloInventario.setForeground(DORADO);
        tituloInventario.setAlignmentX(CENTER_ALIGNMENT);

        JLabel descInventario = new JLabel("<html><div style='text-align:center'>Catalogo de maquinas activos</div></html>", SwingConstants.CENTER);
        descInventario.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descInventario.setForeground(TEXTO_GRIS);
        descInventario.setAlignmentX(CENTER_ALIGNMENT);

        tarjetaInventario.add(Box.createVerticalGlue());
        tarjetaInventario.add(iconoInventario);
        tarjetaInventario.add(Box.createVerticalStrut(8));
        tarjetaInventario.add(tituloInventario);
        tarjetaInventario.add(Box.createVerticalStrut(4));
        tarjetaInventario.add(descInventario);
        tarjetaInventario.add(Box.createVerticalGlue());

        tarjetaOperaciones = new JPanel();
        tarjetaOperaciones.setLayout(new BoxLayout(tarjetaOperaciones, BoxLayout.Y_AXIS));
        tarjetaOperaciones.setBackground(FONDO_TARJETA);
        tarjetaOperaciones.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR, 1),
            new EmptyBorder(20, 16, 20, 16)
        ));
        tarjetaOperaciones.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel iconoOperaciones = new JLabel("BL", SwingConstants.CENTER);
        iconoOperaciones.setFont(new Font("Segoe UI", Font.PLAIN, 26));
        iconoOperaciones.setForeground(DORADO);
        iconoOperaciones.setAlignmentX(CENTER_ALIGNMENT);

        JLabel tituloOperaciones = new JLabel("Bitacoras y Limpieza", SwingConstants.CENTER);
        tituloOperaciones.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tituloOperaciones.setForeground(DORADO);
        tituloOperaciones.setAlignmentX(CENTER_ALIGNMENT);

        JLabel descOperaciones = new JLabel("<html><div style='text-align:center'>Historial tecnico y control sanitario</div></html>", SwingConstants.CENTER);
        descOperaciones.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descOperaciones.setForeground(TEXTO_GRIS);
        descOperaciones.setAlignmentX(CENTER_ALIGNMENT);

        tarjetaOperaciones.add(Box.createVerticalGlue());
        tarjetaOperaciones.add(iconoOperaciones);
        tarjetaOperaciones.add(Box.createVerticalStrut(8));
        tarjetaOperaciones.add(tituloOperaciones);
        tarjetaOperaciones.add(Box.createVerticalStrut(4));
        tarjetaOperaciones.add(descOperaciones);
        tarjetaOperaciones.add(Box.createVerticalGlue());

        grid.add(tarjetaClientes);
        grid.add(tarjetaPagos);
        grid.add(tarjetaRendimiento);
        grid.add(tarjetaPersonal);
        grid.add(tarjetaInventario);
        grid.add(tarjetaOperaciones);

        vistaMenu.add(lblMenu, BorderLayout.NORTH);
        vistaMenu.add(grid, BorderLayout.CENTER);

        contenedorCentral.add(vistaMenu, "MENU_PRINCIPAL");
        add(contenedorCentral, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(FONDO_PANEL);
        footer.setBorder(new EmptyBorder(12, 40, 12, 40));

        JLabel lblFooter = new JLabel("2026 PowerFit Gym  |  IF4007 Bases de Datos I  |  UCR Sede del Caribe");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(TEXTO_GRIS);

        footer.add(lblFooter, BorderLayout.WEST);
        add(footer, BorderLayout.SOUTH);
    }

    public void agregarPanelModulo(JPanel panel, String clave) {
        contenedorCentral.add(panel, clave);
    }

    public void mostrarPanel(String clave) {
        navegador.show(contenedorCentral, clave);
    }

    public JButton getBtnInicio() {
        return btnInicio;
    }

    public void alSeleccionarClientes(MouseListener listener) {
        tarjetaClientes.addMouseListener(listener);
    }

    public void alSeleccionarPagos(MouseListener listener) {
        tarjetaPagos.addMouseListener(listener);
    }

    public void alSeleccionarRendimiento(MouseListener listener) {
        tarjetaRendimiento.addMouseListener(listener);
    }

    public void alSeleccionarPersonal(MouseListener listener) {
        tarjetaPersonal.addMouseListener(listener);
    }

    public void alSeleccionarInventario(MouseListener listener) {
        tarjetaInventario.addMouseListener(listener);
    }

    public void alSeleccionarOperaciones(MouseListener listener) {
        tarjetaOperaciones.addMouseListener(listener);
    }
    
    public void alSeleccionarProgreso(MouseListener listener) {
        tarjetaProgreso.addMouseListener(listener);
    }
    
    public void mostrarMensaje(String mensaje) { 
    	JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public void ocultarTodo() {
        setModuloVisible("CLIENTES", false);
        setModuloVisible("PAGOS", false);
        setModuloVisible("RENDIMIENTO", false);
        setModuloVisible("PROGRESO", false);
        setModuloVisible("PERSONAL", false);
        setModuloVisible("INVENTARIO", false);
        setModuloVisible("OPERACIONES", false);
    }
    
    public void setModuloVisible(String clave, boolean visible) {
        switch (clave.toUpperCase()) {
            case "CLIENTES":    tarjetaClientes.setVisible(visible);    break;
            case "PAGOS":       tarjetaPagos.setVisible(visible);       break;
            case "RENDIMIENTO": tarjetaRendimiento.setVisible(visible); break;
            case "PROGRESO":    tarjetaProgreso.setVisible(visible);    break;
            case "PERSONAL":    tarjetaPersonal.setVisible(visible);    break;
            case "INVENTARIO":  tarjetaInventario.setVisible(visible);  break;
            case "OPERACIONES": tarjetaOperaciones.setVisible(visible); break;
        }
    }
    
}