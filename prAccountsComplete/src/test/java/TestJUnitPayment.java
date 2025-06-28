
//--------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import accounts.AccountsException;
import accounts.Payment;

//--------------------------------------------------------------------------

public class TestJUnitPayment {
	@BeforeAll
	static public void beforeClass() {
		// Code executed before the first test method
		System.out.println("Start of Payment JUnit Test");
	}
	@AfterAll
	static public void afterClass() {
		// Code executed after the last test method
		System.out.println("End of Payment JUnit Test");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void paymentCtorTest1() {
		Payment payment = new Payment("Zoo", 100.0d);
		assertAll("logEntryCtorTest1",
			() -> assertEquals("Zoo", payment.getConcept(), "\n> Error: Concept for new Payment(\"Zoo\", 100.0d) should be Zoo"),
            () -> assertEquals(100.0d, payment.getAmount(), "\n> Error: Amount for new Payment(\"Zoo\", 100.0d) should be 100.0d"),
            () -> assertTrue(payment.equals(new Payment("Zoo", 100.0d)), "\n> Error: Payment should be equal to another Payment with the same values"),
            () -> assertFalse(payment.equals(new Payment("Zoo", 200.0d)), "\n> Error: Payment should not be equal to another Payment with different amount")
		);
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void paymentCtorTestExcept1() {
		
		assertThrows(AccountsException.class, 
		             () -> new Payment("Zoo", -100.0d), 
					 "\n> Error: Expected AccountsException when amount is less than 0.0d");
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void paymentEqTest1() {
		Payment payment = new Payment("Zoo", 100.0d);
		Payment payment2 = new Payment("zoo", 100.0d);
		Payment payment3 = new Payment("Zoo", 200.0d);
		Payment payment4 = new Payment("Theater", 100.0d);
		assertAll("paymentEqTest1",
			() -> assertEquals(payment, payment2, "\n> Error: new Payment(\"Zoo\", 100.0d) and new Payment(\"zoo\", 100.0d) should be equal"),
            () -> assertNotEquals(payment, payment3, "\n> Error: new Payment(\"Zoo\", 100.0d) and new Payment(\"Zoo\", 200.0d) should be equal"),
            () -> assertNotEquals(payment, payment4, "\n> Error: new Payment(\"Zoo\", 100.0d) and new Payment(\"Zoo\", 200.0d) should be equal")
		);
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void paymentHashCodeTest1() {
		Payment payment = new Payment("Zoo", 100.0d);
		Payment payment2 = new Payment("zoo", 100.0d);
		assertEquals(payment.hashCode(),payment2.hashCode(),
				"\n> Error: new Payment(\"Zoo\", 100.0d).hashCode() should be equal to new Payment(\"zoo\", 100.0d).hashCode()");
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void paymentCompareToTest1() {
		Payment payment = new Payment("Zoo", 100.0d);
		Payment payment2 = new Payment("zoo", 100.0d);
		Payment payment3 = new Payment("Zoo", 200.0d);
		Payment payment4 = new Payment("Theater", 100.0d);
		Payment payment5 = new Payment("Zoo", 99.4d);
		assertAll("paymentCompareToTest1", 
				() -> assertEquals(0, payment.compareTo(payment2),
				"\n> Error: new Payment(\"Zoo\", 100.0d).compareTo(new Payment(\"zoo\", 100.0d)) should return 0"),
				() -> assertTrue(payment.compareTo(payment3) < 0,
						"\n> Error: new Payment(\"Zoo\", 100.0d).compareTo(new Payment(\"Zoo\", 200.0d)) should return a negative value"),
				() -> assertTrue(0 < payment.compareTo(payment4),
						"\n> Error: new Payment(\"Zoo\", 100.0d).compareTo(new Payment(\"Theater\", 100.0d)) should return a positive value"),
				() -> assertFalse(payment.compareTo(payment5) == 0,
						"\n> Error: new Payment(\"Zoo\", 100.0d).compareTo(new Payment(\"Zoo\", 99.4d)) should return a value different from 0"));
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void paymentTStringToTest1() {
		String paymentAsString = new Payment("Zoo", 100.0d).toString();
		assertAll("paymentCompareToTest1", 
			() -> assertTrue(paymentAsString.startsWith("Payment(Zoo, 100."),
			"\n> Error: String representation of new Payment(\"Zoo\", 100.0d) should start with \"Payment(Zoo, 100.\""),
			() -> assertTrue(paymentAsString.endsWith(")"),
			"\n> Error: String representation of new Payment(\"Zoo\", 100.0d) should end with a parenthesis")
		);
	}
	
}
