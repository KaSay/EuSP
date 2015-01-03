package shoppinglistcreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdvertisingReader {

	String advertisingPath = "./advertising";

	public AdvertisingReader() {

		File advertisingDir = new File(advertisingPath);
		File[] files = advertisingDir.listFiles();
		for (File file : files) {
			if (file.getName().endsWith(".txt")) {

				// setting supermarket name (deletion of .txt ending)
				String rawName = file.getName();
				Supermarket supermarket = new Supermarket();
				supermarket.setName(getName(rawName));

				List<ItemWithPrice> pricelist = new ArrayList<ItemWithPrice>();
				Scanner scanner;
				try {
					scanner = new Scanner(file);

					while (scanner.hasNextLine()) {
						String line = (String) scanner.nextLine();

						String item, price;
						String[] splitLine;
						if (line.contains(";")) {
							splitLine = line.split(";");
							item = splitLine[0];
							price = splitLine[1];

						} else {
							throw new IllegalArgumentException(
									line
											+ " contains no ; to seperate - seperation failed");
						}

						double price2 = Double.parseDouble(price);

						pricelist.add(new ItemWithPrice(price2, item));
						supermarket.setPricelist(pricelist);
					
					}
					
					scanner.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
		}

	}

	private String getName(String rawName) {
		String name;
		name = rawName.replace(".txt", "");
		return name;
	}

	
	/* public static void main(String[] args) {
	 
		AdvertisingReader add = new AdvertisingReader();
		
	 //List<ItemWithPrice> pricelist = new ArrayList<ItemWithPrice>();
	 //pricelist.add(new ItemWithPrice(0.3, "Salami")); 
	 //ItemWithPrice item  = new ItemWithPrice(0.3, "Salami"); 
		//Supermarket superm = new Supermarket(); 
		//superm.setPricelist(pricelist);
		//System.out.println(supermarket.getPricelist().get(11).getItemName());
		//System.out.println(supermarket.getPricelist().get(11).getPrice());
	
	}*/
}
