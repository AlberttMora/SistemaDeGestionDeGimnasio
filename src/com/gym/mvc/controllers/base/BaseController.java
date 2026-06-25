package com.gym.mvc.controllers.base;

public abstract class BaseController implements IController {
    @Override public void create()  {}
    @Override public void show()    {}
    @Override public void edit()    {}
    @Override public void update()  {}
    @Override public void destroy() {}
    @Override public void cargarDatosIniciales() {}
}