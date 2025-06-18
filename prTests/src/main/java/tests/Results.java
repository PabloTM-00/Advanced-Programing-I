package tests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


public class Results {

	private SortedMap<String,SortedSet<Exercise>> students;
	private SortedSet<Exercise> exercises;
	
	public Results(Set<Exercise> set) {
		exercises = new TreeSet<>();
		students = new TreeMap<>();
		
		for(Exercise e : set) {
			exercises.add(new Exercise("",e.getDescription(),e.getExecuted(),0)); 
			/**
			 * 	"Both the name of the student and the number of successfully-passed 
			 *   is not relevant tests in these exercises " (Documentation) so we 
			 *   just make up the values
			 */
		}
	}
	
	private Exercise search(SortedSet<Exercise> set, String description) {
		for(Exercise e : set) {
			if(e.getDescription().equalsIgnoreCase(description)) return e;
		}
		return null;
	}
	
	public void addExercise(Exercise e) {
		
		Exercise ex = search(exercises,e.getDescription());
		if(ex == null) throw new AppException("Cant find exercise with given description");
		
		// Set the n of executed tests for the received exercise
		e.setExecuted(ex.getExecuted());
		
		// Get student name in lowercase to use it as Key
	    String studentName = e.getName().toLowerCase();
		
	    SortedSet<Exercise> studentExercises = students.get(studentName);
		
		if(studentExercises == null) { // If Set doesnt exist we create it
			studentExercises = new TreeSet<>();
			students.put(studentName, studentExercises);
		}
		
		// If Set already contains exercise, remove then add again
		if(studentExercises.contains(e)) {
			studentExercises.remove(e);
		}
		studentExercises.add(e);
	}
	
	public Results select(Selector s) {
		// Create new Results object with the base exercises
		Results filteredResults = new Results(this.exercises);
		
		for(SortedMap.Entry<String,SortedSet<Exercise>> entry : students.entrySet()) {
			SortedSet<Exercise> studentExercises = entry.getValue();
			
			if(s.isSelectable(studentExercises)) {
				for(Exercise e : exercises) {
					filteredResults.addExercise(e);
				}
			}
		}
		return filteredResults;	
	}

	@Override
	public String toString() {
		return "{" + exercises + "; " + students + "}";
	}
	
	public void saveToFile(String filename) throws IOException {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
	    	
	        for (String studentName : students.keySet()) {
	            for (Exercise exercise : students.get(studentName)) {
	                String line = studentName + "; " + exercise.getDescription() + "; " + exercise.getSuccessful();
	                bw.write(line);
	                bw.newLine();
	            }
	        }
	    }
	}
	
	public void loadFromFile(String filename) throws IOException {
		try (Scanner sc = new Scanner(new File(filename))) {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split("\\s*[;]\\s*");

				if (parts.length != 3) continue;

				try {
					String name = parts[0];
					String description = parts[1];
					int successful = Integer.parseInt(parts[2]);

					Exercise e = new Exercise(name, description, 0, successful);
					addExercise(e);
				} catch (Exception ex) {
					// Se ignora la línea inválida
					continue;
				}
			}
		}
	}




	public void completeExercises() {
		Set<String> studentNames = new TreeSet<>(students.keySet());
		
		SortedSet<Exercise> studentExercises = new TreeSet<>();
		
		for(String student : studentNames) {
			studentExercises = students.get(studentNames);
			for(Exercise ex : exercises) {
				// Find by description if student already has that exercise
				if((search(studentExercises,ex.getDescription()) == null)) {
					// Add it otherwise with executed and successful to 0
					Exercise e = new Exercise(student,ex.getDescription(),0,0);
					addExercise(e);
				}
			}
		}
	}
}
