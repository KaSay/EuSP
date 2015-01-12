package frontend;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import shoppinglistcreation.AdvertisingReader;
import shoppinglistcreation.PriceCalculator;
import shoppinglistcreation.Profiler;

public class UserFrontend extends JFrame {

	public UserFrontend() {
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JLabel supermarket1 = new JLabel("Hallo");
		supermarket1.setBounds(50, 50, 200, 300);
		add(supermarket1);
	
	 	AdvertisingReader reader = new AdvertisingReader();
	 	Profiler profile = new Profiler();
	 	//PriceCalculator priceCalculator = new PriceCalculator(reader);
	 	
		setResizable(false);
		setPreferredSize(new Dimension(750, 550));
		setLocation(50, 50);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		UserFrontend user = new UserFrontend();
	}

}
