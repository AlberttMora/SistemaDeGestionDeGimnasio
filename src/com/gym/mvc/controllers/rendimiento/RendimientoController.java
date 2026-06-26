package com.gym.mvc.controllers.rendimiento;

import com.gym.mvc.controllers.base.BaseController;
import com.gym.mvc.models.AsignacionEntrenador;
import com.gym.mvc.models.Cliente;
import com.gym.mvc.models.DetallePeso;
import com.gym.mvc.models.Entrenador;
import com.gym.mvc.models.Equipo;
import com.gym.mvc.models.services.AsignacionService;
import com.gym.mvc.models.services.ClienteService;
import com.gym.mvc.models.services.EntrenadorService;
import com.gym.mvc.models.services.EquipoService;
import com.gym.mvc.models.repositories.DetallePesoRepository;
import com.gym.mvc.views.rendimiento.ControlProgreso;
import com.gym.mvc.views.rendimiento.ListaAsignaciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class RendimientoController extends BaseController {

    private final ListaAsignaciones vistaAsignaciones;
    private final ControlProgreso vistaProgreso;
    private final AsignacionService asignacionService;
    private final EntrenadorService entrenadorService;
    private final ClienteService clienteService;
    private final EquipoService equipoService;
    private final DetallePesoRepository detallePesoRepo;
    private final SimpleDateFormat formatoFecha;

    public RendimientoController(ListaAsignaciones vistaAsignaciones, ControlProgreso vistaProgreso) {
        this.vistaAsignaciones = vistaAsignaciones;
        this.vistaProgreso     = vistaProgreso;
        this.asignacionService = new AsignacionService();
        this.entrenadorService = new EntrenadorService();
        this.clienteService    = new ClienteService();
        this.equipoService     = new EquipoService();
        this.detallePesoRepo   = new DetallePesoRepository();
        this.formatoFecha      = new SimpleDateFormat("dd/MM/yyyy");
    }

    public ListaAsignaciones getVistaAsignaciones() {
        return vistaAsignaciones;
    }

    public ControlProgreso getVistaProgreso() {
        return vistaProgreso;
    }

    public void init() {
        setUpListeners();
        cargarDatosIniciales();
    }

    @Override
    public void setUpListeners() {
        vistaAsignaciones.alAsignar(e -> create());
        vistaAsignaciones.alEliminar(e -> destroy());
        vistaProgreso.getBtnRegistrar().addActionListener(e -> registrarPeso());
        vistaProgreso.getBtnLimpiar().addActionListener(e -> vistaProgreso.limpiarFormulario());
    }

    @Override
    public void cargarDatosIniciales() {
        cargarComboEquipos();
        index();
    }

    private void cargarComboEquipos() {
        JComboBox<String> combo = vistaProgreso.getCbIdEquipo();
        combo.removeAllItems();
        try {
            List<Equipo> equipos = equipoService.getAll();
            for (Equipo eq : equipos) {
                combo.addItem(eq.getIdEquipo() + " - " + eq.getNombre());
            }
        } catch (Exception ex) {
            vistaAsignaciones.mostrarError("No se pudo cargar el listado de equipos");
        }
    }

    @Override
    public void index() {
        try {
            cargarTablaAsignaciones();
            cargarTablaProgreso();
        } catch (Exception ex) {
            vistaAsignaciones.mostrarError(ex.getMessage());
        }
    }

    private void cargarTablaAsignaciones() {
        DefaultTableModel modelo = vistaAsignaciones.getModelo();
        modelo.setRowCount(0);
        List<AsignacionEntrenador> lista = asignacionService.getAll();
        for (AsignacionEntrenador a : lista) {
            String nombreEntrenador = resolverNombreEntrenador(a.getIdEntrenador());
            String nombreCliente    = resolverNombreCliente(a.getIdCliente());
            modelo.addRow(new Object[]{
                a.getIdAsignacion(),
                a.getIdEntrenador(), nombreEntrenador,
                a.getIdCliente(),    nombreCliente,
                formatoFecha.format(a.getFechaInicio()),
                a.getFechaFin() != null ? formatoFecha.format(a.getFechaFin()) : "",
                a.getObservaciones()
            });
        }
        vistaAsignaciones.actualizarTotal();
    }

    private String resolverNombreEntrenador(int idEntrenador) {
        Entrenador e = entrenadorService.findById(idEntrenador);
        return e != null ? e.getNombre() + " " + e.getApellido() : "";
    }

    private String resolverNombreCliente(int idCliente) {
        Cliente c = clienteService.findById(idCliente);
        return c != null ? c.getNombre() + " " + c.getApellido() : "";
    }

    private void cargarTablaProgreso() {
        DefaultTableModel modelo = vistaProgreso.getModeloTabla();
        modelo.setRowCount(0);
        List<DetallePeso> lista = detallePesoRepo.readAll();
        for (DetallePeso dp : lista) {
            modelo.addRow(new Object[]{dp.getIdEquipo(), dp.getPesoKg()});
        }
    }

    @Override
    public void create() {
        try {
            AsignacionEntrenador a = mapToEntity();
            asignacionService.store(a);
            vistaAsignaciones.limpiarFormulario();
            cargarTablaAsignaciones();
            vistaAsignaciones.mostrarMensaje("Asignacion guardada correctamente");
        } catch (IllegalArgumentException ex) {
            vistaAsignaciones.mostrarError(ex.getMessage());
        } catch (ParseException ex) {
            vistaAsignaciones.mostrarError("Formato de fecha invalido, use dd/MM/yyyy");
        } catch (Exception ex) {
            vistaAsignaciones.mostrarError("No se pudo guardar la asignacion: " + ex.getMessage());
        }
    }

    private AsignacionEntrenador mapToEntity() throws ParseException {
        String idEntrenadorStr = vistaAsignaciones.getIdEntrenador();
        String idClienteStr    = vistaAsignaciones.getIdCliente();

        if (idEntrenadorStr.isEmpty() || idClienteStr.isEmpty() || vistaAsignaciones.getFechaInicio().isEmpty()) {
            throw new IllegalArgumentException("Complete los campos obligatorios");
        }

        int idEntrenador = Integer.parseInt(idEntrenadorStr);
        int idCliente    = Integer.parseInt(idClienteStr);

        Entrenador entrenador = entrenadorService.findById(idEntrenador);
        if (entrenador == null) {
            throw new IllegalArgumentException("El ID " + idEntrenador + " no corresponde a un entrenador registrado");
        }

        Cliente cliente = clienteService.findById(idCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("El ID " + idCliente + " no corresponde a un cliente registrado");
        }

        AsignacionEntrenador a = new AsignacionEntrenador();
        a.setIdEntrenador(idEntrenador);
        a.setIdCliente(idCliente);
        a.setFechaInicio(formatoFecha.parse(vistaAsignaciones.getFechaInicio()));

        String fechaFinStr = vistaAsignaciones.getFechaFin();
        if (fechaFinStr != null && !fechaFinStr.trim().isEmpty()) {
            a.setFechaFin(formatoFecha.parse(fechaFinStr));
        } else {
            a.setFechaFin(null);
        }

        a.setObservaciones(vistaAsignaciones.getObservaciones());
        return a;
    }

    @Override
    public void destroy() {
        int fila = vistaAsignaciones.getTabla().getSelectedRow();
        if (fila == -1) {
            vistaAsignaciones.mostrarMensaje("Seleccione una asignacion");
            return;
        }

        if (!vistaAsignaciones.confirmarAccion("Desea eliminar la asignacion seleccionada?")) {
            return;
        }

        try {
            int id = (int) vistaAsignaciones.getTabla().getValueAt(fila, 0);
            asignacionService.destroy(id);
            cargarTablaAsignaciones();
        } catch (Exception ex) {
            vistaAsignaciones.mostrarError("No se pudo eliminar la asignacion");
        }
    }

    private void registrarPeso() {
        String equipoSeleccionado = vistaProgreso.getEquipoSeleccionado();
        String pesoStr = vistaProgreso.getPesoKg();

        if (equipoSeleccionado == null || pesoStr.isEmpty()) {
            vistaAsignaciones.mostrarMensaje("Seleccione un equipo e ingrese el peso");
            return;
        }

        try {
            int idEquipo = Integer.parseInt(equipoSeleccionado.split(" - ")[0].trim());
            double peso  = Double.parseDouble(pesoStr);

            DetallePeso dp = new DetallePeso(idEquipo, peso);
            detallePesoRepo.create(dp);

            vistaProgreso.limpiarFormulario();
            cargarTablaProgreso();
        } catch (NumberFormatException ex) {
            vistaAsignaciones.mostrarError("El peso debe ser un numero valido");
        } catch (Exception ex) {
            vistaAsignaciones.mostrarError("No se pudo registrar la metrica: " + ex.getMessage());
        }
    }
}