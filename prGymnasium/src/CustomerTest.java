import gym.*;

public class CustomerTest {
    public static void main(String[] args) {
        try {
            // Creating the Customer object, expecting an exception due to 1 activity for a regular customer
            Customer c1 = new Customer("785-36-6518", 1997, 2017, 1, true);
            System.out.println(c1);
        } catch (RuntimeException e) {
            // Catch the exception and print the error message
            System.out.println(e.getMessage());
        }
    }
}
