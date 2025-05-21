package bookStore;

public class DiscountPrice implements FlexDiscount {
    private final double discountPercentage;
    private final double priceThreshold;

    public DiscountPrice(double discountPercentage, double priceThreshold) {
        this.discountPercentage = discountPercentage;
        this.priceThreshold = priceThreshold;
    }

    @Override
    public double getDiscount(Book book) {
        if (book.getBasePrice() >= priceThreshold) {
            return discountPercentage;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return String.format("%.1f%%(%.1f)", discountPercentage, priceThreshold);
    }
}