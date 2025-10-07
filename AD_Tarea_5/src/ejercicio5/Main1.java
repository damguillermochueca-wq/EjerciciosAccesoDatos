package ejercicio5;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int Nia = 0;
		String nombre = null, grupo = null, apellidos = null, ciclo = null, curso = null, fecha = null;
		char genero = 0;
		LocalDate fechaNacimiento;
		List<Alumno> lista = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			System.out.println("Intoduce los datos del alumo " + (i));
			System.out.println("Nia:");
			Nia = entrada.nextInt();
			entrada.nextLine();
			System.out.println("Nombre:");
			nombre = entrada.next();
			System.out.println("Apellidos:");
			apellidos = entrada.next();
			System.out.println("Ciclo:");
			ciclo = entrada.next();
			System.out.println("Curso:");
			curso = entrada.next();
			System.out.println("Grupo");
			grupo = entrada.next();
			System.out.println("Genero");
			genero = entrada.next().charAt(0);
			System.out.println("Fecha de nacimiento");
			fecha = entrada.next();
			fechaNacimiento = LocalDate.parse(fecha);
			Alumno alumno = new Alumno(Nia, nombre, apellidos, ciclo, curso, grupo, genero, fechaNacimiento);
			lista.add(alumno);
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("nuevo.dat"))) {
			oos.writeInt(genero);
			for (Alumno a : lista) {
				oos.writeObject(a);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			entrada.close();
		}
	}
}
