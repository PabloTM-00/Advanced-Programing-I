package ex2305;

import java.util.Objects;

public class Solicitud implements Comparable<Solicitud> {
	
	private String asignatura;
	private int diaSem;
	private int franja;
	private int lab;
	
	public Solicitud(String asignatura, int diaSem, int franja) {
		if(asignatura == null || asignatura.isBlank() || diaSem < 1 || diaSem > 7 || franja < 1 || franja > 7) throw new LabException("Argumentos erróneos");
		
		this.asignatura = asignatura;
		this.diaSem = diaSem;
		this.franja = franja;
		lab = -1; // Sin asignar
	}

	public String getAsignatura() {
		return asignatura;
	}

	public int getDiaSem() {
		return diaSem;
	}

	public int getFranja() {
		return franja;
	}

	public int getLab() {
		return lab;
	}

	public void setDiaSem(int diaSem) {
		if(diaSem < 1 || diaSem > 7) throw new LabException("Argumentos erróneos");
		
		this.diaSem = diaSem;
	}

	public void setFranja(int franja) {
		if(franja < 1 || franja > 7) throw new LabException("Argumentos erróneos");
		
		this.franja = franja;
	}

	public void setLab(int lab) {
		this.lab = lab;
	}

	@Override
	public String toString() {
		return "(" + asignatura + ", " + diaSem + ", " + franja + ", " + lab + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(asignatura, diaSem, franja);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solicitud other = (Solicitud) obj;
		return asignatura.equalsIgnoreCase(other.asignatura) && diaSem == other.diaSem && franja == other.franja;
	}

	@Override
	public int compareTo(Solicitud o) {
		int result = diaSem - o.diaSem;
		if(result != 0) return result;
		
		result = franja - o.franja;
		if(result != 0) return result;
		
		return asignatura.compareToIgnoreCase(o.asignatura);
	}
}
