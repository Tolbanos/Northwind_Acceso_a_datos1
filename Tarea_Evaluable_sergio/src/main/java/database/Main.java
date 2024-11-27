package database;

import static database.CargarProductosJson.cargarProductosDesdeJSON;

public class Main {
    public static void main(String[] args) {
        // Cargar productos desde el JSON
        cargarProductosDesdeJSON();

        // Insertar empleados y pedidos de prueba
        DatosPrueba.insertarDatos();

        // Ejecutar las consultas
        EjecutarConsultas.ejecutarConsultas();
    }
}
