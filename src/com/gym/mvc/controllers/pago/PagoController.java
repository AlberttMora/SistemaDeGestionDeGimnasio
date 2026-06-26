package com.gym.mvc.controllers.pago;

import com.gym.mvc.controllers.base.IController;
import com.gym.mvc.models.Cliente;
import com.gym.mvc.models.Inscripcion;
import com.gym.mvc.models.Pago;
import com.gym.mvc.models.services.ClienteService;
import com.gym.mvc.models.services.InscripcionService;
import com.gym.mvc.models.services.IService;
import com.gym.mvc.models.services.PagoService;
import com.gym.mvc.views.inscripcion.RegistroPagos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PagoController implements IController {

	private final RegistroPagos vista;
	private final IService<Pago> service;
	private final InscripcionService inscripcionService;
	private final ClienteService clienteService;

	public PagoController() {
		this.vista = new RegistroPagos();
		this.service = new PagoService();
		this.inscripcionService = new InscripcionService();
		this.clienteService = new ClienteService();
	}

	public RegistroPagos getVista() {
		return vista;
	}

	@Override
	public void setUpListeners() {
		vista.setControlador(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				procesarEventosAccion(e.getActionCommand());
			}
		}, new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				actualizarResumenVista();
			}
		});
	}

	private void procesarEventosAccion(String comando) {
		switch (comando) {
		case "BUSCAR_INSCRIPCION":
			buscarInscripcion();
			break;
		case "REGISTRAR_PAGO":
			create();
			break;
		case "LIMPIAR":
			vista.limpiarFormulario();
			break;
		}
	}

	private void buscarInscripcion() {
		try {
			Map<String, Object> datos = vista.getDatosFormulario();
			String idStr = (String) datos.get("idInscripcion");
			if (idStr.isEmpty()) {
				vista.mostrarAdvertencia("Debe ingresar un ID de inscripcion.");
				return;
			}

			int idInscripcion = Integer.parseInt(idStr);
			Inscripcion inscripcion = inscripcionService.findById(idInscripcion);

			if (inscripcion == null) {
				vista.mostrarAdvertencia("No se encontro una inscripcion con ID " + idInscripcion);
				return;
			}

			Cliente cliente = clienteService.findById(inscripcion.getIdCliente());
			String nombreCliente = cliente != null ? cliente.getNombre() + " " + cliente.getApellido()
					: "ID: " + inscripcion.getIdCliente();

			vista.setNombreCliente(nombreCliente);
			actualizarResumenVista();

		} catch (NumberFormatException ex) {
			vista.mostrarAdvertencia("El ID de inscripcion debe ser un numero.");
		} catch (Exception ex) {
			vista.mostrarError("Error al buscar la inscripcion: " + ex.getMessage());
		}
	}

	private void actualizarResumenVista() {
		Map<String, Object> datos = vista.getDatosFormulario();
		vista.actualizarResumen((String) datos.get("idInscripcion"), (String) datos.get("nombreCliente"),
				(String) datos.get("monto"), (String) datos.get("metodoPago"), (String) datos.get("estadoInscripcion"));
	}

	@Override
	public void cargarDatosIniciales() {
	}

	@Override
	public void index() {
	}

	@Override
	public void create() {
		try {
			Map<String, Object> datos = vista.getDatosFormulario();
			String idStr = (String) datos.get("idInscripcion");
			String montoStr = (String) datos.get("monto");

			if (idStr.isEmpty() || montoStr.isEmpty()) {
				vista.mostrarAdvertencia("Complete el ID de inscripcion y el monto.");
				return;
			}

			Pago pago = mapToEntity(datos);
			service.store(pago);
			vista.mostrarMensaje("Pago registrado con exito.");
			vista.limpiarFormulario();

		} catch (IllegalArgumentException ex) {
			vista.mostrarAdvertencia(ex.getMessage());
		} catch (Exception ex) {
			vista.mostrarError("Error al registrar el pago: " + ex.getMessage());
		}
	}

	@Override
	public void show() {
	}

	@Override
	public void edit() {
	}

	@Override
	public void update() {
	}

	@Override
	public void destroy() {
	}

	private Pago mapToEntity(Map<String, Object> datos) throws Exception {
		Pago p = new Pago();
		p.setIdInscripcion(Integer.parseInt((String) datos.get("idInscripcion")));
		p.setMonto(Double.parseDouble((String) datos.get("monto")));

		String metodoPago = datos.get("metodoPago") != null ? datos.get("metodoPago").toString() : "";
		p.setMetodoPago(metodoPago);

		String fechaStr = (String) datos.get("fechaPago");
		if (fechaStr != null && !fechaStr.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = sdf.parse(fechaStr);
			p.setFechaPago(fecha);
		} else {
			p.setFechaPago(new Date());
		}
		return p;
	}
}