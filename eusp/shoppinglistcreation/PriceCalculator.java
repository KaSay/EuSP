package shoppinglistcreation;

import frontend.ManipulationFrontend;

public class PriceCalculator {

	public Profiler profile;
	Supermarket supermarket;
	public AdvertisingReader advertReader;

	public PriceCalculator(AdvertisingReader add, Profiler profile2) {
		advertReader = add;
		profile = profile2;
		calculateDependingOnProfile();
	}

	String itemsOutOfProfile;

	private void calculateDependingOnProfile() {


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

		double overAllPrice = 0.0;

		
		
		for (int j = 0; j < advertReader.supermarkets.size(); j++) {
			supermarket = advertReader.supermarkets.get(j);

			for (int i = 0; i < supermarket.getPricelist().size(); i++) {
				if (itemsOutOfProfile.contains(supermarket.getPricelist()
						.get(i).getItemName())) {

					overAllPrice = overAllPrice
							+ supermarket.getPricelist().get(i).getPrice(); //TODO: einspeichern und overallPrice 0 setzen
					
					
				}
				
			}
			System.out.println(overAllPrice);
		}

	}

}
