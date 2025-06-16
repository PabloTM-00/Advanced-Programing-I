
import java.util.List;

import covid.COVIDException;
import covid.HealthDistrict;

/**
 * Executed with cases provided as arguments to the main class, e.g.,
 * 
 * 184 291 262 761 761 99
 */
public class TestHealthDistrict {

	public static void main(String[] args) {
		HealthDistrict axarquia = new HealthDistrict("Axarquia", 170141, Integer.parseInt(args[0])),
				laVega = new HealthDistrict("La Vega", 110176, Integer.parseInt(args[1])),
				guadalhorce = new HealthDistrict("Valle del Guadalhorce", 156298, Integer.parseInt(args[2])),
				costaSol = new HealthDistrict("Costa del Sol", 560785, Integer.parseInt(args[3])),
				malagaDistrito = new HealthDistrict("Malaga Distrito", 633521, Integer.parseInt(args[4])),
				serrania = new HealthDistrict("Serrania", 54999, Integer.parseInt(args[5]));

		List<HealthDistrict> list = List.of(axarquia, laVega, guadalhorce, costaSol, malagaDistrito, serrania);

		int district = (int) (Math.random() * (list.size()));
		try {
			list.get(district).setCovidCases14days(Integer.parseInt(args[0]));
			System.out.println(list);
		} catch (ArrayIndexOutOfBoundsException aiob) {
			System.err.println("No value has been entered to modify positive cases");
		} catch (NumberFormatException nfe) {
			System.err.println("At least one of the entered values is not an integer");
		} catch (COVIDException ce) {
			System.err.println(ce.getMessage());
		}
	}

}