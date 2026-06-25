package com.gym.mvc.controllers.base;

/**
 * Interfaz base para todos los controladores del sistema PowerFit Gym.
 * Sigue una arquitectura de operaciones estandarizadas para el patrón MVC.
 */
public interface IController {

    /**
     * Vincula los escuchadores de eventos (ActionListener, MouseListener, etc.)
     * entre la Vista y los componentes que controla este mediador.
     */
    void setUpListeners();

    /**
     * Coordina la carga inicial de componentes gráficos auxiliares, como
     * rellenar JComboBoxes desde la base de datos antes de mostrar la pantalla.
     */
    void cargarDatosIniciales();

    /**
     * Operación de Listado General. Consulta el Modelo (DAO) para traer
     * todos los registros y poblar la tabla principal de la Vista.
     */
    void index();

    /**
     * Operación de Persistencia de Nuevos Registros. Extrae los datos del 
     * formulario de la vista, los valida y los inserta en la base de datos.
     */
    void create();

    /**
     * Operación de Lectura de un Registro Único. Captura la selección de la tabla,
     * recupera la entidad completa desde el DAO y llena los campos correspondientes.
     */
    void show();

    /**
     * Operación de Preparación de Edición. Activa campos, deshabilita llaves primarias
     * o prepara visualmente el formulario para recibir cambios de un registro existente.
     */
    void edit();

    /**
     * Operación de Persistencia de Modificaciones. Toma los datos modificados 
     * del registro seleccionado y actualiza la información en la base de datos.
     */
    void update();

    /**
     * Limpieza de Ciclo de Vida. Libera recursos, remueve listeners activos
     * o limpia variables cuando la vista es destruida o reemplazada en el Dashboard.
     */
    void destroy();
}