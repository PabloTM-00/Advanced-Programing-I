import gym.GymWithDiscounts;
import gym.Gymnasium;

public class TestGym {
	
	public static void main(String[] args) {
	
	Gymnasium gym = new Gymnasium();
	
	gym.addCustomer("388-77-5692", 2000, 2017, 3, false);
	gym.addCustomer("718-15-9116", 1940, 2023, 2, false);
	gym.addCustomer("820-94-1888", 2004, 2021, 2, true);
	gym.addCustomer("366-35-7779", 1999, 2021, 4, false);
	gym.addCustomer("591-96-2640", 2002, 2019, 2, false);
	gym.addCustomer("626-40-6133", 2001, 2012, 3, false);
	gym.addCustomer("466-12-6029", 1997, 2020, 3, false);
	gym.addCustomer("123-18-2748", 1970, 2012, 2, true);
	gym.addCustomer("608-60-9964", 2003, 2020, 3, false);
	gym.addCustomer("652-92-2849", 1984, 2018, 2, false);
	gym.addCustomer("388-77-5692", 1995, 2023, 2, false);
	gym.addCustomer("652-92-2849", 1993, 2021, 1, true);
	gym.addCustomer("269-58-1651", 2000, 2019, 3, false);
	
	System.out.println(gym.toString());
	
	gym = new GymWithDiscounts(21, 65);
	gym.addCustomer("388-77-5692", 2000, 2017, 3, false);
	gym.addCustomer("718-15-9116", 1940, 2023, 2, false);
	gym.addCustomer("820-94-1888", 2004, 2021, 2, true);
	gym.addCustomer("366-35-7779", 1999, 2021, 4, false);
	gym.addCustomer("591-96-2640", 2002, 2019, 2, false);
	gym.addCustomer("626-40-6133", 2001, 2012, 3, false);
	gym.addCustomer("466-12-6029", 1997, 2020, 3, false);
	gym.addCustomer("123-18-2748", 1970, 2012, 2, true);
	gym.addCustomer("608-60-9964", 2003, 2020, 3, false);
	gym.addCustomer("652-92-2849", 1984, 2018, 2, false);
	gym.addCustomer("388-77-5692", 1995, 2023, 2, false);
	gym.addCustomer("652-92-2849", 1993, 2021, 1, true);
	gym.addCustomer("269-58-1651", 2000, 2019, 3, false);

	System.out.println(gym.toString());
	}
}