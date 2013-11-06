package me.dreamteam.tardis;

import java.awt.Canvas;
import java.awt.Dimension;
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
	private String version = "0.1";
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
		
		// finally make the window visible 
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		container.setLocation (-1,-1);

		
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
