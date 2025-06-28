
//--------------------------------------------------------------------------

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import accounts.AccountsException;

//--------------------------------------------------------------------------

public class TestJUnitAccountsException {
	@BeforeAll
	static public void beforeClass() {
		// Code executed before the first test method
		System.out.println("Start of AccountsException JUnit Test");
	}
	@AfterAll
	static public void afterClass() {
		// Code executed after the last test method
		System.out.println("End of AccountsException JUnit Test");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logEntryCtorTest1() {
		AccountsException le1 = new AccountsException();
		Assertions.assertAll("logEntryCtorTest1",
				() -> Assertions.assertTrue(le1 instanceof RuntimeException,
						"\n> Error: AccountsException should be an unchecked exception"),
				() -> Assertions.assertEquals(null, le1.getMessage(),
						"\n> Error: new AccountsException() message should be the empty string"));
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void logEntryCtorTest2() {
		AccountsException le1 = new AccountsException("Test message");
		Assertions.assertAll("logEntryCtorTest2",
				() -> Assertions.assertTrue(le1 instanceof RuntimeException,
						"\n> Error: WebLogException should be an unchecked exception"),
				() -> Assertions.assertEquals("Test message", le1.getMessage(),
						"\n> Error: The message of new WebLogException(\"Test message\") should be \"Test message\""));
	}

}