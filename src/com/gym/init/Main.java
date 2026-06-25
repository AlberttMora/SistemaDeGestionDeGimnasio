package com.gym.init;

import com.gym.mvc.controllers.dashboard.ControllerDashboard;
import com.gym.mvc.views.principal.VentanaPrincipal;
// IMPORTACIÓN CORRECTA DE TU CLASE ORIGINAL:
import com.gym.mvc.views.cliente.GestionClientes; 
import com.gym.mvc.views.inscripcion.RegistroPagos;
import com.gym.mvc.views.rendimiento.ControlProgreso;
import com.gym.mvc.views.rendimiento.ListaAsignaciones;
import com.gym.mvc.views.personal.GestionPersonal;
import com.gym.mvc.views.inventario.GestionInventario;
import com.gym.mvc.views.operaciones.BitacoraOperaciones;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            VentanaPrincipal ventana = new VentanaPrincipal();

            // 2. Instanciamos TU clase original de Clientes
            GestionClientes moduloClientes = new GestionClientes(); 
            RegistroPagos moduloPagos = new RegistroPagos();
            ListaAsignaciones moduloAsignaciones = new ListaAsignaciones(); 
            GestionPersonal moduloPersonal = new GestionPersonal();
            GestionInventario moduloInventario = new GestionInventario();
            BitacoraOperaciones moduloOperaciones = new BitacoraOperaciones();

            // 3. Lo registramos en la primera posición de la baraja física
            ventana.agregarPanelModulo(moduloClientes, "MODULO_CLIENTES"); 
            ventana.agregarPanelModulo(moduloPagos, "MODULO_PAGOS");     
            ventana.agregarPanelModulo(moduloAsignaciones, "MODULO_RENDIMIENTO");
            ventana.agregarPanelModulo(moduloPersonal, "MODULO_PERSONAL");
            ventana.agregarPanelModulo(moduloInventario, "MODULO_INVENTARIO");
            ventana.agregarPanelModulo(moduloOperaciones, "MODULO_OPERACIONES");

            // 4. Acoplamos el controlador de cambio de tarjetas
            new ControllerDashboard();

            ventana.setVisible(true);
        });
    }
}