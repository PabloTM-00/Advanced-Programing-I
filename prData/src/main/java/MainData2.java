import java.util.Arrays;
import data2.*;

public class MainData2 {

    public static void main(String[] args) {
        double min, max;

        //check correct n of arguments
        if (args.length < 3) {
            System.out.println("Error, there are not enough values");
            return;
        }

        try {
            min = Double.parseDouble(args[0]);
            max = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error, when converting a value to a real number (" + e.getMessage() + ")");
            return;
        }

        String[] values = Arrays.copyOfRange(args, 2, args.length);

        Data data = new Data(values, min, max);

        System.out.println(data);

        try {
            data.setRange("0;4");
            System.out.println(data);

            data.setRange("15 25");
            System.out.println(data);
        } catch (DataException e) {
            System.out.println("Data error setting range: " + e.getMessage());
        }
    }
}
