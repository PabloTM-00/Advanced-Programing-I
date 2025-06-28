import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import accounts.GroupAccount;
import accounts.Payment;

public class TestJUnitGroupAccount {

	
	List<Payment> expectedPayments = 
			List.of(
					new Payment("Lunch", 40.0), 
					new Payment("Tapas", 20.0),
					new Payment("Tapas", 20.0),
					new Payment("Excursion", 50.0),
					new Payment("Theater", 25.0),
					new Payment("Dinner", 50.0),
					new Payment("Excursion", 50.0),
					new Payment("Tapas", 20.0),
					new Payment("Theater", 25.0),
					new Payment("Excursion", 50.0),
					new Payment("Tapas", 30.0),
					new Payment("Dinner", 40.0),
					new Payment("Theater", 30.0),
					new Payment("Excursion", 50.0),
					new Payment("Tapas", 25.0),
					new Payment("Dinner", 40.0),
					new Payment("Tapas", 25.0));
	
	@BeforeAll
	static public void beforeClass() {
		// Code executed before the first test method
		System.out.println("Start of Account JUnit Test");
	}
	@AfterAll
	static public void afterClass() {
		// Code executed after the last test method
		System.out.println("End of Account JUnit Test");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void accountGetEventTest1() {
		GroupAccount account = new GroupAccount("Trip to Titan");
		account.addPayment("Captain Marvel", "Lunch", 40.0);
		account.addPayment("Captain Marvel", "Tapas", 20.0);
		account.addPayment("Captain America", "Tapas", 20.0);
		account.addPayment("Dr. Strange", "Excursion", 50.0);
		account.addPayment("Dr. Strange", "Theater", 25.0);
		account.addPayment("Dr. Strange", "Theater", 51.0);
		account.addPayment("Hulk", "Dinner", 50.0);
		account.addPayment("Hulk", "Excursion", 50.0);
		account.addPayment("Hulk", "Tapas", 20.0);
		account.addPayment("Hulk", "Theater", 25.0);
		account.addPayment("Iron Man", "Excursion", 50.0);
		account.addPayment("Iron Man", "Tapas", 30.0);
	    account.addPayment("Spiderman", "Dinner", 40.0);
	    account.addPayment("Spiderman", "Theater", 30.0);
	    account.addPayment("Thor", "Excursion", 50.0);
	    account.addPayment("Thor", "Tapas", 25.0);
	    account.addPayment("Thor", "Excursion", 51.0);
	    account.addPayment("Black Widow", "Dinner", 40.0);
	    account.addPayment("Black Widow", "Tapas", 25.0);
        List<Payment> payments = account.allPayments();
        assertAll(
        	() -> assertTrue(payments.contains(new Payment("Theater", 76)),
        			"Grouped payment for 'Theater' with amount 76 not found"),
			() -> assertTrue(payments.contains(new Payment("Excursion", 101)),
        			"Grouped payment for 'Excursion' with amount 101 not found")
        	);
    }
}