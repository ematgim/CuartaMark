package cuartamarkt;


import java.util.Random;
/**
 * Sales Responsible is the Thread that upload the Offers to the Website
 * @author Emilio Mateo Gimenez
 *
 */
public class SalesResponsible extends Thread {
	
	private Random r = new Random();
	private Website web; //Saves the Website Object
	private int nOffers; // The number of offers that will be uploaded
	
	/**
	 * Create a Sales Responsible
	 * @param web Website to upload
	 * @param nOffers Number of offers to umpload
	 */
	public SalesResponsible(Website web, int nOffers){
		this.nOffers = nOffers;
		this.web = web;
	}
	/**
	 * This method starts the tread where the Sales Responsible creates all the offers
	 */
	public void run(){
		for(int i = 0; i < nOffers; i++){
			Offer o = randomOffers();
			web.addOffer(o);//Adds the offer to the website
			try {
				sleep(500 + r.nextInt(500));//Wait between the uploads for 0,5 second to 1 second ramdomly
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		web.setStock(false);
	}
	/**
	 * Generate random offers of diferen types of offers
	 * @return A random Offer
	 */
	private Offer randomOffers(){
		Offer result = null;
		
		switch (r.nextInt(3)) {
		case 0:
			 result = new Offer("Aifon X",1000);
			break;
		case 1:
			 result = new Offer("Juan-wei Mate Profesional",925);
			break;
		case 2:
			 result = new Offer("Shang Tsung S9",975);
			break;
		}
		return result;
		
	}
}
