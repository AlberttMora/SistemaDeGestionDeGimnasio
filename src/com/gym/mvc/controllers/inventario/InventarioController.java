package com.gym.mvc.controllers.inventario;

import com.gym.mvc.models.Equipo;
import com.gym.mvc.models.TipoEquipo;
import com.gym.mvc.models.services.EquipoService;
import com.gym.mvc.models.services.IService;
import com.gym.mvc.models.services.TipoService;
import com.gym.mvc.views.inventario.GestionInventario;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.List;

public class InventarioController {

	private final GestionInventario view;
	private final IService service;
	private final TipoService tipoService;

	public InventarioController(GestionInventario view) {
		this.view = view;
		this.service = new EquipoService();
		this.tipoService = new TipoService();
	}

	public GestionInventario getVista() {
		return view;
	}

	public void init() {
		setupListeners();
		cargarComboTipos();
		index();
	}

	private void setupListeners() {
		view.getBtnGuardar().addActionListener(e -> store());
		view.getBtnEliminar().addActionListener(e -> destroy());
		view.getBtnExaminarFoto().addActionListener(e -> abrirFileChooser());
		view.getTabla().getSelectionModel().addListSelectionListener(e -> edit());
	}

	public void index() {
		cargarTabla();
	}

	private void cargarTabla() {
		try {
			List<Equipo> lista = service.getAll();
			DefaultTableModel modelo = view.getModeloTabla();
			modelo.setRowCount(0);
			for (Equipo e : lista) {
				modelo.addRow(new Object[] { e.getIdEquipo(), e.getIdTipo(), e.getNombre(), e.getEstado(),
						e.getFechaAdquisicion() });
			}
		} catch (Exception ex) {
			view.mostrarMensaje(ex.getMessage());
		}
	}

	private void store() {
		try {
			Equipo e = new Equipo();
			if (!view.obtenerIdEquipo().isEmpty()) {
				e.setIdEquipo(Integer.parseInt(view.obtenerIdEquipo()));
			}
			e.setIdTipo(view.getComboTipos().getSelectedIndex() + 1);
			e.setNombre(view.obtenerNombre());
			e.setEstado(view.obtenerEstado());
			e.setFotoRuta(view.obtenerFotoRuta());
			e.setFechaAdquisicion(view.obtenerFecha());
			if (e.getIdEquipo() > 0) {
				service.update(e);
			} else {
				service.store(e);
			}
			view.limpiarFormulario();
			cargarTabla();
		} catch (Exception ex) {
			view.mostrarMensaje(ex.getMessage());
		}
	}

	private void destroy() {
		try {
			int fila = view.getTabla().getSelectedRow();
			if (fila == -1)
				return;
			int id = (int) view.getTabla().getValueAt(fila, 0);
			service.destroy(id);
			cargarTabla();
		} catch (Exception ex) {
			view.mostrarMensaje(ex.getMessage());
		}
	}

	private void edit() {
		int fila = view.getTabla().getSelectedRow();
		if (fila == -1)
			return;
		view.establecerIdEquipo(view.getTabla().getValueAt(fila, 0).toString());
		view.establecerNombre(view.getTabla().getValueAt(fila, 2).toString());
		view.getComboEstado().setSelectedItem(view.getTabla().getValueAt(fila, 3));
	}

	private void abrirFileChooser() {
		JFileChooser fc = new JFileChooser();
		int r = fc.showOpenDialog(view);
		if (r == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			view.establecerFotoRuta(f.getAbsolutePath());
		}
	}
	
	private void cargarComboTipos() {
        List<TipoEquipo> tipos = tipoService.getAll();
        view.getComboTipos().removeAllItems(); 
        for (TipoEquipo t : tipos) {
            view.getComboTipos().addItem(t.getNombre()); 
        }
    }
}