package com.gym.mvc.views.rendimiento;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class ControlProgreso extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color FONDO_OSCURO = new Color(18, 18, 18);
    private static final Color FONDO_PANEL  = new Color(28, 28, 28);
    private static final Color DORADO       = new Color(212, 175, 55);
    private static final Color TEXTO_GRIS   = new Color(160, 160, 160);
    private static final Color SEPARADOR    = new Color(55, 55, 55);

    private JComboBox<String> cbIdEquipo;
    private JTextField txtPesoKg;
    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JTable tablaProgreso;
    private DefaultTableModel modeloTabla;

    public ControlProgreso() {
        setBackground(FONDO_OSCURO);
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(30, 40, 30, 40));

        JLabel lblTitulo = new JLabel("CONTROL DE RENDIMIENTO Y CARGAS (DETALLE PESO)");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(DORADO);
        add(lblTitulo, BorderLayout.NORTH);

        JPanel cuerpo = new JPanel(new GridBagLayout());
        cuerpo.setOpaque(false);

        JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 20));
        panelForm.setBackground(FONDO_PANEL);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(25, 20, 25, 20)
        ));

        JLabel lblEq = new JLabel("ID Equipo / Maquina:");
        lblEq.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEq.setForeground(TEXTO_GRIS);
        panelForm.add(lblEq);

        cbIdEquipo = new JComboBox<>();
        cbIdEquipo.setBackground(FONDO_OSCURO);
        cbIdEquipo.setForeground(Color.WHITE);
        panelForm.add(cbIdEquipo);

        JLabel lblPeso = new JLabel("Peso Registrado (Kg):");
        lblPeso.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPeso.setForeground(TEXTO_GRIS);
        panelForm.add(lblPeso);

        txtPesoKg = new JTextField();
        txtPesoKg.setBackground(FONDO_OSCURO);
        txtPesoKg.setForeground(Color.WHITE);
        txtPesoKg.setCaretColor(DORADO);
        txtPesoKg.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(5, 8, 5, 8)
        ));
        panelForm.add(txtPesoKg);

        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.gridx = 0;
        gbcForm.gridy = 0;
        gbcForm.weightx = 0.35;
        gbcForm.weighty = 0.5;
        gbcForm.fill = GridBagConstraints.BOTH;
        cuerpo.add(panelForm, gbcForm);

        String[] columnas = { "ID Equipo Asociado", "Carga Guardada (Kg)" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        
        tablaProgreso = new JTable(modeloTabla);
        tablaProgreso.setBackground(FONDO_PANEL);
        tablaProgreso.setForeground(Color.WHITE);
        tablaProgreso.setGridColor(SEPARADOR);
        tablaProgreso.setRowHeight(24);
        tablaProgreso.setSelectionBackground(DORADO);
        tablaProgreso.setSelectionForeground(FONDO_OSCURO);
        
        JTableHeader th = tablaProgreso.getTableHeader();
        th.setBackground(FONDO_OSCURO);
        th.setForeground(DORADO);
        th.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scroll = new JScrollPane(tablaProgreso);
        scroll.getViewport().setBackground(FONDO_PANEL);
        scroll.setBorder(BorderFactory.createLineBorder(SEPARADOR));

        GridBagConstraints gbcScroll = new GridBagConstraints();
        gbcScroll.gridx = 1;
        gbcScroll.gridy = 0;
        gbcScroll.weightx = 0.65;
        gbcScroll.weighty = 0.5;
        gbcScroll.fill = GridBagConstraints.BOTH;
        gbcScroll.insets = new Insets(0, 20, 0, 0);
        cuerpo.add(scroll, gbcScroll);

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelAcciones.setOpaque(false);

        btnRegistrar = new JButton("Registrar Metrica");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrar.setBackground(FONDO_PANEL);
        btnRegistrar.setForeground(DORADO);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegistrar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(8, 16, 8, 16)
        ));

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLimpiar.setBackground(FONDO_PANEL);
        btnLimpiar.setForeground(DORADO);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLimpiar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SEPARADOR),
            new EmptyBorder(8, 16, 8, 16)
        ));

        panelAcciones.add(btnRegistrar);
        panelAcciones.add(btnLimpiar);

        GridBagConstraints gbcAcciones = new GridBagConstraints();
        gbcAcciones.gridx = 0;
        gbcAcciones.gridy = 1;
        gbcAcciones.gridwidth = 2;
        gbcAcciones.weighty = 0.5;
        gbcAcciones.fill = GridBagConstraints.HORIZONTAL;
        gbcAcciones.insets = new Insets(20, 0, 0, 0);
        cuerpo.add(panelAcciones, gbcAcciones);

        add(cuerpo, BorderLayout.CENTER);
    }

    // --- GETTERS COMPONENTES (Para que el controlador asigne sus propios Listeners) ---
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JComboBox<String> getCbIdEquipo() { return cbIdEquipo; }
    public JTable getTablaProgreso() { return tablaProgreso; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }

    // --- GETTERS DE DATOS ---
    public String getPesoKg() { return txtPesoKg.getText().trim(); }
    public String getEquipoSeleccionado() { return (String) cbIdEquipo.getSelectedItem(); }
    
    // --- MÉTODOS DE MODIFICACIÓN EXTERNA ---
    public void setPesoKg(String peso) { txtPesoKg.setText(peso); }
    
    public void limpiarFormulario() {
        txtPesoKg.setText("");
        if (cbIdEquipo.getItemCount() > 0) {
            cbIdEquipo.setSelectedIndex(0);
        }
    }
}