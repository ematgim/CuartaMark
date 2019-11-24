package cuartamarkt;

import java.util.Random;
/**
 * This class its used for offers
 * @author Emilio Mateo Gimenez
 *
 */
public class Offer {
	private String model; //Saves the model of the phone
	private double price;//Saves the normal price
	private int discount;//Saves the % of discount 
	private Double finalPrice;//Saves the price with the discount
	private Random r = new Random();
/**
 * Creates an Offer item
 * @param model Model of the Phone
 * @param price Price without discount
 */
	public Offer(String model, double price) {
		super();
		this.model = model;
		this.price = price;
		discount = 30+r.nextInt(10);
		finalPrice = price - (price*(discount/100.0));
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}
	
	
}
