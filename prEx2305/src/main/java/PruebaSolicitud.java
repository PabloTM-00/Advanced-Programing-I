import ex2305.*;

public class PruebaSolicitud {

	public static void main(String[] args) {
		try {
			Solicitud s1 = new Solicitud("POO-B",3,2);
			Solicitud s2 = new Solicitud("poo-b",3,2);
			Solicitud s3 = new Solicitud("Web-A",2,3);
			
			System.out.println(s1.toString());
			System.out.println(s2.toString());
			System.out.println(s3.toString());
			
			if(s1.equals(s2)) System.out.println("Iguales");
			else System.out.println("Distintos");
			
			if(s1.equals(s3)) System.out.println("Iguales");
			else System.out.println("Distintos");
			
			if(s2.equals(s3)) System.out.println("Iguales");
			else System.out.println("Distintos");
			
			System.out.println(s1.compareTo(s2));
			System.out.println(s1.compareTo(s3));
			System.out.println(s2.compareTo(s3));

		}
		catch(LabException e) {
			
		}

	}

}
