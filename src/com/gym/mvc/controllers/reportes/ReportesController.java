package com.gym.mvc.controllers.reportes;

import com.gym.mvc.models.repositories.ReporteRepository;
import com.gym.mvc.views.reportes.PanelReportes;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ReportesController {

	private final PanelReportes vista;
	private final ReporteRepository reporteRepository;

	public ReportesController(PanelReportes vista) {
		this.vista = vista;
		this.reporteRepository = new ReporteRepository();
	}

	public void init() {
		vista.getBtnActualizar().addActionListener(e -> cargarReportes());
		cargarReportes();
	}

	public void cargarReportes() {
		llenarModelo(vista.getModeloPagosPorCliente(), reporteRepository.totalPagadoPorCliente());
		llenarModelo(vista.getModeloAsignacionesPorEntrenador(), reporteRepository.entrenadorConMasAsignaciones());
		llenarModelo(vista.getModeloEquiposPorCategoria(), reporteRepository.equiposPorCategoriaYEstado());
		llenarModelo(vista.getModeloClientesSinPagos(), reporteRepository.clientesSinPagosRecientes());
		llenarModelo(vista.getModeloPesoPorCategoria(), reporteRepository.promedioPesoPorCategoria());
	}

	private void llenarModelo(DefaultTableModel modelo, List<Object[]> filas) {
		modelo.setRowCount(0);
		for (Object[] fila : filas) {
			modelo.addRow(fila);
		}
	}
}