package cuartamarkt;

/**
 * Create the thread customer that go into Website and try to buy a random offer
 * @author Emilio Mateo Gimenez
 *
 */
public class Customer extends Thread{
	private Website web;
	/**
	 * Creates the object Customer
	 * @param web Website where is going to connect
	 */
	public Customer(Website web){	
		this.web = web;
	}
	/**
	 * Start the thread where  is going to try buying an offer
	 */
	public void run(){
		web.buy();//Try to buy an offer
	}
}
