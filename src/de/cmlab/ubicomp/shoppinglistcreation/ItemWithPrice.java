package de.cmlab.ubicomp.shoppinglistcreation;

/**
 * @version 1.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
 *
 */
public class ItemWithPrice {

	double price;
    String itemName;
    
	public ItemWithPrice(double price, String itemName) {
		  this.price = price;
	      this.itemName = itemName;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}




}
