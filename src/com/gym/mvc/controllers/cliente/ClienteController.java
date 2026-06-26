package com.gym.mvc.controllers.cliente;

import com.gym.mvc.controllers.base.IController;
import com.gym.mvc.models.Cliente;
import com.gym.mvc.models.services.ClienteService;
import com.gym.mvc.models.services.IService;
import com.gym.mvc.views.cliente.GestionClientes;
import com.gym.mvc.views.cliente.ListaClientes;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClienteController implements IController {

	private final GestionClientes vistaGestion;
	private final ListaClientes vistaLista;
	private final IService<Cliente> service;

	public ClienteController() {
		this.vistaGestion = new GestionClientes();
		this.vistaLista = new ListaClientes();
		this.service = new ClienteService();
	}

	public GestionClientes getVista() {
		return vistaGestion;
	}

	@Override
	public void setUpListeners() {
		vistaGestion.getBtnBuscar().addActionListener(e -> show());
		vistaGestion.getBtnGuardar().addActionListener(e -> create());
		vistaGestion.getBtnEliminar().addActionListener(e -> destroy());
		vistaGestion.getBtnLimpiar().addActionListener(e -> vistaGestion.limpiar());
		vistaGestion.getBtnExaminarFoto().addActionListener(e -> examinarFoto());
		vistaLista.getBtnNuevo().addActionListener(e -> vistaGestion.limpiar());
		vistaLista.getBtnEditar().addActionListener(e -> edit());
		vistaLista.getBtnElim().addActionListener(e -> eliminarDesdeLista());

		vistaLista.getTxtBuscar().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filtrarClientes();
			}
		});
	}

	private void examinarFoto() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Seleccionar foto del cliente");
		chooser.setFileFilter(
				new FileNameExtensionFilter("Imágenes (jpg, jpeg, png, gif)", "jpg", "jpeg", "png", "gif"));
		chooser.setAcceptAllFileFilterUsed(false);

		int resultado = chooser.showOpenDialog(vistaGestion);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			String ruta = chooser.getSelectedFile().getAbsolutePath();
			vistaGestion.setFotoRuta(ruta); 
		}
	}

	@Override
	public void cargarDatosIniciales() {
		try {
			List<Cliente> clientes = service.getAll();
			renderizarTabla(clientes);
		} catch (Exception ex) {
			vistaGestion.mostrarError("Error al cargar clientes: " + ex.getMessage());
		}
	}

	private void renderizarTabla(List<Cliente> clientes) {
		DefaultTableModel modelo = vistaLista.getModelo();
		modelo.setRowCount(0);
		for (Cliente c : clientes) {
			modelo.addRow(new Object[] { c.getIdCliente(), c.getNombre(), c.getApellido(), c.getEmail(),
					c.getTelefono(), c.getFechaNac(), c.getFotoRuta() });
		}
		vistaLista.actualizarTotal();
	}

	@Override
	public void index() {
		cargarDatosIniciales();
	}

	@Override
	public void create() {
		if (!vistaGestion.validarFormulario())
			return;

		try {
			Map<String, Object> datos = vistaGestion.getDatosFormulario();
			Cliente cliente = mapToEntity(datos);

			if (cliente.getIdCliente() > 0) {
				service.update(cliente);
				vistaGestion.mostrarMensaje("Cliente actualizado correctamente.");
			} else {
				service.store(cliente);
				vistaGestion.mostrarMensaje("Cliente registrado correctamente.");
			}

			vistaGestion.limpiar();
			cargarDatosIniciales();
		} catch (Exception ex) {
			vistaGestion.mostrarError("Error al guardar: " + ex.getMessage());
		}
	}

	@Override
	public void show() {
		String idBusqueda = vistaGestion.getIdBusqueda();
		if (idBusqueda.isEmpty()) {
			vistaGestion.mostrarAdvertencia("Ingrese un ID para buscar.");
			return;
		}

		try {
			int id = Integer.parseInt(idBusqueda);
			Cliente cliente = service.findById(id);
			if (cliente != null) {
				poblarFormularioGestion(cliente);
			} else {
				vistaGestion.mostrarAdvertencia("No se encontro cliente con ID: " + id);
			}
		} catch (NumberFormatException ex) {
			vistaGestion.mostrarAdvertencia("El ID debe ser un numero.");
		} catch (Exception ex) {
			vistaGestion.mostrarError("Error al buscar: " + ex.getMessage());
		}
	}

	@Override
	public void edit() {
		int fila = vistaLista.getFilaSeleccionada();
		if (fila == -1) {
			vistaGestion.mostrarAdvertencia("Seleccione un cliente de la lista.");
			return;
		}

		try {
			int id = (int) vistaLista.getTabla().getValueAt(fila, 0);
			Cliente cliente = service.findById(id);
			if (cliente != null) {
				poblarFormularioGestion(cliente);
			}
		} catch (Exception ex) {
			vistaGestion.mostrarError("Error al editar: " + ex.getMessage());
		}
	}

	@Override
	public void update() {
	}

	@Override
	public void destroy() {
		Map<String, Object> datos = vistaGestion.getDatosFormulario();
		String idStr = (String) datos.get("id");
		if (idStr == null || idStr.isEmpty()) {
			vistaGestion.mostrarAdvertencia("Busque un cliente antes de eliminar.");
			return;
		}

		try {
			int id = Integer.parseInt(idStr);
			if (vistaGestion.confirmarAccion("¿Desea eliminar este cliente?")) {
				service.destroy(id);
				vistaGestion.limpiar();
				cargarDatosIniciales();
				vistaGestion.mostrarMensaje("Cliente eliminado.");
			}
		} catch (Exception ex) {
			vistaGestion.mostrarError("Error al eliminar: " + ex.getMessage());
		}
	}

	private void eliminarDesdeLista() {
		int fila = vistaLista.getFilaSeleccionada();
		if (fila == -1) {
			vistaGestion.mostrarAdvertencia("Seleccione un cliente de la lista.");
			return;
		}

		try {
			int id = (int) vistaLista.getTabla().getValueAt(fila, 0);
			if (vistaGestion.confirmarAccion("¿Desea eliminar este cliente?")) {
				service.destroy(id);
				cargarDatosIniciales();
				vistaGestion.mostrarMensaje("Cliente eliminado.");
			}
		} catch (Exception ex) {
			vistaGestion.mostrarError("Error al eliminar: " + ex.getMessage());
		}
	}

	private void filtrarClientes() {
		String criterio = vistaLista.getTextoBusqueda().toLowerCase();
		try {
			List<Cliente> todos = service.getAll();
			List<Cliente> filtrados = todos.stream()
					.filter(c -> c.getNombre().toLowerCase().contains(criterio)
							|| c.getApellido().toLowerCase().contains(criterio)
							|| c.getEmail().toLowerCase().contains(criterio))
					.collect(Collectors.toList());
			renderizarTabla(filtrados);
		} catch (Exception ex) {
			vistaGestion.mostrarError("Error al filtrar: " + ex.getMessage());
		}
	}

	private Cliente mapToEntity(Map<String, Object> datos) {
		Cliente c = new Cliente();
		String idStr = (String) datos.get("id");
		if (idStr != null && !idStr.isEmpty()) {
			c.setIdCliente(Integer.parseInt(idStr));
		}
		c.setNombre((String) datos.get("nombre"));
		c.setApellido((String) datos.get("apellido"));
		c.setEmail((String) datos.get("email"));
		c.setTelefono((String) datos.get("telefono"));
		c.setFotoRuta((String) datos.get("fotoRuta"));
		c.setFechaNac((Date) datos.get("fechaNac"));
		return c;
	}

	private void poblarFormularioGestion(Cliente c) {
		vistaGestion.poblarCampos(c.getIdCliente(), c.getNombre(), c.getApellido(), c.getEmail(), c.getTelefono(),
				c.getFotoRuta(), c.getFechaNac());
	}
}