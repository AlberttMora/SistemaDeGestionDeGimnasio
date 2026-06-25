package com.gym.mvc.controllers.loggin;

import com.gym.mvc.controllers.base.BaseController;
import com.gym.mvc.models.services.AuthService;
import com.gym.mvc.views.principal.Login;
import com.gym.mvc.views.principal.VentanaPrincipal;
import javax.swing.JOptionPane;

public class ControllerLoggin extends BaseController {

    private final Login login;
    private final AuthService authService;
    private final VentanaPrincipal ventanaPrincipal;

    public ControllerLoggin() {
        this.login = new Login();
        this.authService = new AuthService();
        this.ventanaPrincipal = new VentanaPrincipal();
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
            authService.autenticar(correo, contrasena);
            concederAcceso();
        } catch (IllegalArgumentException ex) {
            mostrarAdvertencia(ex.getMessage());
        } catch (Exception ex) {
            mostrarError("Error de conexión:\n" + ex.getMessage());
        }
    }

    private void concederAcceso() {
        login.dispose();
        ventanaPrincipal.setVisible(true);
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