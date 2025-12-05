package tarea12;

import java.util.Scanner;

public class Main {

	public static int mostrarMenu(Scanner entrada) {

		System.out.println("==============================================");
		System.out.println("            GESTIÓN DE ALUMNOS");
		System.out.println("==============================================");
		System.out.println("1. Insertar un nuevo alumno.");
		System.out.println("2. Insertar un grupo nuevo.");
		System.out.println("3. Mostrar todos los alumnos.");
		System.out.println("4. Guardar todos los alumnos en un fichero.");
		System.out.println("5. Leer alumnos de un fichero y guardarlos en la BD.");
		System.out.println("6. Modificar el nombre de un alumno.");
		System.out.println("7. Eliminar un alumno a partir de su NIA (PK).");
		System.out.println("8. Eliminar los alumnos por curso.");
		System.out.println("9. Guardar todos los alumnos en un fichero XML o JSON.");
		System.out.println("10. Leer un fichero XML o JSON de alumnos.");
		System.out.println("0. Salir del programa.");
		System.out.println("----------------------------------------------");
		System.out.print("Introduce tu opción: ");
		return entrada.nextInt();
	}

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		int opcion = 0;
		do {
			opcion = mostrarMenu(entrada);
			AdminAlum adm = new AdminAlum();
			adm.opciones(opcion);
		} while (opcion != 0);

	}
}