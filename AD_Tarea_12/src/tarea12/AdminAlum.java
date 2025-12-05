package tarea12;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminAlum {
	private static final AlumnoDAO alumnoDAO = new AlumnoDAO();

	private Scanner entrada = new Scanner(System.in);

	public void opciones(int opcion) {

		switch (opcion) {
		case 1:
			pedirDatos();
			break;
		case 2:
			insertarGrupo(verificarGrupo());
			break;
		case 3:
			mostrarAlumnos();
			break;
		case 4:
			guardarAlumno();
			break;
		case 5:
			leerFichero();
			break;
		case 6:
			modificarNombre();
			break;
		case 7:
			llamarBorrarPK();
			break;
		case 8:

			eliminarCurso(mostrarCursos());
			break;
		case 9:
			guardarJSON();
			break;
		case 10:
			leerJSON();
			break;
		case 0:
			System.out.println("Cerrando programa..");
			break;
		}
	}

	private void pedirDatos() {
		System.out.println("\n--- INSERCIÓN DE NUEVO ALUMNO ---");
		Alumno nuevoAlumno = new Alumno();

		try {

			System.out.print("Introduce NIA (Numérico, no puede repetirse entre alumnos): ");
			nuevoAlumno.setNia(Integer.parseInt(entrada.nextLine()));

			System.out.print("Introduce Nombre: ");
			nuevoAlumno.setNombre(entrada.nextLine());
			System.out.print("Introduce Apellidos: ");
			nuevoAlumno.setApellidos(entrada.nextLine());

			System.out.print("Introduce Género: ");
			nuevoAlumno.setGenero(entrada.nextLine().charAt(0));

			System.out.print("Introduce Fecha de Nacimiento (YYYY-MM-DD): ");
			nuevoAlumno.setFechaNacimiento(LocalDate.parse(entrada.nextLine()));

			System.out.print("Introduce Ciclo: ");
			nuevoAlumno.setCiclo(entrada.nextLine());

			System.out.print("Introduce Curso: ");
			nuevoAlumno.setCurso(entrada.nextLine());

			System.out.print("Introduce Grupo (El grupo debe existir): ");
			nuevoAlumno.setGrupo(entrada.nextInt());

			if (alumnoDAO.insertarAlumno(nuevoAlumno)) {
				System.out.println("El alumno ha sido insertado correctamente.");
			} else {
				System.out.println(
						"No se pudo insertar el alumno. Comprueba si el NIA ya existe o si los datos son correctos.");
			}

		} catch (NumberFormatException e) {
			System.err.println("El NIA debe ser un número entero.");
		} catch (IllegalArgumentException e) {
			System.err.println("Formato de fecha incorrecto. Debe ser YYYY-MM-DD.");
		} catch (Exception e) {
			System.err.println("Ocurrió un error inesperado al leer los datos.");
		}
	}

	private int verificarGrupo() {
		List<Integer> lista = alumnoDAO.listarGrupos();
		while (true) {
			System.out.println("Indique el numero del grupo a insertar, asegurese de que no existe ya.");
			int grupo = entrada.nextInt();
			if (lista == null || lista.size() == 0) {
				System.out.println("Insertando grupo...");
				return grupo;

			} else {
				if (lista.contains(grupo)) {
					System.out.println("El grupo ya existe pruebe otra vez con un número distinto.");
				} else {
					System.out.println("Insertando grupo...");
					return grupo;
				}
			}
		}
	}

	private void insertarGrupo(int grupo) {
		if (alumnoDAO.insertarGrupo(grupo)) {
			System.out.println("Grupo insertado correctamente.");
		} else {
			System.err.println("Error al insertar el grupo.");
		}
	}

	private void mostrarAlumnos() {
		List<Alumno> result = new ArrayList<>();
		result = alumnoDAO.cargarAlumnos();
		if (result.size() >= 1) {
			for (Alumno a : result) {
				System.out.println(a.toString());
			}
		} else {
			System.out.println("No hay alumnos");
		}
	}

	private void guardarAlumno() {
		List<Alumno> result = new ArrayList<>();
		result = alumnoDAO.cargarAlumnos();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("nuevo.dat"))) {
			oos.write(result.size());
			for (Alumno a : result) {
				oos.writeObject(a);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void leerFichero() {
		try (ObjectInputStream ots = new ObjectInputStream(new FileInputStream("nuevo.dat"))) {
			int num = ots.read();
			while (num > 0) {
				if (alumnoDAO.insertarAlumno((Alumno) ots.readObject())) {
					System.out.println("Alumno insertado");
				} else {
					System.out.println("Alumno no insertado el Nia estara duplicado");
				}
				num--;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void llamarBorrarPK() {
		System.out.println("Introduce el nia:");
		int nia = entrada.nextInt();
		if (alumnoDAO.modificarNombre(nia)) {
			System.out.println("Alumno eliminado");
		} else {
			System.err.println("Alumno no eliminado revisa el nia introducido");
		}
	}

	private String mostrarCursos() {
		List<String> lista = alumnoDAO.listarCursos();
		if (lista != null && lista.size() > 0) {
			int contador = 1;
			System.out.println("Cursos a elejir");
			for (String s : lista) {
				System.out.println(contador + "->" + s);
				contador++;
			}
			while (true) {
				System.out.println("Indique por cual borrar, escriba el nombre del curso:");
				String eleccion = entrada.nextLine();
				if (!lista.contains(eleccion.toLowerCase())) {
					System.out.println("Escribe un curso de los listados");
				} else {
					return eleccion;
				}
			}
		} else {
			System.err.println("No hay ningun curso");
			return null;
		}
	}

	private void eliminarCurso(String eleccion) {
		if (eleccion != null) {
			if (alumnoDAO.eliminarPorCurso(eleccion)) {
				System.out.println("Alumnos eliminados");
			} else {
				System.err.println("Alumnos no eliminados, revisa el curso introducido");
			}
		} else {
			System.err.println("No existen datos para eliminar");
		}
	}

	private void modificarNombre() {
		System.out.println("Introduce el nia:");
		int nia = entrada.nextInt();
		System.out.println("Introduce el nombre:");
		String nombre = entrada.next();

		if (alumnoDAO.modificarNombre(nombre, nia)) {
			System.out.println("Alumno modificado");
		} else {
			System.err.println("Alumno no modificado, revise el nia");
		}
	}

	private void guardarJSON() {
		List<Alumno> result = new ArrayList<>();
		result = alumnoDAO.cargarAlumnos();
		if (result.size() > 0) {
			JSONArray jArray = new JSONArray();
			for (Alumno a : result) {
				JSONObject objeto = new JSONObject();
				objeto.put("Nia:", String.valueOf(a.getNia()));
				objeto.put("Nombre:", a.getNombre());
				objeto.put("Apellidos:", String.valueOf(a.getApellidos()));
				objeto.put("Genero:", String.valueOf(a.getGenero()));
				objeto.put("Fecha de nacimiento:", String.valueOf(a.getFechaNacimiento()));
				objeto.put("Ciclo:", a.getCiclo());
				objeto.put("Curso:", a.getCurso());
				objeto.put("Grupo:", a.getGrupo());
				jArray.put(objeto);
			}
			crearJSON(jArray.toString());

		}
	}

	private void crearJSON(String jsonString) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("nuevo.json"))) {

			bw.write(jsonString);
			bw.flush();

		} catch (IOException e) {
			System.err.println("Error en la creacion o escritura del archivo .json");
		}

	}

	private void leerJSON() {
		String contenido = null;

		try (BufferedReader br = new BufferedReader(new FileReader("nuevo.json"))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				contenido = (linea);
				transformarDatos(contenido);
			}
			if (contenido.length() == 0) {
				System.err.println("El fichero está vacio");
				return;
			}

		} catch (IOException e) {
			System.err.println("Error al leer el JSON");
		} catch (JSONException j) {
			System.err.println("Error al utilizar el JSON" + j.getMessage());
		}
	}

	private void transformarDatos(String linea) {
		JSONArray objetolist = new JSONArray(linea);
		for (int i = 0; i < objetolist.length(); i++) {
			JSONObject objeto = new JSONObject();
			objeto = objetolist.getJSONObject(i);
			Alumno al = new Alumno();
			al.setNia(Integer.parseInt(objeto.getString("Nia:")));
			al.setNombre(objeto.getString("Nombre:"));
			al.setApellidos(objeto.getString("Apellidos:"));
			al.setGenero(objeto.getString("Genero:").charAt(0));
			al.setFechaNacimiento(LocalDate.parse(objeto.getString("Fecha de nacimiento:")));
			al.setCiclo(objeto.getString("Ciclo:"));
			al.setCurso(objeto.getString("Curso:"));
			al.setGrupo(objeto.getInt("Grupo:"));
			alumnoDAO.insertarAlumno(al);
		}

	}

}
