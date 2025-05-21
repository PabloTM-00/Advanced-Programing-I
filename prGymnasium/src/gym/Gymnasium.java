package gym;

import java.util.ArrayList;

public class Gymnasium {
	private ArrayList <Customer> customers = new ArrayList<>();

	public Gymnasium() {
		this.setCustomers(new ArrayList<>());
	}
	
	public void addCustomer(String id, int yearOfBirth, int memberYear, int activities, boolean weekend_only) {
		Customer newCustomer = new Customer(id, yearOfBirth, memberYear, activities, weekend_only);
	    addCustomer(newCustomer);
	}
	
	protected void addCustomer(Customer c) {
		Customer toReplace = null;
		
		for(Customer existing : getCustomers()) {
			if(existing.getId().equalsIgnoreCase(c.getId())) {
				toReplace = existing;
				break;
			}
		}
		if (toReplace != null) {
	        getCustomers().remove(toReplace);
	    }

	    getCustomers().add(c);
		
	}
	
	public ArrayList<Customer> getCustomersBornAt(int yearOfBirth){
		ArrayList<Customer> bornAt = new ArrayList<>();
		
		for (Customer c : getCustomers()) {
			if(c.getYearOfBirth() == yearOfBirth) {
				bornAt.add(c);
			}
		}
		return bornAt;
	}

	@Override
	public String toString() {
		return "Gymnasium [customers=" + getCustomers() + "]";
	}

	public ArrayList <Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList <Customer> customers) {
		this.customers = customers;
	}
	
	
	
}















