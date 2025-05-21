package bookStore;

public class BookOnSale extends Book {
    private double d;

   
    public BookOnSale(String a, String t, double p, double d) {
        super(a, t, p);
        this.d = d;
    }

  
    public double getDiscount() {
        return d;
    }


    @Override
    protected double getTaxBasePrice() {
        double originalTaxBasePrice = super.getTaxBasePrice();
        return originalTaxBasePrice - (originalTaxBasePrice * d / 100.0);
    }


    @Override
    public String toString() {
        return String.format("(%s; %s; %.2f; %.1f%%; %.2f; %.1f%%; %.3f)", 
            getAuthor(), 
            getTitle(), 
            getBasePrice(), 
            d, 
            getTaxBasePrice(), 
            getVAT(), 
            getFinalPrice());
    }
}