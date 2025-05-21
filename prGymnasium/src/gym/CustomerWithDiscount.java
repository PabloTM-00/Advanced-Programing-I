package gym;

public class CustomerWithDiscount extends Customer {
	public static final int YOUNG_THRESHOLD = 21;
	public static final int ELDER_THRESHOLD = 65;
	
	public static final double YOUNG_DISCOUNT_PERCENTAGE = 0.80;
	public static final double ELDER_DISCOUNT = 9.99;
	private static final String CustomerYear = null;
	
	
	public CustomerWithDiscount(String id, int yearOfBirth, int CustomerYear, int activities, boolean weekend_only) {
		super(id, yearOfBirth, CustomerYear, activities, weekend_only);
		int presentYear = java.time.LocalDate.now().getYear();
		int age = presentYear - yearOfBirth;
		
		
		if(age >= YOUNG_THRESHOLD && age <= ELDER_THRESHOLD ) {
			throw new RuntimeException("ERROR: Not Applicable for discount");	
		}
	}
	
	private double discount() {
		double d = 0.0;
		int presentYear = java.time.LocalDate.now().getYear();
		
		int age = getYearOfBirth() - presentYear;
		
		if(age <= YOUNG_THRESHOLD) {
			d = getMonthlyFee() * YOUNG_DISCOUNT_PERCENTAGE;
		}
		else if(age >= ELDER_THRESHOLD) {
			d = ELDER_DISCOUNT;
		}
		return d;
	}
	
	public double getMonthlyFee() {
		double total = 0.0;
		int presentYear = java.time.LocalDate.now().getYear();
		
		if(presentYear == getCustomerYear()) {
			total = ((INITIAL_FEE + LOCKER_FEE + ACTIVITY_FEE) * getActivities()) - discount() ;
		}
		else {
			total = ((LOCKER_FEE + ACTIVITY_FEE) * getActivities()) - discount();
		}
		return total;
	}
	
	@Override
	public String toString() {
		return "{ *" + getId() + "*, (year of birth:) *" + getYearOfBirth() + "*);" + "customer since *" + CustomerYear
				+ "; " + getActivities() + " activities; discount:"+ discount() +" euros " + getMonthlyFee() + "_euros_}";
	}
	
}





















