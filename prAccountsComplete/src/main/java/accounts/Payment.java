package accounts;

import java.util.Objects;

public class Payment implements Comparable<Payment> {

	private String concept;
	private double amount;
	
	public Payment(String concept, double amount) {
		if (amount < 0.0d) {
			throw new AccountsException("Importe de pago negativo");
		}
		this.concept = concept;
		this.amount = amount;
	}

	public String getConcept() {
		return concept;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(concept.toUpperCase(), amount);
	}

	@Override
	public boolean equals(Object obj) {
		return (this == obj) || ((obj instanceof Payment p) 
				                && concept.toLowerCase().equals(p.concept.toLowerCase())
				                && amount == p.amount);
	}

	@Override
	public int compareTo(Payment o) {
		int compareToResult = concept.compareToIgnoreCase(o.concept);
		if (compareToResult == 0) {
			compareToResult = Double.compare(amount, o.amount);
		}
		return compareToResult;
		
	}

	@Override
	public String toString() {
		return "Payment(" + concept + "," + amount + ")";
	}
	
	
}
