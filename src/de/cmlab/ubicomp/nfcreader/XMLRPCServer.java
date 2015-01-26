package de.cmlab.ubicomp.nfcreader;

import java.awt.Font;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.xmlrpc.WebServer;

import de.cmlab.ubicomp.frontend.ShoppingListPanel;
import de.cmlab.ubicomp.shoppinglistcreation.ItemWithPrice;
import de.cmlab.ubicomp.shoppinglistcreation.ShoppingList;

/**
 * class starts the server and waits for results of the nfc app
 * 
 * @version 2.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
 *
 */

public class XMLRPCServer {

	public ShoppingListPanel myShoppingListPanel;
	public ShoppingList myShoppingList;

	/**
	 * starts the server and waits for responses of the nfc reader gets the
	 * shoppingListPanel for ticking of items read
	 * 
	 * @param shoppingListPanel
	 * @param shoppingList
	 */
	public XMLRPCServer(ShoppingListPanel shoppingListPanel,
			ShoppingList shoppingList) {

		myShoppingListPanel = shoppingListPanel;
		myShoppingList = shoppingList;
		newItem("Sekt 3.4"); // nur für test
		newItem("Gummibärchen");
		newItem("Salzstangen 1.3");
		newItem("Gurke 5.5");
		newItem("Ananas 2.1");
		newItem("Kekse");
		newItem("Kuchen");

		try {
			// start the server and waits for answers of the nfc reader
			System.out.println("Initializing new XML-RPC Server...");
			WebServer server = new WebServer(4455);
			server.addHandler("ItemCatcher", server);
			System.out.println("Starting the server...");
			// server.start();
			System.out.println("Starting successfully.");
			System.out.println("Start to search for NFCReader.");
			DatagramClient client = new DatagramClient();
			client.searchForNFCReader();

		} catch (Exception ex) {
			System.err.println("XMLRPCServer: " + ex);
		}
	}

	/**
	 * method getting the sting of the nfc app and marking the item that was
	 * read
	 * 
	 * @param item
	 *            item read by the nfc reader
	 * @return the string
	 */
	public String newItem(String item) {
		boolean success = false;

		// Delivered package needs to be registered by program.
		// Do something and don't forget to overwrite the success variable. :)

		success = true; // testzwecke
		if (success == true) {

			// sets the hook next to the item that was read

			JLabel itemChecked = new JLabel(new ImageIcon(
					"advertising/Haken.png"));

			boolean itemInList = false;
			int itemPlaceInList = 0;
			
			for (int i = 0; i < myShoppingList.getShoppingList().size(); i++) {

				// hook to drawn list
				
				if (item.contains(myShoppingList.getShoppingList().get(i)
						.getItemName())) {
					
					itemInList = true;
					itemPlaceInList = i;
				} 				
			}
			
			if (itemInList == true) {
				
								
					switch (itemPlaceInList) {
					case 0:
						itemChecked.setBounds(350, 170, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 1:
						itemChecked.setBounds(350, 210, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 2:
						itemChecked.setBounds(350, 250, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 3:
						itemChecked.setBounds(350, 290, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 4:
						itemChecked.setBounds(350, 330, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 5:
						itemChecked.setBounds(350, 370, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 6:
						itemChecked.setBounds(350, 410, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					}

				} 

			List<ItemWithPrice> pricelist = new ArrayList<ItemWithPrice>();
				if (itemInList == false){

					JLabel newItemLabelName = new JLabel();
					JLabel newItemLabelPrice = new JLabel();
					int shoppingListlength = myShoppingList
							.getShoppingList().size();
					
					//split into item and price
					String newItem, price; 
					String[] splitLine;
					
					if (item.contains(" ")) {
						splitLine = item.split(" ");
						newItem = splitLine[0];
						price = splitLine[1];
						Double price2;
						price2 = Double.parseDouble(price);
						
						newItemLabelName.setText(newItem);
						newItemLabelPrice.setText(price);
						
						
						pricelist = myShoppingList.getShoppingList();
						pricelist.add(new ItemWithPrice(price2, newItem));
						myShoppingList.setShoppingList(pricelist);
						
						
						double overAllPrice = 0;
						myShoppingList.setCurrentOverallPrice(myShoppingList.getCurrentOverallPrice() + price2);
						
						overAllPrice = myShoppingList.getCurrentOverallPrice();
						overAllPrice = Math.round(overAllPrice * 1000) / 1000.0; //round to 2 decimal places
						
						myShoppingListPanel.totalPrice.setText(String.valueOf(overAllPrice));
					} else {
						throw new IllegalArgumentException(
								item
										+ " contains no space to seperate - seperation failed");
					}
					
					// draw new item
					
					switch (shoppingListlength) {
					case 0:
						newItemLabelName.setBounds(38, 187, 150, 23);
						newItemLabelName.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelName);
						
						newItemLabelPrice.setBounds(300, 187, 150, 23);
						newItemLabelPrice.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelPrice);
						
						itemChecked.setBounds(350, 170, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 1:
						newItemLabelName.setBounds(38, 227, 150, 23);
						newItemLabelName.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelName);
						
						newItemLabelPrice.setBounds(300, 227, 150, 23);
						newItemLabelPrice.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelPrice);
						
						itemChecked.setBounds(350, 210, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 2:
						newItemLabelName.setBounds(38, 267, 150, 23);
						newItemLabelName.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelName);
						
						newItemLabelPrice.setBounds(300, 267, 150, 23);
						newItemLabelPrice.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelPrice);
						
						itemChecked.setBounds(350, 250, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 3:
						newItemLabelName.setBounds(38, 307, 150, 23);
						newItemLabelName.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelName);
						
						newItemLabelPrice.setBounds(300, 307, 150, 23);
						newItemLabelPrice.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelPrice);
						
						itemChecked.setBounds(350, 290, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 4:
						newItemLabelName.setBounds(38, 347, 150, 23);
						newItemLabelName.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelName);
						
						newItemLabelPrice.setBounds(300, 347, 150, 23);
						newItemLabelPrice.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelPrice);
						
						itemChecked.setBounds(350, 330, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 5:
						newItemLabelName.setBounds(38, 387, 150, 23);
						newItemLabelName.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelName);
						
						newItemLabelPrice.setBounds(300, 387, 150, 23);
						newItemLabelPrice.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelPrice);
						
						itemChecked.setBounds(350, 370, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					case 6:
						newItemLabelName.setBounds(38, 427, 150, 23);
						newItemLabelName.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelName);
						
						newItemLabelPrice.setBounds(300, 427, 150, 23);
						newItemLabelPrice.setFont(new Font("Arial", 1, 16));
						myShoppingListPanel.add(newItemLabelPrice);
						
						itemChecked.setBounds(350, 410, 40, 40);
						myShoppingListPanel.add(itemChecked);
						break;
					}
					
				}

			itemInList = false;

			return "OK";
		} else {
			return "Error";
		}
	}

	/**
	 * private class searching for NFC Reader
	 * 
	 * @version 1.0
	 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
	 */
	private class DatagramClient {

		public DatagramClient() {

		}

		/**
		 * searches for nfc reader
		 * 
		 * @throws IOException
		 *             raises excaption if error occurs during sending of the
		 *             pakes
		 */
		public void searchForNFCReader() throws IOException {
			// get a datagram socket
			DatagramSocket socket = new DatagramSocket();
			// send IP, Port information
			byte[] buf = new byte[256];
			InetAddress address = InetAddress.getByName("10.1.1.59");
			DatagramPacket packet = new DatagramPacket(buf, buf.length,
					address, 4455);
			try {
				socket.send(packet);
			} catch (Exception ex) {
				System.err.println("XMLRPCServer: " + ex);
			}
			socket.close();
		}

	}
}
