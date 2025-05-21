package grading;

import java.util.Objects;

public class Student {
	private String dni;
	private String name;
	private double grade;
	
	public Student(String dni, String name, double grade) throws StudentException {
		super();
		
		if(this.grade < 0.0) {
			throw new StudentException("Negative grade");
		}
		
		this.dni = dni;
		this.name = name;
		this.grade = grade;
	}

	public Student(String dni, String name) {
		super();
		this.dni = dni;
		this.name = name;
		this.grade = 0.0;
	}

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public double getGrade() {
		return grade;
	}

	@Override
	public String toString() {
		return name + "(" + dni + ")";
	}
	@Override
	public boolean equals(Object st) {
		return (st instanceof Student) &&
				(name.equals(((Student) st).name) &&
		(((Student) st).dni.equalsIgnoreCase(dni)));
		}
	@Override
	public int hashCode() {
		return Objects.hash(dni.toUpperCase(),name);
	}

	
}
