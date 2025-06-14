
//--------------------------------------------------------------------------


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import heights.*;



//--------------------------------------------------------------------------

public class TestRunnerPr53 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestCountry {
		private Country p1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Country JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Country JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
			p1 = new Country("Turkey", "Euro/Asia", 1.74);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryCtorTest1() {
			assertAll("countryCtorTest1", 
				() -> assertEquals("Turkey", p1.getName(),
						 "\n> Error: p1.getNombre():"),
				() -> assertEquals("Euro/Asia", p1.getContinent(),
						 "\n> Error: p1.getContinente():"),
				() -> assertEquals(1.74, p1.getHeight(), 0.00001,
						 "\n> Error: p1.getHeight():"));
		} 
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryCtorTest2() {
			assertTrue(((Object)p1 instanceof Comparable<?>), "\n> Error: Country implements Comparable<?>:");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryEqualsTest1() throws Exception {
			precond("Turkey", p1.getName());
			precond("Euro/Asia", p1.getContinent());
			precond(1.74, p1.getHeight(), 0.00001);
			//----------------------
			Country p2 = new Country("Turkey", "Euro/Asia", 1.74);
			Country p3 = new Country("Turkey", "Asia", 1.74);
			Country p4 = new Country("Turkey", "Euro/Asia", 2.00);
			Country p5 = new Country("TURKEY", "Euro/Asia", 1.74);
			Country p6 = new Country("Iceland", "Europe", 1.81);
			//------------------------
			assertAll("countryEqualsTest1", 
				() -> assertTrue(p1.equals(p2), "\n> Error: p1.equals(p2): "), 
				() -> assertTrue(p1.equals(p3), "\n> Error: p1.equals(p3): "), 
				() -> assertTrue(p1.equals(p4), "\n> Error: p1.equals(p4): "), 
				() -> assertFalse(p1.equals(p5), "\n> Error: p1.equals(p5): "), 
				() -> assertFalse(p1.equals(p6), "\n> Error: p1.equals(p6): "),
			//------------------------
				() -> assertFalse(p1.equals(null), "\n> Error: p1.equals(null): "),
				() -> assertFalse(p1.equals("Esto es un String"), "\n> Error: p1.equals(\"Esto es un String\"): "));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryHashCodeTest1() throws Exception {
			precond("Turkey", p1.getName());
			precond("Euro/Asia", p1.getContinent());
			precond(1.74, p1.getHeight(), 0.00001);
			//----------------------
			int p1HashCode = p1.hashCode();
			//------------------------
			Country p2 = new Country("Turkey", "Euro/Asia", 1.74);
			Country p3 = new Country("Turkey", "Asia", 1.74);
			Country p4 = new Country("Turkey", "Euro/Asia", 2.00);
			Country p5 = new Country("TURKEY", "Euro/Asia", 1.74);
			Country p6 = new Country("Iceland", "Europe", 1.81);
			//------------------------
			assertAll("countryHashCodeTest1", 
				() -> assertEquals(p1HashCode, p2.hashCode(), "\n> Error: p2.hashCode(): "), 
				() -> assertEquals(p1HashCode, p3.hashCode(), "\n> Error: p3.hashCode(): "), 
				() -> assertEquals(p1HashCode, p4.hashCode(), "\n> Error: p4.hashCode(): "), 
				//------------------------
				() -> assertNotEquals(p1HashCode, p5.hashCode(), "\n> Error: p5.hashCode():" ),
				() -> assertNotEquals(p1HashCode, p6.hashCode(), "\n> Error: p5.hashCode(): "));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void countryCompareToTest1() throws Exception {
			precond("Turkey", p1.getName());
			precond("Euro/Asia", p1.getContinent());
			precond(1.74, p1.getHeight(), 0.00001);
			//----------------------
			Country p2 = new Country("Turkey", "Euro/Asia", 1.74);
			Country p3 = new Country("Turkey", "Asia", 1.74);
			Country p4 = new Country("Turkey", "Euro/Asia", 2.00);
			Country p5 = new Country("TURKEY", "Euro/Asia", 1.74);
			Country p6 = new Country("Iceland", "Europe", 1.81);
			//------------------------
			assertAll("countryCompareToTest1", 
				() -> assertEquals(0, p1.compareTo(p2), "\n> Error: p1.compareTo(p2): "), 
				() -> assertEquals(0, p1.compareTo(p3), "\n> Error: p1.compareTo(p3): "), 
				() -> assertEquals(0, p1.compareTo(p4), "\n> Error: p1.compareTo(p4): "),
			//------------------------
				() -> assertTrue(p1.compareTo(p5) > 0, "\n> Error: p1.compareTo(p5): "),
				() -> assertTrue(p5.compareTo(p1) < 0, "\n> Error: p5.compareTo(p1): "),
			//------------------------
				() -> assertTrue(p1.compareTo(p6) > 0, "\n> Error: p1.compareTo(p6): "),
				() -> assertTrue(p6.compareTo(p1) < 0, "\n> Error: p6.compareTo(p1): "));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
		private static final String[] inputData = {
			"Albania,Europe,1.74",
			"Algeria,Africa,1.722",
			"Argentina,South America,1.745",
			"Australia,Oceania,1.756",
			"Austria,Europe,1.792",
			"Azerbaijan,Asia,1.718",
			"Bahrain,Asia,1.651",
			"Error,Error,Error",
			"Belgium,Europe,1.786",
			"Bolivia,South America,1.6",
			"Bosnia & Herzegovina,Europe,1.839",
			"Brazil,South America,1.731",
			"Error;Error;Error",
			"Bulgaria,Europe,1.752",
			"Cambodia,Asia,1.625",
			"Cameroon,Africa,1.706",
			"Canada,North America,1.751",
			"Chile,South America,1.71",
			"China,Asia,1.67",
			"Colombia,South America,1.706",
			"Croatia,Europe,1.805",
			"Cuba,North America,1.68",
			"Czech Republic,Europe,1.803",
		};
		private static final String inputList = normalize("[ Country ( Albania , Europe , 1.74 ) , Country ( Algeria , Africa , 1.722 ) , Country ( Argentina , South America , 1.745 ) , Country ( Australia , Oceania , 1.756 ) , Country ( Austria , Europe , 1.792 ) , Country ( Azerbaijan , Asia , 1.718 ) , Country ( Bahrain , Asia , 1.651 ) , Country ( Belgium , Europe , 1.786 ) , Country ( Bolivia , South America , 1.6 ) , Country ( Bosnia & Herzegovina , Europe , 1.839 ) , Country ( Brazil , South America , 1.731 ) , Country ( Bulgaria , Europe , 1.752 ) , Country ( Cambodia , Asia , 1.625 ) , Country ( Cameroon , Africa , 1.706 ) , Country ( Canada , North America , 1.751 ) , Country ( Chile , South America , 1.71 ) , Country ( China , Asia , 1.67 ) , Country ( Colombia , South America , 1.706 ) , Country ( Croatia , Europe , 1.805 ) , Country ( Cuba , North America , 1.68 ) , Country ( Czech Republic , Europe , 1.803 ) ]");
		public static World createWorld() throws Exception {
			World mnd1 = new World();
			try {
				createFile("hghts.txt", inputData);
				mnd1.load("hghts.txt");
			} finally {
				deleteFile("hghts.txt");
			}
			return mnd1;
		}
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
	public class JUnitTestWorld {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of World JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of World JUnit Test");
		}
		@BeforeEach
		public void setUp() throws Exception {
			// Code executed before each test
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCtorTest1() throws Exception {
			World mnd1 = createWorld();
			assertEquals(normalize(inputList),
						 normalize(mnd1.getCountries().toString()),
						 "\n> Error: mnd1.getCountries():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCtorTest2() throws Exception {
			World mnd1 = new World();
			assertEquals(normalize("[ ]"),
						 normalize(mnd1.getCountries().toString()),
						 "\n> Error: mnd1.getCountries():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldPresentInPWTest1() {
			java.util.TreeMap<String,String> map = new java.util.TreeMap<>();
			map.put("joe", "mary");
			map.put("dolly", "juan");
			map.put("jack", "eve");
			String salida = "";
			java.io.StringWriter strwrtr = new java.io.StringWriter();
			try (java.io.PrintWriter pw = new java.io.PrintWriter(strwrtr)) {
				World.presentInPW(pw, map);
			} finally {
				salida = strwrtr.toString();
			}
			assertEquals(normalize("dolly juan jack eve joe mary"),
						 normalize(salida),
						 "\n> Error: World.presentInPW(map):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldPresentConsoleTest1() {
			java.util.TreeMap<String,String> map = new java.util.TreeMap<>();
			map.put("joe", "mary");
			map.put("dolly", "juan");
			map.put("jack", "eve");
			String output = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				World.presentOnConsole(map);
			} finally {
				output = sysOutCapture.sysOutRelease();
			}
			assertEquals(normalize("dolly juan jack eve joe mary"),
						 normalize(output), "\n> Error: World.presentOnConsole(map):");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldNumberOfCountriesPerContinentTest1() throws Exception {
			World mnd1 = createWorld();
			precond(normalize(inputList), normalize(mnd1.getCountries().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = 2 , Asia = 4 , Europe = 7 , North America = 2 , Oceania = 1 , South America = 5 }"),
						 normalize(mnd1.numberOfCountriesPerContinent().toString()),
						 "\n> Error: mnd1.numberOfCountriesPerContinent():");
		}
//		@Test
//		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
//		public void worldCountriesByHeightTest1() throws Exception {
//			World mnd1 = createWorld();
//			precond(normalize(inputList), normalize(mnd1.getCountries().toString()));
//			//----------------------
//			assertEquals(normalize("{ 1.6 = [ Country ( Bahrain , Asia , 1.651 ) , Country ( Bolivia , South America , 1.6 ) , Country ( Cambodia , Asia , 1.625 ) , Country ( China , Asia , 1.67 ) , Country ( Cuba , North America , 1.68 ) ] , 1.7 = [ Country ( Albania , Europe , 1.74 ) , Country ( Algeria , Africa , 1.722 ) , Country ( Argentina , South America , 1.745 ) , Country ( Australia , Oceania , 1.756 ) , Country ( Austria , Europe , 1.792 ) , Country ( Azerbaijan , Asia , 1.718 ) , Country ( Belgium , Europe , 1.786 ) , Country ( Brazil , South America , 1.731 ) , Country ( Bulgaria , Europe , 1.752 ) , Country ( Cameroon , Africa , 1.706 ) , Country ( Canada , North America , 1.751 ) , Country ( Chile , South America , 1.71 ) , Country ( Colombia , South America , 1.706 ) ] , 1.8 = [ Country ( Bosnia & Herzegovina , Europe , 1.839 ) , Country ( Croatia , Europe , 1.805 ) , Country ( Czech Republic , Europe , 1.803 ) ] }"),
//						 normalize(mnd1.countriesByHeight().toString()),
//						 "\n> Error: mnd1.countriesByHeight():");
//		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesPerContinentTest1() throws Exception {
			World mnd1 = createWorld();
			precond(normalize(inputList), normalize(mnd1.getCountries().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = [ Country ( Algeria , Africa , 1.722 ) , Country ( Cameroon , Africa , 1.706 ) ] , Asia = [ Country ( Azerbaijan , Asia , 1.718 ) , Country ( Bahrain , Asia , 1.651 ) , Country ( Cambodia , Asia , 1.625 ) , Country ( China , Asia , 1.67 ) ] , Europe = [ Country ( Albania , Europe , 1.74 ) , Country ( Austria , Europe , 1.792 ) , Country ( Belgium , Europe , 1.786 ) , Country ( Bosnia & Herzegovina , Europe , 1.839 ) , Country ( Bulgaria , Europe , 1.752 ) , Country ( Croatia , Europe , 1.805 ) , Country ( Czech Republic , Europe , 1.803 ) ] , North America = [ Country ( Canada , North America , 1.751 ) , Country ( Cuba , North America , 1.68 ) ] , Oceania = [ Country ( Australia , Oceania , 1.756 ) ] , South America = [ Country ( Argentina , South America , 1.745 ) , Country ( Bolivia , South America , 1.6 ) , Country ( Brazil , South America , 1.731 ) , Country ( Chile , South America , 1.71 ) , Country ( Colombia , South America , 1.706 ) ] }"),
						 normalize(mnd1.countriesPerContinent().toString()),
						 "\n> Error: mnd1.countriesPerContinent():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesPerInitialTest1() throws Exception {
			World mnd1 = createWorld();
			precond(normalize(inputList), normalize(mnd1.getCountries().toString()));
			//----------------------
			assertEquals(normalize("{ A = [ Country ( Albania , Europe , 1.74 ) , Country ( Algeria , Africa , 1.722 ) , Country ( Argentina , South America , 1.745 ) , Country ( Australia , Oceania , 1.756 ) , Country ( Austria , Europe , 1.792 ) , Country ( Azerbaijan , Asia , 1.718 ) ] , B = [ Country ( Bahrain , Asia , 1.651 ) , Country ( Belgium , Europe , 1.786 ) , Country ( Bolivia , South America , 1.6 ) , Country ( Bosnia & Herzegovina , Europe , 1.839 ) , Country ( Brazil , South America , 1.731 ) , Country ( Bulgaria , Europe , 1.752 ) ] , C = [ Country ( Cambodia , Asia , 1.625 ) , Country ( Cameroon , Africa , 1.706 ) , Country ( Canada , North America , 1.751 ) , Country ( Chile , South America , 1.71 ) , Country ( China , Asia , 1.67 ) , Country ( Colombia , South America , 1.706 ) , Country ( Croatia , Europe , 1.805 ) , Country ( Cuba , North America , 1.68 ) , Country ( Czech Republic , Europe , 1.803 ) ] }"),
						 normalize(mnd1.countriesPerInitial().toString()),
						 "\n> Error: mnd1.countriesPerInitial():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldAveragePerContinentTest1() throws Exception {
			World mnd1 = createWorld();
			precond(normalize(inputList), normalize(mnd1.getCountries().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = 1.714 , Asia = 1.666 , Europe = 1.788142857142857 , North America = 1.7155 , Oceania = 1.756 , South America = 1.6984000000000001 }"),
						 normalize(mnd1.averagePerContinent().toString()),
						 "\n> Error: mnd1.averagePerContinent():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldContinentsWithMoreCountriesTest1() throws Exception {
			World mnd1 = createWorld();
			precond(normalize(inputList), normalize(mnd1.getCountries().toString()));
			//----------------------
			assertEquals(normalize("[ Europe ]"),
						 normalize(mnd1.continentsWithMoreCountries().toString()),
						 "\n> Error: mnd1.continentsWithMoreCountries():");
		}
		//------------------------------------------------------------------
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesByHeightTest1() throws Exception {
			World mnd1 = createWorld();
			precond(normalize(inputList), normalize(mnd1.getCountries().toString()));
			//----------------------
			assertEquals(normalize("[ Country ( Bolivia , South America , 1.6 ) , Country ( Cambodia , Asia , 1.625 ) , Country ( Bahrain , Asia , 1.651 ) , Country ( China , Asia , 1.67 ) , Country ( Cuba , North America , 1.68 ) , Country ( Cameroon , Africa , 1.706 ) , Country ( Colombia , South America , 1.706 ) , Country ( Chile , South America , 1.71 ) , Country ( Azerbaijan , Asia , 1.718 ) , Country ( Algeria , Africa , 1.722 ) , Country ( Brazil , South America , 1.731 ) , Country ( Albania , Europe , 1.74 ) , Country ( Argentina , South America , 1.745 ) , Country ( Canada , North America , 1.751 ) , Country ( Bulgaria , Europe , 1.752 ) , Country ( Australia , Oceania , 1.756 ) , Country ( Belgium , Europe , 1.786 ) , Country ( Austria , Europe , 1.792 ) , Country ( Czech Republic , Europe , 1.803 ) , Country ( Croatia , Europe , 1.805 ) , Country ( Bosnia & Herzegovina , Europe , 1.839 ) ]"),
						 normalize(mnd1.countriesOrderedByHeight().toString()),
						 "\n> Error: mnd1.countriesByHeight():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesByHeightPerContinentTest1() throws Exception {
			World mnd1 = createWorld();
			precond(normalize(inputList), normalize(mnd1.getCountries().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = [ Country ( Cameroon , Africa , 1.706 ) , Country ( Algeria , Africa , 1.722 ) ] , Asia = [ Country ( Cambodia , Asia , 1.625 ) , Country ( Bahrain , Asia , 1.651 ) , Country ( China , Asia , 1.67 ) , Country ( Azerbaijan , Asia , 1.718 ) ] , Europe = [ Country ( Albania , Europe , 1.74 ) , Country ( Bulgaria , Europe , 1.752 ) , Country ( Belgium , Europe , 1.786 ) , Country ( Austria , Europe , 1.792 ) , Country ( Czech Republic , Europe , 1.803 ) , Country ( Croatia , Europe , 1.805 ) , Country ( Bosnia & Herzegovina , Europe , 1.839 ) ] , North America = [ Country ( Cuba , North America , 1.68 ) , Country ( Canada , North America , 1.751 ) ] , Oceania = [ Country ( Australia , Oceania , 1.756 ) ] , South America = [ Country ( Bolivia , South America , 1.6 ) , Country ( Colombia , South America , 1.706 ) , Country ( Chile , South America , 1.71 ) , Country ( Brazil , South America , 1.731 ) , Country ( Argentina , South America , 1.745 ) ] }"),
						 normalize(mnd1.countriesByHeightPerContinent().toString()),
						 "\n> Error: mnd1.countriesByHeightPerContinent():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void worldCountriesByReverseHeightPerContinentTest1() throws Exception {
			World mnd1 = createWorld();
			precond(normalize(inputList), normalize(mnd1.getCountries().toString()));
			//----------------------
			assertEquals(normalize("{ Africa = [ Country ( Algeria , Africa , 1.722 ) , Country ( Cameroon , Africa , 1.706 ) ] , Asia = [ Country ( Azerbaijan , Asia , 1.718 ) , Country ( China , Asia , 1.67 ) , Country ( Bahrain , Asia , 1.651 ) , Country ( Cambodia , Asia , 1.625 ) ] , Europe = [ Country ( Bosnia & Herzegovina , Europe , 1.839 ) , Country ( Croatia , Europe , 1.805 ) , Country ( Czech Republic , Europe , 1.803 ) , Country ( Austria , Europe , 1.792 ) , Country ( Belgium , Europe , 1.786 ) , Country ( Bulgaria , Europe , 1.752 ) , Country ( Albania , Europe , 1.74 ) ] , North America = [ Country ( Canada , North America , 1.751 ) , Country ( Cuba , North America , 1.68 ) ] , Oceania = [ Country ( Australia , Oceania , 1.756 ) ] , South America = [ Country ( Argentina , South America , 1.745 ) , Country ( Brazil , South America , 1.731 ) , Country ( Chile , South America , 1.71 ) , Country ( Colombia , South America , 1.706 ) , Country ( Bolivia , South America , 1.6 ) ] }"),
						 normalize(mnd1.countriesByReverseHeightPerContinent().toString()),
						 "\n> Error: mnd1.countriesByReverseHeightPerContinent():");
		}
	}

	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({ JUnitTestCountry.class ,
				JUnitTestWorld.class
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request = 
				LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectClass(JUnitTestWorld.class))
				.build();

		final Launcher launcher = LauncherFactory.create();
		final SummaryGeneratingListener listener = new SummaryGeneratingListener();

		launcher.registerTestExecutionListeners(listener);
		launcher.execute(request);

		TestExecutionSummary summary = listener.getSummary();

//		summary.printTo(new PrintWriter(System.out, true));

		long abortedCount = summary.getTestsAbortedCount();
		long succeededCount = summary.getTestsFoundCount();
		long foundCount = summary.getTestsSucceededCount();
		long skippedCount = summary.getTestsSkippedCount();
		long failedCount = summary.getTestsFailedCount();
		long startedCount = summary.getTestsStartedCount();
		if (failedCount > 0) {
			System.out.println(">>> ------");
			summary.getFailures().forEach(failure -> System.out.println("failure - " + failure.getException()));
		}
		if ((abortedCount > 0)||(failedCount > 0)||(skippedCount > 0)) {
			System.out.println(">>> ------");
			if (skippedCount > 0) {
				System.out.println(">>> Error: Some tests ("+skippedCount+") were ignored");
			}
			if (abortedCount > 0) {
				System.out.println(">>> Error: ("+abortedCount+") tests could not be run due to errors in other methods");
			}
			if (failedCount > 0) {
				System.out.println(">>> Error: ("+failedCount+") tests failed due to errors in methods");
			}
		}
		if (succeededCount == foundCount) {
			System.out.print(">>> JUnit Test Succeeded");
		} else {
			System.out.print(">>> Error: JUnit Test Failed");
		}
		System.out.println(" (Tests: "+foundCount+", Errors: "+failedCount+", ErrorPrecond: "+abortedCount+", Ignored: "+skippedCount+")");

//		public static Result result = null;
//		result = JUnitCore.runClasses(JUnitTestSuite.class);
//		int rc = result.getRunCount();
//		int fc = result.getFailureCount();
//		int ac = 0;//result.getAssumptionFailureCount();
//		int ic = result.getIgnoreCount();
//		if (fc > 0) {
//			System.out.println(">>> ------");
//		}
//		for (Failure failure : result.getFailures()) {
//			System.out.println(failure.toString());
//		}
//		if ((ac > 0)||(fc > 0)) {
//			System.out.println(">>> ------");
//			if (ac > 0) {
//				System.out.println(">>> Error: "+ac+" tests could not be run due to errors in other methods");
//			}
//			if (fc > 0) {
//				System.out.println(">>> Error: "+fc+" tests failed due to errors in methods");
//			}
//		}
//		if (result.wasSuccessful()) {
//			System.out.print(">>> JUnit Test Succeeded");
//		} else {
//			System.out.print(">>> Error: JUnit Test Failed");
//		}
//		System.out.println(" ("+rc+", "+fc+", "+ac+", "+ic+")");
	}
	//----------------------------------------------------------------------
	//-- Utils -------------------------------------------------------------
	//----------------------------------------------------------------------
	private static char normalizeUnicode(char ch) {
		switch (ch) {
		case '\n':
		case '\r':
		case '\t':
		case '\f':
			ch = ' ';
			break;
		case '\u20AC':
			ch = 'E';
			break;
		case '\u00A1':
			ch = '!';
			break;
		case '\u00AA':
			ch = 'a';
			break;
		case '\u00BA':
			ch = 'o';
			break;
		case '\u00BF':
			ch = '?';
			break;
		case '\u00C0':
		case '\u00C1':
		case '\u00C2':
		case '\u00C3':
		case '\u00C4':
		case '\u00C5':
		case '\u00C6':
			ch = 'A';
			break;
		case '\u00C7':
			ch = 'C';
			break;
		case '\u00C8':
		case '\u00C9':
		case '\u00CA':
		case '\u00CB':
			ch = 'E';
			break;
		case '\u00CC':
		case '\u00CD':
		case '\u00CE':
		case '\u00CF':
			ch = 'I';
			break;
		case '\u00D0':
			ch = 'D';
			break;
		case '\u00D1':
			ch = 'N';
			break;
		case '\u00D2':
		case '\u00D3':
		case '\u00D4':
		case '\u00D5':
		case '\u00D6':
			ch = 'O';
			break;
		case '\u00D9':
		case '\u00DA':
		case '\u00DB':
		case '\u00DC':
			ch = 'U';
			break;
		case '\u00DD':
			ch = 'Y';
			break;
		case '\u00E0':
		case '\u00E1':
		case '\u00E2':
		case '\u00E3':
		case '\u00E4':
		case '\u00E5':
		case '\u00E6':
			ch = 'a';
			break;
		case '\u00E7':
			ch = 'c';
			break;
		case '\u00E8':
		case '\u00E9':
		case '\u00EA':
		case '\u00EB':
			ch = 'e';
			break;
		case '\u00EC':
		case '\u00ED':
		case '\u00EE':
		case '\u00EF':
			ch = 'i';
			break;
		case '\u00F0':
			ch = 'd';
			break;
		case '\u00F1':
			ch = 'n';
			break;
		case '\u00F2':
		case '\u00F3':
		case '\u00F4':
		case '\u00F5':
		case '\u00F6':
			ch = 'o';
			break;
		case '\u00F9':
		case '\u00FA':
		case '\u00FB':
		case '\u00FC':
			ch = 'u';
			break;
		case '\u00FD':
		case '\u00FF':
			ch = 'y';
			break;
		}
		return ch;
	}
    //------------------------------------------------------------------
    //private static java.util.regex.Pattern float_pattern = java.util.regex.Pattern.compile("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)([eE][+-]?[0-9]+)?");
    private static java.util.regex.Pattern float_pattern = java.util.regex.Pattern.compile("[+-]?(([0-9]+[.][0-9]+([eE][+-]?[0-9]+)?)|([0-9]+[eE][+-]?[0-9]+))");
	private static String normalize_real_numbers(CharSequence csq) {
		String res = "";
		try {
			StringBuilder sbres = new StringBuilder(csq.length());
			java.util.regex.Matcher matcher = float_pattern.matcher(csq);
			int idx = 0;
			while (matcher.find()) {
				int inicio = matcher.start();
				int fin = matcher.end();
				String num1 = csq.subSequence(inicio, fin).toString();
				String formato = "%.6f";
				if (num1.contains("e") || num1.contains("E")) {
					formato = "%.6e";
				}
				double num2 = Double.parseDouble(num1);
				String num3 = String.format(java.util.Locale.UK, formato, num2);
				sbres.append(csq.subSequence(idx, inicio));
				sbres.append(num3);
				idx = fin;
			}
			sbres.append(csq.subSequence(idx, csq.length()));
			res = sbres.toString();
		} catch (Throwable e) {
			res = csq.toString();
		}
		return res;
	}
	//----------------------------------------------------------------------
	private static String normalize(String s1) {
		int sz = s1 == null ? 16 : s1.length() == 0 ? 16 : 2*s1.length();
		StringBuilder sb = new StringBuilder(sz);
		sb.append(' ');
		if (s1 != null) {
			for (int i = 0; i < s1.length(); ++i) {
				char ch = normalizeUnicode(s1.charAt(i));
				char sbLastChar = sb.charAt(sb.length()-1);
				if (Character.isLetter(ch)) {
					if ( ! Character.isLetter(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isDigit(ch)) {
					if ((i >= 2)
						&& (s1.charAt(i-1) == '.')
						&& Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 ."
						sb.append('.');
					} else if ((i >= 2)
							   && ((s1.charAt(i-1) == 'e')||(s1.charAt(i-1) == 'E'))
							   && Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 e"
						sb.append('e');
					} else if ((i >= 3)
							   && (s1.charAt(i-1) == '+')
							   && ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							   && Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e +"
						sb.append('e');
					} else if ((i >= 3)
							   && (s1.charAt(i-1) == '-')
							   && ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							   && Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e -"
						sb.append("e-");
					} else if ( (sbLastChar != '-') && ! Character.isDigit(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isSpaceChar(ch)) {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
				} else {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
					sb.append(ch);
				}
			}
		}
		if (Character.isSpaceChar(sb.charAt(sb.length()-1))) {
			sb.setLength(sb.length()-1);
		}
		if ((sb.length() > 0) && Character.isSpaceChar(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return normalize_real_numbers(sb);
	}
	//------------------------------------------------------------------
	private static String normalizeListStr(String listaStr, String delims, String prefix, String suffix) {
		listaStr = listaStr.trim();
		String res = listaStr;
		try {
			if (prefix.length() > 0 && listaStr.startsWith(prefix)) {
				listaStr = listaStr.substring(prefix.length());
			}
			if (suffix.length() > 0 && listaStr.endsWith(suffix)) {
				listaStr = listaStr.substring(0, listaStr.length()-suffix.length());
			}
			listaStr = listaStr.trim();
			java.util.List<String> lista = new java.util.ArrayList<>(java.util.List.of(listaStr.split(delims)));
			lista.sort(null);
			res = lista.toString();
		} catch (Throwable e) {
			// ignorar
		}
		return res;
	}
	//----------------------------------------------------------------------
	private static final String precondMessage = "\n> Warning: the test could not be executed due to previous errors";
	//----------------------------------------------------------------------
	private static void precond(boolean expectedValue, boolean currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(char expectedValue, char currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(short expectedValue, short currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(int expectedValue, int currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(long expectedValue, long currValue) {
		assumeTrue(expectedValue == currValue, precondMessage);
	}
	private static void precond(float expectedValue, float currValue, float delta) {
		assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(double expectedValue, double currValue, double delta) {
		assumeTrue(Math.abs(expectedValue - currValue) <= delta, precondMessage);
	}
	private static void precond(Object expectedValue, Object currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			assumeTrue(expectedValue.equals(currValue), precondMessage);
		}
	}
	//------------------------------------------------------------------
	private static void precond(int[] expectedValue, int[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	private static void precond(double[] expectedValue, double[] currValue, double delta) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i], delta);
			}
		}
	}
	private static <T> void precond(T[] expectedValue, T[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	//----------------------------------------------------------------------
	private static void precondNorm(String expectedValue, String currValue) {
		precond(normalize(expectedValue), normalize(currValue));
	}
	private static void precondNorm(String[] expectedValue, String[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(expectedValue == currValue, precondMessage);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precondNorm(expectedValue[i], currValue[i]);
			}
		}
	}
	private static void assertEqualsNorm(String msg, String expectedValue, String currValue) {
		assertEquals(msg, normalize(expectedValue), normalize(currValue));
	}
	private static void assertEqualsNorm(String msg, java.util.List<String> expectedValue, java.util.List<String> currValue) {
		assertEquals(expectedValue.size(), currValue.size(), msg);
		for (int i = 0; i < expectedValue.size(); ++i) {
			assertEquals(normalize(expectedValue.get(i)), normalize(currValue.get(i)), msg);
		}
	}
	private static void assertArrayEqualsNorm(String msg, String[] expectedValue, String[] currValue) {
		assertEquals(expectedValue.length, currValue.length, msg);
		for (int i = 0; i < expectedValue.length; ++i) {
			assertEquals(msg, normalize(expectedValue[i]), normalize(currValue[i]));
		}
	}
	//----------------------------------------------------------------------
	private static void deleteFile(String filename) {
		new java.io.File(filename).delete();
	}
	private static void createFile(String filename, String inputData) throws Exception {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
			pw.println(inputData);
		}
	}
	private static void createFile(String filename, String[] inputData) throws Exception {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
			for (String linea : inputData) {
				pw.println(linea);
			}
		}
	}
	private static String loadFile(String filename) throws Exception {
		java.util.StringJoiner sj = new java.util.StringJoiner("\n");
		try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(filename))) {
			while (sc.hasNextLine()) {
				sj.add(sc.nextLine());
			}
		}
		return sj.toString();
	}
	//----------------------------------------------------------------------
	//------------------------------------------------------------------
	private static Object getMemberObject(Object obj, Class objClass, Class memberClass, String memberName) {
		//--------------------------
		// OBJ puede ser NULL en caso de variable STATIC
		// OBJCLASS puede ser NULL si OBJ no es NULL
		// MEMBERCLASS no puede ser NULL
		// MEMBERNAME puede ser NULL, si solo hay una unica variable de ese tipo
		//--------------------------
		String memberId = (memberName == null ? "" : memberName)+":"+(memberClass == null ? "" : memberClass.getName());
		Object res = null;
		int idx = -1;
		try {
			if ((objClass == null)&&(obj != null)) {
				objClass = obj.getClass();
			}
			if ((objClass != null)&&(memberClass != null)) {
				int cnt = 0;
				int aux = -1;
				java.lang.reflect.Field[] objFields = objClass.getDeclaredFields();
				for (int i = 0; i < objFields.length; ++i) {
					if (memberClass.equals(objFields[i].getType())) {
						if ((memberName != null)&&(memberName.equalsIgnoreCase(objFields[i].getName()))) {
							idx = i;
						} else {
							aux = i;
						}
						++cnt;
					}
				}
				if ((idx < 0)&&(cnt == 1)) {
					idx = aux;	// si solo tiene una variable de ese tipo, no importa el nombre
				}
				if (idx >= 0) {
					objFields[idx].setAccessible(true);
					res = objFields[idx].get(obj);
				}
			}
		} catch (Throwable e) {
			fail("\n> Unexpected Error. getMemberObject["+memberId+"]: " + e);
		}
		if (idx < 0) {
			fail("\n> Error: no ha sido posible encontrar la variable ["+memberId+"]");
		}
		if (res == null) {
			fail("\n> Error: la variable ["+memberId+"] no se ha creado correctamente");
		}
		return res;
	} 
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	private static class SysOutCapture {
		private SysOutErrCapture systemout;
		private SysOutErrCapture systemerr;
		public SysOutCapture() {
			systemout = new SysOutErrCapture(false);
			systemerr = new SysOutErrCapture(true);
		}
		public void sysOutCapture() throws RuntimeException {
			try {
				systemout.sysOutCapture();
			} finally {
				systemerr.sysOutCapture();
			}
		}
		public String sysOutRelease() throws RuntimeException {
			String s1 = "";
			String s2 = "";
			try {
				s1 = systemout.sysOutRelease();
			} finally {
				s2 = systemerr.sysOutRelease();
			}
			return s1 + " " + s2 ;
		}
		//--------------------------
		private static class SysOutErrCapture {
			//--------------------------------
			private java.io.PrintStream sysoutOld;
			private java.io.PrintStream sysoutstr;
			private java.io.ByteArrayOutputStream baos;
			private final boolean systemErr;
			private int estado;
			//--------------------------------
			public SysOutErrCapture(boolean syserr) {
				sysoutstr = null ;
				baos = null;
				sysoutOld = null ;
				estado = 0;
				systemErr = syserr;
			}
			//--------------------------------
			public void sysOutCapture() throws RuntimeException {
				if (estado != 0) {
					throw new RuntimeException("sysOutCapture:BadState");
				} else {
					estado = 1;
					try {
						if (systemErr) {
							sysoutOld = System.err;
						} else {
							sysoutOld = System.out;
						}
						baos = new java.io.ByteArrayOutputStream();
						sysoutstr = new java.io.PrintStream(baos);
						if (systemErr) {
							System.setErr(sysoutstr);
						} else {
							System.setOut(sysoutstr);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutCapture:InternalError");
					}
				}
			}
			//--------------------------------
			public String sysOutRelease() throws RuntimeException {
				String result = "";
				if (estado != 1) {
					throw new RuntimeException("sysOutRelease:BadState");
				} else {
					estado = 0;
					try {
						if (sysoutstr != null) {
							sysoutstr.flush();
						}
						if (baos != null) {
							baos.flush();
							result = new String(baos.toByteArray()); //java.nio.charset.StandardCharsets.ISO_8859_1);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutRelease:InternalError1");
					} finally {
						try {
							if (systemErr) {
								System.setErr(sysoutOld);
							} else {
								System.setOut(sysoutOld);
							}
							if (sysoutstr != null) {
								sysoutstr.close();
								sysoutstr = null;
							}
							if (baos != null) {
								baos.close();
								baos = null;
							}
						} catch (Throwable e) {
							throw new RuntimeException("sysOutRelease:InternalError2");
						}
					}
				}
				return result;
			}
			//--------------------------------
		}
	}
	//----------------------------------------------------------------------
	//--TestRunner-End---------------------------------------------------
	//----------------------------------------------------------------------
}
//--------------------------------------------------------------------------
