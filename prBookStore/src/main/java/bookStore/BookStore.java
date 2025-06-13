
package bookStore;

import java.util.ArrayList;

public class BookStore {
	private ArrayList<Book> books;

	public BookStore() {
		books = new ArrayList<Book>();
	}

	public void addBook(String a, String t, double p) {
		addBook(new Book(a, t, p));
	}

	public void deleteBook(String a, String t) {
		int i = seekBook(a, t);
		if (i >= 0) {
			books.remove(i);
		} else {
			throw new RuntimeException("Book not found ("+ a + ", " + t + ")");
		}
	}

	public double getBasePrice(String a, String t) {
		double p = 0;
		int i = seekBook(a, t);
		if (i >= 0) {
			p = books.get(i).getBasePrice();
		} else {
			throw new RuntimeException("Book not found ("+ a + ", " + t + ")");
		}
		return p;
	}

	public double getFinalPrice(String a, String t) {
		double p = 0;
		int i = seekBook(a, t);
		if (i >= 0) {
			p = books.get(i).getFinalPrice();
		} else {
			throw new RuntimeException("Book not found ("+ a + ", " + t + ")");
		}
		return p;
	}

	@Override
	public String toString() {
		String str = "";
		if (books.size() > 0) {
			str += books.get(0).toString();
			for (int i = 1; i < books.size(); ++i) {
				str += ",\n " + books.get(i).toString();
			}
		}
		return "[" + str + "]";
	}

	protected void addBook(Book b) {
		int i = seekBook(b.getAuthor(), b.getTitle());
		if (i >= 0) {
			books.set(i, b);
		} else {
			books.add(b);
		}
	}

	private int seekBook(String a, String t) {
		int i = books.size() - 1;
		while ((i >= 0)
				&& !(books.get(i).getAuthor().equalsIgnoreCase(a) && books.get(i).getTitle().equalsIgnoreCase(t))) {
			--i;
		}
		return i;
	}
}
