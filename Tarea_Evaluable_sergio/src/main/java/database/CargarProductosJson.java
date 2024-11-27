package database;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CargarProductosJson {

    public static void cargarProductosDesdeJSON() {
        try {
       //Conexión a JSON
            String urlString = "https://dummyjson.com/products";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error de conexion: " + conn.getResponseCode());
            }

       //Leer el JSON desde la conexión
            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

       //Extraer "products"
            JsonArray productosArray = json.getAsJsonArray("products");
            Gson gson = new Gson();

            // Convertir el arreglo en objetos Producto
            Producto[] productos = gson.fromJson(productosArray, Producto[].class);

    //Insertacion de los productos en la base de datos
            try (Connection conexion = ConexionBaseDatos.getConnection()) {
                String sql = "INSERT IGNORE INTO Productos (id, nombre, descripcion, cantidad, precio) VALUES (?, ?, ?, ?, ?)";
     //PreparedStatement pstmt mas seguro y sencillo que Statement
                try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                    for (Producto producto : productos) {
                        pstmt.setInt(1, producto.getId());
                        pstmt.setString(2, producto.getTitle());
                        pstmt.setString(3, producto.getDescription());
                        pstmt.setInt(4, producto.getStock());
                        pstmt.setDouble(5, producto.getPrice());
                        pstmt.addBatch();
                    }
                    pstmt.executeBatch();
                }
            }
            System.out.println("Productos insertados correctamente desde el JSON.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
