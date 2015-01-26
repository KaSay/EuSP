package de.cmlab.ubicomp.frontend;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.cmlab.ubicomp.nfcreader.XMLRPCServer;
import de.cmlab.ubicomp.shoppinglistcreation.AdvertisingReader;
import de.cmlab.ubicomp.shoppinglistcreation.ItemWithPrice;
import de.cmlab.ubicomp.shoppinglistcreation.Profiler;
import de.cmlab.ubicomp.shoppinglistcreation.ShoppingList;
import de.cmlab.ubicomp.shoppinglistcreation.Supermarket;

/**
 * @version 1.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock ShoppingListPanel
 */
/**
 * show the chosen supermarket with its price list
 */
public class ShoppingListPanel extends JPanel {

	public Supermarket mySupermarket;
	public AdvertisingReader myAdvertReader;
	public Profiler myProfile;
	public ShoppingList shoppingList = new ShoppingList();
	public JLabel totalPrice;

	/**
	 * panel showing the chosen supermarket and its price list
	 * @param supermarket holds the chosen supermarket
	 * @param AdvertReader has information about the supermarket
	 * @param profile chosen profile
	 */
	public ShoppingListPanel(Supermarket supermarket,
			AdvertisingReader AdvertReader, Profiler profile) {
		mySupermarket = supermarket;
		myAdvertReader = AdvertReader;
		myProfile = profile;

		// some general properties for the panel (location, size, color, layout)
		setBounds(0, 0, 500, 550);
		setBackground(Color.white);
		setLayout(null);

		//label showing the chosen supermarket again in the titel
		JLabel iconSupermarket2 = new JLabel(new ImageIcon("advertising/"
				+ mySupermarket.getName() + ".png"));
		iconSupermarket2.setBounds(5, 0, 300, 156);
		add(iconSupermarket2);

		//label displaying the word "Einkaufsliste" on top of the frame as titel
		JLabel titel = new JLabel("Einkaufsliste");
		titel.setBounds(320, 50, 300, 50);
		titel.setFont(new Font("Arial", 1, 16));
		add(titel);

		String itemsOutOfProfile = null;

		//sets the status of the profile to a variable
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

		int distanceItems = 0; //for keeping distances between the items 
		int distancePrice = 0; //for keeping distances between the prices
		int cntItems = 0;
		List<ItemWithPrice> pricelist = new ArrayList<ItemWithPrice>();
		
		//Loop selecting the supermarket and goes through its price list to show prices and items on the frame
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
						
						//save variable to create a current shopping list
						pricelist.add(new ItemWithPrice(supermarket.getPricelist()
								.get(i).getPrice(),supermarket.getPricelist()
								.get(i).getItemName()));
						
						JLabel price = new JLabel(String.valueOf(supermarket
								.getPricelist().get(i).getPrice()));
						price.setBounds(300, 187 + distancePrice, 150, 23);
						price.setFont(new Font("Arial", 1, 16));
						add(price);

						distanceItems = distanceItems + 40; //increased to display the distances
						distancePrice = distancePrice + 40;

						cntItems++;
					}

				}

			}

		}
		
		shoppingList.setShoppingList(pricelist);
		cntItems = 0;

		//displays the overall price at the end of the page
		//label for displaying the text (Gesamtpreis)
		JLabel totalPriceLabel = new JLabel("Gesamtpreis:");
		totalPriceLabel.setBounds(38, 457, 265, 46);
		totalPriceLabel.setFont(new Font("Arial", 1, 16));
		add(totalPriceLabel);


		
		//label for displaying the actual amount
		totalPrice = new JLabel(String.valueOf(supermarket
				.getOverallPrice()));
		totalPrice.setBounds(300, 457, 265, 46);
		totalPrice.setFont(new Font("Arial", 1, 16));
		add(totalPrice);
		
		//save variable to create a current shopping list with overall price
		shoppingList.setCurrentOverallPrice(supermarket
				.getOverallPrice());
		
		setVisible(true); //sets the panel visible
		XMLRPCServer server = new XMLRPCServer(this, shoppingList); //starts the XMLRPCserver 
	}



}
