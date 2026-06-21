package com.gym.mvc.views;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {

    private static final Color FONDO_OSCURO  = new Color(18, 18, 18);
    private static final Color FONDO_PANEL   = new Color(28, 28, 28);
    private static final Color FONDO_TARJETA = new Color(38, 38, 38);
    private static final Color DORADO        = new Color(212, 175, 55);
    private static final Color TEXTO_GRIS    = new Color(160, 160, 160);
    private static final Color SEPARADOR     = new Color(55, 55, 55);

    private static final Font FUENTE_TITULO  = new Font("Segoe UI", Font.BOLD, 32);
    private static final Font FUENTE_SUB     = new Font("Segoe UI Light", Font.PLAIN, 14);
    private static final Font FUENTE_BTN     = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FUENTE_ICON    = new Font("Segoe UI", Font.PLAIN, 26);

    public VentanaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        setTitle("PowerFit Gym — Cuida tu mente, Ama tu cuerpo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 620);
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
        lblNombre.setFont(FUENTE_TITULO);
        lblNombre.setForeground(DORADO);

        JLabel lblSub = new JLabel("Cuida tu mente, Ama tu cuerpo");
        lblSub.setFont(FUENTE_SUB);
        lblSub.setForeground(TEXTO_GRIS);

        headerLeft.add(lblNombre);
        headerLeft.add(lblSub);

        JLabel lblSlogan = new JLabel("<html><div style='text-align:right'>Fortaleza · Disciplina · Resultados</div></html>");
        lblSlogan.setFont(new Font("Segoe UI Light", Font.ITALIC, 12));
        lblSlogan.setForeground(TEXTO_GRIS);
        lblSlogan.setHorizontalAlignment(SwingConstants.RIGHT);

        header.add(headerLeft, BorderLayout.WEST);
        header.add(lblSlogan, BorderLayout.EAST);

        JPanel lineaDorada = new JPanel();
        lineaDorada.setBackground(DORADO);
        lineaDorada.setPreferredSize(new Dimension(0, 2));

        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(false);
        headerWrapper.add(header, BorderLayout.CENTER);
        headerWrapper.add(lineaDorada, BorderLayout.SOUTH);
        add(headerWrapper, BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(FONDO_OSCURO);
        contenido.setBorder(new EmptyBorder(30, 40, 20, 40));

        JLabel lblMenu = new JLabel("MÓDULOS DEL SISTEMA");
        lblMenu.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblMenu.setForeground(TEXTO_GRIS);
        lblMenu.setBorder(new EmptyBorder(0, 0, 16, 0));

        JPanel grid = new JPanel(new GridLayout(2, 3, 16, 16));
        grid.setOpaque(false);

        String[][] modulos = {
            {"GC", "Gestión de Clientes",     "Registro, edición y baja de clientes"},
            {"LC", "Lista de Clientes",        "Consulta y búsqueda de clientes"},
            {"RP", "Registro de Pagos",        "Pagos e inscripciones a membresías"},
            {"INS", "Inscripciones",            "Ver y gestionar inscripciones activas"},
            {"AE", "Asignación Entrenadores", "Asignar entrenadores a clientes"},
            {"EG", "Equipos del Gimnasio",    "Ver equipos disponibles"},
        };

        for (int i = 0; i < modulos.length; i++) {
            final int idx = i;
            JPanel tarjeta = crearTarjeta(modulos[i][0], modulos[i][1], modulos[i][2]);
            tarjeta.addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) { abrirModulo(idx); }
                @Override public void mouseEntered(MouseEvent e) { tarjeta.setBackground(new Color(48, 48, 48)); tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
                @Override public void mouseExited(MouseEvent e)  { tarjeta.setBackground(FONDO_TARJETA); }
            });
            grid.add(tarjeta);
        }

        contenido.add(lblMenu, BorderLayout.NORTH);
        contenido.add(grid, BorderLayout.CENTER);
        add(contenido, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(FONDO_PANEL);
        footer.setBorder(new EmptyBorder(12, 40, 12, 40));

        JLabel lblFooter = new JLabel("© 2026 PowerFit Gym — IF4007 Bases de Datos I · UCR Sede del Caribe");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(TEXTO_GRIS);
        footer.add(lblFooter, BorderLayout.WEST);
        add(footer, BorderLayout.SOUTH);
    }

    private JPanel crearTarjeta(String icono, String titulo, String desc) {
        JPanel tarjeta = new JPanel(new GridBagLayout());
        tarjeta.setBackground(FONDO_TARJETA);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR, 1),
            new EmptyBorder(20, 16, 20, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(0, 0, 8, 0);

        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(FUENTE_ICON);
        tarjeta.add(lblIcono, gbc);

        gbc.gridy = 1; gbc.insets = new Insets(0, 0, 4, 0);
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(FUENTE_BTN);
        lblTitulo.setForeground(DORADO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        tarjeta.add(lblTitulo, gbc);

        gbc.gridy = 2; gbc.insets = new Insets(0, 0, 0, 0);
        JLabel lblDesc = new JLabel("<html><div style='text-align:center'>" + desc + "</div></html>");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDesc.setForeground(TEXTO_GRIS);
        lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
        tarjeta.add(lblDesc, gbc);

        return tarjeta;
    }

    private void abrirModulo(int idx) {
        switch (idx) {
            case 0: new GestionClientes().setVisible(true); break;
            case 1: new ListaClientes().setVisible(true); break;
            case 2: new RegistroPagos().setVisible(true); break;
            case 3: new ListaInscripciones().setVisible(true); break;
            case 4: new ListaAsignaciones().setVisible(true); break;
            case 5: JOptionPane.showMessageDialog(this,
                    "Módulo de Equipos — en desarrollo por otro miembro del equipo.",
                    "PowerFit Gym", JOptionPane.INFORMATION_MESSAGE); break;
        }
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
    }