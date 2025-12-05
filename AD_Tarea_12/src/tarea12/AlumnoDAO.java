package tarea12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

	public List<Integer> listarGrupos() {
		String sql = "SELECT idgrupo FROM grupo";
		List<Integer> listaGrupos = new ArrayList<>();
		try (Connection conexion = DBConection.abrirConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				listaGrupos.add(rs.getInt("idgrupo"));
			}
			return listaGrupos;

		} catch (SQLException e) {
			System.err.println("Error al insertar el alumno" + e.getMessage());
			return null;
		}
	}

	public List<String> listarCursos() {
		String sql = "SELECT DISTINCT Curso FROM alumno";
		List<String> listaCursos = new ArrayList<>();
		try (Connection conexion = DBConection.abrirConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				listaCursos.add(rs.getString("Curso"));
			}
			return listaCursos;

		} catch (SQLException e) {
			System.err.println("Error al insertar el alumno" + e.getMessage());
			return null;
		}
	}

	public boolean insertarGrupo(int grupo) {
		String sql = "INSERT INTO grupo (idgrupo) VALUES(?)";

		try (Connection conexion = DBConection.abrirConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, grupo);

			int filas = stmt.executeUpdate();

			return filas > 0;
		} catch (SQLException e) {
			System.err.println("Error al insertar el grupo nuevo" + e.getMessage());
			return false;
		}

	}

	public boolean insertarAlumno(Alumno alumno) {

		String sql = "INSERT INTO alumno (NIA, Nombre, Apellidos, Genero, Fecha_nacimiento, Ciclo, Curso, Grupo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conexion = DBConection.abrirConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, alumno.getNia());
			stmt.setString(2, alumno.getNombre());
			stmt.setString(3, alumno.getApellidos());
			stmt.setString(4, String.valueOf(alumno.getGenero()));
			stmt.setDate(5, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
			stmt.setString(6, alumno.getCiclo());
			stmt.setString(7, alumno.getCurso());
			stmt.setInt(8, alumno.getGrupo());

			int filas = stmt.executeUpdate();

			return filas > 0;
		} catch (SQLException e) {
			System.err.println("Error al insertar el alumno" + e.getMessage());
			return false;
		}
	}

	public List<Alumno> cargarAlumnos() {
		String sql = "SELECT NIA, Nombre, Apellidos, Genero, Fecha_nacimiento, Ciclo, Curso, Grupo FROM alumno";
		List<Alumno> listaAlumnos = new ArrayList<>();
		try (Connection conexion = DBConection.abrirConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {

				Alumno alumno = new Alumno();

				alumno.setNia(rs.getInt("NIA"));
				alumno.setNombre(rs.getString("Nombre"));
				alumno.setApellidos(rs.getString("Apellidos"));
				alumno.setGenero(rs.getString("Genero").charAt(0));
				alumno.setFechaNacimiento((rs.getDate("Fecha_nacimiento")).toLocalDate());
				alumno.setCiclo(rs.getString("Ciclo"));
				alumno.setCurso(rs.getString("Curso"));
				alumno.setGrupo(rs.getInt("Grupo"));

				listaAlumnos.add(alumno);
			}

			return listaAlumnos;
		} catch (SQLException e) {
			System.err.println("Error al mostrar los alumnos");
			return null;
		}
	}

	public boolean modificarNombre(int nia) {

		String sql = "DELETE FROM alumno WHERE NIA = ?";

		try (Connection conexion = DBConection.abrirConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, nia);
			int filas = stmt.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			return false;
		}
	}

	public boolean eliminarPorCurso(String curso) {
		String sql = "DELETE FROM alumno WHERE Curso = ?";

		try (Connection conexion = DBConection.abrirConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, curso.toLowerCase());
			int filas = stmt.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			return false;
		}
	}

	public boolean modificarNombre(String nombre, int nia) {
		String sql = "UPDATE alumno SET Nombre = ? WHERE NIA = ?";

		try (Connection conexion = DBConection.abrirConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, nombre);
			stmt.setInt(2, nia);
			int filas = stmt.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			return false;
		}
	}
}
