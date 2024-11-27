package database;

import java.sql.Connection;
import java.sql.Statement;

public class DatosPrueba {
    public static void insertarDatos() {
        try (Connection conexion = ConexionBaseDatos.getConnection()) {
            Statement stmt = conexion.createStatement();

            // Agregar empleados
            stmt.executeUpdate("INSERT INTO Empleados (nombre, apellidos, correo) VALUES ('Sergio', 'Tolbaños', 'sergiot@hotmail.com')");
            stmt.executeUpdate("INSERT INTO Empleados (nombre, apellidos, correo) VALUES ('María', 'López', 'maria.lopez@hotmail.com')");
            stmt.executeUpdate("INSERT INTO Empleados (nombre, apellidos, correo) VALUES ('Juan', 'martin', 'JuanM@gmail.com')");
            // Agregar pedidos
            stmt.executeUpdate("INSERT INTO Pedidos (id_producto, descripcion, precio_total) VALUES (1, 'Pedido Prueba, ruedas coche', 150.00)");
            stmt.executeUpdate("INSERT INTO Pedidos (id_producto, descripcion, precio_total) VALUES (2, 'Pedido nuevo raton ordenador', 300.00)");
            stmt.executeUpdate("INSERT INTO Pedidos (id_producto, descripcion, precio_total) VALUES (2, 'Pedido bicicleta', 200.00)");
            System.out.println("Datos de prueba insertados correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
