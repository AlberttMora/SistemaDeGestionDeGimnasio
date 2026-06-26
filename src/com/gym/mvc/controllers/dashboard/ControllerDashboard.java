package com.gym.mvc.controllers.dashboard;

import com.gym.mvc.controllers.base.BaseController;
import com.gym.mvc.controllers.cliente.ClienteController;
import com.gym.mvc.controllers.inventario.InventarioController;
import com.gym.mvc.controllers.operaciones.OperacionesController;
import com.gym.mvc.controllers.pago.PagoController;
import com.gym.mvc.controllers.personal.PersonalController;
import com.gym.mvc.controllers.rendimiento.RendimientoController;
import com.gym.mvc.controllers.reportes.ReportesController;
import com.gym.mvc.models.services.UserSession;
import com.gym.mvc.views.inventario.GestionInventario;
import com.gym.mvc.views.operaciones.BitacoraOperaciones;
import com.gym.mvc.views.personal.GestionPersonal;
import com.gym.mvc.views.principal.VentanaPrincipal;
import com.gym.mvc.views.rendimiento.ControlProgreso;
import com.gym.mvc.views.rendimiento.ListaAsignaciones;
import com.gym.mvc.views.reportes.PanelReportes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControllerDashboard extends BaseController {

	private final VentanaPrincipal vista;

	public ControllerDashboard() {
		this.vista = new VentanaPrincipal();
	}

	public void init() {
		inicializarModulos();
		setUpListeners();
		aplicarPermisos();
		mostrarBienvenida();
		index();
	}

	private void inicializarModulos() {
		ClienteController clienteController = new ClienteController();
		clienteController.setUpListeners();
		clienteController.cargarDatosIniciales();
		vista.agregarPanelModulo(clienteController.getVista(), "MODULO_CLIENTES");

		PagoController pagoController = new PagoController();
		pagoController.setUpListeners();
		pagoController.cargarDatosIniciales();
		vista.agregarPanelModulo(pagoController.getVista(), "MODULO_PAGOS");

		ListaAsignaciones vistaAsignaciones = new ListaAsignaciones();
		ControlProgreso vistaProgreso = new ControlProgreso();
		RendimientoController rendimientoController = new RendimientoController(vistaAsignaciones, vistaProgreso);
		rendimientoController.init();
		vista.agregarPanelModulo(vistaAsignaciones, "MODULO_RENDIMIENTO");
		vista.agregarPanelModulo(vistaProgreso, "MODULO_PROGRESO");

		GestionPersonal vistaPersonal = new GestionPersonal();
		PersonalController personalController = new PersonalController(vistaPersonal);
		personalController.init();
		vista.agregarPanelModulo(vistaPersonal, "MODULO_PERSONAL");

		GestionInventario vistaInventario = new GestionInventario();
		InventarioController inventarioController = new InventarioController(vistaInventario);
		inventarioController.init();
		vista.agregarPanelModulo(vistaInventario, "MODULO_INVENTARIO");

		BitacoraOperaciones vistaOperaciones = new BitacoraOperaciones();
		OperacionesController opsController = new OperacionesController(vistaOperaciones);
		opsController.init();
		vista.agregarPanelModulo(vistaOperaciones, "MODULO_OPERACIONES");

		PanelReportes vistaReportes = new PanelReportes();
		ReportesController reportesController = new ReportesController(vistaReportes);
		reportesController.init();
		vista.agregarPanelModulo(vistaReportes, "MODULO_REPORTES");
	}

	@Override
	public void setUpListeners() {
		vista.getBtnInicio().addActionListener(e -> vista.mostrarPanel("MENU_PRINCIPAL"));

		vista.alSeleccionarClientes(crearNavegadorHacia("MODULO_CLIENTES", "CLIENTES"));
		vista.alSeleccionarPagos(crearNavegadorHacia("MODULO_PAGOS", "PAGOS"));
		vista.alSeleccionarRendimiento(crearNavegadorHacia("MODULO_RENDIMIENTO", "RENDIMIENTO"));
		vista.alSeleccionarProgreso(crearNavegadorHacia("MODULO_PROGRESO", "PROGRESO"));
		vista.alSeleccionarPersonal(crearNavegadorHacia("MODULO_PERSONAL", "PERSONAL"));
		vista.alSeleccionarInventario(crearNavegadorHacia("MODULO_INVENTARIO", "INVENTARIO"));
		vista.alSeleccionarOperaciones(crearNavegadorHacia("MODULO_OPERACIONES", "OPERACIONES"));
		vista.alSeleccionarReportes(crearNavegadorHacia("MODULO_REPORTES", "REPORTES"));
	}

	private void aplicarPermisos() {
		UserSession session = UserSession.getInstance();

		if (session.isAdmin()) {
			configurarVisible("CLIENTES", "PAGOS", "RENDIMIENTO", "PERSONAL", "INVENTARIO", "OPERACIONES", "REPORTES");

		} else if (session.isEntrenador()) {
			configurarVisible("CLIENTES", "RENDIMIENTO", "PROGRESO");

		} else if (session.isLimpieza()) {
			configurarVisible("OPERACIONES", "INVENTARIO");

		} else if (session.isCliente()) {
			configurarVisible("PROGRESO", "PAGOS");
		}
	}

	private void configurarVisible(String... modulos) {
		vista.ocultarTodo();
		for (String modulo : modulos) {
			vista.setModuloVisible(modulo, true);
		}
	}

	private void mostrarBienvenida() {
		UserSession session = UserSession.getInstance();
		String nombre;
		String rolTexto;

		if (session.isCliente()) {
			nombre = session.getLoggedCliente().getNombre();
			rolTexto = "CLIENTE";
		} else {
			nombre = session.getLoggedPersonal().getNombre();
			rolTexto = session.getRol() != null ? session.getRol().name() : "";
		}

		vista.setNombreUsuario(nombre + " | " + rolTexto);
		vista.mostrarMensaje("Bienvenido al sistema, " + nombre + ".\nSesion iniciada como: " + rolTexto);
	}

	@Override
	public void index() {
		vista.setVisible(true);
	}

	private MouseAdapter crearNavegadorHacia(String nombrePanel, String clavePermiso) {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				UserSession session = UserSession.getInstance();
				if (!tieneAcceso(session.getRol(), clavePermiso)) {
					vista.mostrarMensaje("Acceso restringido para su rol.");
					return;
				}
				vista.mostrarPanel(nombrePanel);
			}
		};
	}

	private boolean tieneAcceso(UserSession.Rol rol, String modulo) {
		if (rol == null)
			return false;

		switch (rol) {
		case ADMIN:
			return true;

		case ENTRENADOR:
			return modulo.equals("CLIENTES") || modulo.equals("RENDIMIENTO") || modulo.equals("PROGRESO");

		case LIMPIEZA:
			return modulo.equals("INVENTARIO") || modulo.equals("OPERACIONES");

		case CLIENTE:
			return modulo.equals("PAGOS") || modulo.equals("PROGRESO");

		default:
			return false;
		}
	}
}