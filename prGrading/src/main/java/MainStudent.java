import grading.*;

public class MainStudent {

	public static void main(String[] args) throws StudentException {
		Student an1 = new Student("22456784F","Gonzalez Perez, Juan",5.5);
	    Student an2 = new Student("33456777S","Gonzalez Perez, Juan",3.4);
		
	  // Student an2 = new Student("33456777S","Gonzalez Perez, Juan",-3.4); // We get the error "Negative grade"

		
		System.out.println("DNI: " + an1.getDni() + ", Name: " +  an1.getName() + ", Grade: " + an1.getGrade());
		System.out.println("DNI: " + an2.getDni() + ", Name: " +  an2.getName() + ", Grade: " + an2.getGrade());
		
		
		
		try {
			if(an1.equals(an2)) {
				System.out.println("Students are equal");
			}
		
		}
		catch(Exception se) {
			System.out.println(se.getMessage());
			return;
		}
		
	}

}
