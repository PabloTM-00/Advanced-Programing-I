import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import accounts.Account;
import accounts.AccountsException;
import accounts.Payment;

//--------------------------------------------------------------------------

public class TestJUnitAccount {

	
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
		assertEquals("Trip to Titan", new Account("Trip to Titan").getEvent(), 
				"\n> Error: Event name for new Account(\"Trip to Titan\") should be 'Trip to Titan'");
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void addPaymentTest1() {
		Account account = new Account("Trip to Titan");
		account.addPayment("Captain Marvel", "Lunch", 40.0);
		account.addPayment("Captain Marvel", "Tapas", 20.0);
		account.addPayment("Captain America", "Tapas", 20.0);
		account.addPayment("Dr. Strange", "Excursion", 50.0);
		account.addPayment("Dr. Strange", "Theater", 25.0);
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
	    account.addPayment("Black Widow", "Dinner", 40.0);
	    account.addPayment("Black Widow", "Tapas", 25.0);
        List<Payment> payments = account.allPayments();		
		assertTrue(payments.containsAll(expectedPayments));
	}

		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void addPaymentsTest1() {
		String paymentsFileContent = """
Captain Marvel:Lunch-40.0,Tapas-20.0
Captain America:Tapas-20.0
Dr. Strange:Excursion-50.0,Theater-25.0
Hulk:Dinner-50.0,Excursion-50.0,Tapas-20.0,Theater-25.0
Iron Man:Excursion-50.0,Tapas-30.0
Spiderman:Dinner-40.0,Theater-30.0
Thor:Excursion-50.0,Tapas-25.0
Black Widow:Dinner-40.0,Tapas-25.0
""";
		
//		File f = null;
		try {
//			f = new File("testingAddPayments.txt");
			File tempFile = File.createTempFile("temp", ".txt");
			PrintWriter pw = new PrintWriter(tempFile);
			pw.print(paymentsFileContent);
			pw.close();
			Account account = new Account("Trip to Titan");
			account.addPayments(tempFile.getAbsolutePath());
			List<Payment> payments = account.allPayments();
			assertTrue(payments.containsAll(expectedPayments));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} finally {
//			if (f != null) f.delete();
		}
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void addPaymentsTest2() {
		String paymentsFileContent = """
Captain Marvel:Lunch-40.0,Tapas-20.0
Captain America;Tapas-20.0
Dr. Strange:Excursion-50.0,Theater-25.0
Hulk:Dinner-50.0,Excursion-50.0,Tapas-20.0,Theater-25.0
Iron Man:Excursion-50.0,Tapas-30.0
Spiderman:Dinner-40.0,Theater-30.0
Thor:Excursion-50.0,Tapas-25.0
Black Widow:Dinner-40.0,Tapas-25.0
""";
		
//		File f = null;
		try {
//			f = new File("testingAddPayments.txt");
			File tempFile = File.createTempFile("temp", ".txt");
			PrintWriter pw = new PrintWriter(tempFile);
			pw.print(paymentsFileContent);
			pw.flush();
			pw.close();
			Account account = new Account("Trip to Titan");
			assertThrows(AccountsException.class, 
					() -> account.addPayments(tempFile.getAbsolutePath()),
					"\n> Error: Expected AccountsException for invalid payment format (missing semicolon after person's name)");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} finally {
//			if (f != null) f.delete();
		}
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void addPaymentsTest3() {
		String paymentsFileContent = """
Captain Marvel:Lunch-40.0,Tapas-20.0
Captain America.Tapas-20.0
Dr. Strange:Excursion-50.0 Theater-25.0
Hulk:Dinner-50.0,Excursion-50.0,Tapas-20.0,Theater-25.0
Iron Man:Excursion-50.0,Tapas-30.0
Spiderman:Dinner-40.0,Theater-30.0
Thor:Excursion-50.0,Tapas-25.0
Black Widow:Dinner-40.0,Tapas-25.0
""";
		
//		File f = null;
		try {
//			f = new File("testingAddPayments.txt");
			File tempFile = File.createTempFile("temp", ".txt");
			PrintWriter pw = new PrintWriter(tempFile);
			pw.print(paymentsFileContent);
			pw.flush();
			pw.close();
			Account account = new Account("Trip to Titan");
			assertThrows(AccountsException.class, 
					() -> account.addPayments(tempFile.getAbsolutePath()),
					"\n> Error: Expected AccountsException for invalid payment format (missing comma between payments)");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} finally {
//			if (f != null) f.delete();
		}
	}

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void addPaymentsTest4() {
		String paymentsFileContent = """
Captain Marvel:Lunch-40.0,Tapas-20.0
Captain America.Tapas-20.0
Dr. Strange:Excursion 50.0,Theater-25.0
Hulk:Dinner-50.0,Excursion-50.0,Tapas-20.0,Theater-25.0
Iron Man:Excursion-50.0,Tapas-30.0
Spiderman:Dinner-40.0,Theater-30.0
Thor:Excursion-50.0,Tapas-25.0
Black Widow:Dinner-40.0,Tapas-25.0
""";
		
//		File f = null;
		try {
//			f = new File("testingAddPayments.txt");
			File tempFile = File.createTempFile("temp", ".txt");
			PrintWriter pw = new PrintWriter(tempFile);
			pw.print(paymentsFileContent);
			pw.flush();
			pw.close();
			Account account = new Account("Trip to Titan");
			assertThrows(AccountsException.class, 
					() -> account.addPayments(tempFile.getAbsolutePath()),
					"\n> Error: Expected AccountsException for invalid payment format (missing hyphen between concept and amount)");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} finally {
//			if (f != null) f.delete();
		}
	}


	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void addPaymentsTest5() {
		String paymentsFileContent = """
Captain Marvel:Lunch-40.0,Tapas-2oo.0
Captain America.Tapas-20.0
Dr. Strange:Excursion-50.0,Theater-25.0
Hulk:Dinner-50.0,Excursion-50.0,Tapas-20.0,Theater-25.0
Iron Man:Excursion-50.0,Tapas-30.0
Spiderman:Dinner-40.0,Theater-30.0
Thor:Excursion-50.0,Tapas-25.0
Black Widow:Dinner-40.0,Tapas-25.0
""";
		
//		File f = null;
		try {
//			f = new File("testingAddPayments.txt");
			File tempFile = File.createTempFile("temp", ".txt");
			PrintWriter pw = new PrintWriter(tempFile);
			pw.print(paymentsFileContent);
			pw.flush();
			pw.close();
			Account account = new Account("Trip to Titan");
			assertThrows(AccountsException.class, 
					() -> account.addPayments(tempFile.getAbsolutePath()),
					"\n> Error: Expected AccountsException for invalid payment format (invalid double amount)");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} finally {
//			if (f != null) f.delete();
		}
	}
	

	@Test
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	public void savePaymentsTest1() {
		Account account = new Account("Trip to Titan");
		account.addPayment("Captain Marvel", "Lunch", 40.0);
		account.addPayment("Captain Marvel", "Tapas", 20.0);
		account.addPayment("Captain America", "Tapas", 20.0);
		account.addPayment("Dr. Strange", "Excursion", 50.0);
		account.addPayment("Dr. Strange", "Theater", 25.0);
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
	    account.addPayment("Black Widow", "Dinner", 40.0);
	    account.addPayment("Black Widow", "Tapas", 25.0);
	
	    try{
			File tempFile = File.createTempFile("temp", ".txt");
			account.savePayments(tempFile.getAbsolutePath());
			List<String> lines = Files.readAllLines(Path.of(tempFile.getAbsolutePath()));
			assertAll(
				() -> assertEquals(26, lines.size(), "\n> Error: Expected 26 lines in the saved payments file"),
				() -> assertTrue(lines.contains("*** Trip to Titan ***"),
						"\n> Error: Line '" + "*** Trip to Titan ***" + "' not found in expected payments content"),
				() -> assertTrue(lines.contains("Thor:"),
						"\n> Error: Line '" + "Thor:" + "' not found in expected payments content"),
				() -> assertTrue(lines.stream().anyMatch(l -> l.contains("Theater -> 25")),
						"\n> Error: Line '" + "Theater -> 25.0" + "' not found in expected payments content")
			);
			
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
}
}
