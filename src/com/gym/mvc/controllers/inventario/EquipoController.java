package com.gym.mvc.controllers.inventario;

import com.gym.mvc.controllers.base.IController;
import com.gym.mvc.models.Equipo;
import com.gym.mvc.models.TipoEquipo;
import com.gym.mvc.models.services.EquipoService;
import com.gym.mvc.models.services.IService;
import com.gym.mvc.models.services.TipoService;
import com.gym.mvc.views.equipo.GestionEquipo;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;

public class EquipoController implements IController {

	private final GestionEquipo vista;
	private final IService<Equipo> service;
	private final TipoService tipoService;
    private final Map<String, Integer> mapaTipos;
	
    public EquipoController(GestionEquipo vista) { // NO SE POR QUE PERO SOLO CON ESTO CARGA
        this.vista = vista;
        this.service = new EquipoService();
        this.tipoService = new TipoService();
        this.mapaTipos = new HashMap<>();
    }

	@Override
	public void setUpListeners() {
		vista.setControlador(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				procesarEventosAccion(e.getActionCommand());
			}
		}, new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				show();
			}
		});
	}

	private void procesarEventosAccion(String comando) {
		switch (comando) {
		case "REGISTRAR_EQUIPO":
			create();
			break;
		case "LIMPIAR_FORMULARIO":
			vista.limpiarFormulario();
			break;
		}
	}

	@Override
    public void cargarDatosIniciales() {
        try {
            renderizarTabla(service.getAll());
            List<TipoEquipo> listaTipos = tipoService.getAll();
            vista.getComboTipo().removeAllItems();
            mapaTipos.clear();
            
            for (TipoEquipo t : listaTipos) {
                mapaTipos.put(t.getNombre(), t.getIdTipo());
                vista.getComboTipo().addItem(t.getNombre());
            }
        } catch (Exception ex) {
            vista.mostrarError("Error al cargar: " + ex.getMessage());
        }
    }

	private void renderizarTabla(List<Equipo> lista) {
		DefaultTableModel modelo = vista.getModeloTabla();
		modelo.setRowCount(0);
		for (Equipo eq : lista) {
			modelo.addRow(new Object[] { eq.getIdEquipo(), eq.getNombre(), eq.getEstado() });
		}
	}

	@Override
	public void index() {
		cargarDatosIniciales();
	}

	@Override
	public void create() {
		try {
			Map<String, Object> datos = vista.getDatosFormulario();
			Equipo equipo = mapToEntity(datos);

			if (equipo.getIdEquipo() > 0) {
				service.update(equipo);
				vista.mostrarMensaje("Equipo modificado con éxito.");
			} else {
				service.store(equipo);
				vista.mostrarMensaje("Equipo registrado en el inventario con éxito.");
			}

			vista.limpiarFormulario();
			cargarDatosIniciales();
		} catch (IllegalArgumentException ex) {
			vista.mostrarAdvertencia(ex.getMessage());
		} catch (Exception ex) {
			vista.mostrarError("Error al procesar el equipo: " + ex.getMessage());
		}
	}

	@Override
	public void show() {
		int fila = vista.getTablaEquipos().getSelectedRow();
		if (fila == -1)
			return;

		try {
			int id = (int) vista.getTablaEquipos().getValueAt(fila, 0);
			Equipo equipo = service.findById(id);
			if (equipo != null) {
				poblarFormulario(equipo);
			}
		} catch (Exception ex) {
			vista.mostrarError("Error al cargar detalles del equipo: " + ex.getMessage());
		}
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
	
	private Equipo mapToEntity(Map<String, Object> datos) {
        Equipo eq = new Equipo();

        String idStr = (String) datos.get("id");
        if (idStr != null && !idStr.isEmpty()) {
            eq.setIdEquipo(Integer.parseInt(idStr));
        }

        eq.setNombre((String) datos.get("nombre"));
        eq.setEstado((String) datos.get("estado"));
        String nombreTipo = (String) datos.get("tipo");
        if (nombreTipo != null && mapaTipos.containsKey(nombreTipo)) {
            eq.setIdTipo(mapaTipos.get(nombreTipo));
        }

        File archivoImg = (File) datos.get("archivoImagen");
        if (archivoImg != null && archivoImg.exists()) {
            eq.setFotoRuta(archivoImg.getAbsolutePath());
        }
        return eq;
    }

	private void poblarFormulario(Equipo eq) {
        vista.limpiarFormulario();
        
        for (Map.Entry<String, Integer> entry : mapaTipos.entrySet()) {
            if (entry.getValue() == eq.getIdTipo()) {
                vista.getComboTipo().setSelectedItem(entry.getKey());
                break;
            }
        }

        if (eq.getFotoRuta() != null && !eq.getFotoRuta().isEmpty()) {
            File archivo = new File(eq.getFotoRuta());
            if (archivo.exists()) {
                try {
                    byte[] bytesImagen = Files.readAllBytes(archivo.toPath());
                    vista.mostrarImagenEnVistaPrevia(bytesImagen);
                } catch (Exception e) {
                    vista.mostrarImagenEnVistaPrevia(null);
                }
            }
        }
    }

}