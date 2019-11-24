package cuartamarkt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	private static int MIN_CUSTOMERS = 10;//Minimum number of customers
	private static int MAX_CUSTOMERS = 20;//Max number of customers
	private static int NUM_OFFERS = 10;//Number of offers

	public static void main(String[] args) {
		Random r = new Random();
		List<Customer> customers = new ArrayList<Customer>();//Creating an Array list to save all the customers
		Website web = new Website();//Create the object Website
		SalesResponsible sr = new SalesResponsible(web, NUM_OFFERS);//Create the Sales responsible
		sr.start();//Starting the Sales Responsible
		//Start creating a n number of customers and starting them.
		for (int i = 0; i < (MIN_CUSTOMERS + r.nextInt((MAX_CUSTOMERS - MIN_CUSTOMERS))); i++) {
			Customer c = new Customer(web);
			c.setName("Customer " + (i + 1));
			c.start();
			customers.add(c);
		}
		//We make this joins to wait to finish all the threads
		try {
			for (Customer customer : customers) {
				customer.join();
			}
			sr.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//And finally we Print all recipts and the final profits
		web.printAllRecipts();

	}
}
