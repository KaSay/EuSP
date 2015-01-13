package frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shoppinglistcreation.AdvertisingReader;
import shoppinglistcreation.Profiler;
import shoppinglistcreation.Supermarket;

public class ShoppingListPanel extends JPanel {

	public Supermarket mySupermarket;
	public AdvertisingReader myAdvertReader;
	public Profiler myProfile;

	public ShoppingListPanel(Supermarket supermarket,
			AdvertisingReader AdvertReader, Profiler profile) {
		mySupermarket = supermarket;
		myAdvertReader = AdvertReader;
		myProfile = profile;

		setBounds(0, 0, 500, 550);
		setBackground(Color.white);
		setLayout(null);

		JLabel iconSupermarket2 = new JLabel(new ImageIcon("advertising/"
				+ mySupermarket.getName() + ".png"));
		iconSupermarket2.setBounds(5, 0, 300, 156);
		add(iconSupermarket2);

		JLabel titel = new JLabel("Einkaufsliste");
		titel.setBounds(320, 50, 300, 50);
		titel.setFont(new Font("Arial", 1, 16));
		add(titel);

		String itemsOutOfProfile = null;

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

		int distanceItems = 0;
		int distancePrice = 0;
		for (int j = 0; j < myAdvertReader.supermarkets.size(); j++) {
			if (myAdvertReader.supermarkets.get(j).equals(mySupermarket)) {

				for (int i = 0; i < supermarket.getPricelist().size(); i++) {
					if (itemsOutOfProfile.contains(supermarket.getPricelist()
							.get(i).getItemName())) {
						JLabel item = new JLabel(supermarket.getPricelist()
								.get(i).getItemName());
						item.setBounds(38, 187 + distanceItems, 150, 23);
						item.setFont(new Font("Arial", 1, 16));
						add(item);

						JLabel price = new JLabel(String.valueOf(supermarket
								.getPricelist().get(i).getPrice()));
						price.setBounds(300, 187 + distancePrice, 150, 23);
						price.setFont(new Font("Arial", 1, 16));
						add(price);

						distanceItems = distanceItems + 40;
						distancePrice = distancePrice + 40;

					}

				}

			}

		}
		
		JLabel totalPriceLabel = new JLabel("Gesamtpreis:");
		totalPriceLabel.setBounds(38,457,265,46);
		totalPriceLabel.setFont(new Font("Arial", 1, 16));
		add(totalPriceLabel);
		
		
		JLabel totalPrice = new JLabel(String.valueOf(supermarket.getOverallPrice()));
		totalPrice.setBounds(300,457,265,46);
		totalPrice.setFont(new Font("Arial", 1, 16));
		add(totalPrice);
		setVisible(true);
	}

}
