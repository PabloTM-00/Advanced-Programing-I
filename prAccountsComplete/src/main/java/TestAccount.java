import accounts.Account;
import accounts.GroupAccount;
import accounts.Payment;

public class TestAccount {
	static public void main(String[] args) {
		// This code is to test class Account and its methods
		try {
			Account tripToTitan = new GroupAccount("Trip to Titan");
//			tripToTitan.addPayment("Captain America", "Tapas", 20.0);
//			tripToTitan.addPayment("Captain Marvel", "Excursion", 50.0);
//			tripToTitan.addPayment("Black Widow", "Dinner", 40.0);
//			tripToTitan.addPayment("Black Widow", "Tapas", 25.0);
//			tripToTitan.addPayment("Captain Marvel", "Tapas", 20.0);
//			tripToTitan.addPayment("Thor", "Tapas", 25.0);
//			tripToTitan.addPayment("Captain Marvel", "Dinner", 40.0);
//			tripToTitan.addPayment("Thor", "Excursion", 50.0);
//			
//			tripToTitan.addPayments("data/tripToTitan.txt");
//			System.out.println();
//
//			
//			System.out.println("-Totales: " + tripToTitan.getTotales());
//			System.out.println("-Total amount for 'Tapas': " + tripToTitan.conceptAmount("Tapas"));
//			System.out.println("-All payments: " + tripToTitan.allPayments());

			tripToTitan.addPayments("data/tripToTitan.txt");
			System.out.println();
//			System.out.println("-Totales: " + tripToTitan.getTotals());
//			System.out.println("-Total amount for 'Tapas': " + tripToTitan.conceptAmount("Tapas"));
			System.out.println("-All payments: " + tripToTitan.allPayments());

			tripToTitan.savePayments("data/payments.txt");
			System.out.println();
			System.out.println("-Payments saved in 'data/payments.txt'");
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
