package accounts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PruebaPago {

	public static void main(String[] args) {
		// i.	Crear pagos con los siguientes datos (concepto e importe):
		Payment pg1 = new Payment("Tapas", 30);
		Payment pg2 = new Payment("Excursión", 50);
		Payment pg3 = new Payment("Cena", 45);
		Payment pg4 = new Payment("Visita a museo", 20);
		Payment pg5 = new Payment("Tapas", 25);
		Payment pg6 = new Payment("Tapas", 30);

		// ii.	Crear un conjunto con los pagos anteriores que esté 
		// ordenado por el orden natural, y mostrarlo en la consola.
		
		Set<Payment> sp = new TreeSet();
		sp.add(pg1);
		sp.add(pg2);
		sp.add(pg3);
		sp.add(pg4);
		sp.add(pg5);
		sp.add(pg6);
		
		System.out.println("Conjunto ordenado de pagos por orden natural: " + sp.toString());
		
		// iii.	Crear una lista con los mismos pagos, y 
		// ordenarla según el inverso del orden natural, 
		// y mostrarla en la consola.

//		List<Pago> lp = new ArrayList<>(sp);
//		lp.sort(Comparator.naturalOrder()); // ¿Es necesario o los elementos se añaden en el mismo orden del sorted set?
//		lp = lp.reversed();

		List<Payment> lp = new ArrayList<>();
		for (Payment pg : sp) lp.add(pg);

//		lp.sort(Comparator.naturalOrder());
//		lp = lp.reversed();
		lp.sort(Comparator.reverseOrder());
		
		System.out.println("Lista ordenada de pagos por orden inverso al natural: " + lp.toString());

		// iv. Crear un pago con los argumentos del main y 
		// mostrarlo en consola, contemplando la posibilidad de 
		// que los datos no sean los esperados, mediante la captura 
		// de las excepciones correspondientes, y mostrando un mensaje en 
		// la consola de error que identifique la anomalía.
		
		try {
			Payment pgArgs = new Payment(args[0], Double.parseDouble(args[1]));			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Not enough command line arguments.");
		} catch (NumberFormatException e) {
			System.err.println("Second command line argument is not a valid double value.");
		} catch (AccountsException e) {
			System.err.println("Importe negativo.");
		}


	}

}
