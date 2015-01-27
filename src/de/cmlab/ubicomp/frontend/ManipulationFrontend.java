package de.cmlab.ubicomp.frontend;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
 */
/**
 * ManipulationFrontend displays possible profiles - user can choose one
 */
public class ManipulationFrontend extends JPanel implements ActionListener {

	JButton exit; //clicked by the user after decision
	JFrame frame = new JFrame(); //the window
	AdvertisingReader add;
	List profileList = new List(); //list of the profiles

	public Profiler profile = new Profiler();

	/**
	 * ManipulationFrontend displays possible profiles and lets the user choose one
	 * @param add
	 */
	public ManipulationFrontend(AdvertisingReader add) {

		this.add = add;

		// creation of the list
		profileList.add("Profil: süß");
		profileList.add("Profil: gesund");
		profileList.add("Profil: Party");
		profileList.add("Profil: alltäglich");
		
		//adding item listener to the list to check which profile was selected by the user
		profileList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
	
				if (profileList.getSelectedItem().equals("Profil: gesund")) {
					profile.ProfileHealthy();
				} else if (profileList.getSelectedItem().equals("Profil: süß")) {
					profile.ProfileSweet();
				} else if (profileList.getSelectedItem().equals("Profil: alltäglich")) {
					profile.ProfileEverydayLife();
				} else if (profileList.getSelectedItem().equals("Profil: Party")) {
					profile.ProfileParty();
				}
			}
		});
		
		//setting some general properties to the list (location, size, font, color)
		profileList.setBounds(0, 0, 150, 100);
		profileList.setFont(new Font("Arial",1,16));
		profileList.setBackground(Color.lightGray);
		add(profileList);

		//the button for confirming choice - general properties (size, location, font, color)
		exit = new JButton();
		exit.setBounds(30, 120, 40, 30);
		exit.setLabel("Bestätigen");
		exit.setFont(new Font("Arial", 1, 16));
		exit.setBackground(Color.lightGray);
		exit.addActionListener(this); //add action listener to notice if button was clicked
		add(exit);

		setBackground(Color.white);
	}

	/**
	 * creates main frame of the GUI and adds the panel of the constructor to the frame
	 */
	public void createAndShowGUI() {

		//sets some general properties to the window
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//adds the constructor panel onto the window
		JComponent newContentPane = new ManipulationFrontend(add);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		//sets some mor general properties to the window
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(300, 130));
		frame.setLocation(50, 50);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	/**
	 * method is called if button is clicked 
	 * method calls PriceCalculator if button is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if no profile was chosen - GUI is shown again
		if (profile.getStatus().equals("")){
			System.out.println("no profile selected");
			createAndShowGUI();
		}
		// calls PriceCalculator with selected profile and information about the supermarkets
		PriceCalculator priceCalc = new PriceCalculator(add, profile);
		frame.dispose();
		frame.setVisible(false);

	}

}
