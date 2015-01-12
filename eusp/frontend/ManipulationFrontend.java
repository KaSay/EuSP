package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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

import shoppinglistcreation.AdvertisingReader;
import shoppinglistcreation.PriceCalculator;
import shoppinglistcreation.Profiler;
import shoppinglistcreation.Supermarket;

public class ManipulationFrontend extends JPanel implements ItemListener, ActionListener{

	JCheckBox sweetProfile;
	JCheckBox healthyProfile;
	JCheckBox everydayLifeProfile;
	JCheckBox partyProfile;
	JButton exit;
	JFrame frame = new JFrame();
	AdvertisingReader add;
	
	public Profiler profile = new Profiler();
	
	public ManipulationFrontend(AdvertisingReader add) {
		
		super(new BorderLayout());
		this.add = add;
		sweetProfile = new JCheckBox();
		sweetProfile.setSelected(false);
		sweetProfile.setBounds(30,37,60,30);
		sweetProfile.setLabel("sweet");
		
	 	
		healthyProfile = new JCheckBox();
		healthyProfile.setSelected(false);
		healthyProfile.setBounds(30,60,80,30);
		healthyProfile.setLabel("healthy");
		
		everydayLifeProfile = new JCheckBox();
		everydayLifeProfile.setSelected(false);
		everydayLifeProfile.setBounds(30,90,120,30);
		everydayLifeProfile.setLabel("everyday life");
		
		
		partyProfile = new JCheckBox();
		partyProfile.setSelected(false);
		partyProfile.setBounds(30,120,60,30);
		partyProfile.setLabel("party");
		
		sweetProfile.addItemListener(this);
		healthyProfile.addItemListener(this);
		everydayLifeProfile.addItemListener(this);
		partyProfile.addItemListener(this);
		
		exit = new JButton();
		exit.setBounds(30,120,40,30);
		exit.setLabel("Bestštigen");
		exit.addActionListener(this);
				
		JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.add(partyProfile);
        checkPanel.add(healthyProfile);
        checkPanel.add(everydayLifeProfile);
        checkPanel.add(sweetProfile);
        checkPanel.add(exit);

        add(checkPanel, BorderLayout.LINE_START);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	

	public void createAndShowGUI(){

		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JLabel supermarket1 = new JLabel("Profiles");
		supermarket1.setBounds(10, 10, 80, 30);
		frame.add(supermarket1);
		
		JComponent newContentPane = new ManipulationFrontend(add);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
		
		
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(300, 600));
		frame.setLocation(50, 50);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		if (source == healthyProfile){
			profile.ProfileHealthy();
		} else if (source == sweetProfile){
			profile.ProfileSweet();
		} else if (source == everydayLifeProfile){
			profile.ProfileEverydayLife();
		} else if (source == partyProfile ){
			profile.ProfileParty();
		}
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {	
		frame.setVisible(false);
		PriceCalculator priceCalc = new PriceCalculator(add,profile);
		
	}



}
