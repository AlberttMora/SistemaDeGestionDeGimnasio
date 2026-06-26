package com.gym.mvc.controllers.personal;

import com.gym.mvc.controllers.base.BaseController;
import com.gym.mvc.models.Administrador;
import com.gym.mvc.models.Entrenador;
import com.gym.mvc.models.Limpieza;
import com.gym.mvc.models.services.AdministradorService;
import com.gym.mvc.models.services.EntrenadorService;
import com.gym.mvc.models.services.LimpiezaService;
import com.gym.mvc.models.services.UserSession;
import com.gym.mvc.views.personal.GestionPersonal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PersonalController extends BaseController {

	private final GestionPersonal vista;
	private final AdministradorService adminService;
	private final EntrenadorService entrenadorService;
	private final LimpiezaService limpiezaService;

	public PersonalController(GestionPersonal vista) {
		this.vista = vista;
		this.adminService = new AdministradorService();
		this.entrenadorService = new EntrenadorService();
		this.limpiezaService = new LimpiezaService();
	}

	public GestionPersonal getVista() {
		return vista;
	}

	public void init() {
		setUpListeners();
		cargarDatosIniciales();
	}

	@Override
	public void setUpListeners() {
		vista.alContratar(e -> create());
		vista.alDarBaja(e -> destroy());
	}

	@Override
	public void cargarDatosIniciales() {
		index();
	}

	@Override
	public void index() {
		try {
			cargarAdministradores();
			cargarEntrenadores();
			cargarLimpieza();
		} catch (Exception ex) {
			mostrarError(ex.getMessage());
		}
	}

	private void cargarAdministradores() {
		DefaultTableModel modelo = vista.getModelAdmins();
		modelo.setRowCount(0);
		List<Administrador> lista = adminService.getAll();
		for (Administrador a : lista) {
			modelo.addRow(new Object[] { a.getIdPersonal(), a.getNombre() + " " + a.getApellido(), a.getEmail(),
					a.getTelefono(), a.getNivelAcceso() });
		}
	}

	private void cargarEntrenadores() {
		DefaultTableModel modelo = vista.getModelEntrenadores();
		modelo.setRowCount(0);
		List<Entrenador> lista = entrenadorService.getAll();
		for (Entrenador e : lista) {
			modelo.addRow(new Object[] { e.getIdPersonal(), e.getNombre() + " " + e.getApellido(), e.getEmail(),
					e.getEspecialidad(), e.getCertificacion() });
		}
	}

	private void cargarLimpieza() {
		DefaultTableModel modelo = vista.getModelLimpieza();
		modelo.setRowCount(0);
		List<Limpieza> lista = limpiezaService.getAll();
		for (Limpieza l : lista) {
			modelo.addRow(new Object[] { l.getIdPersonal(), l.getNombre() + " " + l.getApellido(), l.getTelefono(),
					l.getTurno(), l.getAreaAsignada() });
		}
	}

	@Override
	public void create() {
		String rol = vista.getRolSeleccionado();
		try {
			if ("Administrador".equals(rol)) {
				Administrador a = new Administrador();
				a.setNombre(vista.obtenerNombre());
				a.setApellido(vista.obtenerApellido());
				a.setEmail(vista.obtenerEmail());
				a.setTelefono(vista.obtenerTelefono());
				a.setContrasena(vista.obtenerContrasena());
				a.setNivelAcceso(vista.obtenerNivelAcceso());
				adminService.store(a);
			} else if ("Entrenador".equals(rol)) {
				Entrenador e = new Entrenador();
				e.setNombre(vista.obtenerNombre());
				e.setApellido(vista.obtenerApellido());
				e.setEmail(vista.obtenerEmail());
				e.setTelefono(vista.obtenerTelefono());
				e.setContrasena(vista.obtenerContrasena());
				e.setEspecialidad(vista.obtenerEspecialidad());
				e.setCertificacion(vista.obtenerCertificacion());
				entrenadorService.store(e);
			} else if ("Limpieza".equals(rol)) {
				Limpieza l = new Limpieza();
				l.setNombre(vista.obtenerNombre());
				l.setApellido(vista.obtenerApellido());
				l.setEmail(vista.obtenerEmail());
				l.setTelefono(vista.obtenerTelefono());
				l.setContrasena(vista.obtenerContrasena());
				l.setTurno(vista.obtenerTurno());
				l.setAreaAsignada(vista.obtenerArea());
				limpiezaService.store(l);
			}
			vista.limpiarFormulario();
			cargarDatosIniciales();
			mostrarMensaje("Empleado contratado con exito");
		} catch (IllegalArgumentException ex) {
			mostrarError(ex.getMessage());
		} catch (Exception ex) {
			mostrarError("No se pudo contratar al empleado");
		}
	}

	@Override
	public void destroy() {
		if (!UserSession.getInstance().isAdmin()) {
			mostrarMensaje("Solo el administrador puede dar de baja personal");
			return;
		}

		int idSeleccionado = obtenerIdSeleccionado();
		if (idSeleccionado <= 0) {
			mostrarMensaje("Seleccione un empleado de la tabla");
			return;
		}

		int confirmacion = JOptionPane.showConfirmDialog(vista, "Desea dar de baja a este empleado?", "Confirmar baja",
				JOptionPane.YES_NO_OPTION);
		if (confirmacion != JOptionPane.YES_OPTION) {
			return;
		}

		try {
			switch (vista.getPestanaSeleccionada()) {
			case 0:
				adminService.destroy(idSeleccionado);
				break;
			case 1:
				entrenadorService.destroy(idSeleccionado);
				break;
			case 2:
				limpiezaService.destroy(idSeleccionado);
				break;
			}
			cargarDatosIniciales();
			mostrarMensaje("Empleado dado de baja");
		} catch (Exception ex) {
			mostrarError("No se pudo dar de baja al empleado");
		}
	}

	private int obtenerIdSeleccionado() {
		JTable tabla;
		switch (vista.getPestanaSeleccionada()) {
		case 0:
			tabla = vista.getTablaAdmin();
			break;
		case 1:
			tabla = vista.getTablaEntrenador();
			break;
		case 2:
			tabla = vista.getTablaLimpieza();
			break;
		default:
			return -1;
		}

		int fila = tabla.getSelectedRow();
		if (fila == -1) {
			return -1;
		}
		return (int) tabla.getValueAt(fila, 0);
	}

	private void mostrarMensaje(String msg) {
		JOptionPane.showMessageDialog(vista, msg);
	}

	private void mostrarError(String msg) {
		JOptionPane.showMessageDialog(vista, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
}