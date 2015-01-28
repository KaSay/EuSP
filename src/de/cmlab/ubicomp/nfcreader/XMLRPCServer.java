package de.cmlab.ubicomp.nfcreader;

import java.awt.Font;
import java.io.IOException;
import java.math.BigInteger;
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
	int port = 4455;
	boolean[] check = new boolean[7]; // default 0 draußen - rausgelöscht 1
	boolean[] checkIfNew = new boolean[7];
	boolean[] alreadyCalled = new boolean[7];
	WebServer server;

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
		boolean[] check = { false, false, false, false, false, false, false };
		boolean[] checkIfNew = { false, false, false, false, false, false,
				false };
		boolean[] alreadyCalled = { false, false, false, false, false, false,
				false };

		try {
			// start the server and waits for answers of the nfc reader
			System.out.println("Initializing new XML-RPC Server...");
			server = new WebServer(port);
			server.addHandler("ItemCatcher", this);
			System.out.println("Starting the server...");
			server.start();
			System.out.println("Starting successfully.");
			System.out.println("Start to search for NFCReader.");
			DatagramClient client = new DatagramClient(this);
			client.searchForNFCReader();

		} catch (Exception ex) {
			System.err.println("XMLRPCServer: " + ex);
		}
	}

	public int getPort() {
		return port;
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

	
		if (success == true) {

			// sets the hook next to the item that was read

			JLabel itemChecked = new JLabel(new ImageIcon(
					"advertising/Haken.png"));
			JLabel itemOutOfBasket = new JLabel(new ImageIcon(
					"advertising/X.png"));

			boolean itemInList = false;
			int itemPlaceInList = 0;
double newPrice = 0;
			if (item.contains("checkout")) {
				for (int i = 0; i < myShoppingList.getShoppingList().size(); i++) {
					
					if (check[i] == false & checkIfNew[i] == false & alreadyCalled[i] == false) {
						newPrice = myShoppingList
								.getCurrentOverallPrice()
								- myShoppingList.getShoppingList().get(i)
										.getPrice();
						newPrice = Math.round(newPrice * 1000) / 1000.0;
						myShoppingList.setCurrentOverallPrice(newPrice);
						myShoppingListPanel.totalPrice.setText(String
								.valueOf(myShoppingList
										.getCurrentOverallPrice()));
						
						
					}
				
					}
				/*
				 * try{ server.shutdown(); }catch (Exception ex) {
				 * System.err.println("XMLRPCServer: " + ex); }
				 */
			}

			for (int i = 0; i < myShoppingList.getShoppingList().size(); i++) {

				// hook to drawn list

				if (item.contains(myShoppingList.getShoppingList().get(i)
						.getItemName())) {

					itemInList = true;
					itemPlaceInList = i;
				}
			}

			double overAllPrice = 0;

			JLabel newItemLabelName = new JLabel();
			JLabel newItemLabelPrice = new JLabel();
			List<ItemWithPrice> pricelist = new ArrayList<ItemWithPrice>();
			int shoppingListlength = myShoppingList.getShoppingList().size();

			if (itemInList == false && !(item.contains("checkout"))) {

				// split into item and price
				String newItem, price;
				String[] splitLine;

				if (item.contains(" ")) {
					splitLine = item.split(" ");
					newItem = splitLine[0];
					price = splitLine[1];
					Double price2;
					price2 = Double.parseDouble(price);

					newItemLabelName.setText(newItem);
					newItemLabelName.setFont(new Font("Arial", 1, 16));
					newItemLabelPrice.setText(price);
					newItemLabelPrice.setFont(new Font("Arial", 1, 16));
					pricelist = myShoppingList.getShoppingList();
					pricelist.add(new ItemWithPrice(price2, newItem));
					myShoppingList.setShoppingList(pricelist);

				} else {
					throw new IllegalArgumentException(
							item
									+ " contains no space to seperate - seperation failed");
				}
			}

			if (itemInList == true) {

				switch (itemPlaceInList) {
				case 0:

					if (check[itemPlaceInList] == false) {
						itemChecked.setBounds(350, 170, 40, 40);
						myShoppingListPanel.add(itemChecked);
						myShoppingListPanel.setComponentZOrder(itemChecked, 2);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								0);
						check[itemPlaceInList] = true;
						if (checkIfNew[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == false) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice();
						}
						alreadyCalled[itemPlaceInList] = true;
					} else if (check[itemPlaceInList] == true) {
						check[itemPlaceInList] = false;
						itemOutOfBasket.setBounds(350, 170, 40, 40);
						myShoppingListPanel.add(itemOutOfBasket);
						myShoppingListPanel.setComponentZOrder(itemChecked, 0);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								2);
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								- myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
					}

					break;
				case 1:

					if (check[itemPlaceInList] == false) {
						itemChecked.setBounds(350, 210, 40, 40);
						myShoppingListPanel.add(itemChecked);
						myShoppingListPanel.setComponentZOrder(itemChecked, 2);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								0);
						check[itemPlaceInList] = true;
						if (checkIfNew[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == false) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice();
						}
						alreadyCalled[itemPlaceInList] = true;
					} else if (check[itemPlaceInList] == true) {
						check[itemPlaceInList] = false;
						itemOutOfBasket.setBounds(350, 210, 40, 40);
						myShoppingListPanel.add(itemOutOfBasket);
						myShoppingListPanel.setComponentZOrder(itemChecked, 0);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								2);
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								- myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();

					}
					break;
				case 2:
					if (check[itemPlaceInList] == false) {
						itemChecked.setBounds(350, 250, 40, 40);
						myShoppingListPanel.add(itemChecked);
						myShoppingListPanel.setComponentZOrder(itemChecked, 2);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								0);
						check[itemPlaceInList] = true;
						if (checkIfNew[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == false) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice();
						}
						alreadyCalled[itemPlaceInList] = true;
					} else if (check[itemPlaceInList] == true) {
						check[itemPlaceInList] = false;

						itemOutOfBasket.setBounds(350, 250, 40, 40);
						myShoppingListPanel.add(itemOutOfBasket);
						myShoppingListPanel.setComponentZOrder(itemChecked, 0);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								2);
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								- myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
					}
					break;
				case 3:
					if (check[itemPlaceInList] == false) {
						itemChecked.setBounds(350, 290, 40, 40);
						myShoppingListPanel.add(itemChecked);
						myShoppingListPanel.setComponentZOrder(itemChecked, 2);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								0);
						check[itemPlaceInList] = true;
						if (checkIfNew[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == false) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice();
						}
						alreadyCalled[itemPlaceInList] = true;
					} else if (check[itemPlaceInList] == true) {
						check[itemPlaceInList] = false;
						itemOutOfBasket.setBounds(350, 290, 40, 40);
						myShoppingListPanel.add(itemOutOfBasket);
						myShoppingListPanel.setComponentZOrder(itemChecked, 0);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								2);
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								- myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
					}
					break;
				case 4:
					if (check[itemPlaceInList] == false) {
						itemChecked.setBounds(350, 330, 40, 40);
						myShoppingListPanel.add(itemChecked);
						myShoppingListPanel.setComponentZOrder(itemChecked, 2);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								0);
						check[itemPlaceInList] = true;
						if (checkIfNew[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == false) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice();
						}
						alreadyCalled[itemPlaceInList] = true;

					} else if (check[itemPlaceInList] == true) {
						check[itemPlaceInList] = false;

						itemOutOfBasket.setBounds(350, 330, 40, 40);
						myShoppingListPanel.add(itemOutOfBasket);
						myShoppingListPanel.setComponentZOrder(itemChecked, 0);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								2);
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								- myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
					}
					break;
				case 5:
					if (check[itemPlaceInList] == false) {
						itemChecked.setBounds(350, 370, 40, 40);
						myShoppingListPanel.add(itemChecked);
						myShoppingListPanel.setComponentZOrder(itemChecked, 2);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								0);

						check[itemPlaceInList] = true;
						if (checkIfNew[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == false) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice();
						}
						alreadyCalled[itemPlaceInList] = true;
					} else if (check[itemPlaceInList] == true) {
						check[itemPlaceInList] = false;

						itemOutOfBasket.setBounds(350, 370, 40, 40);
						myShoppingListPanel.add(itemOutOfBasket);
						myShoppingListPanel.setComponentZOrder(itemChecked, 0);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								2);
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								- myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
					}
					break;
				case 6:

					if (check[itemPlaceInList] == false) {
						itemChecked.setBounds(350, 410, 40, 40);
						myShoppingListPanel.add(itemChecked);
						check[itemPlaceInList] = true;
						myShoppingListPanel.setComponentZOrder(itemChecked, 2);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								0);
						if (checkIfNew[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == true) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice()
									+ myShoppingList.getShoppingList()
											.get(itemPlaceInList).getPrice();
						} else if (checkIfNew[itemPlaceInList] == false
								&& alreadyCalled[itemPlaceInList] == false) {
							overAllPrice = myShoppingList
									.getCurrentOverallPrice();
						}
						alreadyCalled[itemPlaceInList] = true;
					} else if (check[itemPlaceInList] == true) {
						check[itemPlaceInList] = false;
						itemOutOfBasket.setBounds(350, 410, 40, 40);
						myShoppingListPanel.add(itemOutOfBasket);
						myShoppingListPanel.setComponentZOrder(itemChecked, 0);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								2);
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								- myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
					}
					break;
				}

				overAllPrice = Math.round(overAllPrice * 1000) / 1000.0; // round
				// to
				// 2
				// decimal
				// places

myShoppingListPanel.totalPrice
.setText(String.valueOf(overAllPrice));
myShoppingList.setCurrentOverallPrice(overAllPrice);
			}

			if (itemInList == false && !(item.contains("checkout"))) {

				for (int i = 0; i < myShoppingList.getShoppingList().size(); i++) {

					// hook to drawn list

					if (item.contains(myShoppingList.getShoppingList().get(i)
							.getItemName())) {

						itemInList = true;
						itemPlaceInList = i;
					}
				}
				// draw new item

				switch (shoppingListlength) {
				case 0:
					if (check[itemPlaceInList] == false) {
						newItemLabelName.setBounds(38, 187, 150, 23);
						myShoppingListPanel.add(newItemLabelName);

						newItemLabelPrice.setBounds(300, 187, 150, 23);
						myShoppingListPanel.add(newItemLabelPrice);

						itemChecked.setBounds(350, 170, 40, 40);
						myShoppingListPanel.add(itemChecked);
						myShoppingListPanel.setComponentZOrder(itemChecked, 2);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								0);
						check[itemPlaceInList] = true;

						overAllPrice = myShoppingList.getCurrentOverallPrice()
								+ myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();

						checkIfNew[itemPlaceInList] = true;
					}
					break;
				case 1:
					if (check[itemPlaceInList] == false) {
						newItemLabelName.setBounds(38, 227, 150, 23);
						myShoppingListPanel.add(newItemLabelName);

						newItemLabelPrice.setBounds(300, 227, 150, 23);
						myShoppingListPanel.add(newItemLabelPrice);

						itemChecked.setBounds(350, 210, 40, 40);
						myShoppingListPanel.add(itemChecked);
						check[itemPlaceInList] = true;
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								+ myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
						checkIfNew[itemPlaceInList] = true;
					}
					break;
				case 2:
					if (check[itemPlaceInList] == false) {
						newItemLabelName.setBounds(38, 267, 150, 23);
						myShoppingListPanel.add(newItemLabelName);

						newItemLabelPrice.setBounds(300, 267, 150, 23);
						myShoppingListPanel.add(newItemLabelPrice);

						itemChecked.setBounds(350, 250, 40, 40);
						myShoppingListPanel.add(itemChecked);
						check[itemPlaceInList] = true;
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								+ myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
						checkIfNew[itemPlaceInList] = true;
					}
					break;
				case 3:
					if (check[itemPlaceInList] == false) {
						newItemLabelName.setBounds(38, 307, 150, 23);
						myShoppingListPanel.add(newItemLabelName);

						newItemLabelPrice.setBounds(300, 307, 150, 23);
						myShoppingListPanel.add(newItemLabelPrice);

						itemChecked.setBounds(350, 290, 40, 40);
						myShoppingListPanel.add(itemChecked);
						check[itemPlaceInList] = true;

						overAllPrice = myShoppingList.getCurrentOverallPrice()
								+ myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();

						checkIfNew[itemPlaceInList] = true;
					}
					break;
				case 4:
					if (check[itemPlaceInList] == false) {
						newItemLabelName.setBounds(38, 347, 150, 23);
						myShoppingListPanel.add(newItemLabelName);

						newItemLabelPrice.setBounds(300, 347, 150, 23);
						myShoppingListPanel.add(newItemLabelPrice);

						itemChecked.setBounds(350, 330, 40, 40);
						myShoppingListPanel.add(itemChecked);
						myShoppingListPanel.setComponentZOrder(itemChecked, 2);
						myShoppingListPanel.setComponentZOrder(itemOutOfBasket,
								0);
						check[itemPlaceInList] = true;
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								+ myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
						checkIfNew[itemPlaceInList] = true;
					}
					break;
				case 5:
					if (check[itemPlaceInList] == false) {
						newItemLabelName.setBounds(38, 387, 150, 23);
						myShoppingListPanel.add(newItemLabelName);

						newItemLabelPrice.setBounds(300, 387, 150, 23);
						myShoppingListPanel.add(newItemLabelPrice);

						itemChecked.setBounds(350, 370, 40, 40);
						myShoppingListPanel.add(itemChecked);
						check[itemPlaceInList] = true;
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								+ myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
						checkIfNew[itemPlaceInList] = true;
					}
					break;
				case 6:
					if (check[itemPlaceInList] == false) {
						newItemLabelName.setBounds(38, 427, 150, 23);
						myShoppingListPanel.add(newItemLabelName);

						newItemLabelPrice.setBounds(300, 427, 150, 23);
						myShoppingListPanel.add(newItemLabelPrice);

						itemChecked.setBounds(350, 410, 40, 40);
						myShoppingListPanel.add(itemChecked);
						check[itemPlaceInList] = true;
						overAllPrice = myShoppingList.getCurrentOverallPrice()
								+ myShoppingList.getShoppingList()
										.get(itemPlaceInList).getPrice();
						checkIfNew[itemPlaceInList] = true;
					}
					break;
				}

				overAllPrice = Math.round(overAllPrice * 1000) / 1000.0; // round
				// to
				// 2
				// decimal
				// places

myShoppingListPanel.totalPrice
.setText(String.valueOf(overAllPrice));
myShoppingList.setCurrentOverallPrice(overAllPrice);
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

		XMLRPCServer server;

		public DatagramClient(XMLRPCServer server) {
			this.server = server;
		}

		/**
		 * searches for nfc reader and sends information about port of webserver
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
			buf = BigInteger.valueOf(getPort()).toByteArray();
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
