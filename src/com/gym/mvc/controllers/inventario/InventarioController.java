package com.gym.mvc.controllers.inventario;

import com.gym.mvc.models.Equipo;
import com.gym.mvc.models.TipoEquipo;
import com.gym.mvc.models.services.EquipoService;
import com.gym.mvc.models.services.IService;
import com.gym.mvc.models.services.TipoService;
import com.gym.mvc.views.inventario.GestionInventario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class InventarioController {

    private final GestionInventario view;
    private final IService<Equipo> service;
    private final TipoService tipoService;
    private final Map<String, Integer> mapaTipos;

    public InventarioController(GestionInventario view) {
        this.view        = view;
        this.service     = new EquipoService();
        this.tipoService = new TipoService();
        this.mapaTipos   = new HashMap<>();
    }

    public GestionInventario getVista() {
        return view;
    }

    public void init() {
        setupListeners();
        cargarComboTipos();
        index();
    }

    private void setupListeners() {
        view.getBtnGuardar().addActionListener(e -> store());
        view.getBtnEliminar().addActionListener(e -> destroy());
        view.getBtnExaminarFoto().addActionListener(e -> abrirFileChooser());
        view.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) edit();
        });
    }

    public void index() {
        cargarTabla();
    }

    private void cargarComboTipos() {
        List<TipoEquipo> tipos = tipoService.getAll();
        view.getComboTipos().removeAllItems();
        mapaTipos.clear();
        for (TipoEquipo t : tipos) {
            mapaTipos.put(t.getNombre(), t.getIdTipo());
            view.getComboTipos().addItem(t.getNombre());
        }
    }

    private void cargarTabla() {
        try {
            List<Equipo> lista = service.getAll();
            DefaultTableModel modelo = view.getModeloTabla();
            modelo.setRowCount(0);
            for (Equipo e : lista) {
                modelo.addRow(new Object[]{
                    e.getIdEquipo(), e.getIdTipo(), e.getNombre(),
                    e.getEstado(), e.getFechaAdquisicion()
                });
            }
        } catch (Exception ex) {
            view.mostrarMensaje(ex.getMessage());
        }
    }

    private void store() {
        try {
            Equipo e = new Equipo();
            if (!view.obtenerIdEquipo().isEmpty()) {
                e.setIdEquipo(Integer.parseInt(view.obtenerIdEquipo()));
            }

            String nombreTipo = (String) view.getComboTipos().getSelectedItem();
            if (nombreTipo == null || !mapaTipos.containsKey(nombreTipo)) {
                view.mostrarMensaje("Seleccione una categoria valida");
                return;
            }

            e.setIdTipo(mapaTipos.get(nombreTipo));
            e.setNombre(view.obtenerNombre());
            e.setEstado(view.obtenerEstado());
            e.setFotoRuta(view.obtenerFotoRuta());
            e.setFechaAdquisicion(view.obtenerFecha());

            if (e.getIdEquipo() > 0) {
                service.update(e);
            } else {
                service.store(e);
            }
            view.limpiarFormulario();
            cargarTabla();
        } catch (Exception ex) {
            view.mostrarMensaje(ex.getMessage());
        }
    }

    private void destroy() {
        try {
            int fila = view.getTabla().getSelectedRow();
            if (fila == -1) return;
            int id = (int) view.getTabla().getValueAt(fila, 0);
            service.destroy(id);
            cargarTabla();
        } catch (Exception ex) {
            view.mostrarMensaje(ex.getMessage());
        }
    }

    private void edit() {
        int fila = view.getTabla().getSelectedRow();
        if (fila == -1) return;

        view.establecerIdEquipo(view.getTabla().getValueAt(fila, 0).toString());

        int idTipo = Integer.parseInt(view.getTabla().getValueAt(fila, 1).toString());
        for (Map.Entry<String, Integer> entry : mapaTipos.entrySet()) {
            if (entry.getValue().equals(idTipo)) {
                view.getComboTipos().setSelectedItem(entry.getKey());
                break;
            }
        }

        view.establecerNombre(view.getTabla().getValueAt(fila, 2).toString());
        view.getComboEstado().setSelectedItem(view.getTabla().getValueAt(fila, 3));

        int idEquipo = (int) view.getTabla().getValueAt(fila, 0);
        try {
            Equipo eq = service.findById(idEquipo);
            if (eq != null && eq.getFotoRuta() != null && !eq.getFotoRuta().isEmpty()) {
                view.establecerFotoRuta(eq.getFotoRuta());
            }
        } catch (Exception ex) {
        }
    }

    private void abrirFileChooser() {
        JFileChooser fc = new JFileChooser();
        int r = fc.showOpenDialog(view);
        if (r == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            view.establecerFotoRuta(f.getAbsolutePath());
            mostrarVistaPrevia(f.getAbsolutePath());
        }
    }

    private void mostrarVistaPrevia(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(view, "Archivo no encontrado: " + ruta,
                "Imagen", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(350, 280, Image.SCALE_SMOOTH);

        JLabel lblImg = new JLabel(new ImageIcon(imagenEscalada));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(360, 290));
        panel.add(lblImg, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(view, panel,
            archivo.getName(), JOptionPane.PLAIN_MESSAGE);
    }
}