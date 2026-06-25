package com.gym.mvc.controllers.cliente;

import com.gym.mvc.controllers.base.IController;
import com.gym.mvc.models.Cliente;
import com.gym.mvc.models.services.ClienteService;
import com.gym.mvc.models.services.IService;
import com.gym.mvc.views.cliente.GestionClientes;
import com.gym.mvc.views.cliente.ListaClientes;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClienteController implements IController {

    private final GestionClientes vistaGestion;
    private final ListaClientes vistaLista;
    private final IService<Cliente> service;
    private final SimpleDateFormat dateFormat;

    public ClienteController() {
        this.vistaGestion = new GestionClientes();
        this.vistaLista = new ListaClientes();
        this.service = new ClienteService();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public void setUpListeners() {
        vistaGestion.setControlador(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarEventosGestion(e.getActionCommand());
            }
        });

        vistaLista.setControlador(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarEventosLista(e.getActionCommand());
            }
        });

        vistaLista.setEscuchadorBusqueda(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtrarClientes();
            }
        });
    }

    private void procesarEventosGestion(String comando) {
        switch (comando) {
            case "BUSCAR": show(); break;
            case "GUARDAR": create(); break;
            case "ELIMINAR": destroy(); break;
            case "LIMPIAR": vistaGestion.limpiar(); break;
        }
    }

    private void procesarEventosLista(String comando) {
        switch (comando) {
            case "NUEVO": vistaGestion.limpiar(); break;
            case "EDITAR": edit(); break;
            case "ELIMINAR": eliminarDesdeLista(); break;
        }
    }

    @Override
    public void cargarDatosIniciales() {
        try {
            List<Cliente> clientes = service.getAll();
            renderizarTabla(clientes);
        } catch (Exception ex) {
            mostrarError("Error al cargar clientes: " + ex.getMessage());
        }
    }

    private void renderizarTabla(List<Cliente> clientes) {
        DefaultTableModel modelo = vistaLista.getModelo();
        modelo.setRowCount(0);
        for (Cliente c : clientes) {
            modelo.addRow(new Object[]{
                c.getIdCliente(),
                c.getNombre(),
                c.getApellido(),
                c.getEmail(),
                c.getTelefono(),
                c.getFechaNac() != null ? dateFormat.format(c.getFechaNac()) : "",
                c.getFotoRuta()
            });
        }
        vistaLista.actualizarTotal();
    }

    @Override
    public void index() {
        cargarDatosIniciales();
    }

    @Override
    public void create() {
        try {
            Map<String, String> datos = vistaGestion.getDatosFormulario();
            Cliente cliente = mapToEntity(datos);

            if (cliente.getIdCliente() > 0) {
                service.update(cliente);
                mostrarMensaje("Cliente actualizado correctamente.");
            } else {
                service.store(cliente);
                mostrarMensaje("Cliente guardado correctamente.");
            }

            vistaGestion.limpiar();
            cargarDatosIniciales();
        } catch (IllegalArgumentException ex) {
            mostrarAdvertencia(ex.getMessage());
        } catch (Exception ex) {
            mostrarError("Error al procesar cliente: " + ex.getMessage());
        }
    }

    @Override
    public void show() {
        String idBusqueda = vistaGestion.getIdBusqueda();
        if (idBusqueda.isEmpty()) {
            mostrarAdvertencia("Ingrese un ID para buscar.");
            return;
        }

        try {
            int id = Integer.parseInt(idBusqueda);
            Cliente cliente = service.findById(id);
            if (cliente != null) {
                poblarFormularioGestion(cliente);
            } else {
                mostrarMensaje("Cliente no encontrado.");
            }
        } catch (NumberFormatException ex) {
            mostrarAdvertencia("El ID debe ser un número entero.");
        } catch (Exception ex) {
            mostrarError("Error al buscar cliente: " + ex.getMessage());
        }
    }

    @Override
    public void edit() {
        int fila = vistaLista.getFilaSeleccionada();
        if (fila == -1) {
            mostrarAdvertencia("Seleccione un cliente de la lista para editar.");
            return;
        }

        try {
            int id = (int) vistaLista.getTabla().getValueAt(fila, 0);
            Cliente cliente = service.findById(id);
            if (cliente != null) {
                poblarFormularioGestion(cliente);
            }
        } catch (Exception ex) {
            mostrarError("Error al cargar datos para edición: " + ex.getMessage());
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void destroy() {
        Map<String, String> datos = vistaGestion.getDatosFormulario();
        if (datos.get("id").isEmpty()) {
            mostrarAdvertencia("No hay ningún cliente cargado para eliminar.");
            return;
        }

        try {
            int id = Integer.parseInt(datos.get("id"));
            if (confirmarAccion("¿Desea eliminar el cliente actual?")) {
                service.destroy(id);
                mostrarMensaje("Cliente eliminado correctamente.");
                vistaGestion.limpiar();
                cargarDatosIniciales();
            }
        } catch (Exception ex) {
            mostrarError("Error al eliminar cliente: " + ex.getMessage());
        }
    }

    private void eliminarDesdeLista() {
        int fila = vistaLista.getFilaSeleccionada();
        if (fila == -1) {
            mostrarAdvertencia("Seleccione un cliente de la lista para eliminar.");
            return;
        }

        try {
            int id = (int) vistaLista.getTabla().getValueAt(fila, 0);
            if (confirmarAccion("¿Desea eliminar este cliente?")) {
                service.destroy(id);
                mostrarMensaje("Cliente eliminado correctamente.");
                cargarDatosIniciales();
            }
        } catch (Exception ex) {
            mostrarError("Error al eliminar cliente: " + ex.getMessage());
        }
    }

    private void filtrarClientes() {
        String criterio = vistaLista.getTextoBusqueda().toLowerCase();
        try {
            List<Cliente> todos = service.getAll();
            List<Cliente> filtrados = todos.stream()
                .filter(c -> c.getNombre().toLowerCase().contains(criterio) ||
                             c.getApellido().toLowerCase().contains(criterio) ||
                             c.getEmail().toLowerCase().contains(criterio))
                .collect(Collectors.toList());
            renderizarTabla(filtrados);
        } catch (Exception ex) {
            mostrarError("Error al filtrar: " + ex.getMessage());
        }
    }

    private Cliente mapToEntity(Map<String, String> datos) throws Exception {
        Cliente c = new Cliente();
        if (datos.get("id") != null && !datos.get("id").isEmpty()) {
            c.setIdCliente(Integer.parseInt(datos.get("id")));
        }
        c.setNombre(datos.get("nombre"));
        c.setApellido(datos.get("apellido"));
        c.setEmail(datos.get("email"));
        c.setTelefono(datos.get("telefono"));
        c.setFotoRuta(datos.get("fotoRuta"));
        
        String fechaStr = datos.get("fechaNac");
        c.setFechaNac(fechaStr != null && !fechaStr.isEmpty() ? dateFormat.parse(fechaStr) : null);
        return c;
    }

    private void poblarFormularioGestion(Cliente c) {
        Map<String, String> datos = new HashMap<>();
        datos.put("id", String.valueOf(c.getIdCliente()));
        datos.put("nombre", c.getNombre());
        datos.put("apellido", c.getApellido());
        datos.put("email", c.getEmail());
        datos.put("telefono", c.getTelefono());
        datos.put("fotoRuta", c.getFotoRuta());
        datos.put("fechaNac", c.getFechaNac() != null ? dateFormat.format(c.getFechaNac()) : "");
        vistaGestion.setDatosFormulario(datos);
    }

    private void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(vistaGestion, msg, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarAdvertencia(String msg) {
        JOptionPane.showMessageDialog(vistaGestion, msg, "Validación", JOptionPane.WARNING_MESSAGE);
    }

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(vistaGestion, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private boolean confirmarAccion(String msg) {
        return JOptionPane.showConfirmDialog(vistaGestion, msg, "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}