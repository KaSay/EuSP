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

public class ManipulationFrontend extends JPanel implements ActionListener {

	JCheckBox sweetProfile;
	JCheckBox healthyProfile;
	JCheckBox everydayLifeProfile;
	JCheckBox partyProfile;
	JButton exit;
	JFrame frame = new JFrame();
	AdvertisingReader add;
	List profileList = new List();

	public Profiler profile = new Profiler();

	public ManipulationFrontend(AdvertisingReader add) {

		this.add = add;

		
		profileList.add("Profil: süß");
		profileList.add("Profil: gesund");
		profileList.add("Profil: Party");
		profileList.add("Profil: alltäglich");
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
		profileList.setBounds(0, 0, 150, 100);
		profileList.setFont(new Font("Arial",1,16));
		profileList.setBackground(Color.lightGray);
		add(profileList);

		exit = new JButton();
		exit.setBounds(30, 120, 40, 30);
		exit.setLabel("Bestätigen");
		exit.setFont(new Font("Arial", 1, 16));
		exit.setBackground(Color.lightGray);
		exit.addActionListener(this);
		add(exit);

		setBackground(Color.white);
	}

	public void createAndShowGUI() {

		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JComponent newContentPane = new ManipulationFrontend(add);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(300, 130));
		frame.setLocation(50, 50);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (profile.getStatus().equals("")){
			System.out.println("no profile selected");
			createAndShowGUI();
		}
		PriceCalculator priceCalc = new PriceCalculator(add, profile);
		frame.dispose();
		frame.setVisible(false);

	}

}
