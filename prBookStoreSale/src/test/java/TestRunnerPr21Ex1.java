
//--------------------------------------------------------------------------

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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import bookStore.*;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

//--------------------------------------------------------------------------

public class TestRunnerPr21Ex1 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestBook {
		private Book lb1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Book JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of Book JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			lb1 = new Book("Isaac Asimov", "Foundation", 7.30);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookCtorTest1() {
			assertAll("bookCtorTest1",
					() -> assertEquals("Isaac Asimov", lb1.getAuthor(), "\n> Error: new Book(\"Isaac Asimov\", Foundation, 7.30): Autor:"),
					() -> assertEquals("Foundation", lb1.getTitle(), "\n> Error: new Book(\"Isaac Asimov\", Foundation, 7.30): Titulo:"),
					() -> assertEquals(7.30, lb1.getBasePrice(), 1e-6, "\n> Error: new Book(\"Isaac Asimov\", Foundation, 7.30): BasePrice:"),
					() -> assertEquals(10.00, Book.getVAT(), 1e-6, "\n> Error: new Book(\"Isaac Asimov\", Foundation, 7.30): PorcIva:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookFinalPriceTest1() {
			precond("Isaac Asimov", lb1.getAuthor());
			precond("Foundation", lb1.getTitle());
			precond(7.30, lb1.getBasePrice(), 1e-6);
			precond(10.0, Book.getVAT(), 1e-6);
			assertEquals(8.03, lb1.getFinalPrice(), 1e-6, "\n> Error: lb1.getFinalPrice(): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookSetVatTest1() {
			precond("Isaac Asimov", lb1.getAuthor());
			precond("Foundation", lb1.getTitle());
			precond(7.30, lb1.getBasePrice(), 1e-6);
			precond(10.0, Book.getVAT(), 1e-6);
			Book.setVAT(20.00);
			double ivaActual = Book.getVAT();
			Book.setVAT(10.00);
			assertEquals(20.00, ivaActual, 1e-6, "\n> Error: Book.setIVA(20.00): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookToStringTest1() {
			precond("Isaac Asimov", lb1.getAuthor());
			precond("Foundation", lb1.getTitle());
			precond(7.30, lb1.getBasePrice(), 1e-6);
			precond(10.0, Book.getVAT(), 1e-6);
			assertEquals(normalize("(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03)"),
						 normalize(lb1.toString()),
						 "\n> Error: lb1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestBookOnSale {
		private BookOnSale lo1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of BookOnSale JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of BookOnSale JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			lo1 = new BookOnSale("Isaac Asimov", "Foundation", 7.30, 20.0);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookCtorTest1() {
			assertAll("salesBookCtorTest1",
					() -> assertTrue(((Object)lo1 instanceof Book), "\n> Error: BookOnSale extends Book:"),
					() -> assertEquals("Isaac Asimov", lo1.getAuthor(), "\n> Error: new BookOnSale(\"Isaac Asimov\", \"Foundation\", 7.30, 20.0): Author:"),
					() -> assertEquals("Foundation", lo1.getTitle(), "\n> Error: new BookOnSale(\"Isaac Asimov\", \"Foundation\", 7.30, 20.0): Title:"),
					() -> assertEquals(7.30, lo1.getBasePrice(), 1e-6, "\n> Error: new BookOnSale(\"Isaac Asimov\", \"Foundation\", 7.30, 20.0): BasePrice:"),
					() -> assertEquals(20.00, lo1.getDiscount(), 1e-6, "\n> Error: new BookOnSale(\"Isaac Asimov\", \"Foundation\", 7.30, 20.0): DiscPerc:"),
					() -> assertEquals(10.00, BookOnSale.getVAT(), 1e-6, "\n> Error: new BookOnSale(\"Isaac Asimov\", \"Foundation\", 7.30, 20.0): VatPerc:"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookFinalPriceTest1() {
			precond("Isaac Asimov", lo1.getAuthor());
			precond("Foundation", lo1.getTitle());
			precond(7.30, lo1.getBasePrice(), 1e-6);
			precond(20.0, lo1.getDiscount(), 1e-6);
			precond(10.0, BookOnSale.getVAT(), 1e-6);
			assertEquals(6.424, lo1.getFinalPrice(), 1e-6, "\n> Error: lo1.getFinalPrice(): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookSetVatTest1() {
			precond("Isaac Asimov", lo1.getAuthor());
			precond("Foundation", lo1.getTitle());
			precond(7.30, lo1.getBasePrice(), 1e-6);
			precond(20.0, lo1.getDiscount(), 1e-6);
			precond(10.0, BookOnSale.getVAT(), 1e-6);
			Book.setVAT(20.00);
			double presentVAT = Book.getVAT();
			Book.setVAT(10.00);
			assertEquals(20.00, presentVAT, 1e-6, "\n> Error: Book.setVAT(20.00): ");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookToStringTest1() {
			precond("Isaac Asimov", lo1.getAuthor());
			precond("Foundation", lo1.getTitle());
			precond(7.30, lo1.getBasePrice(), 1e-6);
			precond(20.0, lo1.getDiscount(), 1e-6);
			precond(10.0, BookOnSale.getVAT(), 1e-6);
			assertEquals(normalize("(Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995)"),
						 normalize(lo1.toString()),
						 "\n> Error: lo1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestBookStore {
		private BookStore lr1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of BookStore JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of BookStore JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			lr1 = new BookStore();
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		// Carlos (19/03/19) // Vicente (21/03/19)
//		private static final Class arrayBookClass = (new ArrayList<Book>()).getClass();
//		private static ArrayList<Book> getArrayBooks(BookStore lb) {
//			ArrayList<Book> libs = null;
//			boolean encontrado = false;
//			try {
//				Class bookStoreClass = lb.getClass();
//				java.lang.reflect.Field[] bookStoreFields = bookStoreClass.getDeclaredFields();
//				int i = 0;
//				while ((i < bookStoreFields.length)
//					   &&(bookStoreFields[i].getType() != arrayBookClass)) {
//					++i;
//				}
//				if (i < bookStoreFields.length) {
//					bookStoreFields[i].setAccessible(true);
//					libs = (ArrayList<Book>)bookStoreFields[i].get(lb);
//					encontrado = true;
//				}
//			} catch (Throwable e) {
//				fail("\n> Error: getArrayBooks has thrown an exception " + e);
//			}
//			if (! encontrado) {
//				fail("\n> Error: la clase BookStore no contiene un array de books Book[]");
//			}
//			if (libs == null) {
//				fail("\n> Error: el array de books no se ha creado correctamente");
//			}
//			return libs;
//		} 
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreCtorTest1() {
			BookStore lr2 = new BookStore();
			assertEquals(normalize("[]"), normalize(lr2.toString()),"\n> Error: new BookStore(): toString():");
		}

		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreAddBookTest1() {
			lr1.addBook("Isaac Asimov", "Foundation", 7.30);
			lr1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addBook("William Gibson", "Neuromancer", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"), 
					normalize(lr1.toString()), 
					"\n> Error: addBook1(): toString()");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreAddBookTest2() {
			lr1.addBook("isaac asimov", "foundation", 5.30);
			lr1.addBook("aldous huxley", "a brave new world", 4.50);
			lr1.addBook("william gibson", "neuromancer", 6.30);
			lr1.addBook("george orwell", "1984", 4.20);
			lr1.addBook("ray bradbury", "fahrenheit 451", 5.40);
			//------------------------
			assertEquals(normalize("[(isaac asimov; foundation; 5.3; 10.0%; 5.83), (aldous huxley; a brave new world; 4.5; 10.0%; 4.95), (william gibson; neuromancer; 6.3; 10.0%; 6.93), (george orwell; 1984; 4.2; 10.0%; 4.62), (ray bradbury; fahrenheit 451; 5.4; 10.0%; 5.94)]"), 
					normalize(lr1.toString()), 
					"\n> Error: addBook2.1(): toString()");
			//------------------------
			lr1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addBook("William Gibson", "Neuromancer", 8.30);
			lr1.addBook("Isaac Asimov", "Foundation", 7.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			//------------------------
			assertEquals(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"), 
					normalize(lr1.toString()), 
					"\n> Error: addBook2.2(): toString()");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreGetFinalPriceTest1() {
			lr1.addBook("Isaac Asimov", "Foundation", 7.30);
			lr1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addBook("William Gibson", "Neuromancer", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()));
			//------------------------
			assertAll("bookStoreGetFinalPriceTest1",
					() -> assertEquals(8.03, lr1.getFinalPrice("Isaac Asimov", "Foundation"), 1e-6, "\n> Error: getFinalPrice(Issac Asimov, Foundation):"),
					() -> assertEquals(7.15, lr1.getFinalPrice("Aldous Huxley", "A Brave New World"), 1e-6, "\n> Error: getFinalPrice(Aldous Huxley, A Brave New World):"),
					() -> assertEquals(9.13, lr1.getFinalPrice("William Gibson", "Neuromancer"), 1e-6, "\n> Error: getFinalPrice(William Gibson, Neuromancer):"),
					() -> assertEquals(6.82, lr1.getFinalPrice("George Orwell", "1984"), 1e-6, "\n> Error: getFinalPrice(George Orwell, 1984):"),
					() -> assertEquals(8.14, lr1.getFinalPrice("Ray Bradbury", "Fahrenheit 451"), 1e-6, "\n> addBook(): getFinalPrice(Ray Bradbury, Fahrenheit 451):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreGetFinalPriceTest2() {
			lr1.addBook("Isaac Asimov", "Foundation", 7.30);
			lr1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addBook("William Gibson", "Neuromancer", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()));
			//------------------------
			assertAll("bookStoreGetFinalPriceTest2",
					() -> assertEquals(8.03, lr1.getFinalPrice("isaac asimov", "foundation"), 1e-6, "\n> Error: getFinalPrice(isaac asimov, Foundation):"),
					() -> assertEquals(7.15, lr1.getFinalPrice("aldous huxley", "a brave new world"), 1e-6, "\n> Error: getFinalPrice(aldous huxley, a brave new world):"),
					() -> assertEquals(9.13, lr1.getFinalPrice("william gibson", "neuromancer"), 1e-6, "\n> Error: getFinalPrice(william gibson, neuromancer):"),
					() -> assertEquals(6.82, lr1.getFinalPrice("george orwell", "1984"), 1e-6, "\n> Error: getFinalPrice(george orwell, 1984):"),
					() -> assertEquals(8.14, lr1.getFinalPrice("ray bradbury", "fahrenheit 451"), 1e-6, "\n> Error: getFinalPrice(ray bradbury, fahrenheit 451):"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreGetFinalPriceTest3() {
			lr1.addBook("Isaac Asimov", "Foundation", 7.30);
			lr1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addBook("William Gibson", "Neuromancer", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()));
			//------------------------
			Exception exception = assertThrows(RuntimeException.class, () -> lr1.getFinalPrice("xxx", "xxx"), "\n> Error: getFinalPrice(xxx, xxx): No RuntimeException was thrown");
			assertEquals("Book not found (xxx, xxx)", exception.getMessage());
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreRemBookTest1() {
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addBook("Isaac Asimov", "Foundation", 7.30);
			lr1.addBook("William Gibson", "Neuromancer", 8.30);
			//------------------------
			precond(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()));
			//------------------------
			lr1.deleteBook("Isaac Asimov", "Foundation");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(Isaac Asimov, Foundation");
			lr1.deleteBook("Aldous Huxley", "A Brave New World");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(Aldous Huxley, A Brave New World");
			lr1.deleteBook("William Gibson", "Neuromancer");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(William Gibson, Neuromancer");
			lr1.deleteBook("George Orwell", "1984");
			assertEquals(normalize("[(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(George Orwell, 1984");
			lr1.deleteBook("Ray Bradbury", "Fahrenheit 451");
			assertEquals(normalize("[]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(Ray Bradbury, Fahrenheit 451");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreRemBookTest2() {
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addBook("Isaac Asimov", "Foundation", 7.30);
			lr1.addBook("William Gibson", "Neuromancer", 8.30);
			//------------------------
			precond(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()));
			//------------------------
			lr1.deleteBook("isaac asimov", "foundation");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(isaac asimov, foundation");
			lr1.deleteBook("aldous huxley", "a brave new world");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(aldous huxley, a brave new world");
			lr1.deleteBook("william gibson", "neuromancer");
			assertEquals(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(william gibson, neuromancer");
			lr1.deleteBook("george orwell", "1984");
			assertEquals(normalize("[(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(george orwell, 1984");
			lr1.deleteBook("ray bradbury", "fahrenheit 451");
			assertEquals(normalize("[]"),
					normalize(lr1.toString()), "\n> Error: deleteBook(ray bradbury, fahrenheit 451");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreRemBookTest3() {
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addBook("Isaac Asimov", "Foundation", 7.30);
			lr1.addBook("William Gibson", "Neuromancer", 8.30);
			//------------------------
			precond(normalize("[(George Orwell; 1984; 6.2; 10.0%; 6.82), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lr1.toString()));
			//------------------------
			Exception exception = assertThrows(RuntimeException.class, () -> lr1.deleteBook("xxx", "xxx"));
			assertEquals("Book not found (xxx, xxx)", exception.getMessage());
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreToStringTest1() {
			assertEquals(normalize("[]"),
						 normalize(lr1.toString()),
						 "\n> Error: lr1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void bookStoreToStringTest2() {
			lr1.addBook("Isaac Asimov", "Foundation", 7.30);
			lr1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lr1.addBook("William Gibson", "Neuromancer", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(normalize("[(Isaac Asimov; Foundation; 7.3; 10.0%; 8.03), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
						 normalize(lr1.toString()),
						 "\n> Error: lr1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	private static final String[] authorsOnSale = { "george orwell", "isaac asimov" };
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestSalesBookStore {
		private SalesBookStore lro1;
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of SalesBookStore JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of SalesBookStore JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
			lro1 = new SalesBookStore(20.0, authorsOnSale);
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreCtorTest1() {
			assertAll(
					() -> assertTrue(((Object)lro1 instanceof BookStore), "\n> Error: SalesBookStore extends BookShop:"),
					() -> assertEquals(normalize("20.0%[george orwell, isaac asimov][]"),
							 normalize(lro1.toString()),
							 "\n> Error: new SalesBookStore(): toString():"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreGetDiscountTest1() {
			assertEquals(20.00, lro1.getDiscount(), 1e-6, "\n> Error: getDiscount():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreGetAuthorsOnSaleTest1() {
			assertArrayEquals(authorsOnSale, lro1.getSale(), "\n> Error: getAuthorsOnSale():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreAddBookTest1() {
			lro1.addBook("Isaac Asimov", "Foundation", 7.30);
			lro1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lro1.addBook("William Gibson", "Neuromancer", 8.30);
			lro1.addBook("George Orwell", "1984", 6.20);
			lro1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(
					normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lro1.toString()),
					"\n> Error: addBook(): toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreAddBookTest2() {
			lro1.addBook("isaac asimov", "foundation", 5.30);
			lro1.addBook("aldous huxley", "a brave new world", 4.50);
			lro1.addBook("william gibson", "neuromancer", 6.30);
			lro1.addBook("george orwell", "1984", 4.20);
			lro1.addBook("ray bradbury", "fahrenheit 451", 5.40);
			//------------------------
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(isaac asimov; foundation; 5.3; 20.0%; 4.24; 10.0%; 4.664000000000001), (aldous huxley; a brave new world; 4.5; 10.0%; 4.95), (william gibson; neuromancer; 6.3; 10.0%; 6.93), (george orwell; 1984; 4.2; 20.0%; 3.3600000000000003; 10.0%; 3.696), (ray bradbury; fahrenheit 451; 5.4; 10.0%; 5.94)]"),
					 normalize(lro1.toString()),
					 "\n> Error: addBook(): toString():");
			//------------------------
			lro1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lro1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lro1.addBook("William Gibson", "Neuromancer", 8.30);
			lro1.addBook("Isaac Asimov", "Foundation", 7.30);
			lro1.addBook("George Orwell", "1984", 6.20);
			//------------------------
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					 normalize(lro1.toString()),
					 "\n> Error: addTest(): toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreGetFinalPriceTest1() {
			lro1.addBook("Isaac Asimov", "Foundation", 7.30);
			lro1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lro1.addBook("William Gibson", "Neuromancer", 8.30);
			lro1.addBook("George Orwell", "1984", 6.20);
			lro1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lro1.toString()));
			//------------------------
			assertAll("salesBookStoreGetBasePriceTest2",
					() -> assertEquals(6.424, lro1.getFinalPrice("Isaac Asimov", "Foundation"), 1e-6, "\n> Error: addBook(): getFinalPrice():"),
					() -> assertEquals(7.15, lro1.getFinalPrice("Aldous Huxley", "A Brave New World"), 1e-6, "\n> Error: addBook(): getFinalPrice():"),
					() -> assertEquals(9.13, lro1.getFinalPrice("William Gibson", "Neuromancer"), 1e-6, "\n> Error: addBook(): getFinalPrice():"),
					() -> assertEquals(5.456, lro1.getFinalPrice("George Orwell", "1984"), 1e-6, "\n> Error: addBook(): getFinalPrice():"),
					() -> assertEquals(8.14, lro1.getFinalPrice("Ray Bradbury", "Fahrenheit 451"), 1e-6, "\n> Error: addBook(): getFinalPrice():"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreGetFinalPriceTest2() {
			lro1.addBook("Isaac Asimov", "Foundation", 7.30);
			lro1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lro1.addBook("William Gibson", "Neuromancer", 8.30);
			lro1.addBook("George Orwell", "1984", 6.20);
			lro1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lro1.toString()));
			//------------------------
			assertAll("salesBookStoreGetBasePriceTest2",
					() -> assertEquals(6.424, lro1.getFinalPrice("isaac asimov", "Foundation"), 1e-6, "\n> Error: addBook(): getFinalPrice():"),
					() -> assertEquals(7.15, lro1.getFinalPrice("aldous huxley", "A Brave New World"), 1e-6, "\n> Error: addBook(): getFinalPrice():"),
					() -> assertEquals(9.13, lro1.getFinalPrice("william gibson", "Neuromancer"), 1e-6, "\n> Error: addBook(): getFinalPrice():"),
					() -> assertEquals(5.456, lro1.getFinalPrice("george orwell", "1984"), 1e-6, "\n> Error: addBook(): getFinalPrice():"),
					() -> assertEquals(8.14, lro1.getFinalPrice("ray bradbury", "fahrenheit 451"), 1e-6, "\n> Error: addBook(): getFinalPrice():"));
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreGetFinalPriceTest3() {
			lro1.addBook("Isaac Asimov", "Foundation", 7.30);
			lro1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lro1.addBook("William Gibson", "Neuromancer", 8.30);
			lro1.addBook("George Orwell", "1984", 6.20);
			lro1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					normalize(lro1.toString()));
			//------------------------
			Exception exception = assertThrows(RuntimeException.class, () -> lro1.getFinalPrice("xxx", "xxx"));
			assertEquals("Book not found (xxx, xxx)", exception.getMessage(), "\n> Error: finalPrice(xxx, xxx): exception.getMessage()");
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreDeleteBookTest1() {
			lro1.addBook("George Orwell", "1984", 6.20);
			lro1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lro1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lro1.addBook("Isaac Asimov", "Foundation", 7.30);
			lro1.addBook("William Gibson", "Neuromancer", 8.30);
			//------------------------
			precond(normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lro1.toString()));
			//------------------------
			lro1.deleteBook("Isaac Asimov", "Foundation");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					 normalize(lro1.toString()),
					 "\n> Error: deleteBook(Isaac Asimov, Foundation): toString():");
			//------------------------
			lro1.deleteBook("Aldous Huxley", "A Brave New World");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					 normalize(lro1.toString()),
					 "\n> Error: deleteBook(Aldous Huxley, A Brave New World): toString():");
		//------------------------
			lro1.deleteBook("William Gibson", "Neuromancer");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					 normalize(lro1.toString()),
					 "\n> Error: deleteBook(William Gibson, Neuromancer): toString():");
		//------------------------
			lro1.deleteBook("George Orwell", "1984");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] [(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
					 normalize(lro1.toString()),
					 "\n> Error: deleteBook(George Orwell, 1984): toString():");
			//------------------------
			lro1.deleteBook("Ray Bradbury", "Fahrenheit 451");
			assertEquals(
					 normalize("20.0%[george orwell, isaac asimov] []"),
					 normalize(lro1.toString()),
					 "\n> Error: deleteBook(Ray Bradbury, Fahrenheit 451): toString():");
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreDeleteBookTest2() {
			lro1.addBook("George Orwell", "1984", 6.20);
			lro1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lro1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lro1.addBook("Isaac Asimov", "Foundation", 7.30);
			lro1.addBook("William Gibson", "Neuromancer", 8.30);
			//------------------------
			//System.out.println("DBG: "+lro1.toString()); assertTrue("",false);
			precond(normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lro1.toString()));
			//------------------------
			lro1.deleteBook("isaac asimov", "foundation");
			assertEquals(
						 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
						 normalize(lro1.toString()),
						 "\n> Error: deleteBook(isaac asimov, foundation): toString():");
			//------------------------
			lro1.deleteBook("aldous huxley", "a brave new world");
			assertEquals(
						 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
						 normalize(lro1.toString()),
						 "\n> Error: deleteBook(aldous huxley, a brave new world): toString():");
			//------------------------
			lro1.deleteBook("william gibson", "neuromancer");
			assertEquals(
						 normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
						 normalize(lro1.toString()),
						 "\n> Error: deleteBook(william gibson, neuromancer): toString():");
			//------------------------
			lro1.deleteBook("george orwell", "1984");
			assertEquals(
						 normalize("20.0%[george orwell, isaac asimov] [(Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
						 normalize(lro1.toString()),
						 "\n> Error: deleteBook(george orwell, 1984): toString():");
			//------------------------
			lro1.deleteBook("ray bradbury", "fahrenheit 451");
			assertEquals(
						 normalize("20.0%[george orwell, isaac asimov] []"),
						 normalize(lro1.toString()),
						 "\n> Error: deleteBook(ray bradbury, fahrenheit 451): toString():");
			//------------------------
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreDeleteBookTest3() {
			lro1.addBook("George Orwell", "1984", 6.20);
			lro1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lro1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lro1.addBook("Isaac Asimov", "Foundation", 7.30);
			lro1.addBook("William Gibson", "Neuromancer", 8.30);
			//------------------------
			//System.out.println("DBG: "+lro1.toString()); assertTrue("",false);
			precond(normalize("20.0%[george orwell, isaac asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13)]"),
					normalize(lro1.toString()));
			//------------------------
			Exception exception = assertThrows(RuntimeException.class, () -> lro1.deleteBook("xxx", "xxx"));
			assertEquals("Book not found (xxx, xxx)", exception.getMessage(), "\n> Error: deleteBook(xxx, xxx): exception.getMessage()");
			//------------------------
		}
			@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreToStringTest1() {
			assertEquals(normalize("20.0%[george orwell, isaac asimov][]"),
						 normalize(lro1.toString()),
						 "\n> Error: lro1.toString():");
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void salesBookStoreToStringTest2() {
			lro1.addBook("Isaac Asimov", "Foundation", 7.30);
			lro1.addBook("Aldous Huxley", "A Brave New World", 6.50);
			lro1.addBook("William Gibson", "Neuromancer", 8.30);
			lro1.addBook("George Orwell", "1984", 6.20);
			lro1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals(normalize("20.0%[george orwell, isaac asimov][(Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0 % ; 4.96 ; 10.0 % ; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
						 normalize(lro1.toString()),
						 "\n> Error: lro1.toString():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	public class JUnitTestMainSalesBookStore {
		@BeforeAll
		public void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of MainSalesBookStore JUnit Test");
		}
		@AfterAll
		public void afterClass() {
			// Code executed after the last test method
			System.out.println("End of MainSalesBookStore JUnit Test");
		}
		@BeforeEach
		public void setUp() {
			// Code executed before each test
		}
		@AfterEach
		public void tearDown() {
			// Code executed after each test
		}
		@Test
		@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
		public void testMainSalesBookStoreMain() {
			String output = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				MainSalesBookStore.main(new String[]{});
				fail("\n> Error: main(): No exception was thrown.");
			} catch (RuntimeException e) {
				assertEquals(normalize("Book not found (Isaac Newton, Arithmetica Universalis)"),
							 normalize(e.getMessage()),
							 "\n> Error: Main(): exception.getMessage()"
							 );
			} catch (Exception e) {
				fail("\n> Error: main(): exception thrown is not RuntimeException");
			} finally {
				output = sysOutCapture.sysOutRelease();
			}
			assertEquals(
					normalize("20.0%[George Orwell, Isaac Asimov] [(George Orwell; 1984; 6.2; 20.0%; 4.96; 10.0%; 5.4559999999999995), (Philip K. Dick; Do Androids Dream of Electric Sheep?; 3.5; 10.0%; 3.85), (Isaac Asimov; Foundation and Empire; 9.4; 20.0%; 7.5200000000000005; 10.0%; 8.272), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Aldous Huxley; A Brave New World; 6.5; 10.0%; 7.15), (Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (Isaac Asimov; Second Foundation; 8.1; 20.0%; 6.4799999999999995; 10.0%; 7.127999999999999), (Isaac Newton; Arithmetica Universalis; 10.5; 10.0%; 11.55)] 20.0%[George Orwell, Isaac Asimov] [(Philip K. Dick; Do Androids Dream of Electric Sheep?; 3.5; 10.0%; 3.85), (Isaac Asimov; Foundation and Empire; 9.4; 20.0%; 7.5200000000000005; 10.0%; 8.272), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14), (Isaac Asimov; Foundation; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (William Gibson; Neuromancer; 8.3; 10.0%; 9.13), (Isaac Asimov; Second Foundation; 8.1; 20.0%; 6.4799999999999995; 10.0%; 7.127999999999999)] FinalPrice(Philip K. Dick, Do Androids Dream of Electric Sheep?): 3.85 FinalPrice(isaac asimov, foundation and empire): 8.272 FinalPrice(Ray Bradbury, Fahrenheit 451): 8.14 FinalPrice(Isaac Asimov, Foundation): 6.4239999999999995 FinalPrice(william gibson, neuromancer): 9.13 FinalPrice(Isaac Asimov, Second Foundation): 7.127999999999999"),
					normalize(output),
					"\n> Error: SalesBookStoreTest.main():");
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@Suite
	@SelectClasses({JUnitTestBook.class ,
				JUnitTestBookOnSale.class ,
				JUnitTestBookStore.class ,
				JUnitTestSalesBookStore.class ,
				JUnitTestMainSalesBookStore.class 
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static void main(String[] args) {
		final LauncherDiscoveryRequest request = 
				LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectClass(JUnitTestBook.class),
						selectClass(JUnitTestBookOnSale.class),
						selectClass(JUnitTestBookStore.class),
						selectClass(JUnitTestSalesBookStore.class),
						selectClass(JUnitTestMainSalesBookStore.class))
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

//	    public static Result result = null;
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
//				System.out.println(">>> Error: "+ac+" tests could not be executed due to previous errors");
//			}
//			if (fc > 0) {
//				System.out.println(">>> Error: "+fc+" tests failed");
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
