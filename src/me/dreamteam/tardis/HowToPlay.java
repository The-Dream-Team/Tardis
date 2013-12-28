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

public class HowToPlay extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HowToPlay() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
			
		// Window setup	
		int htpdim = Toolkit.getDefaultToolkit().getScreenResolution();	
		int w = getSize().width;
		int h = getSize().height;	
		int x = (htpdim-w)*7+62;	
		int y = (htpdim-h)*4;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setUndecorated(true);
		
		
		contentPane = new JPanel();
		
		setLocation(x, y);
		
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel htpTitle = new JLabel("Welcome to Super Spaceship Adventure");
		htpTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		htpTitle.setBounds(10, 72, 306, 23);
		htpTitle.setForeground(Color.WHITE);
		contentPane.add(htpTitle);
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
		htpHeading.setBounds(10, 11, 430, 59);
		contentPane.add(htpHeading);
		
		JLabel htpLine1 = new JLabel("To begin, choose a spaceship.");
		htpLine1.setForeground(Color.WHITE);
		htpLine1.setBounds(10, 98, 306, 14);
		contentPane.add(htpLine1);
		
		JLabel htpLine2 = new JLabel("Your spaceship will guide you through the universe, an universe filled with enemies.");
		htpLine2.setForeground(Color.WHITE);
		htpLine2.setBounds(10, 118, 430, 14);
		contentPane.add(htpLine2);
		
		JLabel htpLine3 = new JLabel("To move your spaceship, use LEFT ARROW KEY or RIGHT ARROW KEY. ");
		htpLine3.setForeground(Color.WHITE);
		htpLine3.setBounds(10, 143, 430, 14);
		contentPane.add(htpLine3);
		
		JLabel htpLine4 = new JLabel("Alternatively, you can use the A key to move left, or the D key to move right");
		htpLine4.setForeground(Color.WHITE);
		htpLine4.setBounds(10, 161, 430, 14);
		contentPane.add(htpLine4);
		
		JLabel htpHide = new JLabel("Click here to hide...");
		htpHide.setForeground(Color.WHITE);
		htpHide.setFont(new Font("Monospaced", Font.BOLD, 16));
		htpHide.setBounds(220, 264, 220, 25);
		contentPane.add(htpHide);
		
		
	}
}
