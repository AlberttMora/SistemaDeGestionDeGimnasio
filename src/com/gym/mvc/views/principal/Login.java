package com.gym.mvc.views.principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color FONDO_OSCURO = new Color(18, 18, 18);
    private static final Color FONDO_PANEL  = new Color(28, 28, 28);
    private static final Color DORADO       = new Color(212, 175, 55);
    private static final Color TEXTO_GRIS   = new Color(160, 160, 160);
    private static final Color SEPARADOR    = new Color(55, 55, 55);

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnEntrar;
    private JButton btnSalir;

    public Login() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("PowerFit Gym - Credenciales de Acceso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 460);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(FONDO_OSCURO);
        setLayout(new BorderLayout());

        JPanel panelContenedor = new JPanel();
        panelContenedor.setBackground(FONDO_PANEL);
        panelContenedor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR, 1),
            new EmptyBorder(30, 30, 30, 30)
        ));
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        JLabel lblLogo = new JLabel("POWERFIT GYM");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblLogo.setForeground(DORADO);
        lblLogo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Bases de Datos I - Control de Acceso");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(TEXTO_GRIS);
        lblSub.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblUser = new JLabel("Correo Electronico Corporativo:");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(TEXTO_GRIS);
        lblUser.setAlignmentX(CENTER_ALIGNMENT);

        txtUsuario = new JTextField();
        txtUsuario.setMaximumSize(new Dimension(300, 34));
        txtUsuario.setBackground(FONDO_OSCURO);
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(DORADO);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(4, 8, 4, 8)
        ));
        txtUsuario.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblPass = new JLabel("Contrasena de Red:");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass.setForeground(TEXTO_GRIS);
        lblPass.setAlignmentX(CENTER_ALIGNMENT);

        txtContrasena = new JPasswordField();
        txtContrasena.setMaximumSize(new Dimension(300, 34));
        txtContrasena.setBackground(FONDO_OSCURO);
        txtContrasena.setForeground(Color.WHITE);
        txtContrasena.setCaretColor(DORADO);
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtContrasena.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(4, 8, 4, 8)
        ));
        txtContrasena.setAlignmentX(CENTER_ALIGNMENT);

        btnEntrar = new JButton("Autenticar Ingreso");
        btnEntrar.setMaximumSize(new Dimension(300, 38));
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEntrar.setBackground(FONDO_OSCURO);
        btnEntrar.setForeground(DORADO);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setAlignmentX(CENTER_ALIGNMENT);
        btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEntrar.setBorder(BorderFactory.createLineBorder(SEPARADOR));

        btnSalir = new JButton("Cerrar Sistema");
        btnSalir.setMaximumSize(new Dimension(300, 38));
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSalir.setBackground(FONDO_OSCURO);
        btnSalir.setForeground(Color.LIGHT_GRAY);
        btnSalir.setFocusPainted(false);
        btnSalir.setAlignmentX(CENTER_ALIGNMENT);
        btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSalir.setBorder(BorderFactory.createLineBorder(SEPARADOR));

        panelContenedor.add(Box.createVerticalStrut(10));
        panelContenedor.add(lblLogo);
        panelContenedor.add(Box.createVerticalStrut(4));
        panelContenedor.add(lblSub);
        panelContenedor.add(Box.createVerticalStrut(35));
        panelContenedor.add(lblUser);
        panelContenedor.add(Box.createVerticalStrut(6));
        panelContenedor.add(txtUsuario);
        panelContenedor.add(Box.createVerticalStrut(20));
        panelContenedor.add(lblPass);
        panelContenedor.add(Box.createVerticalStrut(6));
        panelContenedor.add(txtContrasena);
        panelContenedor.add(Box.createVerticalStrut(35));
        panelContenedor.add(btnEntrar);
        panelContenedor.add(Box.createVerticalStrut(10));
        panelContenedor.add(btnSalir);

        add(panelContenedor, BorderLayout.CENTER);
    }

    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}