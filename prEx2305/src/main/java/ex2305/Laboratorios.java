package ex2305;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Laboratorios {

	private int maxLabs;
	private List<Solicitud> solicitudes;
	private SortedSet<Solicitud> erroresDeAsignacion;
	private SortedMap<Integer,SortedMap<Integer,List<Solicitud>>> asignaciones; /*
	Mapa con K: Dia de la semana, V: Mapa con Numero de franja y lista de solicitudes asignadas
	*/
	
	public Laboratorios(int maxLabs) {
		if(maxLabs < 1) throw new LabException("Argumentos erróneos");
		
		this.maxLabs = maxLabs;
		solicitudes = new ArrayList<>();
		erroresDeAsignacion = new TreeSet<>();
		asignaciones = new TreeMap<>();
	}
	
	protected SortedSet<Solicitud> getErroresDeAsignacion(){
		return erroresDeAsignacion;
	}

	protected void addSolicitud(Solicitud s) {	
		if(!solicitudes.contains(s)) solicitudes.add(s);
	
	}
	
	public void addSolicitud(String solicitud) {
		try {
			String[] parts = solicitud.split("\\s*[;]\\s*");
		
			if(parts.length != 3) throw new LabException("Argumentos erróneos");
			
			String asignatura = parts[0];
			int diaSem = Integer.parseInt(parts[1]);
			int franja = Integer.parseInt(parts[2]);
			
			addSolicitud(new Solicitud(asignatura,diaSem,franja));
		}
		catch(NumberFormatException | LabException e) {
			throw new LabException("Argumentos Erróneos");
		}	
	}
	
	public SortedSet<Solicitud> getSolicitudesOrdenadas(){
		SortedSet<Solicitud> set = new TreeSet<>(new AltSolicitud());
		set.addAll(solicitudes);
		return set;
	}
	
	public void asignarLabs() {
		// Vaciar set y mapa
		erroresDeAsignacion = new TreeSet<>();
		asignaciones = new TreeMap<>();
		
		for(Solicitud s : solicitudes) {
			asignarLabASolicitud(s);
		}
	}
	
	protected void asignarLabASolicitud(Solicitud s) {
		s.setLab(-1);
		
		int dia = s.getDiaSem();
		int franja = s.getFranja();
		
		SortedMap<Integer, List<Solicitud>> submapa = asignaciones.get(dia);
		
		if (submapa == null) { // Si no existe submapa para ese dia se crea uno nuevo
		    submapa = new TreeMap<>();
		    asignaciones.put(dia, submapa);
		}
		
		List<Solicitud> lista = submapa.get(franja);
		if(lista == null) {
			lista = new ArrayList<>();
			submapa.put(franja, lista);
		}
		
		if(lista.size() < maxLabs) {
			lista.add(s);
			s.setLab(lista.size() - 1); // Posicion donde la solicitud ha sido almacenada en la lista
		}
		else erroresDeAsignacion.add(s);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("(Solicitudes: ");
		sb.append(solicitudes);
		sb.append(", \n" + "ErroresDeAsignacion: ");
		sb.append(erroresDeAsignacion);
		sb.append(", \n" + "Asignaciones");
		sb.append(asignaciones).append(")");
		
		return sb.toString();
	}
	
	
	public void cargarSolicitudesDeFichero(String filename) throws IOException{

		Scanner sc = new Scanner(new File(filename));
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			try {
				addSolicitud(line);
			}
			catch(LabException e) {
				
			}
		}
		sc.close();
	}
	
	public void guardarAsignacionesEnFichero(String filename) throws IOException {
		PrintWriter pw = new PrintWriter(new File(filename));
		
		mostrarAsignaciones(pw);
		
		pw.close();
	}
	
	protected void mostrarAsignaciones(PrintWriter pw) {
	    // Asignaciones ordenadas por día y franja
	    for (Map.Entry<Integer, SortedMap<Integer, List<Solicitud>>> entryDia : asignaciones.entrySet()) {
	        int dia = entryDia.getKey();
	        SortedMap<Integer, List<Solicitud>> franjas = entryDia.getValue();

	        for (Map.Entry<Integer, List<Solicitud>> entryFranja : franjas.entrySet()) {
	            int franja = entryFranja.getKey();
	            List<Solicitud> solicitudesEnFranja = entryFranja.getValue();

	            pw.println("DiaSem: " + dia + "; Franja: " + franja);
	            for (int i = 0; i < solicitudesEnFranja.size(); i++) {
	                pw.println("Lab: " + i + ": " + solicitudesEnFranja.get(i));
	            }
	        }
	    }
	    pw.println("ErroresDeAsignacion:");
	    for (Solicitud s : erroresDeAsignacion) {
	        pw.println(s);
	    }
	}

}