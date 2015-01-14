package de.cmlab.ubicomp.shoppinglistcreation;

import de.cmlab.ubicomp.frontend.ManipulationFrontend;
import de.cmlab.ubicomp.frontend.UserFrontend;

/**
 * @version 1.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
 */
/**
 * calculates the price depending on the chosen profile
 */

public class PriceCalculator {

	public Profiler profile;
	Supermarket supermarket;
	public AdvertisingReader advertReader;
	String itemsOutOfProfile;

	/**
	 * calls calculation method
	 * @param add information about supermarkets
	 * @param profile2 profiles
	 */
	public PriceCalculator(AdvertisingReader add, Profiler profile2) {
		advertReader = add;
		profile = profile2;
		calculateDependingOnProfile();
	}

	
/**
 * calculates total pruce depending on profile
 */
	private void calculateDependingOnProfile() {

		//sets itemsOutofProfile variable depending on variable chosen
		switch (profile.getStatus()) {
		case "ProfileEverydayLife":
			itemsOutOfProfile = profile.ProfileEverydayLife();
			break;
		case "ProfileHealthy":
			itemsOutOfProfile = profile.ProfileHealthy();
			break;
		case "ProfileParty":
			itemsOutOfProfile = profile.ProfileParty();
			break;
		case "ProfileSweet":
			itemsOutOfProfile = profile.ProfileSweet();
			break;
		default:
			System.out.println("Wrong profile name");
		}

		double overAllPrice = 0.0; //variable for overall price

		
		//loop goes through all supermarkets and calculates the price depending on the profile
		for (int j = 0; j < advertReader.supermarkets.size(); j++) {
			supermarket = advertReader.supermarkets.get(j);

			for (int i = 0; i < supermarket.getPricelist().size(); i++) {
				if (itemsOutOfProfile.contains(supermarket.getPricelist()
						.get(i).getItemName())) {

					overAllPrice = overAllPrice
							+ supermarket.getPricelist().get(i).getPrice(); //adding up the prices of the single items
					
					
				}
				
			}
			overAllPrice = Math.round(overAllPrice * 1000) / 1000.0; //round to 2 decimal places
			advertReader.supermarkets.get(j).setOverallPrice(overAllPrice); //saves overall price
			overAllPrice = 0;
			
			
		}
		
		UserFrontend userFrontend = new UserFrontend(advertReader, profile); //calls user frontend

	}

}
