package me.dreamteam.tardis;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import me.dreamteam.tardis.Properties;

public class HowToPlay extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HowToPlay() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
			
		// Window setup	
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		setUndecorated(true);
		
		
		contentPane = new JPanel();
		
		// Set how to play to show in middle of container
		int htpX = Properties.sX + 30;
		int htpY = Properties.sY + 170;
		setLocation(htpX, htpY);
		
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				Properties.wake = 1;
			}
		});
		
		JLabel htpHeading = new JLabel("How to Play...");
		htpHeading.setForeground(Color.WHITE);
		htpHeading.setFont(new Font("Tahoma", Font.BOLD, 39));
		htpHeading.setBounds(10, 5, 430, 59);
		contentPane.add(htpHeading);
		
		JLabel htpTitle = new JLabel("Welcome to Super Spaceship Adventure");
		htpTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		htpTitle.setBounds(10, 65, 306, 23);
		htpTitle.setForeground(Color.WHITE);
		contentPane.add(htpTitle);
		
		JLabel htpLine1 = new JLabel("To begin, choose a spaceship.");
		htpLine1.setForeground(Color.WHITE);
		htpLine1.setBounds(10, 100, 306, 14);
		contentPane.add(htpLine1);
		
		JLabel htpLine2 = new JLabel("Your spaceship will guide you through the universe, an universe filled with enemies.");
		htpLine2.setForeground(Color.WHITE);
		htpLine2.setBounds(10, 115, 430, 14);
		contentPane.add(htpLine2);
		
		JLabel htpLine3 = new JLabel("To move your spaceship, use LEFT ARROW KEY or RIGHT ARROW KEY. ");
		htpLine3.setForeground(Color.WHITE);
		htpLine3.setBounds(10, 145, 430, 14);
		contentPane.add(htpLine3);
		
		JLabel htpLine4 = new JLabel("Alternatively, you can use the A KEY to move left, or the D KEY to move right");
		htpLine4.setForeground(Color.WHITE);
		htpLine4.setBounds(10, 160, 430, 14);
		contentPane.add(htpLine4);
		
		JLabel htpLine5 = new JLabel("Sometimes you may get a powerup, use SPACE KEY to use the powerup.");
		htpLine5.setForeground(Color.WHITE);
		htpLine5.setBounds(10, 185, 430, 14);
		contentPane.add(htpLine5);
		
		JLabel htpLine6 = new JLabel("To switch between powerups, use NUM 1, NUM 2 or NUM 3");
		htpLine6.setForeground(Color.WHITE);
		htpLine6.setBounds(10, 200, 430, 14);
		contentPane.add(htpLine6);
		
		JLabel htpLine7 = new JLabel("Powerups are limited, so use them wisely!");
		htpLine7.setForeground(Color.WHITE);
		htpLine7.setBounds(10, 215, 430, 14);
		contentPane.add(htpLine7);
		
		JLabel htpLine8 = new JLabel("To pause the game at any time, use P KEY or ESC KEY");
		htpLine8.setForeground(Color.WHITE);
		htpLine8.setBounds(10, 245, 430, 14);
		contentPane.add(htpLine8);
		
		JLabel htpHide = new JLabel("Click here to hide...");
		htpHide.setForeground(Color.WHITE);
		htpHide.setFont(new Font("Monospaced", Font.BOLD, 16));
		htpHide.setBounds(220, 364, 220, 25);
		contentPane.add(htpHide);
		
		JLabel htpHaveFun = new JLabel("Have fun!");
		htpHaveFun.setFont(new Font("Tahoma", Font.BOLD, 20));
		htpHaveFun.setBounds(10, 280, 306, 23);
		htpHaveFun.setForeground(Color.WHITE);
		contentPane.add(htpHaveFun);
		
		
	}
}
