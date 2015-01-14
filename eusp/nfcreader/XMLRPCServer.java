package nfcreader;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.xmlrpc.WebServer;

import frontend.ShoppingListPanel;

public class XMLRPCServer {
	
	public ShoppingListPanel myShoppingListPanel;
	
	public XMLRPCServer(ShoppingListPanel shoppingListPanel) {
		// TODO Auto-generated method stub
		myShoppingListPanel = shoppingListPanel;
		try {
			System.out.println("Initializing new XML-RPC Server...");
			WebServer server = new WebServer(4455);
			server.addHandler("ItemCatcher", server);
			System.out.println("Starting the server...");
			server.start();
			System.out.println("Starting successfully.");
			System.out.println("Start to search for NFCReader.");
			DatagramClient client = new DatagramClient();
			client.searchForNFCReader();

		} catch(Exception ex) {
			System.err.println("XMLRPCServer: " + ex);
		}
	}

		public String newItem(String item) {
			boolean success = false;
			
			//Delivered package needs to be registered by program. 
			//Do something and don't forget to overwrite the success variable. :)
		
			if(success == true) {
				
				
				JLabel itemChecked = new JLabel(new ImageIcon("advertising/Haken.png"));
				if (item.contains("Brot")) {
					itemChecked.setBounds(350, 170, 40, 40);
					myShoppingListPanel.add(itemChecked);
				} else if (item.contains("Salami")){
					itemChecked.setBounds(350, 210, 40, 40);
					myShoppingListPanel.add(itemChecked);
				}else if (item.contains("Schinken")){
					myShoppingListPanel.add(itemChecked);
				}else if (item.contains("Gurke")){
					itemChecked.setBounds(350, 290, 40, 40);
					myShoppingListPanel.add(itemChecked);
				}
				
				return "OK";
			} else {			
				return "Error";
			}
		}

		private class DatagramClient {
			
			public DatagramClient() {
				
			}
			
			public void searchForNFCReader() throws IOException {
		        // get a datagram socket
		        DatagramSocket socket = new DatagramSocket();
		        // send IP, Port information
		        byte[] buf = new byte[256];
		        InetAddress address = InetAddress.getByName("192.168.2.255");
		        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4455);
		        try {
		        	socket.send(packet);
		        } catch(Exception ex) {
		        	System.err.println("XMLRPCServer: " + ex);
		        }
		        socket.close();
		    }

		}
}
