package com.gym.mvc.views.operaciones;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;

public class BitacoraOperaciones extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color FONDO_OSCURO = new Color(18, 18, 18);
    private static final Color FONDO_PANEL = new Color(28, 28, 28);
    private static final Color DORADO = new Color(212, 175, 55);
    private static final Color TEXTO_BLANCO = new Color(240, 240, 240);
    private static final Color TEXTO_GRIS = new Color(160, 160, 160);
    private static final Color SEPARADOR = new Color(55, 55, 55);

    private JComboBox cbEquipo;
    private JComboBox cbPersonalMnt;
    private JDateChooser jdFechaMnt;
    private JTextArea txtDescripcionMnt;
    private JButton btnGuardarMnt;
    private DefaultTableModel modelMantenimientos;

    private JComboBox cbPersonalLimp;
    private JTextField txtAreaLimpieza;
    private JTextField txtTurnoRegistro;
    private JButton btnRegistrarLimpieza;
    private DefaultTableModel modelLimpiezas;

    public BitacoraOperaciones() {
        setBackground(FONDO_OSCURO);
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(30, 40, 30, 40));

        JLabel lblTitulo = new JLabel("OPERACIONES, BITACORAS Y CONTROL SANITARIO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(DORADO);
        add(lblTitulo, BorderLayout.NORTH);

        JTabbedPane pestanasMedias = new JTabbedPane();
        pestanasMedias.setBackground(FONDO_PANEL);
        pestanasMedias.setForeground(DORADO);

        JPanel pMnt = new JPanel(new BorderLayout(15, 15));
        pMnt.setOpaque(false);

        JPanel formMnt = new JPanel(new GridBagLayout());
        formMnt.setBackground(FONDO_PANEL);
        formMnt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(15, 15, 15, 15)));
        formMnt.setPreferredSize(new Dimension(340, 0));

        JLabel lblMaquina = new JLabel("Maquina afectada:");
        lblMaquina.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblMaquina.setForeground(TEXTO_GRIS);
        GridBagConstraints gbcLblMaquina = new GridBagConstraints();
        gbcLblMaquina.fill = GridBagConstraints.HORIZONTAL;
        gbcLblMaquina.insets = new Insets(5, 5, 5, 5);
        gbcLblMaquina.weightx = 1.0;
        gbcLblMaquina.gridx = 0;
        gbcLblMaquina.gridy = 0;
        formMnt.add(lblMaquina, gbcLblMaquina);

        cbEquipo = new JComboBox();
        cbEquipo.setBackground(FONDO_OSCURO);
        cbEquipo.setForeground(Color.BLACK);
        GridBagConstraints gbcCbEquipo = new GridBagConstraints();
        gbcCbEquipo.fill = GridBagConstraints.HORIZONTAL;
        gbcCbEquipo.insets = new Insets(5, 5, 5, 5);
        gbcCbEquipo.weightx = 1.0;
        gbcCbEquipo.gridx = 0;
        gbcCbEquipo.gridy = 1;
        formMnt.add(cbEquipo, gbcCbEquipo);

        JLabel lblOperario = new JLabel("Operario / Tecnico:");
        lblOperario.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblOperario.setForeground(TEXTO_GRIS);
        GridBagConstraints gbcLblOperario = new GridBagConstraints();
        gbcLblOperario.fill = GridBagConstraints.HORIZONTAL;
        gbcLblOperario.insets = new Insets(5, 5, 5, 5);
        gbcLblOperario.weightx = 1.0;
        gbcLblOperario.gridx = 0;
        gbcLblOperario.gridy = 2;
        formMnt.add(lblOperario, gbcLblOperario);

        cbPersonalMnt = new JComboBox();
        cbPersonalMnt.setBackground(FONDO_OSCURO);
        cbPersonalMnt.setForeground(Color.BLACK);
        GridBagConstraints gbcCbPersonalMnt = new GridBagConstraints();
        gbcCbPersonalMnt.fill = GridBagConstraints.HORIZONTAL;
        gbcCbPersonalMnt.insets = new Insets(5, 5, 5, 5);
        gbcCbPersonalMnt.weightx = 1.0;
        gbcCbPersonalMnt.gridx = 0;
        gbcCbPersonalMnt.gridy = 3;
        formMnt.add(cbPersonalMnt, gbcCbPersonalMnt);

        JLabel lblFecha = new JLabel("Fecha Reporte:");
        lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblFecha.setForeground(TEXTO_GRIS);
        GridBagConstraints gbcLblFecha = new GridBagConstraints();
        gbcLblFecha.fill = GridBagConstraints.HORIZONTAL;
        gbcLblFecha.insets = new Insets(5, 5, 5, 5);
        gbcLblFecha.weightx = 1.0;
        gbcLblFecha.gridx = 0;
        gbcLblFecha.gridy = 4;
        formMnt.add(lblFecha, gbcLblFecha);

        jdFechaMnt = new JDateChooser();
        jdFechaMnt.setDateFormatString("yyyy-MM-dd");
        jdFechaMnt.setDate(new Date());
        jdFechaMnt.setPreferredSize(new Dimension(0, 30));
        jdFechaMnt.getComponent(0).setBackground(FONDO_OSCURO);
        jdFechaMnt.getComponent(1).setBackground(FONDO_OSCURO);
        jdFechaMnt.getComponent(1).setForeground(Color.WHITE);
        ((JTextField) jdFechaMnt.getComponent(1)).setCaretColor(DORADO);
        jdFechaMnt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(2, 2, 2, 2)
        ));
        GridBagConstraints gbcJdFecha = new GridBagConstraints();
        gbcJdFecha.fill = GridBagConstraints.HORIZONTAL;
        gbcJdFecha.insets = new Insets(5, 5, 5, 5);
        gbcJdFecha.weightx = 1.0;
        gbcJdFecha.gridx = 0;
        gbcJdFecha.gridy = 5;
        formMnt.add(jdFechaMnt, gbcJdFecha);

        JLabel lblDescripcion = new JLabel("Descripcion del Trabajo:");
        lblDescripcion.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDescripcion.setForeground(TEXTO_GRIS);
        GridBagConstraints gbcLblDescripcion = new GridBagConstraints();
        gbcLblDescripcion.fill = GridBagConstraints.HORIZONTAL;
        gbcLblDescripcion.insets = new Insets(5, 5, 5, 5);
        gbcLblDescripcion.weightx = 1.0;
        gbcLblDescripcion.gridx = 0;
        gbcLblDescripcion.gridy = 6;
        formMnt.add(lblDescripcion, gbcLblDescripcion);

        txtDescripcionMnt = new JTextArea(4, 10);
        txtDescripcionMnt.setBackground(FONDO_OSCURO);
        txtDescripcionMnt.setForeground(Color.WHITE);
        txtDescripcionMnt.setCaretColor(DORADO);
        txtDescripcionMnt.setBorder(BorderFactory.createLineBorder(SEPARADOR));
        txtDescripcionMnt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JScrollPane scrollTxtMnt = new JScrollPane(txtDescripcionMnt);
        GridBagConstraints gbcTxtMnt = new GridBagConstraints();
        gbcTxtMnt.fill = GridBagConstraints.BOTH;
        gbcTxtMnt.insets = new Insets(5, 5, 5, 5);
        gbcTxtMnt.weightx = 1.0;
        gbcTxtMnt.weighty = 1.0;
        gbcTxtMnt.gridx = 0;
        gbcTxtMnt.gridy = 7;
        formMnt.add(scrollTxtMnt, gbcTxtMnt);

        btnGuardarMnt = new JButton("Inyectar a Bitacora");
        btnGuardarMnt.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardarMnt.setBackground(FONDO_OSCURO);
        btnGuardarMnt.setForeground(DORADO);
        btnGuardarMnt.setFocusPainted(false);
        btnGuardarMnt.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(8, 16, 8, 16)));
        btnGuardarMnt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GridBagConstraints gbcBtnMnt = new GridBagConstraints();
        gbcBtnMnt.fill = GridBagConstraints.HORIZONTAL;
        gbcBtnMnt.insets = new Insets(5, 5, 5, 5);
        gbcBtnMnt.weightx = 1.0;
        gbcBtnMnt.weighty = 0.0;
        gbcBtnMnt.gridx = 0;
        gbcBtnMnt.gridy = 8;
        formMnt.add(btnGuardarMnt, gbcBtnMnt);

        String[] columnasMnt = { "ID", "Equipo", "Tecnico", "Fecha", "Detalle" };
        modelMantenimientos = new DefaultTableModel(columnasMnt, 0);
        JTable tMnt = new JTable(modelMantenimientos);
        tMnt.setBackground(FONDO_PANEL);
        tMnt.setForeground(Color.WHITE);
        tMnt.getTableHeader().setBackground(FONDO_OSCURO);
        tMnt.getTableHeader().setForeground(DORADO);
        tMnt.setGridColor(SEPARADOR);
        tMnt.setRowHeight(24);
        tMnt.setSelectionBackground(DORADO);
        tMnt.setSelectionForeground(FONDO_OSCURO);
        
        JScrollPane sMnt = new JScrollPane(tMnt);
        sMnt.getViewport().setBackground(FONDO_PANEL);
        sMnt.setBorder(BorderFactory.createLineBorder(SEPARADOR));

        pMnt.add(formMnt, BorderLayout.WEST);
        pMnt.add(sMnt, BorderLayout.CENTER);
        pestanasMedias.addTab("Mantenimiento de Equipos", pMnt);

        JPanel pLimp = new JPanel(new BorderLayout(15, 15));
        pLimp.setOpaque(false);

        JPanel formLimp = new JPanel(new GridLayout(4, 1, 10, 15));
        formLimp.setBackground(FONDO_PANEL);
        formLimp.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(20, 20, 20, 20)));
        formLimp.setPreferredSize(new Dimension(340, 0));

        JPanel sub1 = new JPanel(new GridLayout(2, 1));
        sub1.setOpaque(false);
        JLabel lblConserje = new JLabel("Conserje Asignado:");
        lblConserje.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblConserje.setForeground(TEXTO_GRIS);
        sub1.add(lblConserje);
        cbPersonalLimp = new JComboBox();
        cbPersonalLimp.setBackground(FONDO_OSCURO);
        cbPersonalLimp.setForeground(Color.BLACK);
        sub1.add(cbPersonalLimp);
        formLimp.add(sub1);

        JPanel sub2 = new JPanel(new GridLayout(2, 1));
        sub2.setOpaque(false);
        JLabel lblZona = new JLabel("Zona / Area Desinfectada:");
        lblZona.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblZona.setForeground(TEXTO_GRIS);
        sub2.add(lblZona);
        txtAreaLimpieza = new JTextField();
        txtAreaLimpieza.setBackground(FONDO_OSCURO);
        txtAreaLimpieza.setForeground(Color.WHITE);
        txtAreaLimpieza.setCaretColor(DORADO);
        txtAreaLimpieza.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(4, 6, 4, 6)));
        sub2.add(txtAreaLimpieza);
        formLimp.add(sub2);

        JPanel sub3 = new JPanel(new GridLayout(2, 1));
        sub3.setOpaque(false);
        JLabel lblTurno = new JLabel("Turno Ejecutado:");
        lblTurno.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTurno.setForeground(TEXTO_GRIS);
        sub3.add(lblTurno);
        txtTurnoRegistro = new JTextField();
        txtTurnoRegistro.setBackground(FONDO_OSCURO);
        txtTurnoRegistro.setForeground(Color.WHITE);
        txtTurnoRegistro.setCaretColor(DORADO);
        txtTurnoRegistro.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(4, 6, 4, 6)));
        sub3.add(txtTurnoRegistro);
        formLimp.add(sub3);

        btnRegistrarLimpieza = new JButton("Registrar Higienizacion");
        btnRegistrarLimpieza.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrarLimpieza.setBackground(FONDO_OSCURO);
        btnRegistrarLimpieza.setForeground(DORADO);
        btnRegistrarLimpieza.setFocusPainted(false);
        btnRegistrarLimpieza.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(8, 16, 8, 16)));
        btnRegistrarLimpieza.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        formLimp.add(btnRegistrarLimpieza);

        String[] columnasLimp = { "ID Empleado", "Nombre", "Zona Sanitizada", "Turno Realizado" };
        modelLimpiezas = new DefaultTableModel(columnasLimp, 0);
        JTable tLmp = new JTable(modelLimpiezas);
        tLmp.setBackground(FONDO_PANEL);
        tLmp.setForeground(Color.WHITE);
        tLmp.getTableHeader().setBackground(FONDO_OSCURO);
        tLmp.getTableHeader().setForeground(DORADO);
        tLmp.setGridColor(SEPARADOR);
        tLmp.setRowHeight(24);
        tLmp.setSelectionBackground(DORADO);
        tLmp.setSelectionForeground(FONDO_OSCURO);
        
        JScrollPane sLmp = new JScrollPane(tLmp);
        sLmp.getViewport().setBackground(FONDO_PANEL);
        sLmp.setBorder(BorderFactory.createLineBorder(SEPARADOR));

        pLimp.add(formLimp, BorderLayout.WEST);
        pLimp.add(sLmp, BorderLayout.CENTER);
        pestanasMedias.addTab("Bitacora Sanitaria Zonas", pLimp);

        add(pestanasMedias, BorderLayout.CENTER);
    }

    public String getEquipoMnt() {
        return (String) cbEquipo.getSelectedItem();
    }

    public String getPersonalMnt() {
        return (String) cbPersonalMnt.getSelectedItem();
    }

    public String getFechaMnt() {
        if (jdFechaMnt.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(jdFechaMnt.getDate());
        }
        return "";
    }

    public String getDetalleMnt() {
        return txtDescripcionMnt.getText();
    }

    public String getPersonalLimp() {
        return (String) cbPersonalLimp.getSelectedItem();
    }

    public String getAreaLimp() {
        return txtAreaLimpieza.getText();
    }

    public String getTurnoLimp() {
        return txtTurnoRegistro.getText();
    }

    public JComboBox getComboEquipos() {
        return cbEquipo;
    }

    public JComboBox getComboPersonalMnt() {
        return cbPersonalMnt;
    }

    public JComboBox getComboPersonalLimp() {
        return cbPersonalLimp;
    }

    public DefaultTableModel getModelMantenimientos() {
        return modelMantenimientos;
    }

    public DefaultTableModel getModelLimpiezas() {
        return modelLimpiezas;
    }

    public void alGuardarMantenimiento(ActionListener l) {
        btnGuardarMnt.addActionListener(l);
    }

    public void alRegistrarLimpieza(ActionListener l) {
        btnRegistrarLimpieza.addActionListener(l);
    }
    
    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}