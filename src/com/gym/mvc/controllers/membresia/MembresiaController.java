package com.gym.mvc.controllers.membresia;

import com.gym.mvc.controllers.base.IController;
import com.gym.mvc.models.Membresia;
import com.gym.mvc.models.services.IService;
import com.gym.mvc.views.inscripcion.GestionMembresias;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MembresiaController implements IController {

    private final GestionMembresias vista;
    private final IService<Membresia> service;

    public MembresiaController(GestionMembresias vista, IService<Membresia> service) {
        this.vista = vista;
        this.service = service;
    }

    @Override
    public void setUpListeners() {
        vista.alGuardar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create();
            }
        });

        vista.alEliminar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroy();
            }
        });

        vista.alLimpiar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.limpiarFormulario();
            }
        });

        vista.getTabla().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    show();
                }
            }
        });
    }

    @Override
    public void cargarDatosIniciales() {
        try {
            List<Membresia> lista = service.getAll();
            renderizarTabla(lista);
        } catch (Exception ex) {
        	vista.mostrarError("Error al cargar membresías: " + ex.getMessage());
        }
    }

    private void renderizarTabla(List<Membresia> lista) {
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0);
        for (Membresia m : lista) {
            modelo.addRow(new Object[]{
                m.getIdMembresia(),
                m.getTipo(),
                m.getPrecio(),
                m.getDuracion()
            });
        }
    }

    @Override
    public void index() {
        cargarDatosIniciales();
    }

    @Override
    public void create() {
        try {
            Membresia membresia = mapToEntity();
            String idStr = vista.getIdSeleccionado().trim();

            if (!idStr.isEmpty()) {
                membresia.setIdMembresia(Integer.parseInt(idStr));
                service.update(membresia);
                vista.mostrarMensaje("Membresía actualizada correctamente.");
            } else {
                service.store(membresia);
                vista.mostrarMensaje("Membresía registrada correctamente.");
            }

            vista.limpiarFormulario();
            cargarDatosIniciales();
        } catch (NumberFormatException ex) {
        	vista.mostrarAdvertencia("Precio y Duración deben ser valores numéricos válidos.");
        } catch (IllegalArgumentException ex) {
        	vista.mostrarAdvertencia(ex.getMessage());
        } catch (Exception ex) {
        	vista.mostrarError("Error al procesar membresía: " + ex.getMessage());
        }
    }

    @Override
    public void show() {
        int fila = vista.getTabla().getSelectedRow();
        if (fila != -1) {
            poblarFormularioDesdeFila(fila);
        }
    }

    @Override
    public void edit() {
    }

    @Override
    public void update() {
    }

    @Override
    public void destroy() {
        int fila = vista.getTabla().getSelectedRow();
        if (fila == -1) {
        	vista.mostrarAdvertencia("Seleccione una membresía de la tabla para eliminar.");
            return;
        }

        try {
            int id = (int) vista.getTabla().getValueAt(fila, 0);
            if (vista.confirmarAccion("¿Está seguro de eliminar este plan de membresía?")) {
                service.destroy(id);
                vista.mostrarMensaje("Membresía eliminada correctamente.");
                vista.limpiarFormulario();
                cargarDatosIniciales();
            }
        } catch (IllegalArgumentException ex) {
        	vista.mostrarAdvertencia(ex.getMessage());
        } catch (Exception ex) {
            vista.mostrarError("Error al eliminar membresía: " + ex.getMessage());
        }
    }

    private Membresia mapToEntity() {
        String tipo = vista.getTipo().trim();
        String precioStr = vista.getPrecio().trim();
        String duracionStr = vista.getDuracion().trim();

        double precio = precioStr.isEmpty() ? 0 : Double.parseDouble(precioStr);
        int duracion = duracionStr.isEmpty() ? 0 : Integer.parseInt(duracionStr);

        Membresia m = new Membresia();
        m.setTipo(tipo);
        m.setPrecio(precio);
        m.setDuracion(duracion);
        return m;
    }

    private void poblarFormularioDesdeFila(int fila) {
        String id = vista.getTabla().getValueAt(fila, 0).toString();
        String tipo = vista.getTabla().getValueAt(fila, 1).toString();
        String precio = vista.getTabla().getValueAt(fila, 2).toString();
        String duracion = vista.getTabla().getValueAt(fila, 3).toString();
        
        vista.cargarDatosPlan(id, tipo, precio, duracion);
    }

}