package com.gym.mvc.models.services;

import com.gym.mvc.models.Administrador;
import com.gym.mvc.models.Personal;

public class UserSession {
	private static UserSession instance;
	private Personal loggedUser;

	private UserSession() {
	}

	public static UserSession getInstance() {
		if (instance == null) {
			instance = new UserSession();
		}
		return instance;
	}

	public Personal getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(Personal p) {
		this.loggedUser = p;
	}

	public boolean isAdmin() {
		return loggedUser instanceof Administrador;
	}

	public void clear() {
		loggedUser = null;
	}
}