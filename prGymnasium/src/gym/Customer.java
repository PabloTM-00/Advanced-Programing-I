package gym;

public class Customer {
	public static final double INITIAL_FEE = 4.99;
	public static final double LOCKER_FEE = 0.99;
	public static final double ACTIVITY_FEE = 14.99;
	
	private String id;
	private int yearOfBirth;
	private int CustomerYear;
	private int activities;
	protected boolean weekend_only;
	
	public Customer(String id, int yearOfBirth, int CustomerYear, int activities, boolean weekend_only) {
		super();
		this.id = id;
		this.yearOfBirth = yearOfBirth;
		this.CustomerYear = CustomerYear;
		this.activities = activities;
		this.weekend_only = weekend_only;
		
		if(this.yearOfBirth <1900 || this.yearOfBirth > 2017) {
			throw new RuntimeException ("Year of birth must be between 1900 and 2017");
		}
		
		if (this.weekend_only && this.activities < 1) {
	        throw new RuntimeException("The number of activities must be at least 1 for weekend-only customers");
	    }
	    // Condition for regular customers (activities must be at least 2)
	    else if (!this.weekend_only && this.activities < 2) {
	        throw new RuntimeException("The number of activities must be greater than or equal to 2 for regular customers");
	    }
	}

	public String getId() {
		return id;
	}

	public int getCustomerYear() {
		return CustomerYear;
	}

	public int getActivities() {
		return activities;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public double getMonthlyFee() {
		double total = 0.0;
		int presentYear = java.time.LocalDate.now().getYear();
		
		if(presentYear == CustomerYear) {
			total = (INITIAL_FEE + LOCKER_FEE + ACTIVITY_FEE) * activities;
		}
		else {
			total = (LOCKER_FEE + ACTIVITY_FEE) * activities;
		}
		return total;
	}

	@Override
	public String toString() {
		return "{ *" + id + "*, (year of birth:) *" + yearOfBirth + "*);" + "customer since *" + CustomerYear
				+ "; " + activities + " activities;" + getMonthlyFee() + "_euros_}";
	}

}
