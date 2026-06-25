package com.gym.mvc.controllers.dashboard;

import com.gym.mvc.controllers.base.BaseController;
import com.gym.mvc.views.principal.VentanaPrincipal;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControllerDashboard extends BaseController {

    private final VentanaPrincipal vista;

    public ControllerDashboard() {
        this.vista = new VentanaPrincipal();
    }

    public void init() {
        setUpListeners();
        index();
    }

    @Override
    public void setUpListeners() {
        vista.alSeleccionarClientes(crearNavegadorHacia("MODULO_CLIENTES"));
        vista.alSeleccionarPagos(crearNavegadorHacia("MODULO_PAGOS"));
        vista.alSeleccionarRendimiento(crearNavegadorHacia("MODULO_RENDIMIENTO"));
        vista.alSeleccionarPersonal(crearNavegadorHacia("MODULO_PERSONAL"));
        vista.alSeleccionarInventario(crearNavegadorHacia("MODULO_INVENTARIO"));
        vista.alSeleccionarOperaciones(crearNavegadorHacia("MODULO_OPERACIONES"));
    }

    @Override
    public void index() {
        vista.setVisible(true);
    }

    private MouseAdapter crearNavegadorHacia(String nombrePanel) {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                vista.mostrarPanel(nombrePanel);
            }
        };
    }
}