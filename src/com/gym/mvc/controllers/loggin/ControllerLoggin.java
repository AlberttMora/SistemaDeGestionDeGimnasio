package com.gym.mvc.controllers.loggin;

import com.gym.mvc.controllers.base.BaseController;
import com.gym.mvc.controllers.dashboard.ControllerDashboard;
import com.gym.mvc.models.services.AuthService;
import com.gym.mvc.views.principal.Login;
import javax.swing.JOptionPane;

public class ControllerLoggin extends BaseController {

	private final Login login;
	private final AuthService authService;

	public ControllerLoggin() {
		this.login = new Login();
		this.authService = new AuthService();
	}

	public void init() {
		setUpListeners();
		index();
	}

	@Override
	public void setUpListeners() {
		login.getBtnEntrar().addActionListener(e -> autenticar());
		login.getBtnSalir().addActionListener(e -> salirDelSistema());
	}

	@Override
	public void index() {
		login.setVisible(true);
	}

	private void autenticar() {
	    String correo = login.getUsuario().trim();
	    String contrasena = login.getContrasena();
	    try {
	        Object usuario = authService.autenticar(correo, contrasena);
	        concederAcceso();
	        
	    } catch (IllegalArgumentException ex) {
	        mostrarAdvertencia(ex.getMessage());
	    } catch (Exception ex) {
	        ex.printStackTrace(); 
	        mostrarError("Error de conexion:\n" + ex.getMessage());
	    }
	}

	private void concederAcceso() {
		login.dispose();
		new ControllerDashboard().init();
	}

	private void salirDelSistema() {
		System.exit(0);
	}

	private void mostrarAdvertencia(String msg) {
		JOptionPane.showMessageDialog(login, msg, "Acceso denegado", JOptionPane.WARNING_MESSAGE);
	}

	private void mostrarError(String msg) {
		JOptionPane.showMessageDialog(login, msg, "Error del sistema", JOptionPane.ERROR_MESSAGE);
	}
}