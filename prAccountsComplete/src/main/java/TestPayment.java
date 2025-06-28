

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import accounts.AccountsException;
import accounts.Payment;

public class TestPayment {

	public static void main(String[] args) {
		// i.	Create payments with the following data (concept and amount):
		Payment pg1 = new Payment("Tapas", 30);
		Payment pg2 = new Payment("Excursion", 50);
		Payment pg3 = new Payment("Dinner", 45);
		Payment pg4 = new Payment("Museum visit", 20);
		Payment pg5 = new Payment("Tapas", 25);
		Payment pg6 = new Payment("Tapas", 30);

		// ii.	Create a set with the previous payments,  
		// sorted by the natural orden of Payment and show it on console.
		
		Set<Payment> sp = new TreeSet<>();
		sp.add(pg1);
		sp.add(pg2);
		sp.add(pg3);
		sp.add(pg4);
		sp.add(pg5);
		sp.add(pg6);
		
		System.out.println("Set of payments sorted by natural order: " + sp.toString());
		
		// iii.	Create a list with the same payments and 
		// sorted in the reversed order of the natural order 
		// of Payment. Show the list on console.

//		List<Pago> lp = new ArrayList<>(sp);
//		lp.sort(Comparator.naturalOrder()); // ¿Es necesario o los elementos se añaden en el mismo orden del sorted set?
//		lp = lp.reversed();

		List<Payment> lp = new ArrayList<>();
		for (Payment pg : sp) lp.add(pg);

//		lp.sort(Comparator.naturalOrder());
//		lp = lp.reversed();
		lp.sort(Comparator.reverseOrder());
		
		System.out.println("List of payments sorted by the natural order of payments, reversed: " + lp.toString());

		// iv. Create a payment with the main command line arguments and  
		// show it on console, taking into account the possibility  
		// that the input data do not match the expected format by catching 
		// the corresponding exceptions and showing a message on console
		// reporting about the cause of the unexpected behaviour.
		
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
