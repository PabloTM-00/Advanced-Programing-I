package gym;

public class GymWithDiscounts extends Gymnasium {
	private int youngThreshold = 21;
	private int elderThreshold = 65;
	
	
	public GymWithDiscounts(int youngThreshold, int elderThreshold) {
		super();
		this.youngThreshold = youngThreshold;
		this.elderThreshold = elderThreshold;
	}
	
	public void addCustomer(String id, int yearOfBirth, int memberYear, int activities, boolean weekend_only) {
		int presentYear = java.time.LocalDate.now().getYear();
		int age = presentYear - yearOfBirth;
		
		Customer c;
		if (age <= youngThreshold || age >= elderThreshold) {
			c = new CustomerWithDiscount(id, yearOfBirth, memberYear, activities, weekend_only);
		} 
		else {
			c = new Customer(id, yearOfBirth, memberYear, activities, weekend_only);
		}

		addCustomer(c);
	}

	@Override
	public String toString() {
		return "GymWithDiscounts [youngThreshold=" + youngThreshold + ", elderThreshold=" + elderThreshold + getCustomers() +"]";
	}
	
	
}
