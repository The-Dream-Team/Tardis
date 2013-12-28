package me.dreamteam.tardis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Window.Type;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HowToPlay extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HowToPlay() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
			
		// Window setup	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	
		int w = getSize().width;	
		int h = getSize().height;	
		int x = (dim.width-w)/2;	
		int y = (dim.height-h)/2;
		setLocation(x, y);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setUndecorated(true);
		
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel htpTitle = new JLabel("Welcome to Super Spaceship Adventure");
		htpTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		htpTitle.setBounds(10, 93, 306, 23);
		htpTitle.setForeground(Color.WHITE);
		contentPane.add(htpTitle);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				Properties.wake = 1;
			}
		});
		
		JLabel htpHeading = new JLabel("How to Play");
		htpHeading.setForeground(Color.WHITE);
		htpHeading.setFont(new Font("Tahoma", Font.BOLD, 39));
		htpHeading.setBounds(10, 11, 430, 59);
		contentPane.add(htpHeading);
		
		JLabel htpLine1 = new JLabel("To begin, choose a spaceship.");
		htpLine1.setForeground(Color.WHITE);
		htpLine1.setBounds(10, 127, 306, 14);
		contentPane.add(htpLine1);
		
		JLabel htpLine2 = new JLabel("Your spaceship will guide you through the universe, an universe filled with enemies.");
		htpLine2.setForeground(Color.WHITE);
		htpLine2.setBounds(10, 146, 430, 14);
		contentPane.add(htpLine2);
		
		JLabel htpHide = new JLabel("Click anywhere to hide");
		htpHide.setForeground(Color.WHITE);
		htpHide.setFont(new Font("Monospaced", Font.BOLD, 16));
		htpHide.setBounds(220, 264, 220, 25);
		contentPane.add(htpHide);
		
	}
}
