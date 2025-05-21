package bookStore;

public class Book {
	private static double percVAT = 10;
	private String author;
	private String title;
	private double basePrice;

	public Book(String a, String t, double p) {
		author = a;
		title = t;
		basePrice = p;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public double getBasePrice() {
		return basePrice;
	}

	protected double getTaxBasePrice() {
		return basePrice;
	}

	public double getFinalPrice() {
		return getTaxBasePrice() + getTaxBasePrice() * getVAT() / 100.0d;
	}

	@Override
	public String toString() {
		return "(" + getAuthor() + "; " + getTitle() + "; " + getBasePrice() + "; " + 
	                 getVAT() + "%; " + getFinalPrice() + ")";
	}

	public static double getVAT() {
		return percVAT;
	}

	public static void setVAT(double p) {
		percVAT = p;
	}
}
