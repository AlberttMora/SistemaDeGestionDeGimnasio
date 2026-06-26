package com.gym.mvc.views.rendimiento;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ListaAsignaciones extends JPanel {

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

    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblTotal;

    private JTextField txtIdEntrenador;
    private JTextField txtIdCliente;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtObservaciones;
    
    private JButton btnLimpiar;
    private JButton btnGuardar;
    private JButton btnElim;

    public ListaAsignaciones() {
        setSize(960, 694);
        setLayout(new BorderLayout());

        JPanel h = new JPanel(new GridLayout(2, 1, 0, 4));
        h.setBackground(new Color(28, 28, 28));
        h.setBorder(new EmptyBorder(20, 32, 16, 32));
        
        JLabel t = new JLabel("ASIGNACION DE ENTRENADORES");
        t.setFont(new Font("Segoe UI", Font.BOLD, 18)); 
        t.setForeground(DORADO);
        
        JLabel s = new JLabel("Asignar entrenadores a clientes y gestionar asignaciones activas");
        s.setFont(new Font("Segoe UI Light", Font.PLAIN, 12)); 
        s.setForeground(TEXTO_GRIS);
        
        h.add(t); 
        h.add(s);
        add(h, BorderLayout.NORTH);

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
                "Nueva Asignacion",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 11), DORADO),
            new EmptyBorder(10, 14, 14, 14)
        ));

        txtIdEntrenador = new JTextField();
        txtIdEntrenador.setBackground(FONDO_CAMPO); 
        txtIdEntrenador.setForeground(TEXTO_BLANCO);
        txtIdEntrenador.setCaretColor(DORADO); 
        txtIdEntrenador.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtIdEntrenador.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));
        txtIdEntrenador.setPreferredSize(new Dimension(0, 36));
        txtIdEntrenador.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { txtIdEntrenador.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DORADO), new EmptyBorder(6, 8, 6, 8))); }
            @Override public void focusLost(FocusEvent e)   { txtIdEntrenador.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8))); }
        });

        JPanel wrap1 = new JPanel(new GridLayout(2, 1, 0, 4));
        wrap1.setOpaque(false);
        JLabel lbl1 = new JLabel("ID Entrenador *");
        lbl1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl1.setForeground(TEXTO_GRIS);
        wrap1.add(lbl1); 
        wrap1.add(txtIdEntrenador);

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(6, 6, 6, 6);
        gbc1.gridx = 0; 
        gbc1.gridy = 0; 
        gbc1.weightx = 0.5;
        formPanel.add(wrap1, gbc1);

        txtIdCliente = new JTextField();
        txtIdCliente.setBackground(FONDO_CAMPO); 
        txtIdCliente.setForeground(TEXTO_BLANCO);
        txtIdCliente.setCaretColor(DORADO); 
        txtIdCliente.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtIdCliente.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));
        txtIdCliente.setPreferredSize(new Dimension(0, 36));
        txtIdCliente.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { txtIdCliente.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DORADO), new EmptyBorder(6, 8, 6, 8))); }
            @Override public void focusLost(FocusEvent e)   { txtIdCliente.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8))); }
        });

        JPanel wrap2 = new JPanel(new GridLayout(2, 1, 0, 4));
        wrap2.setOpaque(false);
        JLabel lbl2 = new JLabel("ID Cliente *");
        lbl2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl2.setForeground(TEXTO_GRIS);
        wrap2.add(lbl2); 
        wrap2.add(txtIdCliente);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.insets = new Insets(6, 6, 6, 6);
        gbc2.gridx = 1; 
        gbc2.gridy = 0; 
        gbc2.weightx = 0.5;
        formPanel.add(wrap2, gbc2);

        txtFechaInicio = new JTextField();
        txtFechaInicio.setBackground(FONDO_CAMPO); 
        txtFechaInicio.setForeground(TEXTO_BLANCO);
        txtFechaInicio.setCaretColor(DORADO); 
        txtFechaInicio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtFechaInicio.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));
        txtFechaInicio.setPreferredSize(new Dimension(0, 36));
        txtFechaInicio.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { txtFechaInicio.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DORADO), new EmptyBorder(6, 8, 6, 8))); }
            @Override public void focusLost(FocusEvent e)   { txtFechaInicio.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8))); }
        });

        JPanel wrap3 = new JPanel(new GridLayout(2, 1, 0, 4));
        wrap3.setOpaque(false);
        JLabel lbl3 = new JLabel("Fecha inicio *");
        lbl3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl3.setForeground(TEXTO_GRIS);
        wrap3.add(lbl3); 
        wrap3.add(txtFechaInicio);

        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.insets = new Insets(6, 6, 6, 6);
        gbc3.gridx = 0; 
        gbc3.gridy = 1; 
        gbc3.weightx = 0.5;
        formPanel.add(wrap3, gbc3);

        txtFechaFin = new JTextField();
        txtFechaFin.setBackground(FONDO_CAMPO); 
        txtFechaFin.setForeground(TEXTO_BLANCO);
        txtFechaFin.setCaretColor(DORADO); 
        txtFechaFin.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtFechaFin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));
        txtFechaFin.setPreferredSize(new Dimension(0, 36));
        txtFechaFin.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { txtFechaFin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DORADO), new EmptyBorder(6, 8, 6, 8))); }
            @Override public void focusLost(FocusEvent e)   { txtFechaFin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8))); }
        });

        JPanel wrap4 = new JPanel(new GridLayout(2, 1, 0, 4));
        wrap4.setOpaque(false);
        JLabel lbl4 = new JLabel("Fecha fin");
        lbl4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl4.setForeground(TEXTO_GRIS);
        wrap4.add(lbl4); 
        wrap4.add(txtFechaFin);

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.fill = GridBagConstraints.HORIZONTAL;
        gbc4.insets = new Insets(6, 6, 6, 6);
        gbc4.gridx = 1; 
        gbc4.gridy = 1; 
        gbc4.weightx = 0.5;
        formPanel.add(wrap4, gbc4);

        txtObservaciones = new JTextField();
        txtObservaciones.setBackground(FONDO_CAMPO); 
        txtObservaciones.setForeground(TEXTO_BLANCO);
        txtObservaciones.setCaretColor(DORADO); 
        txtObservaciones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtObservaciones.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8)));
        txtObservaciones.setPreferredSize(new Dimension(0, 36));
        txtObservaciones.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { txtObservaciones.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(DORADO), new EmptyBorder(6, 8, 6, 8))); }
            @Override public void focusLost(FocusEvent e)   { txtObservaciones.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(SEPARADOR), new EmptyBorder(6, 8, 6, 8))); }
        });

        JPanel wrap5 = new JPanel(new GridLayout(2, 1, 0, 4));
        wrap5.setOpaque(false);
        JLabel lbl5 = new JLabel("Observaciones");
        lbl5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl5.setForeground(TEXTO_GRIS);
        wrap5.add(lbl5); 
        wrap5.add(txtObservaciones);

        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.fill = GridBagConstraints.HORIZONTAL;
        gbc5.insets = new Insets(6, 6, 6, 6);
        gbc5.gridx = 0; 
        gbc5.gridy = 2; 
        gbc5.gridwidth = 2; 
        gbc5.weightx = 1.0;
        formPanel.add(wrap5, gbc5);

        JPanel btnForm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnForm.setOpaque(false);
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLimpiar.setBackground(new Color(55, 55, 55)); 
        btnLimpiar.setForeground(TEXTO_GRIS);
        btnLimpiar.setFocusPainted(false); 
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLimpiar.setPreferredSize(new Dimension(130, 36));
        
        btnGuardar = new JButton("Asignar");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setBackground(VERDE); 
        btnGuardar.setForeground(Color.GRAY);
        btnGuardar.setFocusPainted(false); 
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGuardar.setPreferredSize(new Dimension(130, 36));
        
        btnForm.add(btnLimpiar); 
        btnForm.add(btnGuardar);

        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.fill = GridBagConstraints.HORIZONTAL;
        gbc6.insets = new Insets(6, 6, 6, 6);
        gbc6.gridx = 0; 
        gbc6.gridy = 3; 
        gbc6.gridwidth = 2;
        formPanel.add(btnForm, gbc6);

        superior.add(formPanel, BorderLayout.CENTER);

        String[] columnas = {"ID", "ID Entrenador", "Entrenador", "ID Cliente", "Cliente", "Fecha Inicio", "Fecha Fin", "Observaciones"};
        Object[][] datos = {
            {1, 3, "Diego Rojas",   1, "Ana Gonzalez",    "01/04/2026", "01/07/2026", "Plan de fuerza"},
            {2, 3, "Diego Rojas",   2, "Carlos Perez",    "15/04/2026", "15/07/2026", "Perdida de peso"},
            {3, 4, "Marcos Solis",  3, "Maria Rodriguez", "01/05/2026", "01/08/2026", "Cardio avanzado"},
            {4, 4, "Marcos Solis",  5, "Sara Solis",      "10/05/2026", "10/08/2026", ""},
        };

        modelo = new DefaultTableModel(datos, columnas) {
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
        th.setBackground(new Color(40, 40, 40)); 
        th.setForeground(DORADO);
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

        btnElim = new JButton("Eliminar");
        btnElim.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnElim.setBackground(ROJO_SUAVE); 
        btnElim.setForeground(Color.GRAY);
        btnElim.setFocusPainted(false); 
        btnElim.setBorderPainted(false);
        btnElim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnElim.setPreferredSize(new Dimension(130, 36));

        accionesTabla.add(btnElim);
        pie.add(lblTotal, BorderLayout.WEST);
        pie.add(accionesTabla, BorderLayout.EAST);

        JPanel tablaPanel = new JPanel(new BorderLayout());
        tablaPanel.setOpaque(false);
        tablaPanel.add(scroll, BorderLayout.CENTER);
        tablaPanel.add(pie, BorderLayout.SOUTH);

        contenido.add(superior, BorderLayout.NORTH);
        contenido.add(tablaPanel, BorderLayout.CENTER);
        add(contenido, BorderLayout.CENTER);
        
        configurarEventos();
    }

    private void configurarEventos() {
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarForm();
            }
        });
    }

    private void limpiarForm() {
        txtIdEntrenador.setText(""); 
        txtIdCliente.setText("");
        txtFechaInicio.setText(""); 
        txtFechaFin.setText(""); 
        txtObservaciones.setText("");
    }

    public void alAsignar(ActionListener l) {
        btnGuardar.addActionListener(l);
    }

    public void alEliminar(ActionListener l) {
        btnElim.addActionListener(l);
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public String getIdEntrenador() {
        return txtIdEntrenador.getText().trim();
    }

    public String getIdCliente() {
        return txtIdCliente.getText().trim();
    }

    public String getFechaInicio() {
        return txtFechaInicio.getText().trim();
    }

    public String getFechaFin() {
        return txtFechaFin.getText().trim();
    }

    public String getObservaciones() {
        return txtObservaciones.getText().trim();
    }

    public void limpiarFormulario() {
        limpiarForm();
    }

    public void actualizarTotal() {
        lblTotal.setText("Total: " + modelo.getRowCount() + " asignaciones");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmarAccion(String mensaje) {
        int r = JOptionPane.showConfirmDialog(this, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
        return r == JOptionPane.YES_OPTION;
    }
}