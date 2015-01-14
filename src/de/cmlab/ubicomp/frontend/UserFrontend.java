package de.cmlab.ubicomp.frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.cmlab.ubicomp.shoppinglistcreation.AdvertisingReader;
import de.cmlab.ubicomp.shoppinglistcreation.PriceCalculator;
import de.cmlab.ubicomp.shoppinglistcreation.Profiler;
import de.cmlab.ubicomp.shoppinglistcreation.Supermarket;

/**
 * @version 1.0
 * @author Julia Gratzl, Peter Wunderlich, Katharina Sandrock
 * 
 *  
 */

/**
 * UserFrontend / presents the prices of the different supermarket (one can be
 *         chosen by clicking on it)
 */

public class UserFrontend extends JFrame implements MouseListener {

	public AdvertisingReader myAdvertReader;
	public Supermarket supermarket;
	public Profiler myProfile;

	// supermarket logos
	JLabel iconSupermarket1;
	JLabel iconSupermarket2;
	JLabel iconSupermarket3;

	/**
	 * constructor sets some configurations of the frame
	 * 
	 * @param advertReader
	 * @param profile
	 */
	public UserFrontend(AdvertisingReader advertReader, Profiler profile) {

		myProfile = profile; // holding the profile
		myAdvertReader = advertReader; // holding the supermarket

		setLayout(null); // layout is set to null - coordinates are self-defined
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // sets default close
															// operation on
															// default

		// sets first panel on the frame showing the overview over the
		// supermarkets and their total prices
		setContentPane(shoppingOverviewPanel());

		setResizable(false); // window not resizable
		setPreferredSize(new Dimension(500, 550)); // sets size of the window
		setLocation(0, 0); // sets location of the window
		pack();
		setBackground(Color.white);
		setLocationRelativeTo(null);
		setVisible(true); // makes the frame visible
	}

	/**
	 * method responsible for displaying the first content panel showing an
	 * overview over the supermarket and their prices
	 * 
	 * @return JComponent - returns the panel so that it can be set on the frame
	 *         in the constructor
	 */
	public JComponent shoppingOverviewPanel() {

		// creating the panel und set som general configurations
		JPanel shoppingOverview = new JPanel();
		shoppingOverview.setBounds(0, 0, 500, 550);
		shoppingOverview.setBackground(Color.white);
		shoppingOverview.setLayout(null);

		// the panel should show three supermarkets and their prices - now the
		// six label are defined and specified
		JLabel price1 = new JLabel(String.valueOf(myAdvertReader.supermarkets
				.get(0).getOverallPrice()));
		price1.setBounds(345, 75, 53, 21);
		price1.setFont(new Font("Arial", 1, 16));
		shoppingOverview.add(price1);

		iconSupermarket1 = new JLabel(new ImageIcon("advertising/"
				+ myAdvertReader.supermarkets.get(0).getName() + ".png"));
		iconSupermarket1.setBounds(80, 25, 130, 156);
		iconSupermarket1.addMouseListener(this); // supermarket images get an
													// Mouse listener to react
													// on clients click on the
													// picture
		shoppingOverview.add(iconSupermarket1);

		JLabel price2 = new JLabel(String.valueOf(myAdvertReader.supermarkets
				.get(1).getOverallPrice()));
		price2.setBounds(345, 256, 53, 21);
		price2.setFont(new Font("Arial", 1, 16));
		shoppingOverview.add(price2);

		iconSupermarket2 = new JLabel(new ImageIcon("advertising/"
				+ myAdvertReader.supermarkets.get(1).getName() + ".png"));
		iconSupermarket2.setBounds(70, 200, 150, 150);
		iconSupermarket2.addMouseListener(this);
		shoppingOverview.add(iconSupermarket2);

		JLabel price3 = new JLabel(String.valueOf(myAdvertReader.supermarkets
				.get(2).getOverallPrice()));
		price3.setBounds(345, 430, 53, 21);
		price3.setFont(new Font("Arial", 1, 16));
		shoppingOverview.add(price3);

		iconSupermarket3 = new JLabel(new ImageIcon("advertising/"
				+ myAdvertReader.supermarkets.get(2).getName() + ".png"));
		iconSupermarket3.setBounds(8, 384, 300, 118);
		iconSupermarket3.addMouseListener(this);
		shoppingOverview.add(iconSupermarket3);

		shoppingOverview.setVisible(true); // makes the panel visible
		return shoppingOverview; // returns the panel to the frame
	}

	// override method for the mouse listener - reacts on clicks onto
	// supermarket images
	@Override
	public void mouseClicked(MouseEvent e) {
		getContentPane().setVisible(false);

		// sets the supermarket object depending on which supermarket the user
		// clicked
		if (e.getSource().equals(iconSupermarket1)) {
			supermarket = myAdvertReader.supermarkets.get(0);
		} else if (e.getSource().equals(iconSupermarket2)) {
			supermarket = myAdvertReader.supermarkets.get(1);
		} else if (e.getSource().equals(iconSupermarket3)) {
			supermarket = myAdvertReader.supermarkets.get(2);
		}

		// if a picture is clicked the shoppinglist panel is shown
		JComponent newContentPane = new ShoppingListPanel(supermarket,
				myAdvertReader, myProfile);
		newContentPane.setOpaque(true); // content panes must be opaque
		setContentPane(newContentPane);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
