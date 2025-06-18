package ex2305;

import java.util.Comparator;

public class AltSolicitud implements Comparator<Solicitud> {

	@Override
	public int compare(Solicitud o1, Solicitud o2) {
		return o1.getAsignatura().compareToIgnoreCase(o2.getAsignatura());
		// Devuelve en orden ascendente A -> Z
		
		// return o2.get().compareTo(o1.get()) ordenaria Z -> A
	}

}
