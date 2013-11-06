package me.dreamteam.tardis;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lwjgl.Sys;


/**
Main Class
 */

public class Game extends Canvas {
	
	/**
	 * Begin the game parameters that will allow us to define certain elements.
	 */
	
	private BufferStrategy strategy;
	// This provides hardware acceleration
	
	private boolean isRunning = true;
	// Is the game running or not?
	
	private String gameName = "Codename TARDIS ";
	private String build = "Alpha ";
	private String version = "0.1.3";
	// Version set up so that we can see where we are at
	
	int fps;
	long lastFPS;
	// To grab the FPS, for debugging purposes
	
	
	
	
	public Game() {
		// create a frame to contain our game
		JFrame container = new JFrame(gameName + "- " + build + version + " | FPS: " + lastFPS);
		
		// get hold the content of the frame and set up the resolution of the game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(500,650));
		// Katie feel free to change this to the dimensions as given in the photoshop document
		panel.setLayout(null);
		
		// setup our canvas size and put it into the content of the frame
		setBounds(0,0,500,650);
		panel.add(this);
		
		// Tell AWT not to bother repainting our canvas since we're
		// going to do that our self in accelerated mode
		setIgnoreRepaint(true);
		
		//Don't move this variable as it will add extra padding if moved below pack
		container.setResizable(false);
		
		container.pack();
		
		// Make sure the window shows up smack bang in the centre
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int w = container.getSize().width;
		int h = container.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		container.setLocation(x, y);
		
		// Then set the container as visible
		container.setBackground(Color.black);
		container.setVisible(true);
		
		// create the buffering strategy for graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		
		// add a listener to respond to the user closing the window. If they
		// do we'd like to exit the game
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * Garbage collection and looping
	 */
	private void startGame() {
		
	}
	
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
		}
	
	/**
	 * GAME LOOP and Main below
	 */
	
	
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		
		while (isRunning) {
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			lastFPS = getTime();
			
			// Colour in background
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,800,650);
			
			// Draw
			g.dispose();
			strategy.show();
			
			
		}
	}
	
	
	
	/**
	 * Game Start
	 */
	public static void main(String argv[]) {
		Game g =new Game();

		// Start the main game loop
		g.gameLoop();
	}
	
}
