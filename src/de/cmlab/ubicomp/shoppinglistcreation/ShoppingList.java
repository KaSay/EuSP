package de.cmlab.ubicomp.shoppinglistcreation;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

	List<ItemWithPrice> shoppingList = new ArrayList<ItemWithPrice>();
	double currentOverallPrice;

	public List<ItemWithPrice> getShoppingList() {
		return shoppingList;
	}

	public double getCurrentOverallPrice() {
		return currentOverallPrice;
	}

	public void setCurrentOverallPrice(double currentOverallPrice) {
		this.currentOverallPrice = currentOverallPrice;
	}

	public void setShoppingList(List<ItemWithPrice> shoppingList) {
		this.shoppingList = shoppingList;
	}

	public ShoppingList() {
	
	}


	
	
}
