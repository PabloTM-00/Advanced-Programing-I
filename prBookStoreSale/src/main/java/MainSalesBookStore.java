import java.util.ArrayList;
import java.util.List;

class Book {
    String author;
    String title;
    double basePrice;
    static double percVAT = 10;

    public Book(String author, String title, double basePrice) {
        this.author = author;
        this.title = title;
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return String.format("(%s; %s; %.2f)", author, title, basePrice);
    }

	public Object getAuthor() {
		return author;
	}

	public Object getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public double getBasePrice() {
		// TODO Auto-generated method stub
		return basePrice;
	}

	public double getFinalPrice() {
        return getTaxBasePrice() * (1 + getVAT() / 100);
    }

	 protected double getTaxBasePrice() {
        return basePrice;
    }

	public static void setVAT(double newVAT) {
        if (newVAT < 0) {
            throw new IllegalArgumentException("VAT cannot be negative");
        }
        percVAT = newVAT;
    }
	
	public static double getVAT() {
        return percVAT;
    }
}

class SalesBookStore {
    private final List<Book> books = new ArrayList<>();
    private final List<String> discountAuthors;
    private final double discountRate;
    private final double generalMarkup = 1.1;

    public SalesBookStore(double discountRate, List<String> discountAuthors) {
        this.discountRate = discountRate / 100;
        this.discountAuthors = discountAuthors;
    }

    public void addBook(String author, String title, double basePrice) {
        books.add(new Book(author, title, basePrice));
    }

    public void removeBook(String author, String title) {
        books.removeIf(book -> book.author.equalsIgnoreCase(author) && book.title.equalsIgnoreCase(title));
    }

    public double finalPrice(String author, String title) {
        for (Book book : books) {
            if (book.author.equalsIgnoreCase(author) && book.title.equalsIgnoreCase(title)) {
                double price = book.basePrice;
                if (discountAuthors.contains(book.author)) {
                    price *= (1 - discountRate);
                }
                return price * generalMarkup;
            }
        }
        throw new RuntimeException(String.format("Book not found (%s, %s)", author, title));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%.1f%%", discountRate * 100)).append(discountAuthors).append("\n");
        sb.append("[");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            double price = book.basePrice;
            String discountInfo = "";
            if (discountAuthors.contains(book.author)) {
                discountInfo = String.format("; %.1f%%; %.2f", discountRate * 100, price * (1 - discountRate));
                price *= (1 - discountRate);
            }
            sb.append(String.format("(%s; %s; %.2f%s; 10.0%%; %.3f)", book.author, book.title, book.basePrice, discountInfo, price * generalMarkup));
            if (i < books.size() - 1) sb.append(",\n ");
        }
        sb.append("]");
        return sb.toString();
    }
}

public class MainSalesBookStore {
    public static void main(String[] args) {
        SalesBookStore store = new SalesBookStore(20.0, List.of("George Orwell", "Isaac Asimov"));
        store.addBook("George Orwell", "1984", 8.20);
        store.addBook("Philip K. Dick", "Do Androids Dream of Electric Sheep?", 3.50);
        store.addBook("Isaac Asimov", "Foundation and Empire", 9.40);
        store.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
        store.addBook("Aldous Huxley", "A Brave New World", 6.50);
        store.addBook("Isaac Asimov", "Foundation", 7.30);
        store.addBook("William Gibson", "Neuromancer", 8.30);
        store.addBook("Isaac Asimov", "Second Foundation", 8.10);
        store.addBook("Isaac Newton", "Arithmetica Universalis", 7.50);
        store.addBook("George Orwell", "1984", 6.20);
        store.addBook("Isaac Newton", "Arithmetica Universalis", 10.50);
        System.out.println(store);
        store.removeBook("George Orwell", "1984");
        store.removeBook("Aldous Huxley", "A Brave New World");
        store.removeBook("Isaac Newton", "Arithmetica Universalis");
        System.out.println(store);
        String[][] queries = {
            {"Philip K. Dick", "Do Androids Dream of Electric Sheep?"},
            {"isaac asimov", "foundation and empire"},
            {"Ray Bradbury", "Fahrenheit 451"},
            {"Isaac Asimov", "Foundation"},
            {"william gibson", "neuromancer"},
            {"Isaac Asimov", "Second Foundation"},
            {"Isaac Newton", "Arithmetica Universalis"}
        };
        for (String[] query : queries) {
            try {
                System.out.printf("FinalPrice(\"%s\", \"%s\"): %.2f\n", query[0], query[1], store.finalPrice(query[0], query[1]));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
