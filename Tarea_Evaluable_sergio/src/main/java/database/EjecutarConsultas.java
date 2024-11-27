package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EjecutarConsultas {
    public static void ejecutarConsultas() {
        try (Connection conexion = ConexionBaseDatos.getConnection()) {
            Statement stmt = conexion.createStatement();

//ACTIVIDAD EVALUABLE PREGUNTAS:

            // Mostrar todos los empleados
            System.out.println("Empleados:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Empleados");
            while (rs.next()) {
                System.out.println("Empleado: " + rs.getString("nombre") + " " + rs.getString("apellidos"));
            }

            // Mostrar todos los productos
            System.out.println("\nProductos:");
            rs = stmt.executeQuery("SELECT * FROM Productos");
            while (rs.next()) {
                System.out.println("Producto: " + rs.getString("nombre") + ", Precio: " + rs.getDouble("precio"));
            }

            // Mostrar todos los pedidos
            System.out.println("\nPedidos:");
            rs = stmt.executeQuery("SELECT * FROM Pedidos");
            while (rs.next()) {
                System.out.println("Pedido: " + rs.getString("descripcion") + ", Precio Total: " + rs.getDouble("precio_total"));
            }

            // Mostrar productos con precio < 600
            System.out.println("\nProductos con precio menor a 600€:");
            rs = stmt.executeQuery("SELECT * FROM Productos WHERE precio < 600");
            while (rs.next()) {
                System.out.println("Producto: " + rs.getString("nombre") + ", Precio: " + rs.getDouble("precio"));
            }

            // Insertar productos favoritos (precio > 1000)
            stmt.executeUpdate("INSERT INTO Productos_Fav (id_producto) SELECT id FROM Productos WHERE precio > 1000");
            System.out.println("\nProductos favoritos insertados correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}