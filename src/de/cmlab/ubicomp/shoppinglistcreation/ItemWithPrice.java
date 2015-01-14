package de.cmlab.ubicomp.shoppinglistcreation;

/**
 * @version 1.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
 * class ItemWithPrice models the combination betwenn price of an item and the name of an item
 */
public class ItemWithPrice {

	double price;
    String itemName;
    
    /**
     * sets the name and the price
     * @param price price of an item
     * @param itemName name of the item
     */
	public ItemWithPrice(double price, String itemName) {
		  this.price = price;
	      this.itemName = itemName;
	}
	
	/**
	 * gets price
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * sets price
	 * @param price price of an item
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * gets item name
	 * @return returns item name
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * sets item name
	 * @param itemName name of an item
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}




}
