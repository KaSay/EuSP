package frontend;

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

import shoppinglistcreation.AdvertisingReader;
import shoppinglistcreation.PriceCalculator;
import shoppinglistcreation.Profiler;
import shoppinglistcreation.Supermarket;

public class UserFrontend extends JFrame implements MouseListener   {
	
	public AdvertisingReader myAdvertReader;
	public Supermarket supermarket;
	public Profiler myProfile;
	JLabel iconSupermarket1;
	JLabel iconSupermarket2;
	JLabel iconSupermarket3;

	public UserFrontend(AdvertisingReader advertReader, Profiler profile) {
		myProfile = profile;
		myAdvertReader = advertReader;
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setContentPane(shoppingOverviewPanel());
			 	
		setResizable(false);
		setPreferredSize(new Dimension(500, 550));
		setLocation(0, 0);
		pack();
		setBackground(Color.white);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JComponent shoppingOverviewPanel(){
		
		JPanel shoppingOverview = new JPanel();
		shoppingOverview.setBounds(0, 0, 500, 550);
		shoppingOverview.setBackground(Color.white);
		shoppingOverview.setLayout(null);

		JLabel price1 = new JLabel(String.valueOf(myAdvertReader.supermarkets.get(0).getOverallPrice()));
		price1.setBounds(345, 75, 53, 21);
		price1.setFont(new Font("Arial", 1, 16));
		shoppingOverview.add(price1);
		
		iconSupermarket1 = new JLabel(new ImageIcon("advertising/" + myAdvertReader.supermarkets.get(0).getName() + ".png"));
		iconSupermarket1.setBounds(80,25,130,156);
		iconSupermarket1.addMouseListener(this);
		shoppingOverview.add(iconSupermarket1);
		
		
		JLabel price2 = new JLabel(String.valueOf(myAdvertReader.supermarkets.get(1).getOverallPrice()));
		price2.setBounds(345, 256, 53, 21);
		price2.setFont(new Font("Arial", 1, 16));
		shoppingOverview.add(price2);
		
		iconSupermarket2 = new JLabel(new ImageIcon("advertising/" + myAdvertReader.supermarkets.get(1).getName() + ".png"));
		iconSupermarket2.setBounds(70,200,150,150);
		iconSupermarket2.addMouseListener(this);
		shoppingOverview.add(iconSupermarket2);
		
		JLabel price3 = new JLabel(String.valueOf(myAdvertReader.supermarkets.get(2).getOverallPrice()));
		price3.setBounds(345, 430, 53, 21);
		price3.setFont(new Font("Arial", 1, 16));
		shoppingOverview.add(price3);
		
		iconSupermarket3 = new JLabel(new ImageIcon("advertising/" + myAdvertReader.supermarkets.get(2).getName() + ".png"));
		iconSupermarket3.setBounds(8,384,300,118);
		iconSupermarket3.addMouseListener(this);
		shoppingOverview.add(iconSupermarket3);
		
		shoppingOverview.setVisible(true);
		return shoppingOverview;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		getContentPane().setVisible(false);
		
		if(e.getSource().equals(iconSupermarket1)){
			supermarket = myAdvertReader.supermarkets.get(0);
		} else if (e.getSource().equals(iconSupermarket2)){
			supermarket = myAdvertReader.supermarkets.get(1);
		} else if (e.getSource().equals(iconSupermarket3)){
			supermarket = myAdvertReader.supermarkets.get(2);
		}
		
		JComponent newContentPane = new ShoppingListPanel(supermarket, myAdvertReader, myProfile);
        newContentPane.setOpaque(true); //content panes must be opaque
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
