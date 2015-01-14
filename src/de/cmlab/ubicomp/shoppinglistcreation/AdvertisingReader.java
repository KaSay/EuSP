package de.cmlab.ubicomp.shoppinglistcreation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.cmlab.ubicomp.frontend.ManipulationFrontend;

/**
 * reads the different commercials and saves the information
 * @version 1.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
 *
 */
public class AdvertisingReader {

	String advertisingPath = "./advertising"; //path to the textfiles of the supermarkets
	public final List<Supermarket> supermarkets = new ArrayList<Supermarket>(); //list of all supermarkets
	
	/**
	 * reads the different commercials and saves the information
	 */
	public AdvertisingReader() {

		//loop goes through all text files in the path
		File advertisingDir = new File(advertisingPath);
		File[] files = advertisingDir.listFiles();
		for (File file : files) {
			if (file.getName().endsWith(".txt")) {
				
				String rawName = file.getName(); //sets the name out of the filename
				Supermarket supermarket = new Supermarket();
				supermarket.setName(getName(rawName)); //saves name in supermarket object
				
				List<ItemWithPrice> pricelist = new ArrayList<ItemWithPrice>();
				Scanner scanner; //reads the file
				try {
					scanner = new Scanner(file);

					while (scanner.hasNextLine()) { //reads every line of the file
						String line = (String) scanner.nextLine();
						
						String item, price; 
						String[] splitLine;
						
						//splits every line on the ";" symbol 
						//everything before the symbol is an item
						//everything after the symbol is the price for the item
						if (line.contains(";")) {
							splitLine = line.split(";");
							item = splitLine[0];
							price = splitLine[1];

						} else {
							throw new IllegalArgumentException(
									line
											+ " contains no ; to seperate - seperation failed");
						}

						double price2 = Double.parseDouble(price); //parses String to double

						pricelist.add(new ItemWithPrice(price2, item)); //adds the items read to the pricelist of the supermarket
						
						supermarket.setPricelist(pricelist); //saves price list in supermarket object
					
					}
					supermarkets.add(supermarket); //adds the supermarket to the list of supermarkets
					
					
					scanner.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			

		}

	}

	/**
	 * gets the String with file extension and returns string without file extension
	 * @param rawName name string with file extension
	 * @return name string without file extension
	 */
	private String getName(String rawName) {
		String name;
		name = rawName.replace(".txt", "");
		return name;
	}

/**
 * main method starts with AdvertisingReader and opens manipulation GUI afterwards
 * @param args string added after call of the program (not used)
 */
public static void main(String[] args) {
	 
		AdvertisingReader add = new AdvertisingReader();
		ManipulationFrontend mani = new ManipulationFrontend(add);
		mani.createAndShowGUI();
		//while true loop added - makes it possible to run project with ant
		while(true){
			
		}
		
	
	}
}
