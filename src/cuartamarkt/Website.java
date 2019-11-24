package cuartamarkt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * This class Create the shared resouyrce between the sales responsible and the Customer
 * @author Emilio Mateo Gimenez
 *
 */
public class Website {
	private List<Offer> offers; //Its a list of offers
	private Random r;
	private double profits;//Save the total profits of the sales
	private boolean stock;//Save if the sales responsible has stock
	private List<String> recipts; //Save all the Customers Recipts
	
	public Website() {
		r = new Random();
		offers = new ArrayList<Offer>();
		recipts = new ArrayList<String>();
		stock = true;
	}
/**
 * This method allow the Custiomers to buy an Offer uploaded by sales Responsible;
 */
	public synchronized void buy() {
		//We check if there is stock and if there are 
		//uploaded offers. If we have stock but no offers we wait until the sales responsible upload one
		if (!thereAreOffers() && stock) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (thereAreOffers()) {
			Offer o = getOffer();
			profits += o.getFinalPrice();
			generateRecipt(o);
			System.out.println(Thread.currentThread().getName() + " has bought " + o.getModel());
		} else {
			System.out.println(Thread.currentThread().getName() + " we are sorry, we dont have any offer at this moment!");
		}

	}
	/**
	 * This method makes the format of the recipt and save it in the ArrayList recipts
	 * @param o Is the Offer to make the recipt
	 */
	 private void generateRecipt(Offer o) {
		String recipt = String.format("#########RECIPT#########%n") +
				String.format("Client %s %n", Thread.currentThread().getName())+
				String.format("Model: %s%n", o.getModel())+
				String.format("Price: %.2f%n", o.getPrice())+
				String.format("Discount %d %% %n", (o.getDiscount()))+
				String.format("Final Price: %.2f %n", o.getFinalPrice())+
				String.format("########################%n");
		recipts.add(recipt);
	 }
	 /**
	  * This method print all the recipts maked on the website
	  */
	 public void printAllRecipts(){
		 for (String recipt : recipts) {
			System.out.println(recipt);
		}
		 System.out.println("Total Profits: " + profits + "€");
	 }
	 /**
	  * This method allows us to add offers to the website
	  * @param o Offer to upload
	  */
	public synchronized void addOffer(Offer o) {
		offers.add(o);
		System.out.println(o.getModel() + " to the website");
		notify();//We notify all the threads that there are offers avaliable 
	}
/**
 * This method get a random offer of the ArrayList offers
 * @return Random offer
 */
	private Offer getOffer() {
		int rNumber = r.nextInt(offers.size());
		Offer offer = offers.get(rNumber);
		offers.remove(rNumber);
		return offer;
	}
/**
 * This method checks if there are ofers on the ArrayLIst offers
 * @return
 */
	private boolean thereAreOffers() {
		if (offers.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
/**
 * This method permit to the Change the stock value
 * @param b Stock value
 */
	public synchronized void setStock(boolean b) {
		System.out.println("¡¡No more stock!!");
		this.stock = b;
		notifyAll();
	}
}
