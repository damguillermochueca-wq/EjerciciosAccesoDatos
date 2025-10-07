package ejercicio5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		List<Alumno> lista = new ArrayList<>();
		Scanner entrada = new Scanner(System.in);
		// System.out.println("Introduce la ruta:");
		// String ruta = entrada.next();
		// Introduzco la ruta aposta para asegurarme de que es el fichero de alumnos
		try (ObjectInputStream ots = new ObjectInputStream(new FileInputStream("nuevo.dat"))) {
			int contador = ots.readInt();
			for (int i = 0; i < contador; i++) {
				lista.add((Alumno) ots.readObject());
			}
			for (Alumno a : lista) {
				System.out.println(a.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			entrada.close();
		}
	}
}
