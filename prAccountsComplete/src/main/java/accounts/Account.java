package accounts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Account {

	private String name;

	protected Map<String, List<Payment>> personPayments;

	public Account(String name) {
		this.name = name;
		personPayments = new HashMap<>();
	}

	public void addPayment(String person, String concept, double amount) {
		try {
//			personPayments.computeIfAbsent(person, k -> new ArrayList<>()).add(new Payment(concept, amount));
			Payment pg = new Payment(concept, amount);
			List<Payment> lPayments = personPayments.get(person);
			if (lPayments == null) {
				lPayments = new ArrayList<>();
				personPayments.put(person, lPayments);
			}
			lPayments.add(pg);
		} catch (AccountsException e) {
			// Ignorar pagos erróneos
		}
	}
	
	public String getEvent() {
		return name;
	}

	public void addPayments(String fileName) {
		try(BufferedReader br = Files.newBufferedReader(Path.of(fileName))){
			String line = br.readLine();
			while(line != null) {
				try (Scanner sc = new Scanner(line)) {
					sc.useDelimiter(":");
					if(sc.hasNext()) {
						String payersName = sc.next();
						if (sc.hasNext()) {
							String pays = sc.next();
							String [] paysArr = pays.split(",");
							for (String pay : paysArr) {
								int indexOfHyphen = pay.indexOf('-');
								if (0 <= indexOfHyphen) {
									String concept = pay.substring(0, indexOfHyphen);
									String amountAsString = pay.substring(indexOfHyphen+1, pay.length());
									try {
										Double amount = Double.parseDouble(amountAsString.trim());
										this.addPayment(payersName, concept, amount);
									} catch(NumberFormatException e) {
										throw new AccountsException("Incorrect payment line (invalid amount): " + pay);
									}
								} else {
									throw new AccountsException("Incorrect payment line (missing '-'): " + pay);
								}
							}
						} else {
							throw new AccountsException("Incorrect payment line (missing payment info): " + line);
						}
					} else {
						throw new AccountsException("Incorrect payer's name (missing ':'): " + line);
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//
//	public Map<String,Double> getTotals(){
//		Map<String, Double> totalPerPerson = new HashMap<>();
//		for (Map.Entry<String, List<Payment>> e : personPayments.entrySet()) {
//			double thisEntryTotal = 0.0d;
//			for (Payment p: e.getValue()) {
//				thisEntryTotal += p.getAmount();
//			}
//			totalPerPerson.put(e.getKey(), thisEntryTotal);
//		}
//		return totalPerPerson;
//	}
//
//	public double conceptAmount(String concept) {
//		double cncptAmount = 0.0d;
//		for (List<Payment> l : personPayments.values()) {
//			for (Payment p: l) {
//				if (p.getConcept().equalsIgnoreCase(concept)) {
//					cncptAmount += p.getAmount();
//				}
//			}
//		}
//		return cncptAmount;
//	}

	public List<Payment> allPayments(){
//		List<Payment> allPaymentsWithstream = personPayments.values()
//				.stream()
//					.flatMap(Collection::stream)
//						.toList();
//		allPaymentsWithstream.sort(Comparator.comparingDouble(Payment::getAmount)
//				.reversed()
//					.thenComparing(p -> p.getConcept()));
		
		
		List<Payment> lPago = new ArrayList<>();
		for (List<Payment> lp : personPayments.values()) {
			lPago.addAll(lp);
		}
		lPago.sort(
			(Payment p1, Payment p2) -> {
				int comp = Double.compare(p2.getAmount(), p1.getAmount());
				if (comp == 0) {
					comp = p1.getConcept().toLowerCase()
							.compareTo(p2.getConcept().toLowerCase());
				}
				return comp;
			}
		);
		return lPago;
	}

	public void savePayments(String filename) {
		try(PrintWriter pw = new PrintWriter(new File(filename))){
			savePayments(pw);
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		}
	}
	void savePayments(PrintWriter pw) {
		pw.println("*** " + name + " ***");
		for(String name: personPayments.keySet()) {
			pw.println(name+":");
			for (Payment p : personPayments.get(name)) {
				pw.println("\t" + p.getConcept()+" -> " + p.getAmount());
			}
		}
	}

}
