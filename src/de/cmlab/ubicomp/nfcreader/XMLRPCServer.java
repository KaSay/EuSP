package de.cmlab.ubicomp.nfcreader;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.xmlrpc.WebServer;

import de.cmlab.ubicomp.frontend.ShoppingListPanel;

/**
 * class starts the server and waits for results of the nfc app
 * 
 * @version 1.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
 *
 */

public class XMLRPCServer {

	public ShoppingListPanel myShoppingListPanel;

	/**
	 * starts the server and waits for responses of the nfc reader gets the
	 * shoppingListPanel for ticking of items read
	 * 
	 * @param shoppingListPanel
	 */
	public XMLRPCServer(ShoppingListPanel shoppingListPanel) {

		myShoppingListPanel = shoppingListPanel;
		try {
			// start the server and waits for answers of the nfc reader
			System.out.println("Initializing new XML-RPC Server...");
			WebServer server = new WebServer(4455);
			server.addHandler("ItemCatcher", server);
			System.out.println("Starting the server...");
			server.start();
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
	 * @return
	 */
	public String newItem(String item) {
		boolean success = false;

		// Delivered package needs to be registered by program.
		// Do something and don't forget to overwrite the success variable. :)

		if (success == true) {

			// sets the hook next to the item that was read
			JLabel itemChecked = new JLabel(new ImageIcon(
					"advertising/Haken.png"));
			if (item.contains("Gummibärchen")) {
				itemChecked.setBounds(350, 170, 40, 40);
				myShoppingListPanel.add(itemChecked);
				System.out.println("Gummibärchen");
			} else if (item.contains("Kuchen")) {
				itemChecked.setBounds(350, 210, 40, 40);
				myShoppingListPanel.add(itemChecked);
				System.out.println("Kuchen");
			} else if (item.contains("Kekse")) {
				myShoppingListPanel.add(itemChecked);
				itemChecked.setBounds(350, 250, 40, 40);
				System.out.println("Kekse");
			} else if (item.contains("XXXX")) {
				itemChecked.setBounds(350, 290, 40, 40);
				myShoppingListPanel.add(itemChecked);
				System.out.println("XXXX");
			}

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
		 * @throws IOException raises excaption if error occurs during sending of the pakes
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
