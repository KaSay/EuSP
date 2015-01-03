package logic;

public class PriceCalculator {

	Profiler profile = new Profiler();
	Supermarket supermarket = new Supermarket();

	public PriceCalculator() {

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
		
		for (int i = 0; i < supermarket.getPricelist().size(); i++) {
			if (itemsOutOfProfile.contains(supermarket.getPricelist().get(i)
					.getItemName())) {
				System.out
						.println(supermarket.getPricelist().get(i).getPrice());
				overAllPrice = overAllPrice
						+ supermarket.getPricelist().get(i).getPrice();
			}

		}
	

	}

}
