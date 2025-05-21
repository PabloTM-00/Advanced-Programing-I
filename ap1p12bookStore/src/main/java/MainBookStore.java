import bookStore.*;

public class MainBookStore {
	private static void addBooks(BookStore bs) {
		bs.addBook("george orwell", "1984", 8.20);
		bs.addBook("Philip K. Dick", "Do Androids Dream of Electric Sheep?", 3.50);
		bs.addBook("Isaac Asimov", "Foundation and Empire", 9.40);
		bs.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
		bs.addBook("Aldous Huxley", "A Brave New World", 6.50);
		bs.addBook("Isaac Asimov", "Foundation", 7.30);
		bs.addBook("William Gibson", "Neuromancer", 8.30);
		bs.addBook("Isaac Asimov", "Second Foundation", 8.10);
		bs.addBook("Isaac Newton", "arithmetica universalis", 7.50);
		bs.addBook("George Orwell", "1984", 6.20);
		bs.addBook("Isaac Newton", "Arithmetica Universalis", 10.50);
	}

	private static void deleteBooks(BookStore bs) {
		bs.deleteBook("George Orwell", "1984");
		bs.deleteBook("Aldous Huxley", "A Brave New World");
		bs.deleteBook("Isaac Newton", "Arithmetica Universalis");
	}

	private static void printPrices(BookStore bs) {
		System.out.println("FinalPrice(Philip K. Dick, Do Androids Dream of Electric Sheep?): "
				+ bs.getFinalPrice("Philip K. Dick", "Do Androids Dream of Electric Sheep?"));
		System.out.println("FinalPrice(isaac asimov, foundation and empire): "
				+ bs.getFinalPrice("isaac asimov", "foundation and empire"));
		System.out.println("FinalPrice(Ray Bradbury, Fahrenheit 451): " 
				+ bs.getFinalPrice("Ray Bradbury", "Fahrenheit 451"));
		System.out.println("FinalPrice(Isaac Asimov, Foundation): " 
				+ bs.getFinalPrice("Isaac Asimov", "Foundation"));
		System.out.println("FinalPrice(william gibson, neuromancer): " 
				+ bs.getFinalPrice("william gibson", "neuromancer"));
		System.out.println("FinalPrice(Isaac Asimov, Second Foundation): " 
				+ bs.getFinalPrice("Isaac Asimov", "Second Foundation"));
		System.out.println("FinalPrice(Isaac Newton, Arithmetica Universalis): "
				+ bs.getFinalPrice("Isaac Newton", "Arithmetica Universalis"));
	}

	public static void main(String[] args) {
		BookStore bs = new BookStore();
		addBooks(bs);
		System.out.println("\n" + bs + "\n");
		deleteBooks(bs);
		System.out.println("\n" + bs + "\n");
		printPrices(bs);
	}
}
