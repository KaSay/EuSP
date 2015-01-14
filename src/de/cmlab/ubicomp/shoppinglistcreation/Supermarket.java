package de.cmlab.ubicomp.shoppinglistcreation;

import java.util.ArrayList;
import java.util.List;

/**
 * Supermarket class stands for a supermarket with its name, pricelist and
 * overallPrice
 * 
 * @version 1.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
 *
 */
public class Supermarket {

	String name;
	List<ItemWithPrice> pricelist = new ArrayList<ItemWithPrice>();
	double overallPrice;

	public Supermarket() {

	}

	/**
	 * gets name
	 * 
	 * @return string of the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name
	 * 
	 * @param name
	 *            name of the supermarket
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gets the price list for the supermarket
	 * 
	 * @return the price list of the supermarket
	 */
	public List<ItemWithPrice> getPricelist() {
		return pricelist;
	}

	/**
	 * sets the price list of the supermarket
	 * 
	 * @param pricelist
	 *            the price list
	 */
	public void setPricelist(List<ItemWithPrice> pricelist) {
		this.pricelist = pricelist;
	}

	/**
	 * gets the overall price
	 * @return overall price (double)
	 */
	public double getOverallPrice() {
		return overallPrice;
	}

	/**
	 * sets overall price
	 * @param overallPrice the overall price (double)
	 */
	public void setOverallPrice(double overallPrice) {
		this.overallPrice = overallPrice;
	}

}
