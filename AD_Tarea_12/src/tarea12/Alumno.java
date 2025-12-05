package tarea12;

import java.io.Serializable;
import java.time.LocalDate;

public class Alumno implements Serializable{
	private static final long serialVersionUID = 1L;
	private int Nia;
	private String nombre, apellidos, ciclo, curso;
	private int grupo;
	private char genero;
	private LocalDate fechaNacimiento;

	public Alumno(int nia, String nombre, String apellidos, String ciclo, String curso, int grupo, char genero,
			LocalDate fechaNacimiento) {
		Nia = nia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ciclo = ciclo;
		this.curso = curso;
		this.grupo = grupo;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Alumno() {
	}

	public int getNia() {
		return Nia;
	}

	public void setNia(int nia) {
		Nia = nia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "[Nombre=" + nombre + ", Apellidos=" + apellidos + ", Nia=" + Nia + ", Ciclo=" + ciclo + ", Curso="
				+ curso + ", Grupo=" + grupo + ", Genero=" + genero + ", FechaNacimiento=" + fechaNacimiento + "]";
	}

}
