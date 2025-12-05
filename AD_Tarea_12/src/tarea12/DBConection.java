package tarea12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {

	private static final String URL = "jdbc:mysql://localhost:3306/alumnos04?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "alumno";

	public static Connection abrirConexion() {
		Connection conexion = null;

		try {
			System.out.println("Conectando a la base de datos...");
			conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexion exitosa");

		} catch (SQLException e) {
			System.err.println("Conexion fallida" + e.getMessage());
		}
		return conexion;
	}

	public static void cerrarConexion(Connection conexion) {

		if (conexion != null) {
			try {
				System.out.println("Cerrando conexion...");
				conexion.close();
			} catch (SQLException e) {
				System.err.println("Error al cerrar la conexion");
			}
		}
	}
}
