package accounts;

import java.util.ArrayList;
import java.util.List;

public class GroupAccount extends Account {

	public GroupAccount(String nombre) {
		super(nombre);
	}
	
	@Override
	public void addPayment(String person, String concept, double amount) {
		List<Payment> lp = this.personPayments.get(person);
		if (lp == null) {
			lp = new ArrayList<>();
			personPayments.put(person, lp);
		}
		int i = lp.size()-1;
		while(0 <= i && !lp.get(i).getConcept().equalsIgnoreCase(concept)) {
			i--;
		}
		if (i < 0) {
			super.addPayment(person, concept, amount);
		} else {
			Payment p = lp.get(i);
			lp.set(i, new Payment(p.getConcept(), p.getAmount()+amount));
		}
	}

}
