package prScooters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Company {
	private String name;
	private SortedSet<Scooter> scooters;
	private List<String> errors;
	private Map<Employee, SortedSet<Integer>> employees;

	public Company(String name, String filename) throws ScootersException {
		this.name = name;
		this.scooters = new TreeSet<>();
		this.errors = new ArrayList<>();
		this.employees = new TreeMap<>();

		Scanner sc = null;

		try {
			sc = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			throw new ScootersException("File not found: " + filename);
		}

		int lineNumber = 1;

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] parts = line.split(";");

			
			// Means incorrect format; missing parts
			if (parts.length != 5) {
				errors.add("ERROR: Incorrect number of data in LINE " + lineNumber);
				lineNumber++;
				continue;
			}

			try {
				String companyName = parts[0].trim();
				int code = Integer.parseInt(parts[1].trim());
				double lat = Double.parseDouble(parts[2].trim());
				double lon = Double.parseDouble(parts[3].trim());
				double range = Double.parseDouble(parts[4].trim());


				/**
				 * If the company name in that line does not match the name of 
				 * the company being created (ignoring the letter case), 
				 * that line is not taken into account
				 */
				
				if (!companyName.equalsIgnoreCase(this.name)) {
					lineNumber++;
					continue;
				}

				// Check for negative values
				if (code < 0) {
					throw new Exception("Negative code");
				}
				if (range < 0) {
					throw new Exception("Negative range");
				}

				Position pos = new Position(lat, lon);
				Scooter s = new Scooter(companyName, code, pos, range);
				scooters.add(s);

			} catch (NumberFormatException e) {
				errors.add("ERROR: Incorrect data type in LINE " + lineNumber);
			} catch (Exception e) {
				errors.add("ERROR: " + e.getMessage() + " in LINE " + lineNumber);
			}

			lineNumber++;
		}

		sc.close();
	}
	
	public void assignScootersToEmployee(Employee e, SortedSet<Integer> codes) {
		SortedSet<Integer> validCodes = new TreeSet<>();
		
		for(Integer code : codes) {
			// First find if the scooter exists in the company
			Scooter sc = searchScooter(code);
			
			if(sc == null) continue; // Doesn't belong to company
			
			// Is it assigned to someone?
			boolean assigned = false;
			for(SortedSet<Integer> assignedCodes :employees.values()) {
				// Iterate value set of codes for employee, checking i_th code to the one in the main for loop
				if(assignedCodes.contains(code)) {
					assigned = true;
					break;
				}
			}
			
			// We have checked current code is not assigned to anyone, we add it
			if(!assigned) {
				validCodes.add(code);
			}
			
			// Now we have the sets of valid codes, but not assigned to any employee, so
			if(!employees.containsKey(e)) { // If Employee e isnt in map we add it for the first time with its codes
				employees.put(e, validCodes);
			}
			else { // If its in map, we get the employee but we dont add it again, we just add the new valid codes
				employees.get(e).addAll(validCodes);
			}
			
		}
	}

	protected Scooter searchScooter(Integer code) {
		for(Scooter s : scooters) {
			if(s.getCode() == code) {
				return s;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(name).append("\n");
		
		sb.append("Scooters: ").append(scooters).append("\n");
		sb.append("Errors: ").append(errors).append("\n");
		
		sb.append("Employees: {");
		boolean first = true;
		for(Map.Entry<Employee,SortedSet<Integer>> entry : employees.entrySet()) {
			if(!first) sb.append(", ");
			sb.append(entry.getKey().getID()).append(": ").append(entry.getValue());
			first = false;
		}
		sb.append("}");
		
		return sb.toString();
	}
	
	
	public void modifyScooter(int code, Position p, double range) throws ScootersException {
		Scooter sc = searchScooter(code);
		
		if(sc == null) throw new ScootersException("Scooter not in the set");
		
		sc.setPosition(p); // We dont need to remove and add to the set since compareTo(Scooter o) 
						   // isnt dependent on position or range
		sc.setRange(range);
	}
}
