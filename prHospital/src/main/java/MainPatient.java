import hospital.Patient;
import hospital.HospitalException;

public class MainPatient {

	public static void main(String[] args) {
		try {
			Patient c1 = new Patient("Abraham", "Lincoln", "6669444861", 1809);
			Patient c2 = new Patient("Adam", "Smith", "5186046392", 1723);
			Patient c3 = new Patient("Adam", "Smith", "5186046392", 1723);

			System.out.printf("%s is %sequal to %s\n", c1, c1.equals(c2) ? "" : "not ", c2);
			System.out.printf("%s is %sequal to %s\n", c2, c2.equals(c3) ? "" : "not ", c3);
			System.out.printf("%s is %ssmaller than %s\n", c1, c1.compareTo(c2) < 0 ? "" : "not ", c2);
			System.out.printf("%s is %ssmaller than %s\n", c2, c2.compareTo(c3) < 0 ? "" : "not ", c3);
			new Patient("Adam", "Smith", "5186046391", 1723);
		} catch (HospitalException e) {
			e.printStackTrace();
		}
	}

}

/**
 * Expected output:
 * 
 * <pre>
Patient [name=Abraham, surname=Lincoln, ssn=6669444861, birthYear=1809] is not equal to Patient [name=Adam, surname=Smith, ssn=5186046392, birthYear=1723]
Patient [name=Adam, surname=Smith, ssn=5186046392, birthYear=1723] is equal to Patient [name=Adam, surname=Smith, ssn=5186046392, birthYear=1723]
Patient [name=Abraham, surname=Lincoln, ssn=6669444861, birthYear=1809] is not smaller than Patient [name=Adam, surname=Smith, ssn=5186046392, birthYear=1723]
Patient [name=Adam, surname=Smith, ssn=5186046392, birthYear=1723] is not smaller than Patient [name=Adam, surname=Smith, ssn=5186046392, birthYear=1723]
hospital.HospitalException: Wrong SSN
	at hospital.Patient.<init>(Patient.java:40)
	at MainPatient.main(MainPatient.java:16)
 * </pre>
 */