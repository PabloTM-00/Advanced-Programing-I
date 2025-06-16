package hospital;

import java.util.Objects;

public class Patient implements Comparable<Patient> {

	private String name;
	private String surname;
	private String ssn;
	private int birthYear;
	
	public Patient(String name, String surname, String ssn, int birthYear) throws HospitalException {
	    super();

	    if(name == null || surname == null || ssn == null) {
	        throw new HospitalException("Name, surname or SSN cannot be null");
	    }
	    if(name.trim().isEmpty() || surname.trim().isEmpty()) {
	        throw new HospitalException("Name or surname cannot be empty or blank");
	    }
	    if(ssn.length() != 10) {
	        throw new HospitalException("SSN must be exactly 10 digits");
	    }

	    String firstEight = ssn.substring(0, 8);
	    String controlDigits = ssn.substring(8, 10);

	    int number;
	    int control;
	    
	    try {
	        number = Integer.parseInt(firstEight);
	        control = Integer.parseInt(controlDigits);
	    } catch (NumberFormatException e) {
	        throw new HospitalException("SSN must contain only digits");
	    }

	    int remainder = number % 97;

	    if (control != remainder) {
	        throw new HospitalException("SSN control digits incorrect");
	    }

	    this.name = name;
	    this.surname = surname;
	    this.ssn = ssn;
	    this.birthYear = birthYear;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getSsn() {
		return ssn;
	}

	public int getBirthYear() {
		return birthYear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthYear, name, ssn, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return birthYear == other.birthYear && name.equalsIgnoreCase(other.name) && Objects.equals(ssn, other.ssn)
				&& surname.equalsIgnoreCase(other.surname);
	}

	@Override
	public int compareTo(Patient other) {
		if(this.birthYear != other.birthYear) return this.birthYear - other.birthYear;
		
		int ssnComparison = this.ssn.compareTo(other.ssn);
		if(ssnComparison != 0) return ssnComparison;
		
		int surnameComparison = this.surname.compareToIgnoreCase(other.surname);
		if(surnameComparison != 0) return surnameComparison;
		
		return this.name.compareToIgnoreCase(other.name);
	}

	@Override
	public String toString() {
		return "Patient [name=" + name + ", surname=" + surname + ", ssn=" + ssn + ", birthYear=" + birthYear + "]";
	}
	
	
	

}
