
import tests.Exercise;

import java.util.*;

public class MainResults {
	public static void main(String[] args) {
		try {
			Set<Exercise> exercises = new HashSet<Exercise>();
			exercises.add(new Exercise("Exercise", "pr1", 35, 0));
			exercises.add(new Exercise("Exercise", "pr2", 45, 0));
			exercises.add(new Exercise("Exercise", "pr3", 55, 0));
			Results results = new Results(exercises);
			results.loadFromFile("input.txt");
			// ----------------------
			System.out.println();
			System.out.println(results);
			// ----------------------
			results.completeExercises();
			// ----------------------
			System.out.println();
			System.out.println(results);
			// ----------------------
			results.saveToFile("output.txt");
			// ----------------------
			Set<String> spr = new HashSet<>(Arrays.asList("ana luisa", "maria luisa"));
			System.out.println();
			System.out.println(results.select(new SelectorName(spr)));
			// ----------------------
			System.out.println();
			System.out.println(results.select(new SelectorSuccessThreshold(20)));
			// ----------------------
			results.addExercise(new Exercise("xxx", "xxx"));
			// ----------------------
		} catch (Exception e) {
			System.out.println();
			System.out.println("Error: " + e.getMessage());
		}
	}
}
