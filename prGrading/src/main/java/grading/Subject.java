package grading;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import grading2.AverageCalculation;

public class Subject {
	private String name;
	private ArrayList<String> errors;
	private ArrayList<Student> studentsl;

	public Subject(String name, String[] students) {
		this.name = name;
		this.errors = new ArrayList<>();
		this.studentsl = new ArrayList<>();

		for (String student : students) {
 			Scanner sc = new Scanner(student);
			sc.useDelimiter("\\s*;\\s*");  
			sc.useLocale(Locale.ENGLISH);  

			try {
				if (!sc.hasNext()) {
					errors.add("ERROR. Missing data: " + student);
					continue;
				}
				String dni = sc.next();

				if (!sc.hasNext()) {
					errors.add("ERROR. Missing data: " + student);
					continue;
				}
				String name1 = sc.next();

				if (!sc.hasNext()) {
					errors.add("ERROR. Missing data: " + student);
					continue;
				}
				String gradeStr = sc.next();

				double grade;

				try {
					grade = Double.parseDouble(gradeStr);
					if (grade < 0) {
						errors.add("ERROR. Negative grade: " + student);
					} else {
						studentsl.add(new Student(dni, name1, grade));
					}
				} catch (NumberFormatException e) {
					errors.add("ERROR. Non numerical grade: " + student);
				}
			} catch (Exception e) {
				errors.add("ERROR. Missing data: " + student);
			}
			sc.close();
		}
	}

	public List<Student> getStudents() {
		return studentsl;
	}

	public List<String> getErrors() {
		return errors;
	}

	public String getName() {
		return name;
	}

	public double getAverage() throws StudentException {
		if (studentsl.isEmpty()) {
			throw new StudentException("No students");
		}
		double total = 0.0;
		for (Student s : studentsl) {
			total += s.getGrade();
		}
		return total / studentsl.size();
	}

	public double getGrade(Student st) throws StudentException {
		for (Student s : studentsl) {
			if (s.equals(st)) {
				return s.getGrade();
			}
		}
		throw new StudentException("Student " + st.toString() + " has not been found");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(": { [");

		for (int i = 0; i < studentsl.size(); i++) {
			sb.append(studentsl.get(i));
			if (i < studentsl.size() - 1) sb.append(", ");
		}

		sb.append("], [");

		for (int i = 0; i < errors.size(); i++) {
			sb.append(errors.get(i));
			if (i < errors.size() - 1) sb.append(", ");
		}

		sb.append("]}");
		return sb.toString();
	}
	
	public double getAverage(AverageCalculation calc) throws StudentException {
	    return calc.calculate(this.students); // or this.studentsl if that's your actual variable
	}
}
