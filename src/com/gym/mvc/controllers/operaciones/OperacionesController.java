package com.gym.mvc.controllers.operaciones;

import com.gym.mvc.models.BitacoraMantenimiento;
import com.gym.mvc.models.Limpieza;
import com.gym.mvc.models.Equipo;
import com.gym.mvc.models.Personal;
import com.gym.mvc.models.services.BitacoraService;
import com.gym.mvc.models.services.LimpiezaService;
import com.gym.mvc.models.services.EquipoService;
import com.gym.mvc.models.services.PersonalService;
import com.gym.mvc.views.operaciones.BitacoraOperaciones;

import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OperacionesController {

    private BitacoraOperaciones vista;
    private BitacoraService bitacoraService;
    private LimpiezaService limpiezaService;
    private EquipoService equipoService;
    private PersonalService personalService;

    private Map<String, Integer> mapaEquipos = new HashMap<>();
    private Map<String, Integer> mapaPersonal = new HashMap<>();

    public OperacionesController(BitacoraOperaciones vista) {
        this.vista = vista;
        this.bitacoraService = new BitacoraService();
        this.limpiezaService = new LimpiezaService();
        this.equipoService = new EquipoService();
        this.personalService = new PersonalService();
        
        setupListeners();
        llenarCombos();
        cargarDatos();
    }

    protected void setupListeners() {
        vista.alGuardarMantenimiento(e -> guardarMantenimiento());
        vista.alRegistrarLimpieza(e -> registrarLimpieza());
    }

    private void llenarCombos() {
        vista.getComboEquipos().removeAllItems();
        mapaEquipos.clear();
        for (Equipo e : equipoService.getAll()) {
            mapaEquipos.put(e.getNombre(), e.getIdEquipo());
            vista.getComboEquipos().addItem(e.getNombre());
        }

        vista.getComboPersonalMnt().removeAllItems();
        vista.getComboPersonalLimp().removeAllItems();
        mapaPersonal.clear();
        for (Personal p : personalService.getAll()) {
            mapaPersonal.put(p.getNombre(), p.getIdPersonal()); // Ajustar según tu getter
            vista.getComboPersonalMnt().addItem(p.getNombre());
            vista.getComboPersonalLimp().addItem(p.getNombre());
        }
    }

    public void cargarDatos() {
        cargarTablaBitacora();
        cargarTablaLimpieza();
    }

    private void guardarMantenimiento() {
        try {
            BitacoraMantenimiento b = new BitacoraMantenimiento();
            String nombreEquipo = (String) vista.getComboEquipos().getSelectedItem();
            String nombrePersonal = (String) vista.getComboPersonalMnt().getSelectedItem();

            if (nombreEquipo == null || nombrePersonal == null) {
                throw new IllegalArgumentException("Debe seleccionar equipo y personal válidos.");
            }

            b.setIdEquipo(mapaEquipos.get(nombreEquipo));
            b.setIdPersonal(mapaPersonal.get(nombrePersonal));
            b.setFecha(new Date());
            b.setDescripcion(vista.getDetalleMnt());

            bitacoraService.store(b);
            cargarTablaBitacora();
            vista.mostrarMensaje("Mantenimiento registrado correctamente.");
        } catch (Exception e) {
            vista.mostrarError("Error al guardar: " + e.getMessage());
        }
    }

    private void registrarLimpieza() {
        try {
            Limpieza l = new Limpieza();
            String nombrePersonal = (String) vista.getComboPersonalLimp().getSelectedItem();

            if (nombrePersonal == null) {
                throw new IllegalArgumentException("Seleccione personal de limpieza válido.");
            }

            l.setIdPersonal(mapaPersonal.get(nombrePersonal));
            l.setAreaAsignada(vista.getAreaLimp());
            l.setTurno(vista.getTurnoLimp());
            
            limpiezaService.store(l);
            cargarTablaLimpieza();
            vista.mostrarMensaje("Limpieza registrada correctamente.");
        } catch (Exception e) {
            vista.mostrarError("Error al registrar limpieza: " + e.getMessage());
        }
    }

    private void cargarTablaBitacora() {
        DefaultTableModel model = vista.getModelMantenimientos();
        model.setRowCount(0);
        for (BitacoraMantenimiento b : bitacoraService.getAll()) {
            model.addRow(new Object[] { 
                b.getIdBitacora(), b.getIdEquipo(), b.getIdPersonal(), b.getFecha(), b.getDescripcion() 
            });
        }
    }

    private void cargarTablaLimpieza() {
        DefaultTableModel model = vista.getModelLimpiezas();
        model.setRowCount(0);
        for (Limpieza l : limpiezaService.getAll()) {
            model.addRow(new Object[] { 
                l.getIdPersonal(), l.getNombre(), l.getAreaAsignada(), l.getTurno() 
            });
        }
    }
}