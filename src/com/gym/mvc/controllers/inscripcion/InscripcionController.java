package com.gym.mvc.controllers.inscripcion;

import com.gym.mvc.controllers.base.IController;
import com.gym.mvc.models.Inscripcion;
import com.gym.mvc.models.services.IService;
import com.gym.mvc.models.services.InscripcionService;
import com.gym.mvc.views.inscripcion.GestionInscripciones;
import com.gym.mvc.views.inscripcion.ListaInscripciones;

import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

public class InscripcionController implements IController {

    private final GestionInscripciones vistaGestion;
    private final ListaInscripciones vistaLista;
    private final IService<Inscripcion> service;

    public InscripcionController() {
        vistaGestion = new GestionInscripciones();
        vistaLista = new ListaInscripciones();
        service = new InscripcionService();
    }

    @Override
    public void setUpListeners() {

        vistaGestion.alGuardar(e -> create());

        vistaGestion.alLimpiar(e -> vistaGestion.limpiarFormulario());

        vistaLista.getBtnEditar().addActionListener(e -> edit());
        vistaLista.getBtnEliminar().addActionListener(e -> destroy());

        vistaLista.getTxtBuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarInscripciones();
            }
        });
    }

    @Override
    public void cargarDatosIniciales() {
        try {
            List<Inscripcion> lista = service.getAll();
            renderizarTabla(lista);
        } catch (Exception e) {
            vistaGestion.mostrarError(e.getMessage());
        }
    }

    private void renderizarTabla(List<Inscripcion> lista) {

        DefaultTableModel modelo = vistaLista.getModelo();
        modelo.setRowCount(0);

        for (Inscripcion i : lista) {

            modelo.addRow(new Object[]{
                i.getIdInscripcion(),
                i.getIdCliente(),
                i.getIdMembresia(),
                i.getFechaInicio(),
                i.getEstado()
            });
        }

        vistaLista.actualizarTotal(modelo.getRowCount());
    }

    @Override
    public void index() {
        cargarDatosIniciales();
    }

    @Override
    public void create() {

        try {

            Inscripcion i = new Inscripcion();

            Object cliente = vistaGestion.getCmbCliente().getSelectedItem();
            Object membresia = vistaGestion.getCmbMembresia().getSelectedItem();
            Object estado = vistaGestion.getCmbEstado().getSelectedItem();

            if (cliente == null || membresia == null || estado == null || vistaGestion.getDcFechaInicio().getDate() == null) {
                vistaGestion.mostrarError("Complete todos los campos");
                return;
            }

            i.setIdCliente(Integer.parseInt(cliente.toString()));
            i.setIdMembresia(Integer.parseInt(membresia.toString()));
            i.setEstado(estado.toString());
            i.setFechaInicio(vistaGestion.getDcFechaInicio().getDate());

            if (i.getIdInscripcion() > 0) {
                service.update(i);
            } else {
                service.store(i);
            }

            vistaGestion.limpiarFormulario();
            cargarDatosIniciales();

        } catch (Exception e) {
            vistaGestion.mostrarError(e.getMessage());
        }
    }

    @Override
    public void show() {
        // no implementado en UI actual
    }

    @Override
    public void edit() {

        int fila = vistaLista.getFilaSeleccionada();
        if (fila == -1) return;

        try {

            int id = (int) vistaLista.getTabla().getValueAt(fila, 0);
            Inscripcion i = service.findById(id);

            if (i == null) return;

            vistaGestion.getCmbCliente().setSelectedItem(String.valueOf(i.getIdCliente()));
            vistaGestion.getCmbMembresia().setSelectedItem(String.valueOf(i.getIdMembresia()));
            vistaGestion.getCmbEstado().setSelectedItem(i.getEstado());
            vistaGestion.getDcFechaInicio().setDate(i.getFechaInicio());

        } catch (Exception e) {
            vistaGestion.mostrarError(e.getMessage());
        }
    }

    @Override
    public void update() {
        // no separado, se maneja en create()
    }

    @Override
    public void destroy() {

        try {

            int fila = vistaLista.getFilaSeleccionada();
            if (fila == -1) return;

            int id = (int) vistaLista.getTabla().getValueAt(fila, 0);

            if (vistaLista.confirmarAccion("¿Eliminar inscripcion?")) {
                service.destroy(id);
                cargarDatosIniciales();
            }

        } catch (Exception e) {
            vistaGestion.mostrarError(e.getMessage());
        }
    }

    private void filtrarInscripciones() {

        String texto = vistaLista.getTextoBusqueda().toLowerCase();

        try {

            List<Inscripcion> filtrados = service.getAll()
                .stream()
                .filter(i ->
                    String.valueOf(i.getIdInscripcion()).contains(texto) ||
                    String.valueOf(i.getIdCliente()).contains(texto)
                )
                .collect(Collectors.toList());

            renderizarTabla(filtrados);

        } catch (Exception e) {
            vistaGestion.mostrarError(e.getMessage());
        }
    }
}