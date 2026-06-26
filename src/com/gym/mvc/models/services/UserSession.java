package com.gym.mvc.models.services;

import com.gym.mvc.models.Administrador;
import com.gym.mvc.models.Cliente;
import com.gym.mvc.models.Entrenador;
import com.gym.mvc.models.Limpieza;
import com.gym.mvc.models.Personal;

public class UserSession {

    public enum Rol { ADMIN, ENTRENADOR, LIMPIEZA, CLIENTE }

    private static UserSession instance;
    private Personal loggedPersonal;
    private Cliente loggedCliente;
    private Rol rol;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setLoggedPersonal(Personal p) {
        this.loggedPersonal = p;
        this.loggedCliente = null;
        if (p instanceof Administrador) {
            rol = Rol.ADMIN;
        } else if (p instanceof Entrenador) {
            rol = Rol.ENTRENADOR;
        } else if (p instanceof Limpieza) {
            rol = Rol.LIMPIEZA;
        } else {
            rol = null;
        }
    }

    public void setLoggedCliente(Cliente c) {
        this.loggedCliente = c;
        this.loggedPersonal = null;
        this.rol = Rol.CLIENTE;
    }

    public void setLoggedUser(Personal p) {
        setLoggedPersonal(p);
    }

    public Personal getLoggedUser() {
        return loggedPersonal;
    }

    public Personal getLoggedPersonal() {
        return loggedPersonal;
    }

    public Cliente getLoggedCliente() {
        return loggedCliente;
    }

    public Rol getRol() {
        return rol;
    }

    public int getIdSesion() {
        if (rol == Rol.CLIENTE && loggedCliente != null) return loggedCliente.getIdCliente();
        if (loggedPersonal != null) return loggedPersonal.getIdPersonal();
        return -1;
    }

    public boolean isAdmin()      { return rol == Rol.ADMIN; }
    public boolean isEntrenador() { return rol == Rol.ENTRENADOR; }
    public boolean isLimpieza()   { return rol == Rol.LIMPIEZA; }
    public boolean isCliente()    { return rol == Rol.CLIENTE; }

    public void clear() {
        loggedPersonal = null;
        loggedCliente  = null;
        rol            = null;
    }
}