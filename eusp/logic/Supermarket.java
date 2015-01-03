package logic;

import java.util.ArrayList;
import java.util.List;

public class Supermarket {

	String name;

	List<ItemWithPrice> pricelist = new ArrayList<ItemWithPrice>();

	public Supermarket() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ItemWithPrice> getPricelist() {
		return pricelist;
	}

	public void setPricelist(List<ItemWithPrice> pricelist) {
		this.pricelist = pricelist;
	}
}
